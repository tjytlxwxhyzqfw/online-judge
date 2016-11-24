/**
 * 1754
 */

#include <algorithm>
#include <array>
#include <cassert>
#include <cstdio>
#include <vector>

#include "arraytree.h"
#include "common.h"

using namespace std;
using namespace segutils;

#define N 200000
#define SEGN (1<<19)

struct node {
	int g;
};

int n, m;
array<node, SEGN> seg;
const int off = (1 << 18);

void update(int i)
{
	seg[i].g = max(seg[lc(i)].g, seg[rc(i)].g);
}

void build(void)
{
	int i;

	forn(i, off) if (i >= n) seg[i+off].g = -1;
	rforre(i, prt(SEGN-1), 1) update(i);
}

void alter(int oi, int grade)
{
	int i;

	i = oi + off;
	seg[i].g = grade;

	for (i = prt(i); valiad(i); i = prt(i))
		update(i);
}

int query(int bgn, int end)
{
	int b, e, maxv;

	maxv = -1;
	b = bgn+off, e = end+off;
	while (b <= e) {
		if (atright(b)) maxv = max(maxv, seg[b].g);
		if (atleft(e)) maxv = max(maxv, seg[e].g);

		b = rightelder(b);
		e = leftelder(e);
	}

	return maxv;
}

bool read(void)
{
	int i;

	if (scanf("%d%d", &n, &m) != 2)
		return false;
	forn(i, n) scanf("%d", &seg[i+off].g);
	return true;
}

void solve(void)
{
	int x, y, qres;
	char cmd;

	build();
	scanf("%*c");

	while (--m >= 0) {
		scanf("%c%d%d%*c", &cmd, &x, &y);
		switch (cmd) {
			case 'Q':
				--x, --y;
				qres = query(x, y);
				printf("%d\n", qres);
				break;
			case 'U':
				--x;
				alter(x, y);
				break;
			default:
				assert(false);
		}
	}
}


int main(void)
{
	freopen("Inputs/1754", "r", stdin);
	setbuf(stdout, NULL);

	while (read())
		solve();

	return 0;
}
