#include <stdio.h>

int f() {


  void *x;
  void *y;

  asm ("movl %%esp, %0" "\n\t" "movl %%ebp, %1 "
             : "=r"(y), "=r"(x));
  printf("Function F : \n");
  printf("x= %p y= %p\n",x, y);

}

void main(void)

{
    void *x;
    void *y;

    asm ("movl %%esp, %0" "\n\t" "movl %%ebp, %1 "
               : "=r"(y), "=r"(x));

  printf("Main : \n");
  printf("x= %p, y= %p\n",x, y);
   f();

   asm ("movl %%esp, %0" "\n\t" "movl %%ebp, %1 "
              : "=r"(y), "=r"(x));

   printf("Main : \n");
   printf("x= %p y= %p\n",x, y);

}
