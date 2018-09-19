#include <stdio.h>

/*
Chaque fonction est associé à un registre tel que esp et ebp
Les adresses reste identique pour une même fonction

Main :
x= 0xff94e4f8, y= 0xff94e4e0
Function F :
x= 0xff94e4d8 y= 0xff94e4c0
Main :
x= 0xff94e4f8 y= 0xff94e4e0

*/
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
