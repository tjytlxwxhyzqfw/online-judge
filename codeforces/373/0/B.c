/*
 * B
 */

#include <stdio.h>
#include <string.h>

#include "common.h"

#define N 100000

int n;
char a[N+1], b[2];
char c[N+1];

int test(void)
{
	int i, nr, nb;

	memset(c, 0, sizeof(c));
	forn(i, n) if (a[i] != b[i%2]) c[i] = b[i%2];

	//forn(i, n) printf("%c", a[i]);
	//puts("");
	//forn(i, n) printf("%c", c[i] ? c[i] : '0');
	//puts("");

	nr = nb = 0;
	forn(i, n) {
		if (c[i] == 'r')
			++nr;
		else if (c[i] == 'b')
			++nb;
	}

	return min(nr, nb) + abv(nr-nb);
}

int main(void)
{	
	int res1, res2;

	//freopen("Inputs/B", "r", stdin);

	scanf("%d", &n);
	scanf("%s", a);

	b[0] = 'r', b[1] = 'b';
	res1 = test();

	b[0] = 'b', b[1] = 'r';
	res2 = test();

	//printf("res1: %6d\nres2: %6d\n", res1, res2);

	printf("%d\n", min(res1, res2));

	return 0;
}
