/**
 * 5178
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"

using namespace std;

#define N 100000

long long n, k, a[N];

long long nxt(const long long bgn, const long long end, const long long dst) {
	long long i;
	for (i = bgn; a[end]-a[i] > dst; ++i);
	return i;
}

long long check_2(void) {
	long long i, j, t;

	t = i = 0;
	forn(j, n) {
		if (a[j]-a[i] > k) i = nxt(i, j, k);
		//printf("pairs: [%5lld, %5lld)\n", i, j);
		t += j-i;
	}

	return t;
}


bool check(const long long nps) {
	long long i, j, t;

	t = i = 0;
	forn(j, n) {
		if (a[j]-a[i] > k) i = nxt(i, j, k);
		//printf("pairs: [%5lld, %5lld)\n", i, j);
		t += j-i;
	}

	return t >= nps;
}

bool read(void) {
	long long i;
	scanf("%lld%lld", &n, &k);
	forn(i, n) scanf("%lld", &a[i]);
	sort(a, a+n);
	return true;
}

void solve_2(void) {
	long long ans;
	ans = check_2();
	printf("%lld\n", ans);
}

void solve(void) {
	long long i, j, m, ans;
	bool ck;

	i = 0, j = n * n;
	while (i <= j) {
		m = (i+j) / 2;
		ck = check(m);

		if (ck) {
			ans = m;
			i = m + 1;
		} else {
			j = m - 1;
		}
	}

	printf("%lld\n", ans);
}

int main(void) {
	int ncs;

	freopen("Inputs/5178", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d", &ncs);
	while (--ncs >= 0) {
		read();
		solve_2();
	}

	return 0;
}
