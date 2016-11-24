/**
 * 3911
 */

#include <algorithm>
#include <array>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"
#include "segtree.h"

using namespace std;

#define N (1<<17) //(1<<17)

int n, nqs;
vector<int> a(N);

struct segnode: public segnode_base {
	int l, r, m;
	int lz, rz, mz;
	bool reverse;

	inline bool c(void) const { return len() == m; }
	inline bool cz(void) const { return len() == mz; }
	inline void print(int i) const {
		printf("\t%2d: [%2d, %2d]: (%2d, %2d, %2d), (%2d, %2d, %2d), %s\n", i, b, e, l, r, m, lz, rz, mz, reverse ? "true" : "false");
	}
	inline bool dirty(void) const { return not p() and reverse; }
	inline void clear(void) { reverse = false; }
};

struct segtree: public segtree_base<segnode> {
	segtree(int sz) { init(sz); }
	
	void update(int i) {
		segnode *curr;
		const segnode *left, *rght;
		curr = &seg[i], left = &seg[li(i)], rght = &seg[ri(i)];

		curr->l = left->l + (left->c() ? rght->l : 0);
		curr->r = rght->r + (rght->c() ? left->r : 0);
		curr->m = max3(left->m, rght->m, left->r+rght->l);

		curr->lz = left->lz + (left->cz() ? rght->lz : 0);
		curr->rz = rght->rz + (rght->cz() ? left->rz : 0);
		curr->mz = max3(left->mz, rght->mz, left->rz+rght->lz);

		//seg[i].print(i);
	}

	void build(void) {
		int i;
		builditv();
		forn(i, half) {
			arr[i].l = arr[i].r = arr[i].m = (i < n ? a[i] : 0);
			arr[i].lz = arr[i].rz = arr[i].mz = (i < n ? 1-a[i] : 0);
		}
		rforre(i, lstprt(), 1) update(i);
		forn(i, size) get(i).clear();
	}

	int query(int i, int b, int e) {
		int l, r, m;
		segnode *curr, *left, *rght;

		if (not itvld(i, b, e)) return 0;
		curr = &seg[i];
		if (curr->eq(b, e)) return curr->m;
		left = &seg[li(i)], rght = &seg[ri(i)];
		if (curr->dirty()) {
			do_alter(li(i));
			do_alter(ri(i));
			curr->clear();
		}
		l = query(li(i), max(b, left->b), min(e, left->e));
		r = query(ri(i), max(b, rght->b), min(e, rght->e));
		m = max(l, r);
		if (curr->spl(b, e)) m = max(m, min(left->r, left->e-b+1)+min(rght->l, e-rght->b+1));
		return m;
	}

	void alter(const int i, const int b, const int e) {
		if (not itvld(i, b, e)) return;
		if (get(i).eq(b, e)) return do_alter(i);
		if (get(i).dirty()) {
			do_alter(li(i));
			do_alter(ri(i));
			get(i).clear();
		}
		alter(li(i), max(b, lc(i).b), min(e, lc(i).e));
		alter(ri(i), max(b, rc(i).b), min(e, rc(i).e));
		update(i);
	}

	inline void do_alter(const int i) {
		get(i).reverse = not get(i).reverse;
		swap(get(i).l, get(i).lz);
		swap(get(i).r, get(i).rz);
		swap(get(i).m, get(i).mz);
	}
};

segtree seg(2*N);

bool read(void)
{
	int i;

	if (scanf("%d", &n) != 1)
		return false;
	forn(i, n) scanf("%d", &a[i]);
	scanf("%d", &nqs);
	return true;
}

void solve(void)
{
	int i, c, x, y;
	int qres;

	seg.build();
	//seg.print();
	forn(i, nqs) {
		scanf("%d%d%d", &c, &x, &y);
		--x, --y;
		switch(c) {
		case 0:
			//printf("[command]$ query: [%2d, %2d]\n", x, y);
			qres = seg.query(1, x, y);
			printf("%d\n", qres);
			//printf("\n---query---\n");
			//seg.print();
			//printf("------\n\n");
			break;
		case 1:
			//printf("[command]$ alter: [%2d, %2d]\n", x, y);
			seg.alter(1, x, y);
			//printf("\n---alter---\n");
			//seg.print();
			//printf("------\n\n");
			break;
		default:
			assert(false);
		}
	}
}

int main(void)
{
	freopen("Inputs/3911", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) solve();

	return 0;
}
