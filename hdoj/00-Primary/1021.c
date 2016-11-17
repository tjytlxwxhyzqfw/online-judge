/*
 * 1021
 * 被秀智商了
 */

#include <stdio.h>
#include "numth.h"

int f(n)
{
	int x, y, z;
	int i;

	x = 1;
	y = 2;
	
	if (n == 0 || n == 1)
		return 1;

	for (i = 2; i <= n; ++i) {
		x %= 3;
		y %= 3;
		z = y;
		y = x + y;
		x = z;
	}

	return y % 3;
}

int trival(void)
{
	int n;

	while (scanf("%d", &n) != EOF)
		printf(f(n) ? "no\n" : "yes\n");
	
	return 0;
}
