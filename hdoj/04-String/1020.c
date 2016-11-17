#include <stdio.h>
#include <string.h>

#define LMAX 10001

char iln[LMAX];
int count[LMAX];

int
main( void ){
	int cases, i, j, n;

	scanf("%d",&cases);
	while( cases-->0 ){
		scanf( "%s", iln );
		n = strlen( iln );
		for( i=0; i<n; i++ ){
			count[i] = 0;
		}
		i = 0;
		for( j=0; j<n; j++ ){
			if( iln[j]==iln[i] ){
				count[i] += 1;
			}else{
				count[i=j] += 1;
			}
		}
		for( i=0; i<n; i++ ){
			if( count[i] != 0 ){
				if( count[i] != 1 ){
					printf( "%d", count[i] );
				}
				printf( "%c", iln[i] );
			}
		}
		puts( "" );
	}
	return 0;
}
		
