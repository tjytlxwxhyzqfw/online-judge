#include <stdio.h>
#include <string.h>

#define NUMSIZE 1000000

/*
** 003
** ++++3
** -0003
** 1.00000
*/

int
isdec( char *n ){
	int i;
	for( i=strlen(n)-1; i>=0; i-- ){
		if( n[i]=='.' ){
			return 1;
		}
	}
	return 0;
}

int
norm( char *n ){
	int len, i, j, sign = 1;
	len = strlen( n );
	if( n[len-1]=='\n' ){
		n[--len]=0;
	}
	for( j=i=0; i<len && (n[i]=='0'||n[i]=='-'||n[i]=='+'); ++i ){
		if( n[i]=='-' ){
			sign *= -1;
		}
	}
	while( n[j++]=n[i++] ){}
	if( isdec(n) ){
		for( i=strlen(n)-1; i>=0 && (n[i]=='0'||n[i]=='.'); i-- ){
			if( n[i]=='.' ){
				n[i] = 0;
				break;
			}
			n[i] = 0;
	}
	}
	return strlen(n)==0?0:sign;
}

int
main( void ){
	char a[NUMSIZE], b[NUMSIZE];
	int sa, sb;
	
	freopen( "Inputs/2054", "r", stdin );

	while( scanf("%s%s",a,b)!=EOF ){
		sa=norm( a );
		//printf( "norm:%s\n", a );
		sb=norm( b );
		//printf( "norm:%s\n", b );
		printf( "%s\n", sa==sb?(strcmp(a,b)?"NO":"YES"):"NO" );
	}
} 
