/**
 * 1540
 *
 * 求点所在的最长连续区间
 */

#include <array>
#include <cassert>
#include <cstdio>
#include <stack>
#include <vector>

#include "arraytree.h"
#include "common.h"

using namespace std;

using namespace arraytree;

#define N 50000
#define SEGN (1 << 17)

struct segnode {
	int lcnt, rcnt;
	int bgn, end;

	int length(void) { return end - bgn + 1; }
	bool c(void) { return lcnt == length(); }
	bool cont(int oi) { return bgn <= oi and oi <= end; }

	int lbrk(void) { return bgn + lcnt; }
	int rbrk(void) { return end - rcnt; }

	void print(int idx) {
		printf("%2d: [%2d, %2d], lcnt: %2d, rcnt: %2d\n", idx, bgn, end, lcnt, rcnt);
	}
};

int nvs, nqs;
array<char, N> vs;
stack<int> stk;

array<segnode, SEGN> segnodes;
const int off = SEGN / 2;

void update(int i)
{
	segnode &n = segnodes[i];
	segnode &left = segnodes[lc(i)];
	segnode &right = segnodes[rc(i)];

	n.bgn = left.bgn;
	n.end = right.end;

	n.lcnt = left.lcnt;
	if (left.c())
		n.lcnt += right.lcnt;

	n.rcnt = right.rcnt;
	if (right.c())
		n.rcnt += left.rcnt;
}


void build(void)
{
	int i, oi;
	segnode *n;

	forn(oi, nvs) {
		n = &segnodes[oi+off];
		n->bgn = n->end = oi;
		n->lcnt = n->rcnt = 1;
	}

	forr(oi, nvs, off) {
		n = &segnodes[oi+off];
		n->bgn = n->end = oi;
		n->lcnt = n->rcnt = 0;
	}

	rforre(i, prt(SEGN-1), 1) {
		update(i);
	}
}

void destroy(int oi)
{
	segnode *n;
	int i;

	i = oi + off;
	n = &segnodes[i];
	n->lcnt = n->rcnt = 0;

	for (i = prt(i); valiad(i); i = prt(i))
		update(i);

	//printf("after destroy: %d\n", oi+1);
	//forn(i, SEGN) segnodes[i].print(i);

}

void restore(int oi)
{
	segnode *n;
	int i;

	i = oi + off;
	n = &segnodes[i];
	n->lcnt = n->rcnt = 1;

	for (i = prt(i); valiad(i); i = prt(i))
		update(i);

	//printf("after restore: %d\n", oi+1);
	//forn(i, SEGN) segnodes[i].print(i);
}

int query(int oi)
{	
	int i, lend, rend;
	int elder, curr;
	segnode *n;

	i = oi + off;
	if (segnodes[i].lcnt == 0)
		return 0;

	/* go to left */
	for (curr = i; valiad(leftelder(curr)); curr = leftelder(curr))
		if (not segnodes[curr].c()) break;
	n = &segnodes[curr];
	lend = (not n->c() ? n->rbrk()+1 : n->bgn);

	/* go to right */
	for (curr = i; valiad(rightelder(curr)); curr = rightelder(curr))
		if (not segnodes[curr].c()) break;
	n = &segnodes[curr];
	rend = (not n->c() ? n->lbrk()-1 : n->end);

	return rend - lend + 1;
}

void qall(void)
{
	int oi, qres;

	forn(oi, nvs) {
		qres = query(oi);
		printf("%d%s", qres, tailer(oi, nvs-1));
	}
}

int solve(void)
{
	int i, v, qres;
	char cmd;

	build();
	//forn(i, SEGN) segnodes[i].print(i);

	forn(i, nqs) {
		scanf("%c%*c", &cmd);
		switch(cmd) {
			case 'D':
				scanf("%d%*c", &v);
				//printf("D: %d\n", v);
				--v;
				stk.push(v);
				destroy(v);
				break;
			case 'Q':
				scanf("%d%*c", &v);
				//printf("Q: %d\n", v);
				--v;
				qres = query(v);
				printf("%d\n", qres);
				break;
			case 'R':
				if (stk.empty())
					break;
				v = stk.top();
				stk.pop();
				if (segnodes[v+off].lcnt != 0)
					break;
				//printf("R: %d\n", v+1);
				restore(v);
				break;
			default:
				assert(false);
		}
	}

	return 0;
}

bool read(void)
{
	if (scanf("%d%d%*c", &nvs, &nqs) != 2)
		return false;
	return true;
}

int main(void)
{
	//freopen("Inputs/1540", "r", stdin);
	while (read())
		solve();

	return 0;
}
