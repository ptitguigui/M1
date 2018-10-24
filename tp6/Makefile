# $Id: Makefile,v 1.2 2004/10/12 09:06:17 marquet Exp $
##############################################################################

ROOTDIR=./

CC	= gcc
CFLAGS	= -Wall -ansi -pedantic 
CFLAGS  += -g -m32
LIBDIR  = $(ROOTDIR)/lib
INCDIR  = -I$(ROOTDIR)/include
LIBS    = -L$(LIBDIR) -lhardware

###------------------------------
### Main targets 
###------------------------------------------------------------
BINARIES= mkhd dmps dvol mkvol 
OBJECTS	= mkhd.o dmps.o drive.o dvol.o mkvol.o 

all: $(BINARIES) $(OBJECTS)


###------------------------------
### Main rules 
###------------------------------------------------------------
mkhd: mkhd.o
	$(CC) $(CFLAGS) -o mkhd mkhd.o $(LIBS)

dmps: dmps.o drive.o
	$(CC) $(CFLAGS) -o dmps $^ $(LIBS)

mkvol: mkvol.o drive.o mbr.o
	$(CC) $(CFLAGS) -o mkvol $^ $(LIBS)

dvol: dvol.o drive.o mbr.o
	$(CC) $(CFLAGS) -o dvol $^ $(LIBS)

%.o: %.c %.h
	$(CC) $(CFLAGS) -c $^ $(INCDIR)

###------------------------------
### Misc.
###------------------------------------------------------------
.PHONY: clean realclean depend
clean:
	$(RM) *.o $(BINARIES)
realclean: clean 
	$(RM) vdiskA.bin vdiskB.bin