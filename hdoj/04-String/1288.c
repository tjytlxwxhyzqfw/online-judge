/*
** 1000 500 10 1000
** 100 100 0 10
** 20 15 1 2
*/

#include <stdio.h>

int
main( void ){
	int tmax, xmax, ymax, zmax, t, x, y, z, lastx, lasty, lastz;
	while( scanf( "%d%d%d%d",&tmax,&xmax,&ymax,&zmax)!=EOF ){
		if( tmax==0&&xmax==0&&ymax==0&&zmax==0 ){
			break;
		}
		t = 0;
		for( z=0; z<zmax; z+=1 ){
			if( (t+=10)>tmax ){
				t -= 10;
				break;
			}
		}
		for( y=0; y<ymax; y+=1 ){
			if( (t+=5)>tmax ){
				t -= 5;
				break;
			}
		}
		for( x=0; x<xmax; x+=1 ){
			if( (t+=1)>tmax ){
				t-=1;
				break;
			}
		}
		if( t<tmax ){
			printf( "Hat cannot buy tea.\n" );
			continue;
		}
		/*printf( "%d*10+%d*5+%d\n", z, y, x );*/
	goon:
		lastx = x;
		lasty = y;
		lastz = z;
		while( z-1>=0 && x+10<=xmax ){
			z -= 1;
			x += 10;
		}
		while( z-1>=0 && y+1<=ymax && x+5<=xmax ){
			z -= 1;
			y += 1;
			x += 5;
		}
		while( z-1>=0 && y+2<=ymax ){
			/*printf( "z=%d, y=%d\n", z, y );*/
			z -= 1;
			y += 2;
		}
		while( y-1>=0 && x+5<=xmax ){
			y -= 1;
			x += 5;
		}
		if( !(lastx==x && lasty==y && lastz==z) )
			goto goon;
		
		printf( "%d YiJiao, %d WuJiao, and %d ShiJiao\n", x, y, z );
	}
	return 0;
}
		
