#include "mi_syscall.h"

static int 
sum(void *ptr) 
{
    int i;
    int sum = 0;
    
    for(i = 0; i < PAGE_SIZE * N/2 ; i++)
        sum += ((char*)ptr)[i];
    return sum;
}


int main(int argc, char **argv) 
{
    void *ptr;
    int res;

    init();

    _mask(0x1001);

    ptr = virtual_memory;

    _int(SYSCALL_SWTCH_0);
    memset(ptr, 1, PAGE_SIZE * N/2);
    _int(SYSCALL_SWTCH_1);
    memset(ptr, 3, PAGE_SIZE * N/2);

    _int(SYSCALL_SWTCH_0);
    res = sum(ptr);
    printf("Resultat du processus 0 : %d\n",res);
    _int(SYSCALL_SWTCH_1);
    res = sum(ptr);
    printf("Resultat processus 1 : %d\n",res);

    return 0;
}