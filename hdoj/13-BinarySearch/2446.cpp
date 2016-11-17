/**
 * 2446
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"

using namespace std;

#define N 3900000

long long s, n, a[N];


bool read(void) {
	scanf("%lld", &s);
	return true;
};

long long bs(long long tgt, bool usesum) {
	long long i, j, k, v, maxv;

	//printf("target: %lld\n", tgt);
	i = 0, j = n - 1;
	while (i <= j) {
		k = (i+j) / 2;
		v = (usesum ? a[k] : a[k]-a[k-1]);
		//printf("\t[%20lld, %20lld]: %20lld: %lld\n", i, j, k, v);
		if (v < tgt) {
			maxv = k;
			i = k + 1;
		} else {
			 j = k - 1;
		}
	}

	return maxv;
}

void solve(void) {
	long long i, j, k;

	i = bs(s, true);
	j = bs(s-a[i], false);
	k = s - a[i] - (a[j]-a[j-1]);

	//printf("%I64d %I64d %I64d\n", i+1, j+1, k);
	printf("%lld %lld %lld\n", i+1, j+1, k);
}

int main(void) {
	int ncs, i;

	//freopen("Inputs/2446", "r", stdin);
	//setbuf(stdout, NULL);

	a[0] = 0;
	forr(n, 1, N) a[n] = a[n-1] + n;
	forr(i, 1, n) {
		a[i] += a[i-1];
		if (a[i] < 0) break;
	}
	n = i;

	scanf("%d", &ncs);
	while (--ncs >= 0) {
		read();
		solve();
	}

	return 0;
}
