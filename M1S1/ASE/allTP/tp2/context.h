#include <stdlib.h>
#include <stdio.h>
#include <assert.h>

#define CTXMAGIC 0xCAFEBEBE

typedef void (func_t) (void *);
enum ctx_state_e {CTX_INIT, CTX_EXEC, CTX_END};

struct ctx_s{
  void *ctx_esp;
  void *ctx_ebp;
  int ctx_magic;
  unsigned char * ctx_base;
  func_t * ctx_f;
  void * ctx_arg;
  enum ctx_state_e ctx_state;
};

int init_ctx( struct ctx_s *ctx, int stack_size, func_t f, void * args );
void switch_to_ctx (struct ctx_s *ctx);
void start_current_ctx();
