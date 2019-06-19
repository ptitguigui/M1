ROOTDIR=.

CC	= gcc
CFLAGS	= -Wall -ansi -pedantic
CFLAGS  += -std=iso9899:1999
CFLAGS  += -g -m32
LIBDIR  = -L$(ROOTDIR)/lib
INCDIR  = -I$(ROOTDIR)/include
LIBS    = -lhardware


OBJ = mi_user.o 

all: mi_user

mi_user: $(OBJ) mi_user.o mi_kernel.o swap.o
	$(CC) $(CFLAGS) -o $@ $^ $(LIBDIR) $(LIBS)


mi_user.o : mi_user.c mi_kernel.c swap.c mi_syscall.h swap.h
mi_kernel.o : mi_kernel.c swap.c mi_syscall.h swap.h	
swap.o : swap.c swap.h
%.o : %.c 
	$(CC)  $(CFLAGS) -c $< $(INCDIR)


.PHONY: clean realclean depend
clean:
	$(RM) *.o $(BINARIES)
realclean: clean 
depend : 
	$(CC) $(CFLAGS) -MM $(INCDIR) *.c 