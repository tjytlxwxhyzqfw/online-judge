#include <stdio.h>

int s[64]={ 0, 1, 3 };

int
main( void ){
	int n, i;
	while( scanf("%d",&n)!=EOF ){
		for( i=3; i<=n; i++ ){
			s[i] = 2*s[i-2]+3;
			printf( "s[%d]=%d\n", i, s[i] );
		}
	}
	return 0;
}
