#include "mi_syscall.h"


unsigned int current_process = 0;
unsigned int vpage_mapped;

static int ppage_of_vpage(int process, unsigned vpage){
    if(vpage > N/2 -1){
        return -1;
    }

    if(process == 0){
        return vpage+1;
    }else if(process == 1){
        return vpage+N/2+1;
    }else{
        return -1;
    }
}


static void switch_to_process0(void) 
{
    current_process = 0;
    _out(MMU_CMD, MMU_RESET);
}

static void switch_to_process1(void) 
{
    current_process = 1;
    _out(MMU_CMD, MMU_RESET);
}

static int error(){
    printf("tentative d’accès illégal à l’adresse %p\n", _in(MMU_FAULT_ADDR));
    exit(EXIT_FAILURE);
}

static void mmuhandler() 
{
    int vaddr;

    vaddr = _in(MMU_FAULT_ADDR);

    if(vaddr >= ((unsigned int) virtual_memory + VM_SIZE) || vaddr < virtual_memory){
        error();
    }

    store_to_swap(vpage_mapped, 1);
    vpage_mapped = (vaddr >> 12) & 0xFFF;

    fetch_from_swap(vpage_mapped, 1);
    struct tlb_entry_s tlbe;
    tlbe.tlbe_vpage = vpage_mapped ;
    tlbe.tlbe_ppage = 1;
    tlbe.tlbe_xwr = 7;
    tlbe.tlbe_access= 1;

    _out(TLB_ADD_ENTRY, *((int *)(&tlbe)));
}

void init(){
     /* init hardware */
    if(init_hardware("hardware.ini") == 0) {
	    fprintf(stderr, "Error in hardware initialization\n");
	    exit(EXIT_FAILURE);
    }

    /* Interreupt handlers */
    IRQVECTOR[MMU_IRQ] = mmuhandler;
    IRQVECTOR[SYSCALL_SWTCH_0] = switch_to_process0;
    IRQVECTOR[SYSCALL_SWTCH_1] = switch_to_process1;
    
    
}
