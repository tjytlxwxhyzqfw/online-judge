/*
** 没有提交, 此程序可运行,但一定超时
*/

#include <stdio.h>
#include <string.h>

#define RSBITS 40000
#define LVA (RSBITS-2)
#define END (RSBITS-1)
#define BITS ((int)(RSBITS/3+1)*10)

char le[RSBITS], ri[RSBITS], rs[RSBITS], t1[RSBITS], t2[RSBITS], t3[RSBITS];

char pow2table[BITS][RSBITS];

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
pow2( int n, char *r ){
	int i;
	char a[RSBITS];

	if( pow2table[n][0]!=0 ){
		strcpy( r, pow2table[n] );
		return;
	}

	setnum( r, "1" );
	for( i=1; i<=n; i++ ){
		strcpy( a, r );
		plu( a, a, r );
		strcpy( pow2table[i], r );
	}
}

int
compare( const char *x, const char *y ){
	int nx = strlen(x), ny = strlen(y), i;
	if( nx>ny )
		return 1;
	else if( nx<ny )
		return -1;

	for( i=nx-1; i>=0; i-- ){
		if( x[i] < y[i] )
			return -1;
		else if( x[i]>y[i] )
			return 1;
	}
	return 0;
}

int
tobi( const char *x, char *r ){
	int h = (strlen(x)/3+1)*10, i;
	char po[RSBITS], tmp[RSBITS], sum[RSBITS];
	setnum( sum, "0" );
	for( i=h; i>=0; i-- ){
		pow2( i, po );
		strcpy( tmp, sum );
		plu( tmp, po, sum );
		if( compare( sum, x ) <= 0 ){
			r[i] = 1;
		}else{
			r[i] = 0;
			strcpy( sum, tmp );
		}
	}
	return h;
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

void
qmul( const char *x, const char *y, char *r ){
	char sum[RSBITS], tmp[RSBITS], a[RSBITS], b[RSBITS], bin[BITS];
	int c = compare(x,y), n, i;
	const char *t;

	if( c<0 ){
		t = x;
		x = y;
		y = t;
	}
	n = tobi( y, bin );
	setnum( r, "0" );
	strcpy( sum, x );
	for( i=0; i<=n; i++ ){
		if( bin[i] ){
			strcpy( tmp, r );
			plu( sum, tmp, r );
		}
		strcpy( a, sum );
		strcpy( b, sum );
		plu( a, b, sum );
	}
}

void
qjc( const char *n, char *r ){
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
		qmul(a,b,r);
		setnum(b,"1");
		plu(a,b,t);
	}while( strcmp(a,n) );
	
}
	
int
main( void ){
	int n;
	char t[RSBITS], a[RSBITS], b[RSBITS];


	while( scanf("%s",t)!=-1 ){
		setnum( le, t );
	
		scanf( "%d", &n );
		
		if( n==1 ){
			qjc( le, t );
			printnum( t );
		}else{
			jc( le, rs );
			printnum( rs );
		}

	}
		
	return 0;
}
			 
