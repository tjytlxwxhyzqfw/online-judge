/*
 * 707C
 *
 * What a pity! I thought that all 2^n yeild a '-1', but in fact none of them did, except 2.
 * Because there is a 4 in '3 4 5'!!!!
 * 4 !!!!!!!
 */


#include <stdio.h>
#include <stdlib.h>

#include "common.h"

int main(void)
{
	long long x, y, z;
	long long i;

	scanf("%lld", &x);

	if (x == 1 || x == 2) {
		printf("-1\n");
		return 0;
	}

	if (x % 4 == 0) {
		y = (x / 4) * 3;
		z = (x / 4) * 5;
		printf("%lld %lld\n", y, z);
		return 0;
	}

	for (i = 0; i < 64; ++i) {
		if (x & ULL1)
			break;
		x >>= 1;
	}

	y = (x * x - 1) / 2;
	z = (x * x + 1) / 2;

	printf("%lld %lld\n", y << i, z << i);

	return 0;
}
