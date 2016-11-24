/**
 * 3308
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

#define N (1<<17)

int n, m;
int a[N];

struct Node {

	Segitv itv;
	int x, y, z;

	template <class Data> void create(const int i, const Data *a, const int n) {
		if (ir(i, 0, n)) {
			x = y = z = 1;
		} else {
			x = y = z = 0;
		}
	}

	inline void update(struct Node &left, struct Node &rght) {
		x = left.x;
		y = rght.y;
		z = max(left.z, rght.z);
		if (a[left.itv.e] < a[rght.itv.b]) {
			if (left.x == left.itv.len())
				x += rght.x;
			if (rght.y == rght.itv.len())
				y += left.y;
			int w = max3(x, y, left.y+rght.x);
			z = max(z, w);
		}
	}

	inline void print(int segidx) {
		printf("\t%3d: [%3d, %3d]: %3d, %3d, %3d\n", segidx, itv.b, itv.e, x, y, z);
	}
};

bool read(void) {
	int i;
	scanf("%d%d", &n, &m);
	forn(i, n) scanf("%d", &a[i]);
	//printa(a, a+n, "a: ");
	return true;
}

int query(int b, int e, int i, const Segtree<Node> &seg) {
	Segtree<Node>::Pos pos;

	pos.at(i, seg);
	assert(pos.curr->itv.ct(b, e));

	if (pos.curr->itv.eq(b, e))
		return pos.curr->z;

	if (pos.left->itv.ct(b, e))
		return query(b, e, pos.lefti, seg);
	if (pos.rght->itv.ct(b, e))
		return query(b, e, pos.rghti, seg);

	int x = query(b, pos.left->itv.e, pos.lefti, seg);
	int y = query(pos.rght->itv.b, e, pos.rghti, seg);
	int z = 0;
	if (a[pos.left->itv.e] < a[pos.rght->itv.b]) {
		int lend = max(b, pos.left->itv.e-pos.left->y+1);
		int rend = min(e, pos.rght->itv.b+pos.rght->x-1);
		z = rend - lend + 1;
	}
	return max3(x, y, z);
}

void solve(void) {
	int x, y, z;
	char cmd[2];
	Segtree<Node> seg;

	seg.build(a, n);
	//printt("build", 2);
	//seg.print();

	while (--m >= 0) {
		scanf("%s%d%d", cmd, &x, &y);
		//printf("%s %3d, %3d\n", cmd, x, y);
		switch (cmd[0]) {
		case 'Q':
			//printa(a, a+n, "a: ");
			//printt("query", 2);
			//seg.print();
			z = query(x, y, 1, seg);
			//printdzl("%d\n", z);
			printf("%d\n", z);
			break;
		case 'U':
			a[x] = y;
			seg.update(x+seg.round);
			break;
		default:
			assert(false);
		}
	}
}

int main(void) {
	int ncases;

	freopen("Inputs/3308", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d", &ncases);
	while (--ncases >= 0) {
		read();
		solve();
	}

	return 0;
}
