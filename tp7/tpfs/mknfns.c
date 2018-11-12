
#include <stdio.h>
#include <ctype.h>
#include "mbr.h"

int main(int argc, char **argv)
{

	init_mbr();
	init_volume(0);
	display_vol();
    save_mbr();

	new_bloc();
	new_bloc();
	save_super();
	printf("Superbloc rempli de 2 blocs !\n");
	save_super();	
	exit(EXIT_SUCCESS);
}