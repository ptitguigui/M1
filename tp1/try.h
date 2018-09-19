struct ctx_s{
  void *ctx_esp;
  void *ctx_ebp;
  unsigned int ctx_magic;
};

typedef int (funct_t)(int);
#define static unsigned int CTXMAGIC = 0xCAFEBEBE;
