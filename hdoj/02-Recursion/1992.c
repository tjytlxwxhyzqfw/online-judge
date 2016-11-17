#include <stdio.h>

#define MAXS 1000

unsigned long st[MAXS]={1,1}, sp[MAXS]={0,1}, spp[MAXS]={0,1};

int
main( void ){
	int ncas,cas, i, w;

	scanf("%d",&ncas);
	for( cas=1; cas<=ncas; cas++ ){
		scanf("%d",&w);
		for( i=2; i<=w; i++ ){
			sp[i] = sp[i-1]+st[i-1];
			spp[i] = st[i-1]+spp[i-2];
			st[i] = st[i-1]+3*st[i-2]+spp[i-1]+2*sp[i-2];
		}
		printf( "%d %d\n",cas,st[w] );
	}
	return 0;
}
