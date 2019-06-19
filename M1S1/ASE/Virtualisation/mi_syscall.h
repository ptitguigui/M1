#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <assert.h>
#include "hardware.h"

#define SYSCALL_SWITCH_0 16
#define SYSCALL_SWITCH_1 17

#define PAGE_SIZE 4096
#define PM_PAGES 256
#define VM_PAGES 4096
#define PM_SIZE (PM_PAGES * PAGE_SIZE)
#define VM_SIZE (VM_PAGES * PAGE_SIZE)

#define N (PM_PAGES -2)

#define MMU_IRQ 13
#define MMU_CMD 0x66
#define MMU_FAULT_ADDR 0xCD

#define TLB_ADD_ENTRY	0xCE

struct tlb_entry_s{
    unsigned int tlbe_rfu:8;
    unsigned int tlbe_vpage:12;
    unsigned int tlbe_ppage:8;
    unsigned int tlbe_xwr:3;
    unsigned int tlbe_access:1;
};

void init(void);