#include "try.h"

/*struct ctx_s ctx;
static int i = 1;
*/
static int copie_v;

int try(struct ctx_s *pctx, func_t *f, int arg){
  pctx-> ctx_magic = CTXMAGIC;
  asm ("movl %%esp, %0" "\n\t" "movl %%ebp, %1 "
             : "=r"(pctx->ctx_esp), "=r"(pctx->ctx_ebp));
 return f(arg);
}

int throw(struct ctx_s *pctx, int n){
  assert(pctx->ctx_magic == CTXMAGIC);
  copie_v = n;

  asm ("movl  %0, %%esp" "\n\t" "movl %1, %%ebp"
             :"=r"(pctx->ctx_esp), "=r"(pctx->ctx_ebp));
  return copie_v;
}

/*
void h(){
  throw(&ctx, i);
}

int g(int i){
  h();
  return i ;
}

void f(){
  try(&ctx, g, i);
}

void main(void){
    f();
}*/
