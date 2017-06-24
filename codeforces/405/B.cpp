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

#define N 160000
#define M 160020

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

int n, m;
dsjset set;

bool read(void) {
	int i, left, rght;

	scanf("%d%d", &n, &m);
	set.init(n);

	forn(i, m) {
		scanf("%d%d", &left, &rght);
		--left, --rght;

		set.un(left, rght);
	}

	return true;
}

void solve(void) {
	int i, nedges, s, z;

	//printf("begin\n");

	nedges = 0;
	forn(i, n) {
		s = -1 * a[i];
		if (s <= 0) continue;
		//printf("%3d: size: %d\n", i, s);
		z = s*(s-1) / 2;
		nedges += z;
	}

	//printf("nedges: %d\n", nedges);
	printf(nedges == m ? "YES\n" : "NO\n");
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
