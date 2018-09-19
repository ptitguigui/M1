
CC      = gcc -m32

all = display, try

display: display_stack.o
	$(CC) -o $@ $^

try: try.o
		$(CC) -o $@ $^

%.o: %.c
	$(CC) -c $<

clean:
	rm *.o
