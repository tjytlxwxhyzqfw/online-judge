#include <stdio.h>
#include <string.h>
#include <ctype.h>

#define MAX 81

int count, ch;
char word[81];
int pos;

/*
** In any case of trying to print a word,
** this function would be invoked
*/
void
safe_print( void ){
	if( pos==0 ){
		goto out;
	}
	word[pos] = 0;
	if( count==0 ){
		printf("%s",word);
		count = pos;
	}else if( count+1+pos<MAX ){
		printf( " %s", word );
		count += 1+pos;
	}else{
		printf( "\n%s", word );
		count = pos;
	}
	word[pos=0] = 0;
out:
	if( ch==EOF ){
		puts( "" );
	}
}


int
main( void ){
	int i;

	freopen( "Inputs/1088", "r", stdin );

	while( (ch=getchar())!=EOF ){
		if( ch=='<' ){
			safe_print();
			word[0] = ch;
			for( pos=1; pos<4; pos++ ){
				word[pos] = getchar();
			}
			if( word[1]=='b' ){
				printf( "\n" );
				count = 0;
			}else{
				if( count!= 0 ){
					printf( "\n" );
					count = 0;
				}
				for( i=0; i<80; i++ ){
					printf( "%c", '-' );
				}
				puts( "" );
			}
			word[pos=0] = 0;
		}else if( ispunct(ch)||isalpha(ch)||isdigit(ch) ){
			word[pos++] = ch;
		}else{
			safe_print();
		}
	}
	safe_print();
	return 0;
}
