
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include "hardware.h"

#define HDA_CMDREG 0x3F6     
#define HDA_DATAREGS 0x110
#define HDA_IRQ 14
#define SECTORSIZE 256

void go_to_sector(unsigned int cylinder, unsigned int sector);
void read_sector(unsigned int cylinder, unsigned int sector, unsigned char *buffer);
void read_sector_size(unsigned int cylinder, unsigned int sector, unsigned int size, unsigned char *buffer);
void write_sector(unsigned int cylinder, unsigned int sector, unsigned int size, unsigned char *buffer);
void format_sector(unsigned int cylinder, unsigned int sector, unsigned int nsector, unsigned int value);