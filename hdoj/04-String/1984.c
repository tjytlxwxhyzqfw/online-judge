#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int
main( void ){
	int ncas, n, i, cas;
	int ch;
	
	freopen( "Inputs/1984", "r", stdin );

	scanf( "%d%*c", &ncas );
	for( cas=1; cas<=ncas; cas++ ){
		printf( "%d ", cas );
		scanf( "%d%*c", &n );
		i = 0;
		while( (ch=getchar())!=EOF ){
			if( ch=='\n' ){
				putchar( ch );
				break;
			}
			if( ++i!=n ){
				putchar( ch );
			}
		}
	}
	return 0;
}
