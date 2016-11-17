/*
 * 678D
 *
 * (A-1)y = (A-1)xA^n + BA^n - B
 */
#include <stdio.h>
#include <stdlib.h>

#include "common.h"
#include "numth.h"

#define P LL(1000000007)

/* x, y, n must not be negitive */
#define modmul(x, y, n) ((((x) % (n)) * ((y) % (n))) % (n))
#define modpls(x, y, n) ((((x) % (n)) + ((y) % (n))) % (n))

int main(void)
{
	long long A, B, n, x;
	long long right, q, ans, d;

	scanf("%lld%lld%lld%lld", &A, &B, &n, &x);
	if (A == 1) {
		ans = modpls(x, modmul(B, n, P), P);
		goto print_answer;
	}
	q = numth_modexp(A, n, P);
	//printf("q: %lld\n", q);
	right = modmul(modmul(A - 1, x, P), q, P) + modmul(B, q, P) - B;
	//printf("right: %lld\n", right);
	NUMTH_CORRECT(right, P);

	numth_mle(A - 1, right, P, &ans, &d);
	assert(d == 1);

	print_answer:
	printf("%lld\n", ans);

	return 0;
}
