#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define SCHMAX 5
#define STRMAX 80

int count[SCHMAX];

int
main( void ){
	char search_list[SCHMAX+1], string[STRMAX+1], ch;
	int i, j, str_len, sch_len;

	freopen( "Inputs/1860", "r", stdin );

	while( (ch=getchar())!='#' ){
		ungetc( ch, stdin );
		for( i=0; i<SCHMAX; i++ ){
			count[i] = 0;
		}
		fgets( search_list, SCHMAX+1, stdin );
		sch_len = strlen( search_list );
		if( search_list[sch_len-1]=='\n' ){
			search_list[--sch_len] = 0;
		}
		/*printf( "search_list:%s\n", search_list );*/
		fgets( string, STRMAX+1, stdin );
		str_len = strlen( string );
		if( string[str_len-1] == '\n' ){
			string[--str_len] = 0;
		}
		/*printf( "string:%s\n", string );*/

		for( i=0; i<str_len; i++ ){
			for( j=0; j<sch_len; j++ ){
				if( string[i]==search_list[j] ){
					count[j] += 1;
				}
			}
		}
		for( i=0; i<sch_len; i++ ){
			printf( "%c %d\n", search_list[i], count[i] );
		}
	}
	return 0;
}
		
