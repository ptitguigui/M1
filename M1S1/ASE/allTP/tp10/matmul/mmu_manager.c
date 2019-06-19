#include "../mi_syscall.h"

extern void user_process();
unsigned int rr_page;

int main(int argc, char **argv) {

    /* hardware initialisation and co */
    /* .. your code here ... */
    vaddr = _in(MMU_FAULT_ADDR);
    if(vaddr >= ((unsigned int) virtual_memory + VM_SIZE) || vaddr < virtual_memory){
        error();
    }
    vpage = (vaddr >> 12) & 0xFFF;
    if(vm_mapping[vpage].vm_mapped == 1){
        struct tlb_entry_s tlbe;
        tlbe.tlbe_vpage = vpage ;
        tlbe.tlbe_ppage = vm_mapping[vpage].vm_mapped;
        tlbe.tlbe_xwr = 7;
        tlbe.tlbe_access= 1;
        _out(TLB_ADD_ENTRY, *((int *)(&tlbe)));
    }
    else{
        if(pm_mapping[rr_page].pm_mapped == 1){
            store_to_swap(pm_mapping[vpage].pm_mapped, rr_page);
            vm_mapping[pm_mapping[rr_page].pm_mapped].vm_mapped = 1;
            tlb.
        }
    }
    

    }
    
    /* user mode */
    user_process();
    return 0;
}

