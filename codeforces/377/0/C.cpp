/**
 * C
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"

using namespace std;

long long b, l, s;

long long compute(long long x, long long y, long long z) {
	long long n;
	if (x < 0 || y < 0 || z < 0)
		return LLONG_MAX;
	n = max3(x, y, z);
	return n-x + n-y + n-z;
}
	

bool read(void) {
	if (scanf("%lld%lld%lld", &b, &l, &s) != 3)
		return false;
	return true;
}

void solve(void) {
	long long ans[7], minv;
	int i;

	ans[0] = compute(b-1, l, s);
	ans[1] = compute(b, l-1, s);
	ans[2] = compute(b, l, s-1);
	ans[3] = compute(b-1, l-1, s);
	ans[4] = compute(b, l-1, s-1);
	ans[5] = compute(b-1, l, s-1);
	ans[6] = compute(b, l, s);

	minv = LLONG_MAX;
	forn(i, 7) minv = min(minv, ans[i]);

	printf("%lld\n", minv == LLONG_MAX ? 0 : minv);
}

int main(void)
{
	freopen("Inputs/C", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) solve();

	return 0;
}
