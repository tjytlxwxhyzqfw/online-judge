/*
 * 1141
 */

#include <math.h>
#include <stdio.h>

#include "common.h"

#define largenum_log10fact(n) ((double).5 * log10(2 * acos(-1) * (n)) \
	+ (n) * log10((n) / exp(1)) \
	+ log10((double)1 + (double)1 / (12 * (n)) + (double)1 / ((double)288 * (n) * (n))))

int main(void)
{
	int ans[23], i, n, y, e;
	double t, s;
	double a = log10(2);

	n = 1;
	for (i = 2; i < 23; ++i) {
		e = 1UL << i;
		if (e < 31)
			t = log10((1UL << e) - 1);
		else
			t = e * a;
		//printf("e: %d, t: %f\n", e, t);
		for (; ; ++n) {
			s = largenum_log10fact(n);
			if (s > t)
				break;
		}
		//printf("\tn: %d, s: %f\n", n, s);
		ans[i] = n - 1;
	}

	while (scanf("%d", &y) == 1) {
		if (y == 0)
			break;
		i = (y - 1960) / 10 + 2;
		printf("%d\n", ans[i]);
	}

	return 0;
}
	
	
