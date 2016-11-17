#include <stdio.h>
#include <string.h>

#define LMAX 101

int
main( void ){
	int i, len;
	char ibuf[LMAX], maxch;
	
	freopen( "Inputs/2025", "r", stdin );
	
	while( scanf("%s",ibuf)!=EOF ){
		maxch=0;
		len = strlen(ibuf);
		for( i=0; i<len; i++ ){
			if( ibuf[i]>maxch ){
				maxch = ibuf[i];
			}
		}
		for( i=0; i<len; i++ ){
			putchar( ibuf[i] );
			if( ibuf[i]==maxch ){
				printf( "(max)" );
			}
		}
		printf( "\n" );
	}
	return 0;
}
