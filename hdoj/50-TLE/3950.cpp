/**
 * 3950
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"
#include "segtree.h"

using namespace std;

#define N 65536
#define Q 100000

int n, nqs;
int eds[N];

struct segnode: public segnode_base {
	int l, r, m;
	int tag;

	inline bool c(void) const { return len() == m; };
	inline bool dirty(void) const { return tag != -1; }
	inline int lbrk(void) const { return b + l; }
	inline int rbrk(void) const { return e - r; }
	inline void clear(void) { tag = -1; }
	inline void merge(int t) { tag = t; }
};

struct segtree: public segtree_base<segnode> {
	segtree(int sz) { init(sz); }
	inline int m(void) const { return seg[1].m; }

	void update(int i) {
		segnode *curr, *left, *rght;
		curr = &seg[i], left = &seg[li(i)], rght = &seg[ri(i)];
		curr->l = left->l + (left->c() ? rght->l : 0);
		curr->r = rght->r + (rght->c() ? left->r : 0);
		curr->m = max3(left->m, rght->m, left->r+rght->l);
	}

	void build(void) {
		int i;
		builditv();
		forn(i, size) seg[i].clear();
		forn(i, half) arr[i].l = arr[i].r = arr[i].m = (i < n ? 1 : 0);
		for (i = lstprt(); valiad(i); --i) update(i);
	}

	inline void do_alter(int i, int tag) {
		get(i).merge(tag);
		get(i).l = get(i).r = get(i).m = (1-tag) * get(i).len();
	}

	inline void push_down(int i) {
		do_alter(li(i), get(i).tag);
		do_alter(ri(i), get(i).tag);
		get(i).clear();
	}

	void alter(int i, int b, int e, int tag) {
		if (not itvld(i, b, e)) return;
		if (get(i).eq(b, e)) return do_alter(i, tag);
		if (get(i).dirty()) push_down(i);
		alter(li(i), max(b, get(li(i)).b), min(e, get(li(i)).e), tag);
		alter(ri(i), max(b, get(ri(i)).b), min(e, get(ri(i)).e), tag);
		update(i);
	}

	int queryb(int i, int m, int l, int r, int b, int &e) {
		int x;

		if (get(i).m < m) {
			e = -1;
			return -1;
		}

		if (eds[b] != -1) b = eds[b]+1;

		if (get(i).e < b) {
			e = -1;
			return -1;
		}

		if (get(i).p()) {
			e = get(i).e;
			return get(i).b;
		}

		if (get(i).dirty()) push_down(i);

		//printf("\tqueryb in [%2d, %2d] (%2d, %2d, %2d) >=%2d\n", get(i).b, get(i).e, m, l, r, b);

		/* goto left */
		//printf("\t[%5d, %5d]: left\n", get(i).b, get(i).e);
		x = queryb(li(i), m, l, r, b, e);
		//printf("\t[%5d, %5d]: return from left: [%2d, %2d]\n", get(i).b, get(i).e, x, e);
		if (x != -1 and (e != lc(i).e or e-x+1 + rc(i).l <= m+l+r)) {
				if (e == lc(i).e) e = rc(i).lbrk() - 1;
				return x;
		}

		/* left + rght */
		//printf("\t[%5d, %5d]: left + rght: %3d + %3d\n", get(i).b, get(i).e, lc(i).r, rc(i).l);
		x = max(0, min(lc(i).r, lc(i).e-b+1)) + min(rc(i).l, rc(i).e-b+1);
		//printf("\t[%5d, %5d]: x: %d\n", get(i).b, get(i).e, x);
		if (m <= x and x <= m+l+r) {
			e = rc(i).lbrk()-1;
			//printf("\t[%5d, %5d]: return: [%2d, %2d]\n", get(i).b, get(i).e, lc(i).rbrk()+1, e);
			return lc(i).rbrk()+1;
		}

		/* goto right */
		//printf("\t[%5d, %5d]: rght\n", get(i).b, get(i).e);
		x = queryb(ri(i), m, l, r, rc(i).lbrk(), e);
		//printf("\t[%5d, %5d]: return from rght: x: [%2d, %2d]\n", get(i).b, get(i).e, x, e);
		if (x == -1) return x;
		if (x == rc(i).b) x = lc(i).rbrk()+1;
		return e-x+1 <= l+m+r ? x : -1;
	}

	int queryl(int i, int x) {
		if (get(i).p()) return get(i).m;
		if(get(i).dirty()) push_down(i);
		if (lc(i).rbrk() < x and x < rc(i).lbrk())
			return rc(i).lbrk() - lc(i).rbrk() - 1;
		if (lc(i).ct(x, x)) return queryl(li(i), x);
		assert(rc(i).ct(x, x));
		return queryl(ri(i), x);
	}
};

