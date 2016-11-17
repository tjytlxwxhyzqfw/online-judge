#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define BITS 1000

int
plu( const char *x, const char *y, char *r, int pr ){
	int nx, ny, i;

	nx = strlen(x);
	ny = strlen(y);

	for( i=0; i<nx&&i<ny; i++ ){
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

	r[i] = pr+48;
	if( r[i]=='0' ){
		return r[i]=0;
	}else{
		r[i+1] = 0;
		return 1;
	}
}

void
reverse( char *buf ){
	char tmp;
	int n = strlen( buf ), i;

	for( i=0; i<n/2; i++ ){
		tmp = buf[i];
		buf[i] = buf[n-1-i];
		buf[n-1-i] = tmp;
	}
}

void
printinrev( const char *numstr, int eol ){
	int n = strlen(numstr), i;
	
	for( i=n-1; i>=0; i-- )
		putchar( numstr[i] );
	if( eol == 1 )
		puts( "" );
}

int
main( void ){
	char *ll, *lr, *rl, *rr, *point;
	char xa[BITS], ya[BITS], ra[BITS];
	char xb[BITS], yb[BITS], rb[BITS];
	char right[BITS], left[BITS];
	int i, j, nlr, nrr, pr;

	while( scanf("%s%s",left,right)!=EOF ){
		point = strstr(left,".");
		ll = left;
		if( point!=NULL ){
			*point = 0;
			lr = point+1;
		}else{
			lr = "";
		}
		
		point = strstr(right,".");
		rl = right;
		if( point!= NULL ){
			*point = 0;
			rr = point+1;
		}else{
			rr = "";
		}

		strcpy( xa, ll );
		strcpy( ya, rl );
		reverse( xa );
		reverse( ya );

		nlr = strlen( lr );
		nrr = strlen( rr );
		for( i=0; i<nlr&&i<nrr; i++ ){
			xb[i] = lr[i];
			yb[i] = rr[i];
		}
		for( ; i<nlr; i++ ){
			xb[i] = lr[i];
			yb[i] = '0';
		}
		for( ; i<nrr; i++ ){
			xb[i] = '0';
			yb[i] = rr[i];
		}
		xb[i] = yb[i] = 0;
		reverse( xb );
		reverse( yb );

		pr = plu( xb, yb, rb, 0 );
		rb[strlen(xb)] = 0;
		plu( xa, ya, ra, pr );
		
		#if 0
		printf( "xa, ya\n" );
		printinrev( xa );
		printinrev( ya );
		#endif

		for( i=strlen(ra)-1; i>=0&&ra[i]=='0'; i-- )
			;
		if( i==-1 ){
			printf( "0" );
		}else{
			for( ; i>=0; i-- ){
				putchar( ra[i] );
			}
		}

		for( j=0; j<strlen(rb)&&rb[j]=='0'; j++ )
			;
		if( j!=strlen(rb) ){
			printf( "." );
			for( i=strlen(rb)-1; i>=j; i-- ){
				putchar( rb[i] );
			}
		}
		puts( "" );
	}
	return 0;
}
