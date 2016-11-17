
#include <stdio.h>
#include <string.h>

#define RSBITS 1001

char le[RSBITS], ri[RSBITS], rs[RSBITS], tp[RSBITS], mr[RSBITS], nu[RSBITS];
char t[RSBITS],a[RSBITS],b[RSBITS];


/*
** 使用一个显式字符串初始化目标变量
** 参数:dst 要被初始化的目标变量的地址,容量至少为RSBITS
** 参数:src 用字符串表示的数值,最高位下标位0,来自于"直接"的输入
*/
void
setnum( char *dst, char const *src ){
	int i, n = strlen(src);
	dst[RSBITS-1] = 0;
	for( i=RSBITS-2; i>=n; i-- ){
		dst[i] = '0';
	}
	
	while( i>=0 ){
		dst[i] = src[n-i-1];
		i -= 1;
	}
}
	
/*
** 将一个已经被格式化的数值字符串以符合阅读习惯的方式打印到标准输出,以换行符结束
*/
void
printnum( const char *n ){
	int i;
	for( i=RSBITS-2; i>=0 && n[i]=='0'; i-- )
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
	int i, max, pr;

	setnum( r, "0" );
	
	for( pr=i=0; i<RSBITS-1; i++ ){
		pr = x[i]-'0'+y[i]-'0'+pr;
		r[i] = pr%10+'0';
		pr /= 10;
	}
}	

int
main( void ){
	int c=0, n;
	scanf( "%d", &n );
	while( c!=n ){
		scanf( "%s%s", a, b );
		setnum( le, a );
		setnum( ri, b );
		plu( le, ri, rs );
		printf( "Case %d:\n", ++c );
		printf( "%s + %s = ", a, b );
		printnum( rs );
		if( c!=n )
			puts( "" );
	}
	return 0;
}
