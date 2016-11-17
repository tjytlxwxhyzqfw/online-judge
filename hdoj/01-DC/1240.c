/*
** 超时
** 试着用堆来优化一下
** 不要轻易更改地基性的数据结构!!
** 莫名奇妙的错误:
...
enteroutreport:(8,2,0) is adjcent to (7,2,0) type:1
enteroutreport:(8,2,0) is adjcent to (8,3,0) type:2
enteroutreport:(8,2,0) is adjcent to (8,1,0) type:3
Segmentation fault

** 1. 测试的时候,要刷新输出!!!!!!!!!
** 2. 我!!!!!!!用!!!!!!!!!!!char!!!!!!!!!!!!!声明!!!!!!!!!!!!!!!!!!!了!!!!!!!!!!!!!!!!!!!space[10][10][10]!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

试着提交这个版本的精简版

*/
#include <stdio.h>
#include <limits.h>
#include <ctype.h>

typedef struct _Point Point;

struct _Point{
	int x,y,z;
	int dist;
	int ingroup;
	int reachable;
	Point *adj[6];
	Point *pre;
};

int xx[] = { 1, -1, 0, 0, 0, 0 };
int yy[] = { 0, 0, 1, -1, 0, 0 };
int zz[] = { 0, 0, 0, 0, 1, -1 };

int space[10][10][10];
int scale;

Point points[1000];

Point *
getpointer( int x, int y, int z ){
	if( x<0 || x>scale-1 || y<0 || y>scale-1 || z<0 || z>scale-1 )
		return NULL;
	return (points+space[x][y][z]);
}

Point *heap[1001];
int heapsize;

void
topdown( int p ){
	int c, min;
	Point *tmp;
	while( 1 ){
		min = heap[p]->dist;
		if( p<<1<=heapsize && heap[p<<1]->dist<min )
			min = heap[c=p<<1]->dist;
		if( (p<<1)+1<=heapsize && heap[(p<<1)+1]->dist<min )
			min = heap[c=(p<<1)+1]->dist;
		if( min==heap[p]->dist )
			break;
		
		tmp = heap[p];
		heap[p] = heap[c];
		heap[c] = tmp;

		p=c;
	}
}

void
buildheap( void ){
	int i;
	for( i=heapsize>>1; i!=0; i-- )
		topdown( i );
}

Point *
deletemin( void ){
	Point *r = heap[1];
	heap[1] = heap[heapsize--];
	return r;
}

int
countstep( Point *s, Point *t ){
	int i, j;
	Point *p;

	for( i=0; i<heapsize; i++ ){
		if( points[i].x==s->x && points[i].y==s->y && points[i].z==s->z )
			s = points+i;
		if( points[i].x==t->x && points[i].y==t->y && points[i].z==t->z )
			t = points+i;
	}
	
	s->dist = 0;

	do{
		buildheap();
		p = deletemin();

		if( p->dist == INT_MAX )
			break;

		p->ingroup = 1;
		if( p == t )
			break;

		for( i=0; i<6; i++ ){
			if( p->adj[i]!=NULL && p->adj[i]->reachable && p->dist+1<p->adj[i]->dist ){
				p->adj[i]->dist = p->dist+1;
				p->adj[i]->pre = p;
			}
		}
	}while( heapsize != 0 );

	return t->dist;
}

int
main( void ){
	char cmd[10], ch;
	int x, y, z, i, j, n;
	Point *p, s, t;

	freopen( "Inputs/1240", "r", stdin );

	while( scanf("%s%d",cmd,&scale) && strcmp(cmd,"wcc") ){
		i = 0;
		for( z=0; z<scale; z++ ){
			for( y=0; y<scale; y++ ){
				for( x=0; x<scale; x++ ){
					p = points+i;
					space[x][y][z] = i;
					i += 1;
					heap[i] = p;
					do{
						scanf( "%c", &ch );
					}while( isspace(ch) );
					p->x = x;
					p->y = y;
					p->z = z;
					p->dist = INT_MAX;
					p->ingroup = 0;
					p->reachable = ch=='O';
					for( j=0; j<6; j++ )
						p->adj[j] = NULL;
					p->pre = NULL;
				}
			}
		}

		heapsize = n = i;
		for( i=0; i<n; i++ ){
			if( !points[i].reachable )
				continue;
			for( j=0; j<6; j++ ){
				points[i].adj[j] = getpointer( points[i].x+xx[j], points[i].y+yy[j], points[i].z+zz[j] );
			}
		}

		scanf( "%d%d%d%d%d%d", &s.x, &s.y, &s.z, &t.x, &t.y, &t.z );		
		scanf( "%s", cmd );

		i = countstep( &s, &t );
		if( i != INT_MAX )
			printf( "%d %d\n", scale, i );
		else
			printf( "NO ROUTE\n" );
	}
	return 0;
}
					
				
