/*
 * 1019
 */

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

int lcm(int x, int y)
{
	int i, z;

	if (y > x) {
		z = x;
		x = y;
		y = z;
	}

	for (i = 1; ; ++i) {
		z = x * i;
		if (1.0 * z / y == z / y)
			return z;
	}
}

int main(void)
{
	int ncas, x, y, i, j, m;

	scanf("%d", &ncas);
	while (ncas--) {
		scanf("%d", &m);
		x = 1;
		for (i = 0; i < m; ++i) {
			scanf("%d", &y);
			x = lcm(x, y);
			printf("x = %d\n", x);
		}
		printf("%d\n", x);
	}
	
	return 0;
}

			
