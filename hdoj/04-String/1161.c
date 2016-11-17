#include <stdio.h>
#include <ctype.h>

int
main( void ){
	int ch;

	while((ch=getchar())!=EOF)
		putchar(tolower(ch));
	return 0;
}
