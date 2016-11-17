#include <stdio.h>
#include <string.h>

#define LNMAX 202
#define CMAX 20

int
main( void ){
	int col, icol, i, j, inlen;
	char inbuf[LNMAX], *pos, tmp;
	int right_to_left;

	freopen( "Inputs/1200", "r", stdin );

	while( scanf("%d",&col) && col!=0 ){
		scanf( "%s",inbuf );

		/*printf( "case: %s,%d\n", inbuf, col );*/

		icol = col-1;
		right_to_left = 0;
		inlen = strlen(inbuf);
		for( pos=inbuf; pos<inbuf+inlen; pos+=col ){
			if( right_to_left ){
				for( i=0; i<=icol/2; i++ ){
					tmp = pos[i];
					pos[i] = pos[icol-i];
					pos[icol-i] = tmp;
				}
			}
			right_to_left = !right_to_left;
		}

		for( i=0; i<=icol; i++ ){
			/*
			** >>> for( j=i; j<=inlen; j+=col ){
			*/			
			for( j=i; j<inlen; j+=col ){
				printf( "%c", inbuf[j] );
			}
		}
		printf( "%c", '\n' );
	}
	return 0;
}
			
			
