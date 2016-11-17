#include <ctype.h>
#include <stdio.h>
/*
** 这是不靠谱的想法:scanf( "%*s%*c" )这种方式取完剩下的一行
*/

int
main( void ){
	int ncas;
	int i;
	int legal, ch;

	freopen( "Inputs/2024", "r", stdin );

	scanf( "%d%*c", &ncas );
	while( ncas-- ){
		legal = 1;
		i = 0;
		while( (ch=getchar())!=EOF ){
			printf( "ch=%c(%d)\n", ch, ch );
			if( ch=='\n' ){
				if( i==0 ){
					legal = 0;
				}
				break;
			}else if( i++==0&&!(ch=='_'||isalpha(ch)) ){
				legal = 0;
				break;
			}else if( !(ch=='_'||isalpha(ch)||isdigit(ch)) ){
				legal = 0;
				break;
			}
		}
		printf( "%s\n", legal==0?"no":"yes" );
		if( ch=='\n' ){
			continue;
		}
		while( (ch=getchar())!='\n' ){
			if( ch==EOF ){
				break;
			}
		}
	}
	return 0;
}
