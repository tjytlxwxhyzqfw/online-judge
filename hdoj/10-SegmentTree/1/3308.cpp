/**
 * 3308
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

#define SEGN (1<<18) /*(1<<18)*/

struct node {
	int b, e;
	int maxl, maxr, maxv;

	void init(int bgn, int end) {
		b = bgn;
		e = end;
	}

	bool continuous(void) {
		return maxv == (e-b+1);
	}

	bool eq(int bgn, int end) {
		//printf("eq: (%2d, %2d) vs. (%2d, %2d)\n", b, e, bgn, end);
		return b == bgn and e == end;
	}

	bool ct(int b, int e) {
		return this->b <= b and this->e >= e;
	}

	void print(int i) {
		printf("%2d: [%2d, %2d]:  %3d, %3d, %3d\n", i, b, e, maxv, maxl, maxr);
	}
};

int n, m;
array<int, SEGN/2> a;
array<node, SEGN> seg;
const int off = (SEGN>>1);

void update(int i)
{
	node *left, *right, *curr;
	bool adj;

	assert(i < off);

	curr = &seg[i];
	left = &seg[lc(i)], right = &seg[rc(i)];
	adj = (a[left->e] < a[right->b]);

	curr->maxv = max(right->maxv, left->maxv);
	if (adj)
		curr->maxv = max(curr->maxv, left->maxr+right->maxl);

	curr->maxl = left->maxl;
	if (left->continuous() and adj)
		curr->maxl += right->maxl;

	curr->maxr = right->maxr;
	if (right->continuous() and adj)
		curr->maxr += left->maxr;

	//seg[i].print(i);
}

void build(void)
{
	int i;
	node *curr;

	forn(i, off) {
		curr = &seg[i+off];
		curr->init(i, i);
		/*TODO*/
		curr->maxl = curr->maxr = curr->maxv = (i < n ? 1 : 0);
	}

	rforre(i, prt(SEGN-1), 1) {
		seg[i].init(seg[lc(i)].b, seg[rc(i)].e);
		update(i);
	}
}

void alter(int oi, int val)
{
	int i;

	//printf("alter: a[%2d]: %2d -> %2d\n", oi, a[oi], val);
	a[oi] = val;

	i = oi + off;
	for (i = prt(i); valiad(i); i = prt(i))
		update(i);
}

int query(int i, int bgn, int end)
{
	node *curr, *left, *right;
	int maxl, maxr, maxv;

	curr = &seg[i];
	left = &seg[lc(i)], right = &seg[rc(i)];

	//printf("query: [%2d, %2d] in [%2d, %2d]\n", bgn, end, curr->b, curr->e);

	if (curr->eq(bgn, end)) return curr->maxv;

	maxl = maxr = 0;
	if (left->ct(bgn, end)) {
		maxv = query(lc(i), bgn, end);
	} else if (right->ct(bgn, end)) {
		maxv = query(rc(i), bgn, end);
	} else {
		maxl = query(lc(i), bgn, left->e);
		maxr = query(rc(i), right->b, end);
		maxv = max(maxl, maxr);

		if (a[left->e] < a[right->b]) {
			maxl = min(left->maxr, left->e-bgn+1);
			maxr = min(right->maxl, end-right->b+1);
			maxv = max(maxv, maxl+maxr);
		}

	}

	return maxv;
}


bool read(void)
{
	int i;

	if (scanf("%d%d", &n, &m) != 2)
		return false;

	forn(i, n) scanf("%d", &a[i]);
	//forn(i, n) printf("%d%s", a[i], tailer(i, n-1));

	return true;
}

void solve(void)
{
	char cmd[2];
	int x, y;
	int qres;

	build();

	while (--m >= 0) {
		scanf("%s%d%d", cmd, &x, &y);
		//printf("%c %2d %2d\n", cmd, x, y);
		switch(cmd[0]) {
			case 'U':
				alter(x, y);
				break;
			case 'Q':
				qres = query(1, x, y);
				printf("%d\n", qres);
				break;
			default:
				assert(false);
		}
	}
}
	
int main(void)
{
	freopen("Inputs/3308", "r", stdin);
	setbuf(stdout, NULL);

	int ncases;

	scanf("%d", &ncases);

	while (--ncases >= 0) {
		if (read())
			solve();
	}

	return 0;
}
