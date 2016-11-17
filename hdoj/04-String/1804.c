#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define CMAX 20
#define QMAX 100
#define WMAX 21

char dic[CMAX][2][WMAX];

int
main( void ){
	int ncas, nque, i;
	int wlen;
	char word[WMAX];

	freopen( "Inputs/1804", "r", stdin );

	scanf( "%d%d",&ncas,&nque );
	for( i=0; i<ncas; i++ ){
		scanf( "%s%s", dic[i][0], dic[i][1] );
	}

	while( nque-- ){
		scanf( "%s", word );
		for( i=0; i<ncas; i++ ){
			if( !strcmp(dic[i][0],word) )
				break;
		}
		if( i<ncas ){
			printf( "%s\n", dic[i][1] );
			continue;
		}
		wlen = strlen(word);
		if( word[wlen-1]=='y' ){
			if( word[wlen-2]!='a' && word[wlen-2]!='e' && word[wlen-2]!='i'
					&& word[wlen-2]!='o' && word[wlen-2]!='u' ){
				word[wlen-1] = 0;
				printf( "%sies\n", word );
				continue;
			}
		}
		//"o", "s", "ch", "sh" or "x"
		if( word[wlen-1]=='o' || word[wlen-1]=='s' || word[wlen-1]=='x'
				|| word[wlen-1]=='h' && (word[wlen-2]=='c'||word[wlen-2]=='s') ){
			printf( "%ses\n", word );
		}else{
			printf( "%ss\n", word );
		}
	}
	return 0;
}




