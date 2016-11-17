#include <stdio.h>

#include "common.h"
#include "numth.h"

int main(void)
{
	long long i, a, b, x, d;
	long long s, t;

	scanf("%lld%lld", &a, &b);

	d = numth_gcd(a, 10);
	s = 10 / d;
	t = LLONG_MAX;

	//printf("b: %lld, d: %lld\n", b, d);

	if (b % d == 0) {
		numth_mle(a, b, 10, &x, &d);
		numth_for_each_solution(i, x, 10, d) {
			//printf("i: %lld, x: %lld\n", i, x);
			if (x < t) t = x;
		}
	}

	printf("%lld\n", min(s, t));

	return 0;
}
