#include <stdio.h>
#include <ctype.h>
#include "mbr.h"

int main(int argc, char **argv)
{ int i;
    init_mbr();
	display_vol();
	save_mbr();
  for(i; i<MAXVOL; i++){
    if(i == current_volume){
      load_super(current_volume);
      printf("Chargement du superbloc effectué !\n\n");
    }
  }
	display_bloc();
	exit(EXIT_SUCCESS);
}
