#include <stdlib.h>
#include <stdio.h>
#include <assert.h>
#include "hardware.h"

#define CTXMAGIC 0xCAFEBEBE

typedef void (func_t) (void *);
enum ctx_state_e {CTX_INIT, CTX_EXEC, CTX_BLCK, CTX_END};

struct ctx_s{
  void *ctx_esp;
  void *ctx_ebp;
  int ctx_magic;
  unsigned char * ctx_base;
  func_t * ctx_f;
  void * ctx_arg;
  enum ctx_state_e ctx_state;
  struct ctx_s *ctx_next;
  struct ctx_s *ctx_sem_next;
};

struct sem_s{
    int sem_cpt;
    struct ctx_s *sem_ctx_list;
};

void sem_init(struct sem_s *sem, unsigned int val);
void sem_down(struct sem_s *sem);
void sem_up(struct sem_s *sem);

int init_ctx( struct ctx_s *ctx, int stack_size, func_t f, void * args );
void switch_to_ctx (struct ctx_s *ctx);
void start_current_ctx();
int create_ctx(int stack_size, func_t f, void *args);
void yield();
void start_schedule();
void irq_disable();
void irq_enable();