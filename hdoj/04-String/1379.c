#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define LMAX 51
#define NMAX 100

char inrepo[NMAX][LMAX];
int nxs[NMAX];

struct node{
	char *pos;
	int nxs;
};

struct node nodes[NMAX];

int 
qcomp( const void *x, const void *y ){
	return ((struct node*)x)->nxs-((struct node*)y)->nxs;
}

int
nxs_and_sort( int b, int e, char *str ){
	int nxs,nxsle,nxsri;
	int m, i, j, k, h;
	char *buf;

	if( b==e ){
		return 0;
	}

	assert( b<e );

	m = (b+e)/2;
	nxsle = nxs_and_sort(b,m,str);
	nxsri = nxs_and_sort(m+1,e,str);

	nxs=0;
	j = m+1;
	for( i=b; i<=m; i++ ){
		while( j<=e && str[j]<str[i] ){
			j++;
		}
		nxs += j-m-1;
	}
	
	assert( (buf=(char*)malloc(sizeof(char)*(e-b+1)))!=NULL );

	k = 0;
	i = b;
	j = m+1;
	while( i<=m && j<=e ){
		buf[k++] = (str[i]<str[j]?str[i++]:str[j++]);
	}
	while( i<=m ){
		buf[k++] = str[i++];
	}
	while( j<=e ){
		buf[k++] = str[j++];
	}
	for( k=0,i=b; i<=e; i++,k++ ){
		str[i] = buf[k];
	}
	
	free( buf );
	
	return nxs+nxsle+nxsri;
}

int
main( void ){
	char inbuf[LMAX];
	int len;
	int i, num;
	int ncas;

	freopen( "Inputs/1379", "r", stdin );

	scanf("%d",&ncas );
loop:
	scanf("%d%d",&len,&num);
	for( i=0; i<num; i++ ){
		scanf( "%s", inbuf );
		strcpy( inrepo[i], inbuf );
		nodes[i].pos = inrepo[i];
		nodes[i].nxs = nxs_and_sort(0,strlen(inbuf)-1,inbuf);
	}
	
	qsort( nodes, num, sizeof(struct node), qcomp );

	for( i=0; i<num; i++ ){
		printf( "%s\n", nodes[i].pos/*, nodes[i].nxs*/ );
	}

	if( --ncas ){
		puts( "" );
		goto loop;
	}
	
	return 0;
}	
