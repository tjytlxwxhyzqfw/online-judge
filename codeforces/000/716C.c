/**
 * 716C
 */
#include <stdio.h>

#include "common.h"
#include "numth.h"

#define M 1000000007

#define pls(x, y) NUMTH_MODPLS((x), (y), M)
#define mul(x, y) NUMTH_MODMUL((x), (y), M)

long long k, l, r;

long long cmul(long long n, long long m)
{
	long long ans = n;
	long long next;
	long long i;

	forr(i, 1, m) {
		next = pls(n, -1*i);
		NUMTH_CORRECT(next, M);	
		ans = mul(ans, next);
	}

	return ans;
}

void solve(void)
{
	long long i, n, left, right, ans = 0;

	scanf("%lld%lld%lld", &k, &l, &r);

	forre(i, l, r) {
		n = numth_fibonacci(i+1, M);
		left = cmul(n, k);
		right = numth_modexp(cmul(k, k), M-2, M);
		ans = pls(ans, mul(left, right));
		printf("ans: %lld\n", ans);
	}

	printf("%lld\n", ans);
}

int main(void)
{
	solve();
	return 0;
}

