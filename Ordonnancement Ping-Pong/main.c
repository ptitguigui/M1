#include "context.h"

void f_ping(void *arg);
void f_pong(void *arg);
int i;

int main(int argc, char *argv[])
{
    create_ctx(16384, f_ping, NULL);
    create_ctx(16384, f_pong, NULL);
    start_schedule();
    printf("\nfinis\n");
    exit(EXIT_SUCCESS);
}

void f_ping(void *args)
{
    for(i=0; i<10; i++) {
        printf("A") ;
        for (i=0; i<1000000; i++);
        printf("B") ;
        for (i=0; i<1000000; i++);
        printf("C") ;
        for (i=0; i<1000000; i++);
    }
}

void f_pong(void *args)
{
    for(i=0; i<10; i++) {
        printf("1") ;
        for (i=0; i<1000000; i++);
        printf("2") ;
        for (i=0; i<1000000; i++);
    }
}
