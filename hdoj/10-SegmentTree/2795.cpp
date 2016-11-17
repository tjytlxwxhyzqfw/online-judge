/**
 * 2795
 */

#include <algorithm>
#include <array>
#include <cassert>
#include <cstdio>
#include <vector>

#include "arraytree.h"
#include "common.h"

using namespace std;

#define N 200000
#define SEGN  (1<<19)

struct node {
	int c;
	int bgn, end;

	void init(int b, int e) {
		bgn = b;
		end = e;
	}

	bool point(void) {
		return bgn == end;
	}

	bool hold(int width) {
		return c >= width;
	}
};

array<node, SEGN> seg;
const int off = (1<<18);

int h, w;
int nrqs;
array<int, N> rqs;

void update(int i)
{
	seg[i].c = max(seg[lc(i)].c, seg[rc(i)].c);
}

void build(void)
{
	int oi, i, height;

	height = min(h, N);

	forn(oi, off) {
		seg[oi+off].init(oi, oi);
		seg[oi+off].c = (oi < height ? w : 0);
	}

	//forn(oi, 10) {
		//printf("%2d: %2d\n", oi, seg[oi+off].c);
	//}

	rforre(i, prt(SEGN-1), 1) {
		seg[i].init(seg[lc(i)].bgn, seg[rc(i)].end);
		update(i);
	}
}

int add(int root, int width)
{
	//printf("at: %2d, c: %2d\n", root, seg[root].c);
	//if (seg[root].point())
		//printf("point: %2d\n", root - off);

	if (not seg[root].hold(width)) return -2;

	if (seg[root].point()) {
		int i = root;

		seg[i].c -= width;
		for (i = prt(i); valiad(i); i = prt(i))
			update(i);

		return seg[root].bgn;
	}

	if (seg[lc(root)].hold(width)) return add(lc(root), width);
	if (seg[rc(root)].hold(width)) return add(rc(root), width);

	assert(false);
	return -1;
}
	

bool read(void)
{
	int i;

	if (scanf("%d%d%d", &h, &w, &nrqs) != 3)
		return false;

	forn(i, nrqs) {
		scanf("%d", &rqs[i]);
	}

	return true;
}

void solve(void)
{
	int i, idx;

	build();

	forn(i, nrqs) {
		idx = add(1, rqs[i]);
		printf("%d\n", idx+1);
	}
}

int main(void)
{
	freopen("Inputs/2795", "r", stdin);
	setbuf(stdout, NULL);

	while (read())
		solve();

	return 0;
}
