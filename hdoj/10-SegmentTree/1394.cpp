/**
 * 1394
 */

#include <algorithm>
#include <array>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"
#include "arraytree.h"

using namespace std;
using namespace segutils;

#define N 5000
#define SEGN (1<<14)

struct node {
	int b, e;
	int k;

	 void init(int begin, int end) {
		b = begin;
		e = end;
	}
};

int n;
array<int, N> a;
array<node, SEGN> seg;
const int off = (1<<13);

void update(int i)
{
	seg[i].k = seg[lc(i)].k + seg[rc(i)].k;
	//printf("update: %2d: --> %2d\n", i, seg[i].k);
}

void build(void)
{
	int i, j;

	forr(j, off, SEGN) {
		seg[j].init(j-off, j-off);
		seg[j].k = 0;
	}

	rforre(i, prt(SEGN-1), 1) update(i);
}

void place(int oi)
{
	int i;

	i = oi + off;
	seg[i].k = 1;

	//printf("place: %2d: %5d\n", oi, i);

	for (i = prt(i); valiad(i); i = prt(i))
		update(i);
}

int query(int begin, int end)
{
	int res = 0;
	int b, e;

	b = begin+off, e = end+off;
	while (b <= e) {
		if (atright(b)) res += seg[b].k;
		if (atleft(e)) res += seg[e].k;

		b = rightelder(b);
		e = leftelder(e);
	}

	//printf("query: [%2d, %2d]: %2d\n", begin, end, res);
	return res;
}

bool read(void)
{
	int i;

	if (scanf("%d", &n) != 1)
		return false;
	forn(i, n) scanf("%d", &a[i]);

	return true;
}

void solve(void)
{
	int res = 0;
	int i, minv;

	build();

	forn(i, n) {
		res += query(a[i]+1, n-1);
		place(a[i]);
	}

	minv = res;
	forn(i, n) {
		res = res-a[i]+n-1-a[i];
		minv = min(minv, res);
		//printf("i: %2d, res: %3d, minv: %3d\n", i, res, minv);
	}

	printf("%d\n", minv);
}
	

int main(void)
{
	freopen("Inputs/1394", "r", stdin);
	setbuf(stdout, NULL);

	while (read())
		solve();

	return 0;
}
