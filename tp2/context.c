#include "context.h"

static struct ctx_s *current_ctx = NULL;
/*static void *main_esp;
static void *main_ebp;*/
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
void switch_to_ctx (struct ctx_s *ctx){

  assert(ctx->ctx_magic == CTXMAGIC);
  assert(ctx->ctx_state == CTX_INIT || ctx->ctx_state == CTX_EXEC);

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

  switch_to_ctx(&main_ctx);
}
