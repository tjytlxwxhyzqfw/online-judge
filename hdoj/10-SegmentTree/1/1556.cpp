/**
 * 1556
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"
#include "segtree.h"

using namespace std;

#define N (1<<17)

int n;

struct segnode: public segnode_base {
	int npts;
	void clear(void) { npts = 0; }
	bool dirty(void) const { return not p() and npts != 0; }
	void print(int idx) {
		printf("\t%2d: [%2d, %2d]: npts: %2d\n", idx, b, e, npts);
	}
};

struct segtree: public segtree_base<segnode> {
	segtree(int sz) { init(sz); }
	void build(void) {
		int i;
		builditv();
		forn(i, size) seg[i].clear();
	}
	void do_alter(int i, int inc) {
		get(i).npts += inc;
	}
	void alter(int i, int b, int e) {
		if (not itvld(i, b, e)) return;
		if (get(i).eq(b, e)) return do_alter(i, 1);
		if (get(i).dirty()) {
			do_alter(li(i), get(i).npts);
			do_alter(ri(i), get(i).npts);
			get(i).clear();
		}
		alter(li(i), max(b, lc(i).b), min(e, lc(i).e));
		alter(ri(i), max(b, rc(i).b), min(e, rc(i).e));
	}
	int query(int i) {
		int sum;

		sum = arr[i].npts;
		i += half;
		for (i = prt(i); valiad(i); i = prt(i))
			sum += seg[i].npts;

		return sum;
	}
};

segtree seg(2*N);

bool read(void)
{
	if (scanf("%d", &n) != 1)
		return false;
	return n != 0;
}

void solve(void)
{
	int i, b, e;
	int sum;

	seg.build();
	//seg.print();

	forn(i, n) {
		scanf("%d%d", &b, &e);
		--b, --e;
		seg.alter(1, b, e);
	}

	forn(i, n) {
		sum = seg.query(i);
		printf("%d%s", sum, tailer(i, n-1));
	}
}

int main(void)
{
	freopen("Inputs/1556", "r", stdin);
	//setbuf(stdout, NULL);

	while (read()) solve();

	return 0;
}