struct motor {
	static bool cmp(const motor &x, const motor &y) { return x.b < y.b; }
	int b, e;
};

segtree seg(2*N);

struct segnodek: public segnode_base {
	int h;
};

struct segtreek: public segtree_base<segnodek> {
	segtreek(int sz) { init(sz); }
	inline void update(int i) { get(i).h = lc(i).h + rc(i).h; }

	void build(void) {
		int i;
		builditv();
		forn(i, size) seg[i].h = 0;
	}

	void alter(int i, int tag) {
		i += half;
		seg[i].h = tag;
		for (i = prt(i); valiad(i); i = prt(i)) update(i);
	}

	int query(int i, int k) {
		if (get(i).p()) {
			assert(k == 1);
			return i-half;
		}
		if (lc(i).h >= k) return query(li(i), k);
		return query(ri(i), k-lc(i).h);
	}

	int query_itv(int bgn, int end) {
		int res = 0;
		int b, e;

		b = bgn+half, e = end+half;
		while (b <= e and valiad(b)) {
			if (atr(b)) res += get(b).h;
			if (atl(e)) res += get(e).h;
			b = re(b), e = le(e);
		}

		return res;
	}

	inline bool first(int b) { return query_itv(0, b) == 0; }
	inline bool last(int b) { return query_itv(b, n-1) == 0; }
	inline bool ctk(int k) { return seg[1].h >= k; }
};

/* 因为两颗线段树的标记是严格区分了的,所以
 * 每个线段树都有一套单独的alter和query过程
 * 所以,用了两个线段树
 */ 
segtreek segk(2*N);

bool read(void)
{
	scanf("%d%d", &n, &nqs);
	return true;
}

void solve(int cid)
{
	char cmd[2];
	int m, pl, pr, idx;
	int b, e, l, i;
	int le, re;

	fprintf(stdout, "Case #%d:\n", cid);

	seg.build();
	segk.build();
	forn(i, n) eds[i] = -1;

	forn(i, nqs) {
		scanf("%s", cmd);
		switch (cmd[0]) {
		case 'A':
			scanf("%d%d%d", &m, &pl, &pr);
			/* check head */
			l = seg.queryl(1, 0);
			//printf("\t-->head: l: %d\n", l);
			if (l >= m) {
				if (segk.seg[1].h == 0) b = 0;
				else {
					l = seg.queryl(1, 0);
					b = max(0, l-m-pr);
				}
				goto add;
			}

			/* check middle */
			//printf("\t-->middle\n");
			le = seg.queryl(1, 0);
			re = seg.queryl(1, n-1);
			if (le > 0) seg.alter(1, 0, le-1, 1);
			if (re > 0) seg.alter(1, n-re, n-1, 1);
			
			b = seg.queryb(1, m, pl, pr, 0, e);
			if (b != -1) {
				l = seg.queryl(1, b);
				//printf("\tb: %d, l: %d\n", b, l);
				if (not segk.last(b)) b += max(0, l-m-pr);

				if (le > 0) seg.alter(1, 0, le-1, 0);
				if (re > 0) seg.alter(1, n-re, n-1, 0);
				goto add;
			}
			if (le > 0) seg.alter(1, 0, le-1, 0);
			if (re > 0) seg.alter(1, n-re, n-1, 0);

			/* check tail */
			l = seg.queryl(1, n-1);
			//printf("\t-->tail: l: %d\n", l);
			if (l >= m) {
				b = n - l;
				goto add;
			}

			fprintf(stdout, "-1\n");
			break;

			add:
			e = b + m - 1;
			fprintf(stdout, "%d\n", b+1);
			seg.alter(1, b, e, 1);
			segk.alter(b, 1);
			eds[b] = e;
			break;
		case 'B':
			scanf("%d", &idx);
			if (not segk.ctk(idx)) break;
			b = segk.query(1, idx);
			seg.alter(1, b, eds[b], 0);
			segk.alter(b, 0);
			eds[b] = -1;
			break;
		default:
			assert(false);
		}
	}
}

int main(void)
{
	int ncs, cid;

	freopen("Inputs/3950", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d", &ncs);
	forre(cid, 1, ncs) {
		read();
		solve(cid);
	}

	return 0;
}
