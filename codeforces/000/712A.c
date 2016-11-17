/*
 * 712A
 */

#include <stdio.h>
#include "common.h"

#define N 100000

int main(void)
{
	long long a[N], n, i;

	freopen("Inputs/712A", "r", stdin);

	scanf("%lld", &n);
	forn(i, n) scanf("%lld", a + i);
	forn(i, n - 1) printf("%lld ", a[i] + a[i + 1]);
	printf("%lld\n", a[n - 1]);

	return 0;
}
