#include <stdio.h>

int s[30], c[30];

int
main( void ){
	s[0] = 1;
	s[1] = 0;
	s[2] = 3;

	c[0] = 0;
	c[1] = 1;

	int n, i;

	while( scanf("%d",&n)&&n!=-1 ){
		for( i=3; i<=n; i++ ){
			c[i] = s[i-1]+c[i-2];
			s[i] = 3*s[i-2]+2*c[i-3];
		}
		printf( "%d\n", s[n] );
	}
	return 0;
}
