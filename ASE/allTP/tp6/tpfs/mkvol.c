#include <stdio.h>
#include <ctype.h>
#include "drive.h"

static void empty_it()
{
    return;
}

int main(int argc, char **argv)
{
    int cylinder;
    int sector;
    int nbsector;
    int type;

    init_mbr();
   
    printf("Saisir le 1er cylindre:\n");
    scanf("%i", &cylinder);
    printf("Saisir le 1er secteur:\n");
    scanf("%i", &sector);
    printf("Saisir le nombre de secteurs:\n");
    scanf("%i", &nbsector);
    printf("Choisir le type de volume (0- VBASE 1- VANX 2- VOTHER)\n");
    scanf("%i", &type);
  
    make_vol(sector, cylinder, nbsector, type);
	display_vol();
    save_mbr();
    
    exit(EXIT_SUCCESS);
}