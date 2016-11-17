/*
 * A Mathematical Curiosity
 */

#include <stdio.h>
#include <stdlib.h>

int main(void)
{
	int m, n, ncas, i, count, k;
	int a, b;
	float f;

	freopen("Inputs/1017", "r", stdin);

	scanf("%d", &ncas);
	while (ncas--) {
		k = 0;
		while (1) {
			count = 0;
			scanf("%d%d", &n, &m);
			if (!m && !n) {
				if (ncas)
					printf("\n");
				break;
			}
			for (a = 1; a < n; ++a) {
				for (b = a + 1; b < n; ++b) {
					f = 1.0 * (a * a + b * b + m) / (a * b);
					i = (a * a + b * b + m) / (a * b);
					if (i == f)
						++count;
					//printf("f = %g, i = %d, count = %d\n",
						 	//f, i, count);
				}
			}
			printf("Case %d: %d\n", ++k, count);
		}
	}
	return 0;
}
