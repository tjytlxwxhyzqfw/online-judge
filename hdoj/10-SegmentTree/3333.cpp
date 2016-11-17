/**
 * 3333
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

#define N (1<<15)
#define M 100000

#define SEGN (N<<1)

struct qnode {
	int idx;
	int b, e;
	long long ans;
};

bool cmp_by_e(const qnode &x, const qnode &y)
{
	return x.e < y.e;
}

bool cmp_by_i(const qnode &x, const qnode &y)
{
	return x.idx < y.idx;
}

struct node {
	long long sum;
};

int n, m;
array<node, SEGN> seg;
array<long long, N> a, d;
array<int, N> used, plc;
array<qnode, M> q;
const int off = N;

void update(int i) { seg[i].sum = seg[lc(i)].sum + seg[rc(i)].sum; }

void build(void)
{
	int i;
	forn(i, SEGN) seg[i].sum = 0;
}

void alter(int oi, long long val)
{
	int i;
	assert(oi < off);
	i = oi + off;
	seg[i].sum = val;
	for (i = prt(i); valiad(i); i = prt(i))
		update(i);
}

long long query(int bgn, int end)
{
	long long sum = 0;
	int b, e;

	//printf("query: [%5d, %5d]\n", bgn, end);

	b = bgn+off, e = end+off;
	while (b <= e) {
		if (atright(b)) sum += seg[b].sum;
		if (atleft(e)) sum += seg[e].sum;

		b = rightelder(b);
		e = leftelder(e);
	}

	return sum;
}

void place(int j)
{
	int idx;

	//printf("\tplace: %d\n", j);
	//printf("d: [%d, %d]\n", *d.begin(), *(d.begin()+n-1));

	//FIXME: GREATE BUG!!!!!!!!!
	// idx = *(lower_bound(d.begin(), d.begin()+n));

	idx = lower_bound(d.begin(), d.begin()+n, a[j]) - d.begin();

	//printf("idx: %d\n", idx);

	if (used[idx]) alter(plc[idx], 0);
	alter(j, a[j]);
	used[idx] = 1;
	plc[idx] = j;

	//printf("place: a[%2d]: %2I64d, total: %3I64\n", j, a[j], seg[1].sum);
}

bool read(void)
{
	int i;
	int b, e;

	scanf("%d", &n);
	forn(i, n) scanf("%I64d", &a[i]);
	//printf("n: %d\n", n);

	scanf("%d", &m);
	//printf("m: %d\n", m);
	forn(i, m) {
		scanf("%d%d", &b, &e);
		--b, --e;
		q[i].idx = i;
		q[i].b = b, q[i].e = e;
	}

	//printf("read\n");

	forn(i, n) d[i] = a[i];
	forn(i, n) used[i] = 0;
	forn(i, n) plc[i] = -1;
	sort(d.begin(), d.begin()+n);

	//printf("inlitialized d[], used[], plc[]\n");

	return true;
}

void solve(void)
{
	int i, j, last;

	build();

	sort(q.begin(), q.begin()+m, cmp_by_e);

	place(last = 0);
	forn(i, m) {
		//printf("i: %d\n", i);
		if (q[i].e > last) {
			forre(j, last+1, q[i].e) place(j);
			last = q[i].e;
		}
		q[i].ans = query(q[i].b, q[i].e);
	}
	sort(q.begin(), q.begin()+m, cmp_by_i);
	forn(i, m) printf("%I64d\n", q[i].ans);
	//forn(i, m) printf("%lld\n", q[i].ans);
}
	

int main(void)
{
	int ncases;

	freopen("Inputs/3333", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d", &ncases);
	//printf("ncases: %d\n", ncases);
	while (--ncases >= 0) {
		read();
		solve();
	}

	return 0;
}
