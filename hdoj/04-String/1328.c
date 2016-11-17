#include <stdio.h>
#include <string.h>

#define LINEMAX 52

int
main( void ){
	int ncas, cas;
	int n, i;
	char inbuf[LINEMAX];

	freopen( "Inputs/1328", "r", stdin );

	scanf( "%d%c", &ncas, &i );
	for( cas=1; cas<=ncas; cas++ ){
		fgets( inbuf, LINEMAX, stdin );
		if( inbuf[(n=strlen(inbuf))-1]=='\n' ){
			inbuf[n-=1] = 0;
		}
		printf( "String #%d\n", cas );
		for( i=0; i<n; i++ ){
			fputc( inbuf[i]=='Z'?'A':(inbuf[i]+1), stdout );
		}
		puts( "\n" );
	}
	return 0;
}
