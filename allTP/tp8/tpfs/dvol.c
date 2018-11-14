#include <stdio.h>
#include <ctype.h>
#include "drive.h"

int main(int argc, char **argv)
{
    init_mbr();
    display_vol();
    exit(EXIT_SUCCESS);
}