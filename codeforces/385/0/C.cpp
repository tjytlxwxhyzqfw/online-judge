/**
 * C
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <map>
#include <stack>
#include <string>
#include <vector>

#include "common.h"

using namespace std;

#define N 100020
#define M 100020

int a[N];

struct dsjset {
	int n;

	dsjset(void) {
		n = 0;
	}

	void init(int size) {
		n = size;
		for (int i = 0; i < n; ++i)
			a[i] = -1;
	}

	int find(int i) {
		if (a[i] < 0)
			return i;
		return a[i] = find(a[i]);
	}

	int un(int i, int j) {
		int big, small;

		big = find(i);
		small = find(j);
		if (big == small)
			return big;

		
		if (abs(a[big]) < abs(a[small]))
			swap(big, small);

		a[big] += a[small];
		a[small] = big;

		return big;
	}
};

int n, m, k;
int cap[N], nes[N];
int edg[M][2];
dsjset set;

bool read(void) {
	int i, left, rght;

	scanf("%d%d%d", &n, &m, &k);

	// captials
	forn(i, k) scanf("%d", &cap[i]);
	forn(i, k) --cap[i];

	set.init(n);
	forn(i, m) {
		scanf("%d%d", &left, &rght);
		--left, --rght;
		if (left > rght)
			swap(left, rght);

		edg[i][0] = left;
		edg[i][1] = rght;

		set.un(left, rght);
	}

	return true;
}

void solve(void) {
	int i, root;
	int nns, maxnns, inc;
	int fn, fe;
	long long ans;

	forn(i, n) nes[i] = 0;
	forn(i, m) {
		root = set.find(edg[i][0]);
		nes[root] += 1;
	}

	//printa(a, a+n, "a: ");
	//printa(nes, nes+n, "nes: ");

	maxnns = 0;
	ans = 0;
	forn(i, k) {
		root = set.find(cap[i]);
		nns = -1 * a[root];
		maxnns = max(maxnns, nns);
		inc = nns * (nns-1) / 2 - nes[root];
		ans += inc;

		//printf("\tcaptal: %3d, nns: %3d, nes: %3d, inc: %d\n", cap[i]+1, nns, nes[root], inc);

		nes[root] = -1;
	}

	//printf("--> maxnns: %d\n", maxnns);

	fn = fe = 0;
	forn(i, n) {
		// i is supposed to be root
		root = set.find(i);
		if (nes[root] < 0) continue;

		//printf("\t--->free node : %d\n", i+1);
		nns = -1 * a[root];
		fn += nns;
		fe += nes[root];
		nes[root] = -1;
	}
	//printf("fn: %3d, fe: %3d\n", fn, fe);

	ans += fn*(fn-1) / 2 - fe;
	ans += fn * maxnns;

	printf("%lld\n", ans);
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
