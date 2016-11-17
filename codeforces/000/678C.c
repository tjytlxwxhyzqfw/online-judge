/*
 * 678C
 */

#include <stdio.h>
#include "common.h"
#include "numth.h"

int main(void)
{
	long long n, a, b, p, q;
	long long lcm;

	scanf("%lld%lld%lld%lld%lld", &n, &a, &b, &p, &q);
	lcm = a / numth_gcd(a, b) * b;
	printf("%lld\n", (n / a) * p + (n / b) * q - n / lcm * min(p, q));
	return 0;
}
