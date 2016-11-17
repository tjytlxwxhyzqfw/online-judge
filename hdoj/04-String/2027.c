#include <stdio.h>
#include <string.h>

int
main( void ){
	int ncas;
	int num[5], i, len;
	char ibuf[101];

	freopen( "Inputs/2027", "r", stdin );

	scanf( "%d\n", &ncas );
	while( ncas-- ){
		fgets(ibuf,101,stdin);
		len = strlen( ibuf );
		for( i=0; i<5; i++ ){
			num[i] = 0;
		}
		for( i=0; i<len; i++ ){
			switch( ibuf[i] ){
			case 'a':
				num[0] += 1;
				break;
			case 'e':
				num[1] += 1;
				break;
			case 'i':
				num[2] += 1;
				break;
			case 'o':
				num[3] += 1;
				break;
			case 'u':
				num[4] += 1;
				break;
			}
		}
		printf( "a:%d\ne:%d\ni:%d\no:%d\nu:%d\n%s",num[0],num[1],num[2],num[3],num[4], ncas==0?"":"\n");
	}
	return 0;
}	
