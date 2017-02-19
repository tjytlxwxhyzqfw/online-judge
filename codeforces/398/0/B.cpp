/**
 * C
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <map>
#include <set>
#include <stack>
#include <string>
#include <vector>

#include "common.h"

using namespace std;

#define N 100001

long long ts, tf, t;
long long n, a[N];

bool read(void) {
	int i;

	scanf("%lld%lld%lld", &ts, &tf, &t);
	scanf("%lld", &n);
	forn(i, n) scanf("%lld", &a[i]);

	return true;
}

int position(const long long x) {
	long long *p;
	int idx;

	p = upper_bound(a, a+n, x);
	idx = p - a;
	return idx;
}

void solve(void) {
	int i;
	long long last, bgn, end, arr;
	long long minclk, minwait, wait, nbefore;
	long long ends_idx, ends[N];
	set<long long> cands;
	set<long long>::const_iterator it;

	if (n == 0) {
		printf("%lld\n", ts);
		return;
	}

	last = ts;
	ends_idx = 0;
	forn(i, n) {
		arr = a[i];
		bgn = max(arr, last);
		end = bgn + t;
		ends[ends_idx++] = end;
		last = end;
	}
	assert(ends_idx == n);
	//printa(ends, ends+n);

	forn(i, n) {
		cands.insert(ends[i]);
		arr = a[i];
		cands.insert(arr);
		cands.insert(arr+1);
		if (arr-1 >= 0)
			cands.insert(arr-1);
	}
	//!!! elements in std::set are sorted automaticlly
	//printa(cands.begin(), cands.end());

	minclk = *cands.begin();
	if (minclk > ts) {
		printf("%lld\n", ts);
		return;
	}
	minwait = ts - minclk;
	if (minclk == 0) {
		nbefore = position(minclk);
		minwait = ts + nbefore*t;
	}

	foriter(it, cands) {
		arr = *it;
		nbefore = position(arr);
		end = (nbefore == 0 ? ts : ends[nbefore-1]);
		bgn = max(arr, end);
		//printf("arr: %3lld, nbefore: %3lld, end: %3lld, bgn: %3lld\n", arr, nbefore, end, bgn);
		if (bgn + t > tf)
			continue; //!!! continue? break?
		wait = bgn - arr;
		//printf("wait: %lld\n", wait);
		if (wait < minwait) {
			minclk = arr;
			minwait = wait;
		}
	}

	printf("%lld\n", minclk);
}

int main(void) {
	#ifdef DEBUG
	//freopen("Inputs/C", "r", stdin);
	setbuf(stdout, NULL);
	#endif

	read();
	solve();

	return 0;
}
