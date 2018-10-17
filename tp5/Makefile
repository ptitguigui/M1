# $Id: Makefile,v 1.2 2004/10/12 09:06:17 marquet Exp $
##############################################################################

ROOTDIR=/home/enseign/ASE

CC	= gcc
CFLAGS	= -m32 -Wall -ansi -pedantic 
CFLAGS  += -g
LIBDIR  = $(ROOTDIR)/lib
INCDIR  = -I$(ROOTDIR)/include
LIBS    = -L$(LIBDIR) -lhardware

###------------------------------
### Main targets 
###------------------------------------------------------------
BINARIES= dmps 
OBJECTS	= $(addsuffix .o,\
	  dmps)

all: $(BINARIES) $(OBJECTS)


###------------------------------
### Main rules 
###------------------------------------------------------------
dmps.o: dmps.c drive.h
	$(CC) $(CFLAGS) -c dmps.c $(INCDIR)
drive.o: drive.c drive.h
	$(CC) $(CFLAGS) -c drive.c $(INCDIR)
dmps: dmps.o drive.o  
	$(CC) $(CFLAGS) -o dmps drive.o dmps.o $(LIBS)

###------------------------------
### Misc.
###------------------------------------------------------------
.PHONY: clean realclean depend
clean:
	$(RM) *.o $(BINARIES)
realclean: clean 
	$(RM) vdiskA.bin vdiskB.bin
