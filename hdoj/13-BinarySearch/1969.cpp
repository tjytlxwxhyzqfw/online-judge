/**
 * 1969
 */

#include <algorithm>
#include <cassert>
#include <cmath>
#include <cstdio>
#include <vector>

#include "common.h"

using namespace std;

#define N 10000

const double errtol = 0.000001;

int n, m, a[N];
double s[N];

bool read(void) {
	int i;
	double pi;

	if (scanf("%d%d", &n, &m) != 2)
		return false;
	forn(i, n) scanf("%d", &a[i]);
	++m;
	pi = acos(-1);
	forn(i, n) s[i] = pi * a[i] * a[i];

	return true;
}

bool check(double sz) {
	int x, i;
	x = 0;
	forn(i, n) x += (int)(s[i]/sz);
	return x >= m;
}

void solve(void) {
	double b, e, mid;
	int i;

	e = 0.0;
	forn(i, n) e += s[i];
	e /= m;

	b = 0.0;
	while (e-b >= errtol) {
		mid = (b+e) / 2.0;
		check(mid) ? b = mid : e = mid;
	}

	printf("%.4lf\n", (b+e)/2);
}

int main(void)
{
	int ncs;

	freopen("Inputs/1969", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d", &ncs);
	while (--ncs >= 0) {
		read();
		solve();
	}

	return 0;
}
