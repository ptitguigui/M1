#include <stdio.h>
#include <ctype.h>
#include "drive.h"

static void empty_it()
{
    return;
}

int main(int argc, char **argv)
{
    unsigned int i, sectorsize, sector, cylinder;
    unsigned char* buff;
    
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
    
    cylinder = 5;
    sector = 5;
    
    _out(HDA_CMDREG,CMD_DSKINFO);
    sectorsize=_in(HDA_DATAREGS+4)<<8;
    sectorsize+=_in(HDA_DATAREGS+5);
    buff=malloc(sectorsize);
    read_sector(cylinder,sector,buff);
    dump(buff,sectorsize,0,1);
    printf("\nDonnées du cylindre %d et de secteur %d\n", cylinder, sector);

    /* and exit! */
    exit(EXIT_SUCCESS);
}