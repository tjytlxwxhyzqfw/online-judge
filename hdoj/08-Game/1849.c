/*
 * 1849 Rabbit and Grass
 *
 * Just submit
 *
 * SG
 */

#include <stdio.h>

int main(void)
{
	int m, h, result;
	int i;

	while (scanf("%d", &m) == 1) {
		if (m == 0)
			break;

		result = 0;
		for (i = 0; i < m; ++i) {
			scanf("%d", &h);
			result ^= h;
		}

		printf("%s Win!\n", result ? "Rabbit" : "Grass");
	}

	return 0;
}
