#include <stdio.h>
#include <string.h>

#define BITS 50

void
printlnum( char *num ){
	int i;
	for( i=strlen(num)-1; i>=0; i-- ){
		printf( "%c", num[i] );
	}
	puts( "" );
}


void
plu( const char *x, const char *y, char *r ){
	int nx, ny, i;
	int pr;

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
	// printlnum( r );
}

void
pow2( int n, char *r ){
	char tmp[BITS];

	strcpy( r, "1" );
	while( n-- ){
		plu( r, r, tmp );
		strcpy( r, tmp );
	}
}

int
main( void ){
	int ncas, n, k;
	char res[BITS];

	scanf( "%d",&ncas );
	while( ncas-- ){
		scanf( "%d%d", &n, &k );
		pow2( n-k, res );
		printlnum( res );
	}
	return 0;
}


