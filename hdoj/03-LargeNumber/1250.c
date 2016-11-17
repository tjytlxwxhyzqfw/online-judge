#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define RSBITS 2006

void
setnum( char *dst, char const *src ){
	int i, n = strlen(src);
	dst[n]=0;
	for( i=0; i<n; i++ )
		dst[i] = src[n-1-i];
}
	
/*
** 将一个已经被格式化的数值字符串以符合阅读习惯的方式打印到标准输出,以换行符结束
*/
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

/*
** 加: r=x+y
** 当心: 不能用此函数计算减法
*/
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

typedef struct _list_node{
	char sum[RSBITS];
	int n;
	struct _list_node *next;
} list_node;



int
main( void ){
	list_node lsn;
	list_node *ls = &lsn, *tmp;
	char w[RSBITS], x[RSBITS], y[RSBITS], z[RSBITS];
	int n;

	freopen( "Inputs/1250", "r", stdin );

	ls->next = (list_node*)malloc( sizeof(list_node) );
	setnum( ls->next->sum, "1" );
	ls->next->n = 4;

	ls->next->next = (list_node*)malloc( sizeof(list_node) );
	setnum( ls->next->next->sum, "1" );
	ls->next->next->n = 3;

	ls->next->next->next = (list_node*)malloc( sizeof(list_node) );
	setnum( ls->next->next->next->sum, "1" );
	ls->next->next->next->n = 2;

	ls->next->next->next->next = (list_node*)malloc( sizeof(list_node) );
	setnum( ls->next->next->next->next->sum, "1" );
	ls->next->next->next->next->n = 1;
	ls->next->next->next->next->next = NULL;
	

	while( scanf("%d",&n)!=EOF ){
		//printf( "ls->next->n=%d, n=%d\n", ls->next->n, n );
		while( ls->next->n<n ){
			tmp = (list_node*)malloc( sizeof(list_node) );
			plu( ls->next->sum, ls->next->next->sum, x );
			plu( ls->next->next->next->sum, ls->next->next->next->next->sum, y );
			plu( x, y, tmp->sum );

			tmp->n = ls->next->n+1;
			tmp->next = ls->next;
			ls->next = tmp;
		}
	
		tmp = ls;
		while( tmp->next->n>n ){
			tmp = tmp->next;
		}

		printnum( tmp->next->sum );
	}
	return 0;
}

	

	
