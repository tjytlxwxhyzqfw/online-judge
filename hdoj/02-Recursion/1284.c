/*
 * 1284 钱币兑换问题
 *
 * I thought this is a difficult dp problem, 
 * But, in fact, this is a primary problem.
 *
 */
#include <stdio.h>

int main(void)
{
	int coins, total, threes, i, remainder;
	int ways;

	while (scanf("%d", &total) == 1) {
		threes = total / 3;
		ways = 0;
		for (i = 0; i <= threes; ++i) {
			remainder = total - 3 * i;
			ways += remainder / 2 + 1;
		}

		printf("%d\n", ways);
	}

	return 0;
}
