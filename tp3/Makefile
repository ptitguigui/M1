CC = gcc

CFLAGS 	= -Wall -Werror -pedantic
CFLAGS += -D_XOPEN_SOURCE=500
CFLAGS += -g
CFLAGS += -m32

OBJ = context.o main.o

all: ping_pong

ping_pong: $(OBJ) context.h
	$(CC) $(CFLAGS) -o $@ $(OBJ)

.PHONY: clean realclean
clean:
	$(RM) $(OBJ)
