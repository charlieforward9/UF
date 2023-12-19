CC=cc
CFLAGS=-c

mean: mean.c
	$(CC) $(CFLAGS) mean.c
	ar cr libmath.a mean.o

.PHONY: clean

clean:
	rm -f *.o *.a
