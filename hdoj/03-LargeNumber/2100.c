#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define BITS 201

void
reverse( char *num ){
	int i,n=strlen(num);
	char tc;

	for( i=0; i<n/2; i++ ){
		tc = num[i];
		num[i] = num[n-1-i];
		num[n-1-i] = tc;
	}
}

int
main( void ){
	char x[BITS],y[BITS],r[BITS];
	int i, nx, ny, carry;

	while(scanf("%s%s",x,y)!=EOF){
		reverse(x);
		reverse(y);
		
		nx=strlen(x);
		ny=strlen(y);

		for(carry=i=0;i<nx&&i<ny;i++){
			carry = x[i]+y[i]+carry-130;
			r[i] = carry%26+65;
			carry /= 26;
		}
		for(;i<nx;i++){
			carry = x[i]+carry-65;
			r[i] = carry%26+65;
			carry /= 26;
		}
		for(;i<ny;i++){
			carry = y[i]+carry-65;
			r[i] = carry%26+65;
			carry /= 26;
		}
		r[i] = carry+65;
		r[i+1] = 0;

		for(i=strlen(r)-1;i>0&&r[i]==65;i--)
			;
		for(;i>=0;i--)
			putchar(r[i]);
		putchar('\n');
	}
	return 0;
}
			
