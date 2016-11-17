/**
 * C
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <map>
#include <string>
#include <vector>

#include "common.h"

using namespace std;

#define N 200000

long long n, m, k, x, s;
long long a[N], b[N], c[N], d[N];

bool read(void) {
	long long i;
	if (scanf("%lld%lld%lld", &n, &m, &k) != 3)
		return false;
	scanf("%lld%lld", &x, &s);
	forn(i, m) scanf("%lld", &a[i]);
	forn(i, m) scanf("%lld", &b[i]);
	forn(i, k) scanf("%lld", &c[i]);
	forn(i, k) scanf("%lld", &d[i]);
	return true;
}

long long caltime(long long i, long long j) {
	long long nmk, cst;

	if (i == -1 && j == -1)
		return n * x;

	if (i == -1) {
		nmk = n - c[j];
		return nmk * x;
	}
	if (j == -1) {
		cst = a[i];
		return cst * n;
	}

	nmk = n - c[j];
	cst = a[i];

	return nmk * cst;
}

void printtime(long long i, long long j, long long time) {
	printf("\t(%2lld, %2lld): %2lld\n", i, j, time);
}

void solve(void) {
	long long i, j, bgn, end;
	long long mintime, time;
	bool ck;

	/* none */
	mintime = caltime(-1, -1);
	//printtime(-1, -1, mintime);
	forn(i, m) {
		bgn = 0, end = k-1;
		while (bgn <= end) {
			j = (bgn+end) / 2;
			ck = (b[i] + d[j] <= s);
			if (ck) {
				/* both */
				time = caltime(i, j);
				//printtime(i, j, time);
				mintime = min(time, mintime);
				bgn = j + 1;
			} else {
				end = j - 1;
			}
		}
	}

	/* type 1 */
	forn(i, m) {
		if (b[i] > s) continue;
		time = caltime(i, -1);
		//printtime(i, -1, time);
		mintime = min(mintime, time);
	}

	/* type 2 */
	forn(j, k) {
		if (d[j] > s) continue;
		time = caltime(-1, j);
		//printtime(-1, j, time);
		mintime = min(mintime, time);
	}

	printf("%lld\n", mintime);
}

int main(void) {
	freopen("Inputs/C", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) {
		solve();
	}

	return 0;
}
