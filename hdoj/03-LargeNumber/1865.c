#include <string.h>
#include <stdio.h>
#include <stdlib.h>

#define BITS 10000

struct list_node{
	int n;
	char numstr[BITS];
	struct list_node *next;
};

void
plu( const char *x, const char *y, char *r ){
	int nx, ny, i, pr;

	nx = strlen( x );
	ny = strlen( y );
	for( pr=i=0; i<nx&&i<ny; i++ ){
		pr = x[i]+y[i]+pr-96;
		r[i] = pr%10+48;
		pr /= 10;
	}
	for( ; i<nx; i++ ){
		pr = x[i]+pr-48;
		r[i] = pr%10+48;
		pr /= 10;
	}
	for( ; i<ny; i++ ){
		pr = y[i]+pr-48;
		r[i] = pr%10+48;
		pr /= 10;
	}
	(r[i]=pr+48)=='0' ? (r[i]=0) : (r[i+1]=0);
}

void
printnum( char *num ){
	int i, n=strlen(num);
	for( i=n-1; i>=0; i-- ){
		putchar(num[i]);
	}
	putchar( '\n' );
}

int
main( void ){
	int n, cases;
	char ones[BITS];
	struct list_node head;
	struct list_node *list = &head, *p;

	list->next = (struct list_node*)malloc( sizeof(struct list_node) );
	list->next->n = 2;
	strcpy(list->next->numstr,"2");
	list->next->next = (struct list_node*)malloc( sizeof(struct list_node) );
	
	list->next->next->n = 1;
	strcpy( list->next->next->numstr, "1" );
	list->next->next->next = NULL;

	scanf( "%d", &cases );
	while( cases-->0 ){
		scanf( "%s",ones );
		n = strlen( ones );
		while( list->next->n<n ){
			p = (struct list_node*)malloc( sizeof(struct list_node) );
			p->n = list->next->n+1;
			p->next = list->next;
			plu( list->next->numstr, list->next->next->numstr, p->numstr );
			list->next = p;
		}
	
		if( list->next->n==n ){
			printnum( list->next->numstr );
			continue;
		}

		for( p=list; p->next!=NULL&&p->next->n>n; p=p->next )
			;
		printnum( p->next->numstr );
	}
	return 0;
}


