#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define BITS 10000

struct list_node{
	int n;
	char numstr[BITS];
	struct list_node *next;
};

void
printnum( const char *num ){
	int i, n = strlen(num);

	for( i=0; i<n; i++ )
		putchar(num[n-1-i]);
	puts( "" );
}


void
plu( const char *x, const char *y, char *r ){
	int nx, ny, i, pr;

	nx = strlen(x);
	ny = strlen(y);

	for( pr=i=0; i<nx&&i<ny;i++ ){
		pr = pr+x[i]+y[i]-96;
		r[i] = pr%10+48;
		pr /=10;
	}

	for( ; i<nx; i++ ){
		pr = pr+x[i]-48;
		r[i] = pr%10+48;
		pr /= 10;
	}

	for( ; i<ny; i++ ){
		pr = pr+y[i]-48;
		r[i] = pr%10+48;
		pr /= 10;
	}

	(r[i]=pr+48)=='0' ? (r[i]=0) : (r[i+1]=0);
}

int
main( void ){
	struct list_node nd;
	struct list_node *list = &nd, *p;
	int m, n, i;
	
	list->next = (struct list_node *)malloc( sizeof(struct list_node) );

	list->next->n = 2;
	strcpy( list->next->numstr, "1" );
	list->next->next=(struct list_node *)malloc( sizeof(struct list_node) );

	list->next->next->n = 1;
	strcpy( list->next->next->numstr, "1" );
	list->next->next->next = NULL;

	scanf( "%d", &n );
	for( i=0; i<n; i++ ){
		scanf( "%d", &m );
		while( list->next->n<m ){
			p = (struct list_node *)malloc( sizeof(struct list_node) );
			p->n = list->next->n+1;
			plu( list->next->numstr, list->next->next->numstr, p->numstr );
			p->next = list->next;
			list->next = p;
		}

		if( list->next->n == m ){
			printnum( list->next->numstr );
			continue;
		}

		for( p=list; p->next!=NULL&&p->next->n>m; p=p->next )
			;
		printnum( p->next->numstr );
	}
	return 0;
}
		
