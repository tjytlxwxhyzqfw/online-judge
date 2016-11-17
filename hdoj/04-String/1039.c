#include <stdio.h>
#include <string.h>

char ibuf[21];

int
isvo( char ch ){
	return ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u';
}

int
main( void ){
	int n, vo, c3, s2, i;

	freopen( "Inputs/1039", "r", stdin );

	while( scanf("%s",ibuf)!=EOF && strcmp(ibuf,"end") ){
		n = strlen(ibuf);
		vo = c3 = s2 = 0;
		for( i=0; i<n; i++ ){
			if( isvo(ibuf[i]) ){
				vo = 1;
			}
			if( i+1<n && ibuf[i]==ibuf[i+1] && ibuf[i]!='e' && ibuf[i]!='o' ){
				s2 = 1;
			}
			if( i+2<n 
				&& ( isvo(ibuf[i]) && isvo(ibuf[i+1]) && isvo(ibuf[i+2])
				|| !isvo(ibuf[i]) && !isvo(ibuf[i+1]) && !isvo(ibuf[i+2]) ) ){
				c3 = 1;
			}
		}
		printf( "<%s> is %sacceptable.\n",ibuf,vo&&!c3&&!s2?"":"not " );
	}
	return 0;
}
