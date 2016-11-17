#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <assert.h>

#define LINEMAX 72

int
main( void ){
	int ncas, i, n, sh;
	char line[LINEMAX], ch;

	freopen( "Inputs/1321", "r", stdin );

	scanf( "%d%c", &ncas, &ch );
	assert( ch=='\n' );
	while( ncas-- ){
		fgets( line, LINEMAX, stdin );
		sh = (line[strlen(line)-1]=='\n'?2:1);
		n = strlen(line)-sh;
		for( i=0; i<=n/2; i++ ){
			ch = line[i];
			line[i] = line[n-i];
			line[n-i] = ch;
		}
		fputs( line, stdout );
	}
	return 0;
}
