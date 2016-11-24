/**
 * 1754
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <map>
#include <string>
#include <vector>

#include "common.h"
#include "lightseg.h"

using namespace std;

struct Node {
	Segitv itv;
	int x;

	template <class Data> void create(int i, Data *a, int n) {
		if (ir(i, 0, n)) {
			x = a[i];
		} else {
			x = 0;
		}
	}

	inline void update(const Node &left, const Node &rght) {
		x = max(left.x, rght.x);
	}

	inline void print(int segidx) {
		printf("\t%3d: [%3d, %3d]: %d\n", segidx, itv.b, itv.e, x);
	}
};

#define N 200000

int n, m;
int a[N];

bool read(void) {
	int i;

	if (scanf("%d%d", &n, &m) != 2)
		return false;

	forn(i, n) scanf("%d", &a[i]);
	//printa(a, a+n, "a: ");

	return true;
}

int query(const int b, const int e, const int i, const Segtree<Node> &seg) {
	Segtree<Node>::Pos pos;

	pos.at(i, seg);
	assert(pos.curr->itv.ct(b, e));

	if (pos.curr->itv.eq(b, e))
		return pos.curr->x;
	if (pos.left->itv.ct(b, e))
		return query(b, e, pos.lefti, seg);
	if (pos.rght->itv.ct(b, e))
		return query(b, e, pos.rghti, seg);

	int x = query(b, pos.left->itv.e, pos.lefti, seg);
	int y = query(pos.rght->itv.b, e, pos.rghti, seg);

	return max(x, y);
}

void solve(void) {
	int i, segidx;
	int x, y, z;
	char cmd[3];
	Segtree<Node> seg;

	seg.build(a, n);
	//printt("build", 2);
	//seg.print();

	forn(i, m) {
		scanf("%s%d%d", cmd, &x, &y);
		//printf("%c: %3d, %3d\n", cmd[0], x, y);
		switch(cmd[0]) {
		case 'Q':
			--x, --y;
			z = query(x, y, 1, seg);
			//printdzl("%d\n", z);
			printf("%d\n", z);
			break;
		case 'U':
			--x;
			segidx = x + seg.round;
			seg[segidx].x = y;
			seg.update(segidx);
			//printt("upadte", 2);
			//seg.print();
			break;
		default:
			assert(false);
		}
	}
}

int main(void) {
	freopen("Inputs/1754", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) {
		solve();
	}

	return 0;
}
