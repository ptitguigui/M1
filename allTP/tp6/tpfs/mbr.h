#include "drive.h"

#define MAXVOL 8
#define MBR_MAGIC 0xBEBE

enum vol_type_e {VBASE, VANX, VOTH, VNONE};

struct vol_descr_s {
    unsigned int vol_first_sector;
    unsigned int vol_first_cylinder;
    unsigned int vol_n_sectors;
    enum vol_type_e vol_type;
};

struct mbr_s{
    struct vol_descr_s mbr_vols[MAXVOL];
    unsigned int mbr_magic;
};

int load_mbr();
void save_mbr();
int sector_of_bloc(int numVol, int numBloc);
int cylinder_of_bloc(int numVol, int numBloc);
void read_bloc(unsigned int numVol,unsigned int numBloc, unsigned char *buffer);
void write_bloc(unsigned int numVol,unsigned int numBloc, unsigned char *buffer);
void display_vol();
void make_vol(unsigned int first_sector, unsigned int first_cylinder,unsigned int n_sectors, enum vol_type_e type);
void init_mbr();
