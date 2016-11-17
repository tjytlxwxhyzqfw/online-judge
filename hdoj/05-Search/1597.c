/*
 * 1597 find the nth digit
 *
 * NA: S->H
 * 经验很多,见纸
 */

#include <stdio.h>
#include <stdlib.h>

int find_nth(int n)
{
	return (--n) % 9 + 1;
}
	

int main(void)
{
	unsigned long i, s, n, ncas, res;

	scanf("%ld", &ncas);
	while (ncas--) {
		scanf("%ld", &n);
		for (i = 1; n > i; ++i)
			n -= i;
		//printf("i = %d\n", i);
		//sleep(1);
		printf("%d\n", find_nth(n));
	}

	return 0;
}
