/*
** 正向分析:
** s(m,n,t)=s(m-1,n,t+1)+s(m,n-1,t-1)
** if t<0 return 0 else:
** m==0 return t与n可调度
** n==0 返回1
** 溢出,需要大数,或者用unsigned:额外的数组;
** WA 不知道是不是溢出 但是他们说时DP
*/

#include <stdio.h>

__int64 state[21][21][43];
int state_v[21][21][43];

__int64
get_from_state( int m, int n, int t ){
	__int64 r;
	__int64 le, ri;
	int lev, riv;
	/*printf( "state(%d,%d,%d)\n", m, n, t );*/
	if( t<0 ){
		r = 0;
	}else if( m==0 ){
		if( n>t ){
			r = 0;
		}else{
			r = 1;
		}
	}else if( n==0 ){
		r = 1;
	}else{
		lev = state_v[m-1][n][t+1];
		riv = (t==0?0:state_v[m][n-1][t-1]);
		le = (lev?state[m-1][n][t+1]:get_from_state(m-1,n,t+1));
		if( t==0 ){
			ri = get_from_state(m,n-1,t-1);
		}else{
			ri = (riv?state[m][n-1][t-1]:get_from_state(m,n-1,t-1));
		}
		r = le+ri;
	}
	/*printf( "state(%d,%d,%d)=%ld\n", m, n, t, r );*/
	state_v[m][n][t] = 1;
	return (state[m][n][t]=r);
}

int
main( void ){
	int m,n;
	int x,y,z;
	for(x=0;x<21;x++){
		for(y=0;y<21;y++){
			for(z=0;z<43;z++){
				state_v[x][y][z]=0;
			}
		}
	}
	while( scanf("%d%d",&m,&n)!=EOF ){
		printf( "%ld\n",get_from_state(m,n,0) );
	}
	return 0;
}
