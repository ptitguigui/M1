ROOTDIR=.

CC = gcc

CFLAGS 	= -Wall -Werror -pedantic
CFLAGS += -D_XOPEN_SOURCE=500
CFLAGS += -g
CFLAGS += -m32
INCS    = -I$(ROOTDIR)/include
LIBS    = -L$(ROOTDIR)/lib -lhardware

ping_pong :main.o context.o
	$(CC) $(CFLAGS) -o main main.o context.o $(LIBS)
main.o :main.c hwconfig.h
	$(CC) $(CFLAGS) $(INCS) -c main.c
context.o :context.c hwconfig.h
	$(CC) $(CFLAGS) $(INCS) -c context.c



.PHONY: clean realclean
clean:
	$(RM) $(OBJ)
