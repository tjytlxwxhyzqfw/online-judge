#include <stdio.h>

int result[41] = { 0, 1, 1 };


int
main( void ){
	int n, i, m, cur;
	
	scanf( "%d", &n );
	for( i=0; i<n; i++ ){
		scanf( "%d", &m );

		cur = m;
		while( result[cur--]==0 )
			;

		for( cur += 2; cur<=m; cur++ ){
			result[cur] = result[cur-1] + result[cur-2];
		}

		printf( "%d\n", result[cur-1] );
	}

	return 0;
}
