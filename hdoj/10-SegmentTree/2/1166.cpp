/**
 * 1166
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

	void update(const Node &left, const Node &rght) {
		x = left.x + rght.x;
	}

	void print(int segidx) {
		printf("\t%3d: %d\n", segidx, x);
	}
};

#define N 50000

int n, a[N];

bool read(void) {
	int i;
	scanf("%d", &n);
	forn(i, n) scanf("%d", &a[i]);
	return true;
}

int query(int i, int b, int e, const Segtree<Node> &seg) {
	Segtree<Node>::Pos pos;

	//printf("\t\tquery: %3d: [%3d, %3d]\n", i, b, e);

	pos.at(i, seg);
	assert(pos.curr->itv.ct(b, e));
	if (pos.curr->itv.eq(b, e))
		return pos.curr->x;
	if (pos.left->itv.ct(b, e))
		return query(pos.lefti, b, e, seg);
	if (pos.rght->itv.ct(b, e))
		return query(pos.rghti, b, e, seg);
	int x = query(pos.lefti, b, pos.left->itv.e, seg);
	int y = query(pos.rghti, pos.rght->itv.b, e, seg);
	return x + y;
}


void solve(int casid) {
	char cmd[16];
	Segtree<Node> seg;
	int segidx, x, y, z;

	printf("Case %d:\n", casid);

	seg.build(a, n);
	//seg.print((char*)"after build:");
	while (true) {
		scanf("%s", cmd);
		//printf("cmd: %s\n", cmd);
		switch (cmd[0]) {
		case 'Q':
			scanf("%d%d", &x, &y);
			z = query(1, x-1, y-1, seg);
			//printf("----->%d\n", z);
			printf("%d\n", z);
			break;
		case 'A':
			scanf("%d%d", &x, &y);
			segidx = x - 1 + seg.round;
			seg[segidx].x += y;
			seg.update(segidx);
			//seg.print();
			break;
		case 'S':
			scanf("%d%d", &x, &y);
			segidx = x - 1 + seg.round;
			seg[segidx].x -= y;
			seg.update(segidx);
			//seg.print();
			break;
		case 'E':
			goto solved;
		default:
			assert(false);
		}
	}
	solved:
	return;
}

int main(void) {
	int ncases, casid;

	freopen("Inputs/1166", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d", &ncases);
	forn(casid, ncases) {
		read();
		solve(casid+1);
	}

	return 0;
}
