/*
******错误1******
** 找了一个小时的错,发现使用%.2f输出整形!
**
******错误2******
**外面的循环可以不初始化le,但是里面的循环却不能不初始化ri!
tmin = dist( p, lp=b, rp=m+1 );
	for( ; le <= m; le++ ){
		for( ; ri>=m+1; ri-- ){
			if( abs(p[le].y-p[ri].y)<=min && (d=dist(p,le,ri))<tmin ){
				tmin = d;
				lp = le;
				rp = ri;
			}
		}
	}
** 真的是尽力了,超时没办法
** 改用堆排序!........也不过.......
** 按照y方向排序
******
*/

#include <math.h>
#include <stdio.h>
#include <stdlib.h>

#define DBG 0

typedef struct _Point{
	double x, y;
}Point;
Point points_repo[100000], *heap[100001];

double
dist( Point *p[], int le, int ri ){
	return pow(p[le]->x-p[ri]->x,2) + pow(p[le]->y-p[ri]->y,2);
}

int heapsize;

void
topdown( int p ){
	int c;
	Point *tmp;
	double max;

	while( 1 ){
		max = heap[p]->y;
		if( p<<1<=heapsize && heap[p<<1]->y>max )
			max = heap[c=p<<1]->y;
		if( (p<<1)+1<=heapsize && heap[(p<<1)+1]->y>max )
			max = heap[c=(p<<1)+1]->y;
		if( max==heap[p]->y )
			break;

		tmp = heap[p];
		heap[p] = heap[c];
		heap[c] = tmp;

		p = c;
	}
}

void
hsort(){
	Point *tmp;
	int i;

	for( i=heapsize>>1; i>0; i-- )
		topdown( i );

	while( heapsize>1 ){
		tmp = heap[1];
		heap[1] = heap[heapsize];
		heap[heapsize--] = tmp;
		topdown( 1 );
	}
}
		

double
fmd( Point *p[], int b, int e ){
	int m, le, ri, lp, rp, ribase;
	double lmin, rmin, tmin ,min, realmin, d, midline;

	if( e-b == 1 )
		return dist( p, b, e );
	else if( e-b == 2 ){
		lmin = dist( p, b, b+1 );
		rmin = dist( p, b+1, e );
		tmin = dist( p, b, e );
		return lmin<rmin ? (lmin<tmin?lmin:tmin) : (rmin<tmin?rmin:tmin);
	}else if( b>= e )
		return -1;

	m = (b+e)/2;

	lmin = fmd( p, b, m );
	rmin = fmd( p, m+1, e );

	min = (lmin<rmin?lmin:rmin);

	midline = (p[m]->y+p[m+1]->y)/2;
	realmin = sqrt(min)/2;

	for( le=b; le<=m && (midline-p[le]->y>realmin); le++ )
		;
	for( ri=e; ri>=m+1 && (p[ri]->y-midline>realmin); ri-- )
		;
	ribase = ri;
	

	tmin = dist( p, lp=b, rp=m+1 );
	for( ; le <= m; le++ ){
		for( ri=ribase; ri>=m+1; ri-- ){
			if( abs(p[le]->x-p[ri]->x)<=realmin && (d=dist(p,le,ri))<tmin ){
				tmin = d;
				lp = le;
				rp = ri;
			}
		}
	}

	return min<tmin ? min : tmin;
}

int
main( void ){
	int n, i, j, k;
	
	freopen( "Inputs/1007", "r", stdin );

	while( scanf("%d",&n) && n>=2 ){
		for( i = 0; i < n; i++ ){
			heap[i+1] = points_repo+i;
			scanf( "%lf%lf", &heap[i+1]->x, &heap[i+1]->y );
		}
		heap[0] = NULL;
		heapsize=n;

		hsort();

		printf( "%.2f\n", sqrt(fmd(heap,1,n))/2 );
	}
	return 0;
}

	
