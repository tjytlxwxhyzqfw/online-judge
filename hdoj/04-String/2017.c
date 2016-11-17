#include <ctype.h>
#include <stdio.h>

int
main( void ){
	int ncas, count;
	int ch;
	
	freopen( "Inputs/2017", "r", stdin );

	scanf( "%d%*c", &ncas );
	while( ncas-- ){
		count = 0;
		while( 1 ){
			ch = getchar();
			if( ch=='\n' ){
				printf( "%d\n", count );
				break;
			}else if( ch==EOF ){
				printf( "%d\n", count );
				return 0;
			}
			if( isdigit(ch) ){
				++count;
			}
		}
	}
	return 0;
}
