#include <ctype.h>
#include <stdio.h>
#include <string.h>

int
main( void ){
	int ncas, safe;
	int len, i;
	char ok[4], ibuf[51];
	
	scanf( "%d\n", &ncas );
	while( ncas-- ){
		fgets( ibuf, 51, stdin );
		len = strlen( ibuf );
		if( ibuf[len-1]=='\n' ){
			ibuf[--len]=0;
		}
		for( i=0; i<4; ++i ){
			ok[i]=0;
		}
		if( len<8 || len>16 ){
			goto out;
		}
		for( i=0; i<len; ++i ){
			if( islower(ibuf[i]) ){
				ok[0]=1;
			}else if( isupper(ibuf[i]) ){
				ok[1]=1;
			}else if( isdigit(ibuf[i]) ){
				ok[2]=1;
			}else if( ibuf[i]=='~' 
					|| ibuf[i]=='!'
					|| ibuf[i]=='@'
					|| ibuf[i]=='#'
					|| ibuf[i]=='$'
					|| ibuf[i]=='%'
					|| ibuf[i]=='^' ){
				ok[3]=1;
			}
		}
		for( safe=i=0; i<4; i++ ){
			safe += ok[i];
		}
		out:
		printf( "%s\n", safe>2?"YES":"NO" );
	}
	return 0;
}
		 
