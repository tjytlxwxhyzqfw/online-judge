#include <stdio.h>
#include <assert.h>

void
printnchar( char ch, int n ){
	int i;
	for( i=0; i<n; i++ ){
		printf( "%c", ch );
	}
}

void
print1( char ch, int a, int w ){
	printnchar( ' ', w );
	printnchar( ch, a );
}

void
print2( char ch, int a, int w ){
	printnchar( ch, w );
	printnchar( ' ', a );
	printnchar( ch, w );
}

int
main( void ){
	int ncas;
	int a, b, c, w, n, i;
	char ch;
	
	freopen( "Inputs/1256", "r", stdin );

	scanf( "%d%c", &ncas, &ch );
	assert( ch=='\n' );
	while( ncas-- ){
		scanf( "%c%d", &ch, &n );
		//printf( "ch=%c, n=%d\n", ch, n );
		w = 1+n/6;
		if( (n-3)%2 ){
			a = c = (n-3)/2+1;
			b = (n-3)/2;
		}else{
			a = b = c = (n-3)/2;
		}
		print1( ch, a, w );
		puts( "" );
		for( i=0; i<b; i++ ){
			print2( ch, a, w );
			puts( "" );
		}
		print1( ch, a, w );
		puts( "" );
		for( i=0; i<c; i++ ){
			print2( ch, a, w );
			puts( "" );
		}
		print1( ch, a, w );
		puts( "" );
		if( ncas!=0 ){
			puts( "" );
			scanf( "%c", &ch ); /* '\n' */
			assert( ch=='\n' );
		}
	}
	return 0;
}
