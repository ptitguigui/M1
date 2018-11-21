#include "inode.h"


void read_inode (unsigned int inumber, struct inode_s *inode){
    read_bloc(current_volume, inumber, sizeof(struct inode_s), inode);
}
void write_inode (unsigned int inumber, const struct inode_s *inode){
    write_bloc(current_volume, inumber, sizeof(struct inode_s), inode);
}

unsigned int create_inode(enum file_type_e type){
    struct inode_s inode;
    int i;
    int inumber;

    inode.inode_ft = type;
    inode.ind_size = 0;
    
    for (i=0; i < NDIRECT; i++){
        inode.inode_direct[i] = BLOC_NULL;
    }
    
    inode.inode_indirect = BLOC_NULL;
    inode.inode_2indirect = BLOC_NULL;

    inumber = new_bloc();
    printf("%d", inumber);
    write_inode(inumber, &inode);

    return inumber;
} 
int delete_inode(unsigned int inumber){
    struct inode_s inode;

    read_inode(inumber, &inode);
    free_blocs(inode.inode_direct, NDIRECT);

    if ( inode.inode_indirect != 0){
        unsigned int blocs[NNBPB];
        read_bloc(current_volume, inode.inode_indirect, blocs, NNBPB*sizeof(unsigned int));
        free_blocs(blocs, NNBPB);
        free_bloc(inode.inode_indirect);
    }

    /* TODO double indirect*/

    free_bloc(inumber);

    return 0;
}

unsigned int vbloc_of_fbloc(unsigned int inumber, unsigned int fbloc,bool_t do_allocate){
    struct inode_s inode;

    read_inode(inumber, &inode);

    if(fbloc < NDIRECT){
        if(do_allocate && inode.inode_direct[fbloc] == 0){
            inode.inode_direct[fbloc] = new_bloc();
            write_inode(inode.inode_direct[fbloc], &inode);
        }
    
        return inode.inode_direct[fbloc];
    }

    fbloc -= NDIRECT;

    if(fbloc < NNBPB){
        if(do_allocate && inode.inode_indirect == 0){
            inode.inode_indirect = new_bloc_zero();
        }
        if(inode.inode_indirect == 0){
            return 0;
        }

        unsigned blocs[NNBPB];
        read_bloc(current_volume, inode.inode_indirect, blocs, NNBPB*sizeof(unsigned int));
        
        return blocs[fbloc];
    }

    /* TODO double indirect */
}
