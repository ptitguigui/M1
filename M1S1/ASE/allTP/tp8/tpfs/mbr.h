#include "drive.h"

#define MAXVOL 8
#define MBR_MAGIC 0xBEBE
#define BLOC_SIZE  SECTORSIZE
/*TP7*/
#define SUPERMAGIC 0xCAFE
#define SUPER 0
#define BLOCKEND 0

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
    int current_volume;
};

extern struct mbr_s mbr;

/*TP7*/
struct super_s{
    unsigned int super_magic;
    unsigned int super_first_free;
    unsigned int nb_bloc_free;
};

struct freeb_s {
    unsigned int fb_nbloc;
    unsigned int fb_next;
};

extern struct super_s super;
extern int current_volume;

int load_mbr();
void save_mbr();
int sector_of_bloc(int numVol, int numBloc);
int cylinder_of_bloc(int numVol, int numBloc);
void read_bloc(unsigned int numVol,unsigned int numBloc, unsigned int size, unsigned char *buffer);
void write_bloc(unsigned int numVol,unsigned int numBloc,unsigned int size, unsigned char *buffer);
void display_vol();
void make_vol(unsigned int first_sector, unsigned int first_cylinder,unsigned int n_sectors, enum vol_type_e type);
void init_mbr();

/*TP7*/
void init_volume(unsigned int vol);
int load_super(unsigned int vol);
void save_super();
unsigned int new_bloc();
unsigned int new_bloc_zero();
void free_bloc(unsigned int bloc);
void free_blocs(unsigned int blocs[], unsigned int size);
void display_bloc();
