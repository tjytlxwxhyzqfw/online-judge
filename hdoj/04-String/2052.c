#include <stdio.h>

void 
printheader( int size, char boarder, char fill){
	int i;
	putchar(boarder);
	for( i=0; i<size; ++i ){
		putchar(fill);
	}
	putchar(boarder);
	putchar( '\n' );
}

int
main( void ){
	int i, a, b;
	while( scanf("%d%d",&a,&b)!=EOF ){
		printheader(a, '+', '-' );
		for( i=0; i<b; ++i ){
			printheader( a, '|', ' ' );
		}
		printheader( a, '+', '-' );
		putchar( '\n' );
	}
	return 0;
}
