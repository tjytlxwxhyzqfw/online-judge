/*
** TLE
*/

#include <stdio.h>
#include <string.h>

#define DBG 0

#define RSBITS 10000

void
setnum( char *dst, char const *src ){
	int i, n = strlen(src);
	dst[n]=0;
	for( i=0; i<n; i++ )
		dst[i] = src[n-1-i];
}

void
printnum( const char *n ){
	int i, h;
	h = strlen(n)-1;
	for( i=h; i>=0 && n[i]=='0'; i-- )
		;
	if( i==-1 ){
		printf( "0\n" );
	}else{
		while( i>=0 ){
			printf( "%c", n[i--] );
		}
		printf( "\n" );
	}
}

void
mul( char const *x, char const *y, char *r ){
	int ny, nx, i, j, k, mr, pr, h=0;

	nx = strlen(x);
	ny = strlen(y);

	h = nx+ny;
	for( i=0; i<=h; i++ )
		r[i] = '0';
	r[h+1] = 0;


	for( j=0; j<ny; j++ ){
		for( i=0; i<nx; i++ ){
			mr = (x[i]-'0')*(y[j]-'0');
			pr = mr%10+r[i+j]-'0';
			r[i+j] = pr%10+'0';
			pr = pr/10+r[i+j+1]-'0'+mr/10;
			r[i+j+1] = pr%10+'0';
			k = 2;
			while( pr/10==1 ){
				pr = r[i+j+k]-'0'+pr/10;
				r[i+j+k++] = pr%10+'0';
			}
		}
	}

	for( i=h; i>0&&r[i]=='0'; i-- )
		;
	r[i+1] = 0;
}

void
plu( const char *x, const char *y, char *r ){
	int i, max, pr, nx, ny;
	
	nx = strlen(x)-1;
	ny = strlen(y)-1;

	setnum( r, "0" );
	
	for( pr=i=0; i<=nx&&i<=ny; i++ ){
		pr = x[i]-'0'+y[i]-'0'+pr;
		r[i] = pr%10+'0';
		pr /= 10;
	}	

	for( ; i<=nx; i++ ){
		pr = x[i]-'0'+pr;
		r[i] = pr%10+'0';
		pr /= 10;
	}
	for( ; i<=ny; i++ ){
		pr = y[i]-'0'+pr;
		r[i] = pr%10+'0';
		pr /= 10;
	}
	
	if( pr==0 ){
		r[i] = 0;
	}else{
		r[i] = '1';
		r[i+1] = 0;
	}	
}
	

void
jc( const char *n, char *r ){
	char t[RSBITS], a[RSBITS], b[RSBITS];
	setnum(r,"1");
	setnum(t,"0");
	if( !strcmp(n,t) ){
		return;
	}

	setnum(t,"1");
	do{
		strcpy(a,t);
		strcpy(b,r);
		mul(a,b,r);
		setnum(b,"1");
		plu(a,b,t);
	}while( strcmp(a,n) );
	
}


char le[RSBITS], ri[RSBITS], rs[RSBITS], tp[RSBITS], mr[RSBITS], nu[RSBITS];
char t[RSBITS],a[RSBITS],b[RSBITS];

char state[101][101][RSBITS];
	

int
main( void ){
	int m, n, i, j, c=0;

	while( scanf("%d%d",&m,&n)!=EOF && (m!=0||n!=0) ){
		for( i=0; i<=100; i++ ){
			for( j=0; j<=100; j++ ){
				setnum(state[i][j],"0");
			}
		}

		if( m<n ){
			printf( "Test #%d:\n", c++ );
			printnum( state[m][n] );
			continue;
		}
			
		for( i=0; i<=m; i++ ){
			sprintf( nu, "%d", i );
			setnum( le, nu );
			jc( le, rs );
			strcpy( state[i][0], rs );
		}
		for( j=0; j<=n; j++ ){
			setnum( state[0][j], "0" );
		}

		for( i=1; i<=m; i++ ){
			for( j=1; j<=i; j++ ){
				
				#if DBG
				printf( "i=%d,j=%d\n", i, j );	
				#endif
				
				strcpy( le, state[i-1][j] );
				sprintf( nu, "%d", i );
				setnum( ri, nu );
				mul( le, ri, mr );

				#if DBG
				printnum( le );
				printnum( ri );
				printf( "mul-le:" );
				printnum( mr );
				#endif
				
				strcpy( le, state[i][j-1] );
				sprintf( nu, "%d", j );
				setnum( ri, nu );
				mul( le, ri, rs );

				#if DBG
				printnum( le );
				printnum( ri );
				printf( "mul-ri:" );
				printnum( rs );
				#endif

				plu( mr, rs, state[i][j] );

				#if DBG
				printf( "plu@(%d,%d):", i, j );
				printnum( state[i][j] );
				#endif
			}
			if( i==m && j==n )
				break;
		}
		
		printf( "Test #%d:\n", ++c );
		printnum( state[m][n] );
	}		
	return 0;
}
			 
