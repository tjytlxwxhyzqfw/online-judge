/**
 * 4302
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <map>
#include <vector>

#include "common.h"

using namespace std;

#define N 100000

template <class T> struct great: public binary_function<T, T, bool> {
	bool operator() (const T &x, const T &y) {
		return y < x;
	}
};

int len, nqs;
int a[N];
map<int, int, great<int> > left;
map<int, int> rght;

void read(void) {
	scanf("%d%d", &len, &nqs);
}

void solve(int cid) {
	int idx, dir, total;
	int x, cmd;
	map<int, int>::iterator it, jt;
	int l, r, ld, rd;

	left.clear(), rght.clear();
	idx = 0, dir = 1, total = 0;
	while (--nqs >= 0) {
		scanf("%d", &cmd);
		switch (cmd) {
		case 0:
			scanf("%d", &x);
			left[x] += 1, rght[x] += 1;
			//printf("%d %d\n", cmd, x);
			break;
		case 1:
			it = left.lower_bound(idx);
			jt = rght.lower_bound(idx);
			l = (it == left.end() ? -2*len : it->first);
			r = (jt == rght.end() ? 3*len : jt->first);

			//printf("l: %d, r: %d\n", l, r);

			if (l < 0 && r >= len)
				break;

			ld = idx - l, rd = r - idx;
			if (ld < rd) {
				total += ld;
				idx = l;
				dir = -1;
			} else if (ld > rd) {
				total += rd;
				idx = r;
				dir = 1;
			} else {
				total += ld;
				idx = (dir < 0 ? l : r);
			}
			//printf("idx: %d\n", idx);
			left[idx] -= 1, rght[idx] -= 1;
			if (left[idx] == 0) {
				assert(rght[idx] == 0);
				left.erase(idx), rght.erase(idx);
			}
			break;
		default:
			assert(false);
		}
	}

	printf("Case %d: %d\n", cid, total);
}

int main(void) {
	int ncs, cid;

	freopen("Inputs/4302", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d", &ncs);
	forn(cid, ncs) {
		read();
		solve(cid+1);
	}

	return 0;
}
