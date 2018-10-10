#include "context.h"

static struct ctx_s *current_ctx = NULL;
static struct ctx_s *ring = NULL; 
static struct ctx_s main_ctx;
static int level = 1;

int init_ctx( struct ctx_s *ctx, int stack_size, func_t f, void * args ){

  ctx-> ctx_magic = CTXMAGIC;
  ctx -> ctx_state = CTX_INIT;
  ctx -> ctx_f = f;
  ctx -> ctx_arg = args;
  ctx -> ctx_base = malloc(stack_size);

  assert(ctx->ctx_base);

  ctx -> ctx_esp = ctx-> ctx_base + stack_size -4;
  ctx -> ctx_ebp = ctx-> ctx_base + stack_size -4;

  return 0;
}
int create_ctx (int stack_size, func_t f, void *args){
  struct ctx_s *new;
  new = malloc(sizeof(struct ctx_s));
  assert(new);
  init_ctx(new, stack_size, f, args);

  irq_disable();
  if (!ring){
    ring = new;
    new -> ctx_next = new;
  }else{
    new -> ctx_next = ring -> ctx_next;
    ring -> ctx_next = new; 
  }
  irq_enable();
  return 0;

  }

void switch_to_ctx (struct ctx_s *ctx){

  assert(ctx->ctx_magic == CTXMAGIC);
  int cpt = 0;
  irq_disable();
  while( ctx -> ctx_state == CTX_END || ctx -> ctx_state == CTX_BLCK){
    if (ctx -> ctx_state == CTX_END){
      if (ctx -> ctx_next == ctx){
        switch_to_ctx(&main_ctx);
      }
      free(ctx-> ctx_base);
      current_ctx -> ctx_next = ctx -> ctx_next;
      free(ctx);
      ctx = current_ctx -> ctx_next;
    }
    if (ctx -> ctx_state == CTX_BLCK){
      ctx = ctx -> ctx_next;
      cpt ++;
    }
    if (cpt > 1000000){
      printf("Tous les contextes sont bloques\n");
      switch_to_ctx(&main_ctx);
    }

    
  }
 
  if (current_ctx){
    asm ("movl %%esp, %0" "\n\t" "movl %%ebp, %1 "
               : "=r"(current_ctx->ctx_esp), "=r"(current_ctx->ctx_ebp));
  }else{
    asm ("movl %%esp, %0" "\n\t" "movl %%ebp, %1 "
             : "=r"(main_ctx.ctx_esp), "=r"(main_ctx.ctx_ebp));
    main_ctx.ctx_magic = CTXMAGIC;
    main_ctx.ctx_state = CTX_EXEC;
  }

  current_ctx = ctx;

  asm ("movl %0, %%esp" "\n\t" "movl %1, %%ebp"
             :
             : "r"(current_ctx->ctx_esp), "r"(current_ctx->ctx_ebp));
  
  irq_enable();           

  if (current_ctx->ctx_state == CTX_INIT){
    start_current_ctx();
  }
}

void start_current_ctx(){
  current_ctx -> ctx_state = CTX_EXEC;
  current_ctx -> ctx_f(current_ctx->ctx_arg);
  current_ctx -> ctx_state = CTX_END;
  yield();
}

void yield(){
  if (current_ctx){
    switch_to_ctx(current_ctx-> ctx_next);
  }else if(ring){
    switch_to_ctx(ring);
  }else{
    return; 
  }
}
static void empty_it(void)
{
    return;
}

static void timer_it() {
    _out(TIMER_ALARM,0xFFFFFFFE);
    yield();
}

void start_schedule(){
 unsigned int i;
    
    /* init hardware */
    if (init_hardware(INIFILENAME) == 0) {
	fprintf(stderr, "Error in hardware initialization\n");
	exit(EXIT_FAILURE);
    }
    
    /* dummy interrupt handlers */
    for (i=0; i<16; i++)
	IRQVECTOR[i] = empty_it;

    /* program timer */
    IRQVECTOR[TIMER_IRQ] = timer_it;    
    _out(TIMER_PARAM,128+64+32+8); /* reset + alarm on + 8 tick / alarm */
    _out(TIMER_ALARM,0xFFFFFFFE);  /* alarm at next tick (at 0xFFFFFFFF) */

   /* allows all IT */
    _mask(level);
    
    /* count for a while... */
    for (i=0; i<(1<<28); i++)
	  ;
}

void irq_disable(){
  _mask(level++);
}

void irq_enable(){
  _mask(level--);
}

void sem_init(struct sem_s *sem, unsigned int val){
    sem -> sem_cpt = val;
    sem -> sem_ctx_list = NULL;
}
void sem_down(struct sem_s *sem){
    irq_disable();
    sem-> sem_cpt --;
    if(sem->sem_cpt < 0){
        current_ctx -> ctx_state = CTX_BLCK;
        current_ctx -> ctx_sem_next = sem-> sem_ctx_list;
        sem -> sem_ctx_list = current_ctx;
        irq_enable();
        yield();
    }else{
      irq_enable();
    }
}
void sem_up(struct sem_s *sem){
  irq_disable();
  sem->sem_cpt ++;

  if(sem-> sem_cpt <= 0){
    sem->sem_ctx_list -> ctx_state = CTX_EXEC;
    sem -> sem_ctx_list = sem -> sem_ctx_list -> ctx_sem_next;
  }
  irq_enable();
}