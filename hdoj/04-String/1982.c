#include <assert.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define LMAX 10000

int
main( void ){
	int ncas, ch, tmp;

	freopen( "Inputs/1982", "r", stdin );

	scanf( "%d%*c", &ncas );
	while( ncas-- ){
		while( (ch=getchar())!=EOF ){
			if( isdigit(ch) ){
				tmp = ch;
				ch = getchar();
				if( isdigit(ch) ){
					putchar( (tmp-48)*10+ch+16 );
				}else{
					putchar( tmp+16 );
					ungetc( ch, stdin );
				}
			}else if( ch=='#' ){
				putchar( ' ' );
			}else if( ch=='\n' ){
				putchar( ch );
				break;
			}
		}
	}
	return 0;
}
