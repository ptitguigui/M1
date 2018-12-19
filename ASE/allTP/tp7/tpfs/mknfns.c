
#include <stdio.h>
#include <ctype.h>
#include "mbr.h"

int main(int argc, char **argv)
{
	int vol;
	int nb_blocs;
	int i;

	init_mbr();
	display_vol();
	save_mbr();

	printf("Saisir le numero du volume:\n");
    scanf("%i", &vol);
    while (mbr.mbr_vols[vol].vol_n_sectors == 0)
    {
    	printf("Veuillez saisir un  numero de volume valide:\n");
    	scanf("%i", &vol);
    }

	init_volume(vol);
    printf("Saisir le nombre de blocs:\n");
    scanf("%i", &nb_blocs);

	for (i=0; i<nb_blocs; i++)
	{
		new_bloc();
	}

	save_super();
	if (nb_blocs > mbr.mbr_vols[vol].vol_n_sectors-1)
	{
		nb_blocs = mbr.mbr_vols[vol].vol_n_sectors-1;
	}
	printf("Superbloc rempli de %d blocs !\n", nb_blocs);
		
	exit(EXIT_SUCCESS);
}