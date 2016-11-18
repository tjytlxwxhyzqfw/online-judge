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

int a[200000];

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

		//if (abs(big) < abs(small))!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		if (abs(a[big]) < abs(a[small]))
			swap(big, small);

		a[big] += a[small];
		a[small] = big;

		return big;
	}
};

#define N 200000

int n, m, k;
int c[N];
dsjset set;
map<int, int> mps[N];

bool read(void) {
	int i, left, rght;
	//int root;
	if (scanf("%d%d%d", &n, &m, &k) != 3)
		return false;
	//printf("read: %2d %2d %2d ----------->\n", n, m, k);
	forn(i, n) scanf("%d", &c[i]);
	//forn(i, n) printf("%2d%s", c[i], tailer(i, n-1));
	set.init(n);
	forn(i, m) {
		scanf("%d%d", &left, &rght);
		--left, --rght;
		set.un(left, rght);
		//root = set.un(left, rght);
		//printf("\tun(%2d, %2d): %2d\n", left, rght, root);
	}

	return true;
}

void clear(void) {
	int i;
	forn(i, N) mps[i].clear();
}

void solve(void) {
	int i, nchgs, maxc, root, total;
	map<int, int>::iterator it;
	map<int, int> *mp;

	//mps[sock/set][color] = number
	//forn(i, n)
		//printf("mps[%2d].size(): %2d\n", i, (int)mps[i].size());
	forn(i, n) {
		//printf("\t\ti: %d\n", i);
		root = set.find(i);
		//printf("\t\troot: %d\n", root);
		//printf("\t\tc[i]: %d\n", c[i]);
		mps[root][c[i]] += 1;
	}

	//forn(i, n)
		//printf("mps[%2d].size(): %2d\n", i, (int)mps[i].size());

	nchgs = 0;
	forn(i, n) {
		mp = &mps[i];
		maxc = 0;
		total = 0;
		foriter(it, *mp) {
			//printf("\tmp: %2d, it->first: %2d, it->second: %2d\n", i, it->first, it->second);
			total += it->second;
			if (it->second > maxc)
				maxc = it->second;
		}
		nchgs += total - maxc;
	}

	printf("%d\n", nchgs);
}

int main(void) {
	freopen("Inputs/C", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) {
		solve();
		clear();
	}

	return 0;
}
