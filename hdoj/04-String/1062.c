#include <stdio.h>
#include <ctype.h>
#include <string.h>

void
reverse( char *s, int b, int e ){
	int i, m = (b+e)/2;
	char t;

	for( i=b; i<=m; i++ ){
		t = s[i];
		s[i] = s[b+e-i];
		s[b+e-i] = t;
	}
}

int
main( void ){	
	char ibuf[1024], ch;
	int ncase, i, j;

	freopen( "Inputs/1062", "r", stdin );

	scanf( "%d%c", &ncase, &ch );
	while( ncase-->0 ){
		i = 0;
		while( (ch=getchar())!='\n' ){
			ibuf[i++] = ch;
			/*printf( "ch=%d\n", ch );*/
		}
		ibuf[i] = '\n';
		ibuf[i+1] = 0;
		/*puts( ibuf );*/
		i = 0;
		for( j=0; j<strlen(ibuf); j++ ){
			if( isspace(ibuf[j]) ){
				if( i==j ){
					i += 1;
				}else{
					reverse( ibuf, i, j-1 );
					i = j+1;
				}
			}
		}
		printf( "%s", ibuf );
	}
	return 0;
}
