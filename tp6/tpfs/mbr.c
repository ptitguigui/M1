#include "mbr.h"

/*
TODO : 
    - Verifier si on écrit dans une partition --> isCorrect
    - MAIN
*/

static struct mbr_s mbr;

int load_mbr(){
    assert(sizeof(struct mbr_s) <= SECTORSIZE);
    read_sector(0,0,sizeof(struct mbr_s), &mbr);

    if(mbr.mbr_magic != MBR_MAGIC){
        /*initialisation de mbr*/
        
        mbr.mbr_magic = MBR_MAGIC;
        int i;
        for(i=0; i<MAXVOL; i++){
        mbr.mbr_vols[i].vol_type = VNONE;
        mbr.mbr_vols[i].vol_first_sector = 0;
        mbr.mbr_vols[i].vol_first_cylinder = 0;
        mbr.mbr_vols[i].vol_n_sectors = 0;}

        return 0;
    }
    return 1;
}
void save_mbr(){
    write_sector(0,0, sizeof(struct mbr_s), &mbr);
}
int sector_of_bloc(int numVol, int numBloc){

    struct vol_descr_s vol = mbr.mbr_vols[numVol];

    assert(numVol < MAXVOL);
    assert(numBloc < vol.vol_n_sectors);
    assert(vol.vol_type != VNONE);
    
    return (vol.vol_first_sector + numBloc)%HDA_MAXSECTOR; 
}
int cylinder_of_bloc(int numVol, int numBloc){
  
    struct vol_descr_s vol = mbr.mbr_vols[numVol];

    assert(numVol < MAXVOL);
    assert(numBloc < vol.vol_n_sectors);
    assert(vol.vol_type != VNONE);
    
    return vol.vol_first_cylinder + (int)((numBloc+vol.vol_first_sector) / HDA_MAXSECTOR);   
}
void read_bloc(unsigned int numVol,unsigned int numBloc, unsigned char *buffer){
    assert(mbr.mbr_vols[numVol].vol_type != VNONE);
    read_sector(cylinder_of_bloc(numVol, numBloc), sector_of_bloc(numVol, numBloc),5, buffer);
}
void write_bloc(unsigned int numVol,unsigned int numBloc, unsigned char *buffer){
    assert(mbr.mbr_vols[numVol].vol_type == VNONE);
    assert(isCorrect(mbr.mbr_vols[numVol].vol_first_cylinder, mbr.mbr_vols[numVol].vol_first_sector, mbr.mbr_vols[numVol].vol_n_sectors));
    write_sector(cylinder_of_bloc(numVol, numBloc), sector_of_bloc(numVol, numBloc),5, buffer);
}

char * printType(enum vol_type_e type){
    if(type == VBASE){
        return "VBASE";
    }

    if(type == VANX){
        return "VANX";
    }

    if(type == VOTH){
        return "VOTH";
    }
    return "VNONE";
}

void display_vol(){
    int c0,s0,nBloc,cl,sl,i;
    char *type; 
    for(i=0; i<MAXVOL; i++){
        if(mbr.mbr_vols[i].vol_type != VNONE){

            type = printType(mbr.mbr_vols[i].vol_type);
            c0 = mbr.mbr_vols[i].vol_first_cylinder;
            s0 = mbr.mbr_vols[i].vol_first_sector;
            nBloc = mbr.mbr_vols[i].vol_n_sectors;
            cl = cylinder_of_bloc(i,0);
            sl = sector_of_bloc(i,0);

            printf("%d - (%d,%d) - %d - %s - (%d,%d)\n",i,c0,s0,nBloc,type,cl,sl);
        }
    }
}

int isCorrect(unsigned int first_sector, unsigned int first_cylinder,unsigned int n_sectors){
    /* TODO */
    return 1;
}

void make_vol(unsigned int first_sector, unsigned int first_cylinder,unsigned int n_sectors, enum vol_type_e type){
    int i;
    assert(isCorrect(first_sector, first_cylinder, n_sectors));
    for(i=0; i<MAXVOL; i++){
        if(mbr.mbr_vols[i].vol_type == VNONE){
            mbr.mbr_vols[i].vol_first_cylinder = first_cylinder;
            mbr.mbr_vols[i].vol_first_sector = first_sector;
            mbr.mbr_vols[i].vol_type = type;
            mbr.mbr_vols[i].vol_n_sectors = n_sectors;
            break;
        }
    }
    
}
static void empty_it()
{
    return;
}

void init_mbr()
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

	load_mbr();
}