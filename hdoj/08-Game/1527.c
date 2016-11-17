/*
 * 1527
 */

#include <math.h>
#include <stdio.h>

#include "common.h"

int main(void)
{
	int a, b, k;

	while (scanf("%d%d", &a, &b) == 2) {
		k = abv(a - b);
		if ((int)((double)k*(sqrt(5)+1)/2) == min(a, b))
			printf("0\n");
		else
			printf("1\n");
	}
	return 0;
}
