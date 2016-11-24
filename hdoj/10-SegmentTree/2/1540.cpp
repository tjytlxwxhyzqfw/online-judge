/**
 * 1540
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <map>
#include <stack>
#include <string>
#include <vector>

#include "common.h"
#include "lightseg.h"

using namespace std;

struct Node {
	Segitv itv;
	int x, y;
	bool z;

	template <class Data> void create(const int i, const Data *a, const int n) {
		if (ir(i, 0, n)) {
			z = true;
			x = y = 1;
		} else {
			z = false;
			x = y = 0;
		}
	}

	inline void update(const Node &left, const Node &rght) {
		x = left.x;
		y = rght.y;
		z = left.z && rght.z;
		if (left.z)
			x += rght.x;
		if (rght.z)
			y += left.y;
	}

	inline void print(int segidx) {
		printf("\t%3d: [%3d, %3d]: (%3d, %3d, %6s)\n", segidx, itv.b, itv.e, x, y, blstr(z));
	}

	inline void destroy(void) {
		assert(itv.p());
		x = y = 0;
		z = false;
	}

	inline void restore(void) {
		assert(itv.p());
		x = y = 1;
		z = true;
	}
};


#define N 50000

int n, m;

bool read(void) {
	if (scanf("%d%d", &n, &m) != 2)
		return false;
	return true;
}

void query(const int target, const int i, const Segtree<Node> &seg, int *left, int *rght) {
	Segtree<Node>::Pos pos;

	pos.at(i, seg);
	assert(pos.curr->itv.ct(target, target));

	if (pos.curr->itv.eq(target, target)) {
		*left = *rght = target;
		return;
	}

	if (pos.left->itv.ct(target, target))
		query(target, pos.lefti, seg, left, rght);
	if (pos.rght->itv.ct(target, target))
		query(target, pos.rghti, seg, left, rght);
	if (*rght+1 == pos.rght->itv.b)
		*rght += pos.rght->x;
	if (*left-1 == pos.left->itv.e)
		*left -= pos.left->y;

	assert(ir(*left, 0, n));
	assert(ir(*rght, 0, n));
}

void solve(void) {
	Segtree<Node> seg;
	stack<int> stk;
	char cmd[2];
	int i, idx, segidx, left, rght;

	seg.build<int>(NULL, n);
	//seg.print((char*)"------ build ------>\n");
	forn(i, m) {
		scanf("%s", cmd);
		//printf("------ cmd ------> %s\n", cmd);
		switch(cmd[0]) {
		case 'D':
			scanf("%d", &idx);
			--idx;
			//printf("idx: %d\n", idx);
			stk.push(idx);
			segidx = idx + seg.round;
			seg[segidx].destroy();
			seg.update(segidx);
			break;
		case 'Q':
			scanf("%d", &idx);
			--idx;
			//printf("idx: %d\n", idx);
			//seg.print();
			if (seg.arr[idx].z == false) {
				left = 0, rght = -1;
			} else {
				query(idx, 1, seg, &left, &rght);
			}
			//printf("---> result: [%3d, %3d]\n", left, rght);
			printf("%d\n", rght-left+1);
			break;
		case 'R':
			if (!stk.empty()) {
				idx = stk.top();
				stk.pop();
				segidx = idx + seg.round;
				seg[segidx].restore();
				seg.update(segidx);
			}
			break;
		default:
			assert(false);
		}
	}
}

int main(void) {
	freopen("Inputs/1540", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) {
		solve();
	}

	return 0;
}
