#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define RAW 0
#define STD 1

char dic[2][100][11], *sortbuf[100];

int
qcmp( const void *x, const void *y ){
	return *((char*)x)-*((char*)y);
}

/*
** element: char *
*/
int
qcmp_str( const void *x, const void *y ){
	return strcmp( *(char**)x, *(char**)y );
}

int
main( void ){
	int i, j, count;
	char word[11];
	
	freopen( "Inputs/1113", "r", stdin );

	i = 0;
	while( scanf("%s",word)!=EOF && strcmp("XXXXXX",word) ){
		strcpy( dic[RAW][i], word );
		qsort( word, strlen(word), sizeof(char), qcmp );
		strcpy( dic[STD][i++], word );
	}
	while( scanf("%s",word)!=EOF && strcmp("XXXXXX",word) ){
		qsort(word,strlen(word),sizeof(char),qcmp);
		for( count=j=0; j<i; j++ ){
			if( !strcmp(word, dic[STD][j]) ){
				sortbuf[count++]=dic[RAW][j];
			}
		}
		if( !count ){
			printf( "NOT A VALID WORD\n" );
		}else{
			qsort( sortbuf, count, sizeof(char*), qcmp_str );
			for( j=0; j<count; j++ ){
				puts( sortbuf[j] );
			}
		}
		printf("******\n");
	}
	return 0;
}
