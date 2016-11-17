#include <ctype.h>
#include <stdio.h>
#include <string.h>

int
main( void ){
	char ibuf[101];
	int i, len;

	while( fgets(ibuf,101,stdin)!=NULL ){
		len = strlen( ibuf );
		for( i=0; i<len; i++ ){
			if( islower(ibuf[i]) && (i-1<0 || isspace(ibuf[i-1])) ){
				ibuf[i] -= 32;
			}
		}
		printf( "%s", ibuf );
	}
	return 0;
}  
