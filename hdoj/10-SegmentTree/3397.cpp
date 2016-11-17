/**
 * 3397
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"
#include "segtree.h"

using namespace std;

#define N (1<<17)
static int map[] = {2, 1, 0, -1};

int nns, nqs;
vector<int> a(N);

struct segnode: public segnode_base {
	int l, r, m, n;
	int lz, rz, mz, nz;
	int tag;

	bool c(void) const { return m == len(); }
	bool cz(void) const { return mz == len(); }
	bool dirty(void) const { return not p() and not (tag == -1); }
	void clear(void) { tag = -1; }
	void merge(int t) { tag = (t == 2 ? map[tag+1] : t); }

	void print(int idx) const {
		printf("\t%2d: [%2d, %2d]: (%2d, %2d, %2d, %2d), (%2d, %2d, %2d, %2d), tag: %2d\n", idx, b, e, l, r, m, n, lz, rz, mz, nz, tag);
	}
};

struct segtree: public segtree_base<segnode> {
	segtree(int sz) { init(sz); }
	void update(int i) {
		segnode *curr, *left, *rght;

		curr = &seg[i], left = &seg[li(i)], rght = &seg[ri(i)];

		curr->n = left->n + rght->n;
		curr->l = left->l + (left->c() ? rght->l : 0);
		curr->r = rght->r + (rght->c() ? left->r : 0);
		curr->m = max3(left->m, rght->m, left->r+rght->l);

		curr->nz = left->nz + rght->nz;
		curr->lz = left->lz + (left->cz() ? rght->lz : 0);
		curr->rz = rght->rz + (rght->cz() ? left->rz : 0);
		curr->mz = max3(left->mz, rght->mz, left->rz+rght->lz);
	}

	void build(void) {
		int i;

		builditv();
		forn(i, half) {
			arr[i].l = arr[i].r = arr[i].m = arr[i].n = (i < nns ? a[i] : 0);
			arr[i].lz = arr[i].rz = arr[i].mz = arr[i].nz = (i < nns ? (1-a[i]) : 0);
		}

		for (i = lstprt(); i >= 1; --i) update(i);
		forn(i, size) seg[i].clear();
	}

	void do_alter(int i, int tag) {
		segnode *curr = &seg[i];
		curr->merge(tag);
		if (tag != 2) {
			assert(tag == 0 or tag == 1);
			curr->l = curr->r = curr->m = curr->n = tag * curr->len();
			curr->lz = curr->rz = curr->mz = curr->nz = (1-tag) * curr->len();
		} else {
			swap(curr->l, curr->lz);
			swap(curr->r, curr->rz);
			swap(curr->m, curr->mz);
			swap(curr->n, curr->nz);
		}
	}

	void alter(int i, int b, int e, int tag) {
		if (not itvld(i, b, e)) return;
		if (get(i).eq(b, e)) return do_alter(i, tag);
		if (get(i).dirty()) {
			assert(get(i).tag == 0 or get(i).tag == 1 or get(i).tag == 2);
			do_alter(li(i), get(i).tag);
			do_alter(ri(i), get(i).tag);
			get(i).clear();
		}
		alter(li(i), max(b, lc(i).b), min(e, lc(i).e), tag);
		alter(ri(i), max(b, rc(i).b), min(e, rc(i).e), tag);
		update(i);
	}

	/* qtype: 3: n, 4: m */
	int query(int i, int b, int e, int qtype) {
		int l, r, m;

		if (not itvld(i, b, e)) return 0;
		//printf("query [%2d, %2d] in [%2d, %2d], dirty: %s\n", b, e, get(i).b, get(i).e, get(i).dirty() ? "true" : "false");
		if (get(i).eq(b, e)) return (qtype == 3 ? get(i).n : get(i).m);
		if (get(i).dirty()) {
			//printf("dirty\n");
			assert(get(i).tag == 0 or get(i).tag == 1 or get(i).tag == 2);
			do_alter(li(i), get(i).tag);
			do_alter(ri(i), get(i).tag);
			get(i).clear();
		}
		l = query(li(i), max(b, lc(i).b), min(e, lc(i).e), qtype);
		r = query(ri(i), max(b, rc(i).b), min(e, rc(i).e), qtype);
		if (qtype == 3) return l + r;
		m = max(l, r);
		if (get(i).spl(b, e)) {
			l = min(lc(i).r, lc(i).e-b+1);
			r = min(rc(i).l, e-rc(i).b+1);
			m = max(m, l+r);
		}
		return m;
	}
};

segtree seg(2*N);

bool read(void)
{
	int i;

	if (scanf("%d%d", &nns, &nqs) != 2)
		return false;
	forn(i, nns) scanf("%d", &a[i]);

	return true;
}

void solve(void)
{
	int i;
	int c, x, y;
	int qres;

	seg.build();
	//seg.print();
	forn(i, nqs) {
		scanf("%d%d%d", &c, &x, &y);
		switch(c) {
			case 0:
			case 1:
			case 2:
				//printf("\nalter: %2d: [%2d, %2d]\n", c, x, y);
				seg.alter(1, x, y, c);
				//seg.print();
				//puts("");
				break;
			case 3:
			case 4:
				//printf("\nquery: %2d: [%2d, %2d]\n", c, x, y);
				qres = seg.query(1, x, y, c);
				printf("%d\n", qres);
				//seg.print();
				//puts("");
				break;
			default:
				assert(false);
		}
	}
}

int main(void)
{
	int ncases;

	freopen("Inputs/3397", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d", &ncases);
	while (--ncases >= 0) {
		read();
		solve();
	}

	return 0;
}
