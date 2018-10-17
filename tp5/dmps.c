#include <stdio.h>
#include <ctype.h>
#include "drive.h"

static void empty_it()
{
    return;
}

int main(int argc, char **argv)
{
    unsigned int i;
    
    /* init hardware */
    if(init_hardware("hwconfig.ini") == 0) {
	fprintf(stderr, "Error in hardware initialization\n");
	exit(EXIT_FAILURE);
    }

    /* Interreupt handlers */
    for(i=0; i<16; i++)
	IRQVECTOR[i] = empty_it;

    /* Allows all IT */
    _mask(1);


    unsigned char *buffer = malloc(SECTORSIZE);
    
    /*write_sector(5,5,4,buffer);*/
    dump(buffer, SECTORSIZE, 1,1);
    read_sector(5,5,buffer);
    

    /* and exit! */
    exit(EXIT_SUCCESS);
}