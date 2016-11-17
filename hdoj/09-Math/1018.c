/*
 * 1018
 *
 * Stirling's Approximation
 */

#include <math.h>
#include <stdio.h>

#include "common.h"
#define rest(n) ((double)1 + (double)1 / (12 * n) + (double)1 / (288 * n * n))

int main(void)
{
	int nn, i, n;
	double result;
	scanf("%d", &nn);
	forn(i, nn) {
		scanf("%d", &n);
		result = (double).5 * log10(2 * acos(-1) * n) + n * log10(n / exp(1)) + log10(rest(n));
		printf("%d\n", 1 + (int)floor(result));
	}

	return 0;
}
