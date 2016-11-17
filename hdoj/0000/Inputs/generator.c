#include <stdio.h>
#include <time.h>
#include <limits.h>

int
main( void ){
	int i;
	srand( (unsigned)time(NULL) );
	printf( "%d\n", 100000 );
	for( i = 100000; i > 2; i-- )
		printf( "%f %f\n", rand()*0.14159*-1, rand()*0.45854*-1 );
	printf( "%f %f\n", 1.0, 1.0 );
	printf( "%f %f\n", 1.1, 1.1 );
	printf( "%d\n", 0 );
}
