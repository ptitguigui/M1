#include <stdio.h>
#include <ctype.h>
#include "mbr.h"

int main(int argc, char **argv)
{
    init_mbr();
	display_vol();
	save_mbr();
    if (load_super(current_volume) == 0)
    {
    	printf("Chargement du superbloc effectué !\n\n");
    }
	display_bloc();
	exit(EXIT_SUCCESS);
}
