/*
** 1106
** 错误的代码 
while( sscanf(ibuf,"%d",array+n) && array[n]!=-1 ){
			printf( "receive:%d\n", array[n] );
			n += 1;
		}
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void isort( int *a, int b, int e );
void hsort( int *a, int b, int e );
void qsort2( int *a, int b, int e );

char ibuf[1004];
int array[1000];

int
main( void ){
	int i, j, n;
	char nbuf[10], *cur;

	freopen( "../Inputs/1106", "r", stdin );

	while( scanf("%s",ibuf)!=EOF ){
		strcpy( ibuf+strlen(ibuf),"5-1" );
		for(cur=ibuf; *cur=='5'; cur++ )
			;
		n = strlen(cur);
		for( i=j=0; j<=n; j++ ){
			if( cur[j] != '5' )
				ibuf[i++] = cur[j];
			else if( ibuf[i-1] != 0 )
				ibuf[i++] = 0;
		}
		
		for( n=0,cur=ibuf; strcpy(nbuf,cur)&&strcmp(nbuf,"-1"); cur+=strlen(nbuf)+1 ){
			sscanf( nbuf, "%d", array+n++ );
		}
		
		
		isort( array, 0, --n );

		for( i=0; i<=n; i++ )
			printf( "%d%c", array[i],i==n?'\n':' ' );
	}
	return 0;
}

void
isort( int* a, int b, int e ){
	int i, j;
	int key;

	for( i=b+1; i<=e; i++ ){
		key = a[i];
		for( j=i-1; j>=b&&key<a[j]; j-- )
			a[j+1] = a[j];
		a[j+1] = key;
	}
}
		
	

