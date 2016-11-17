#include <stdio.h>
#include <string.h>

#define MXCH 5100

char ans[MXCH], sub[MXCH];

int
isspace2( int ch ){
	return ch=='\n' || ch=='\t' || ch==' ';
}

void
filter(){
	int ch;
	while( isspace(ch=getchar()) )
		;
	ungetc(ch,stdin);
}

int
main( void ){
	int ch;
	int ncas, i, j;

	freopen( "Inputs/1073", "r", stdin );

	scanf("%d",&ncas);
	while( ncas-->0 ){
		i = 0;
		filter();
		while( (ch=getchar())!=EOF ){
			ans[i++] = ch;
			if( i>=5 && ans[i-5]=='\n' && ans[i-4]=='E' && ans[i-3]=='N' && ans[i-2]=='D' && ans[i-1]=='\n' ){
				ans[i-1] = 0;
				break;
			}
		}
		i = 0;
		filter();
		while( (ch=getchar())!=EOF ){
			sub[i++] = ch;
			if( i>=5 && sub[i-5]=='\n' && sub[i-4]=='E' && sub[i-3]=='N' && sub[i-2]=='D' && sub[i-1]=='\n' ){
				sub[i-1] = 0;
				break;
			}
		}
		if( !strcmp(ans,sub) ){
			puts( "Accepted" );
			/*puts( ans );*/
			/*puts( sub );*/
		}else{
			for( i=j=0; j<=strlen(ans); j++ ){
				if( !isspace2(ans[j]) ){
					ans[i++]=ans[j];
				}
			}
			for( i=j=0; j<=strlen(sub); j++ ){
				if( !isspace2(sub[j]) ){
					sub[i++]=sub[j];
				}
			}
			if( !strcmp(ans,sub) ){
				puts( "Presentation Error" );
				/*puts( ans );*/
				/*puts( sub );*/
			}else{
				puts( "Wrong Answer" );
				/*puts( ans );*/
				/*puts( sub );*/
			}
		}
	}
	return 0;
}
