
#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

typedef int (funct_t)(int);

#define static unsigned int CTXMAGIC = 0xCAFEBEBE;

struct ctx_s{
  void *ctx_esp;
  void *ctx_ebp;
  unsigned int ctx_magic;
};

int try(struct ctx_s *p_ctx, func_t *f, int arg);
int throw(struct ctx_s *p_ctx, int r);
