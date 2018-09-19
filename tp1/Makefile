
CC      = gcc -m32


display: display_stack.o
	$(CC) -o $@ $^

%.o: %.c
	$(CC) -c $<

clean:
	rm *.o
