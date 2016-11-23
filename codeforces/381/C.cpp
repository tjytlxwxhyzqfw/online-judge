/**
 * C
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <map>
#include <string>
#include <vector>

#include "common.h"

using namespace std;

struct Itv {

	static bool compare(const Itv &x, const Itv &y) {
		return x.b < y.b;
	}

	int b, e;

	inline void init(int b, int e) {
		this->b = b;
		this->e = e;
	}

	inline int len(void) const {
		return e - b + 1;
	}

	void print(void) {
		printf("[%3d, %3d]\n", b, e);
	}
};

#define N 200000

int n, m;
int mex;
int a[N];
Itv b[N];

inline int nxt(int cur) {
	return (cur+1) % mex;
}

inline int pre(int cur) {
	if (cur == 0)
		return mex - 1;
	return cur - 1;
}

bool read(void) {
	int i;
	int left, rght;

	if (scanf("%d%d", &n, &m) != 2)
		return false;

	mex = 2*N;
	forn(i, m) {
		scanf("%d%d", &left, &rght);
		--left, --rght;
		if (left > rght)
			swap(left, rght);
		mex = min(mex, rght-left+1);
		b[i].init(left, rght);
	}

	//printf("mex: %d\n", mex);

	forn(i, n) a[i] = -1;

	sort(b, b+m, Itv::compare);
	//printb(b, b+m, "sorted b", 2);

	return true;
}

void solve(void) {
	int i, idx, first;
	int current;
	int bgn, end;

	// shorest
	idx = 0;
	current = 0;
	forn(i, m) {
		if (b[i].len() != mex) continue;

		bgn = max(idx, b[i].b);
		end = b[i].e;

		if (bgn > end) continue;

		//printi(1, "idx: %3d, current: %3d, [%3d, %3d]\n", idx, current, bgn, end);

		forre(idx, bgn, end) {
			a[idx] = current;
			current = nxt(current);
		}
	}

	forn(first, m) {
		if (b[first].len() == mex)
			break;
	}

	//printf("first: %d\n", first);

	//normal
	current = 0;
	forr(i, b[first].b, n) {
		//printa(a, a+n, "a:");
		if (a[i] != -1) {
			current = nxt(a[i]);
		} else {
			a[i] = current;
			current = nxt(current);
		}
	}

	current = mex - 1;
	rforre(i, b[first].b-1, 0) {
		a[i] = current;
		current = pre(current);
	}

	printf("%d\n", mex);
	printa(a, a+n);
}

int main(void) {
	freopen("Inputs/C", "r", stdin);
	setbuf(stdout, NULL);

	int x[] = {12345, 12345, 12345};
	printa(x, x+3);

	while (read()) {
		solve();
	}

	return 0;
}
