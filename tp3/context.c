#include "context.h"

static struct ctx_s *current_ctx = NULL;
static struct ctx_s *ring = NULL; 
static struct ctx_s main_ctx;

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

  if (!ring){
    ring = new;
    new -> ctx_next = new;
  }else{
    new -> ctx_next = ring -> ctx_next;
    ring -> ctx_next = new; 
  }
  return 0;
}

void switch_to_ctx (struct ctx_s *ctx){

  assert(ctx->ctx_magic == CTXMAGIC);
  
  while( ctx -> ctx_state == CTX_END){
    if (ctx -> ctx_next == ctx){
      switch_to_ctx(&main_ctx);
    }
    free(ctx-> ctx_base);
    current_ctx -> ctx_next = ctx -> ctx_next;
    free(ctx);
    ctx = current_ctx -> ctx_next;
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

  if (current_ctx->ctx_state == CTX_INIT){
    start_current_ctx();
  }
}

void start_current_ctx(){
  current_ctx -> ctx_state = CTX_EXEC;
  current_ctx -> ctx_f(current_ctx->ctx_arg);
  current_ctx -> ctx_state = CTX_END;
  yield();
  //switch_to_ctx(&main_ctx);
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
