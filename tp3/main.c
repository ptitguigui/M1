#include "context.h"

void f_ping(void *arg);
void f_pong(void *arg);

int main(int argc, char *argv[])
{
    create_ctx(16384, f_ping, NULL);
    create_ctx(16384, f_pong, NULL);
    yield();
    printf("\nfinis\n");
    exit(EXIT_SUCCESS);
}

void f_ping(void *args)
{
    for(int i=0; i<10; i++) {
        printf("A") ;
        yield();
        printf("B") ;
        yield();
        printf("C") ;
        yield();
    }
}

void f_pong(void *args)
{
    for(int i=0; i<10; i++) {
        printf("1") ;
        yield();
        printf("2") ;
        yield();
    }
}
