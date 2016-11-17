/*
 * 2176 取(m堆)石子游戏
 *
 * Just submit
 */

#include <stdio.h>

int nheaps, heaps[200000];
int xorall;

int init_a_case(void)
{
	int i;

	scanf("%d", &nheaps);
	if (nheaps == 0)
		return 0;

	xorall = 0;
	for (i = 0; i < nheaps; ++i) {
		scanf("%d", heaps + i);
		xorall ^= heaps[i];
	}

	return 1;
}

int main(void)
{
	int other;
	int i;

	while (init_a_case()) {
		if (xorall == 0) {
			printf("No\n");
			continue;
		}
		printf("Yes\n");
		for (i = 0; i < nheaps; ++i) {
			other = xorall ^ heaps[i];
			if (heaps[i] > other)
				printf("%d %d\n", heaps[i], other);
		}
	}

	return 0;
}
			
