#include "drive.h"

void go_to_sector(unsigned cylinder, unsigned int sector){
    _out(HDA_DATAREGS, (cylinder>>8&0xFF));
    _out(HDA_DATAREGS+1, (cylinder&0xFF));
    _out(HDA_DATAREGS+2, (sector>>8&0xFF));
    _out(HDA_DATAREGS+3, (sector&0xFF));

    _out(HDA_CMDREG, CMD_SEEK);

    _sleep(HDA_IRQ);
}

void read_sector(unsigned int cylinder, unsigned int sector,unsigned int size, unsigned char *buffer){

	go_to_sector(cylinder, sector);
	_out(HDA_DATAREGS, 0x00);
	_out(HDA_DATAREGS+1, 0x01);
	_out(HDA_CMDREG, CMD_READ);
	_sleep(HDA_IRQ);
	_out(HDA_CMDREG, CMD_DSKINFO);

	memcpy(buffer, MASTERBUFFER, size);
}

void write_sector(unsigned int cylinder, unsigned int sector, unsigned int size, unsigned char *buffer){
	
    _out(HDA_CMDREG, CMD_DSKINFO);
    memcpy(MASTERBUFFER, buffer, size);
	 go_to_sector(cylinder, sector);
    _out(HDA_DATAREGS, 0x00);
	_out(HDA_DATAREGS+1, 0x01);
	_out(HDA_CMDREG, CMD_WRITE);
	_sleep(HDA_IRQ);

}
void format_sector(unsigned int cylinder, unsigned int sector, unsigned int nsector, unsigned int value){
    go_to_sector(cylinder,sector);
	_out(HDA_DATAREGS, (nsector>>8)&0xFF);
	_out(HDA_DATAREGS + 1, nsector&0xFF);
	_out(HDA_DATAREGS + 2, (value>>24)&0xFF);
	_out(HDA_DATAREGS + 3, (value>>16)&0xFF);
	_out(HDA_DATAREGS + 4, (value>>8)&0xFF);
	_out(HDA_DATAREGS + 5, value&0xFF);
	_out(HDA_CMDREG, CMD_FORMAT);
	_sleep(HDA_IRQ);
}

/* dump buffer to stdout,
   and octal dump if octal_dump; an ascii dump if ascii_dump! */
void dump(unsigned char *buffer, unsigned int buffer_size, int ascii_dump, int octal_dump) 
{
    int i,j;
    
    for (i=0; i<buffer_size; i+=16) {
        /* offset */
        printf("%.8o",i);

        /* octal dump */
        if (octal_dump) {
            for(j=0; j<8; j++)
            printf(" %.2x", buffer[i+j]);
            printf(" - ");
            
            for( ; j<16; j++)
            printf(" %.2x", buffer[i+j]);
            
            printf("\n");
        }
        /* ascii dump */
        if (ascii_dump) {
            printf("%8c", ' ');
            
            for(j=0; j<8; j++)
            printf(" %1c ", isprint(buffer[i+j])?buffer[i+j]:' ');
            printf(" - ");
            
            for( ; j<16; j++)
            printf(" %1c ", isprint(buffer[i+j])?buffer[i+j]:' ');
            
            printf("\n");
        }
    }
}