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

#define N 502

struct monster {
	int idx, cidx;
	int weight;
	int left, rght;

	void init(int i, int w) {
		cidx = idx = i;
		weight = w;
		left = i-1;
		rght = i+1;
	}

	inline void destroy(void) {
		left = rght = -1;
		weight = INT_MAX;
	}

	void print(void) {
		printf("%2d: (%2d, %2d): %3d\n", idx, left, rght, weight);
	}
};

int n, m;
monster msts[N];
int b[N];
int rec[N][2], ridx;


void record_init(void) {
	ridx = 0;
}

void record(int i, int d) {
	rec[ridx][0] = i;
	rec[ridx][1] = d;
	++ridx;
}

void record_print(void) {
	int i;

	forn(i, ridx) {
		printf("%d %s\n", rec[i][0],  rec[i][1] == 0 ? "L" : "R");
	}
}

void check(void) {
	int i;
	forre(i, 1, n) {
		////printf("\t\t\tcheck:");
		//msts[i].print();
		if (msts[i].left != -1)
			assert(msts[msts[i].left].rght == i);
		if (msts[i].rght != -1)
			assert(msts[msts[i].rght].left == i);
	}
}

void fresh(void) {
	int i, j;

	i = 1;
	forre(j, 1, n) {
		if (msts[j].weight == INT_MAX) continue;
		msts[j].cidx = i++;
	}
}


void eat(monster &mst, monster &food) {
	mst.weight += food.weight;
	if (food.idx < mst.idx) {
		mst.left = food.left;
		msts[mst.left].rght = mst.idx;
		record(mst.cidx, 0);
	} else {
		mst.rght = food.rght;
		msts[mst.rght].left = mst.idx;
		record(mst.cidx, 1);
	}
	food.destroy();

	fresh();
	check();
}

inline bool can_eat(int j, int i, int bgn, int end) {
	return bgn <= i && i <= end && msts[j].weight > msts[i].weight;
}


int raise(int boss, int target, int bgn, int end) {
	monster *curr, *rght, *left;

	//printf("\traise: target: %d\n", target);

	curr = &msts[boss];
	//printf("\t\tcurr:");
	//curr->print();

	while (curr->weight != target) {
		rght = &msts[curr->rght];
		left = &msts[curr->left];
		if (can_eat(curr->idx, rght->idx, bgn, end)) {
			eat(*curr, *rght);
		} else if (can_eat(curr->idx, left->idx, bgn, end)) {
			eat(*curr, *left);
		} else {
			break;
		}
		//printf("\t\tcurr:");
		//curr->print();
	}

	if (curr->weight == target) {
		//printf("\traise: ok\n");
		return curr->rght;
	} else {
		//printf("\traise: cannot\n");
		return -1;
	}
}

bool read(void) {
	int i, weight;

	record_init();

	if (scanf("%d", &n) != 1)
		return false;

	msts[0].init(0, 0);
	msts[n+1].init(n+1, 0);

	forre(i, 1, n) {
		scanf("%d", &weight);
		msts[i].init(i, weight);
	}

	//printf("---read---\n");
	//forre(i, 0, n+1) msts[i].print();
	//printf("---read end---\n\n");

	scanf("%d", &m);
	forn(i, m) scanf("%d", &b[i]);

	return true;
}

int find(int bgn, int tar, int *boss) {
	int weight, maxw;
	int i;

	weight = 0;
	maxw = 0, *boss = -1;
	forre(i, bgn, n) {
		weight += msts[i].weight;
		////printf("\t\t\t\tfind: i: %2d, weight: %2d\n", i, weight);
		if (msts[i].weight > maxw) {
			maxw = msts[i].weight;
			*boss = i;
		}
		if (weight >= tar) break;
	}

	return weight == tar ? i : -1;
}

void solve(void) {
	int i, bgn, end, boss;
	int ok;
	int j, weight;

	ok = true;
	bgn = 1;
	forn(i, m) {
		//printf("bgn: %3d, target: %3d\n", bgn ,b[i]);
		end = find(bgn, b[i], &boss);
		if (end == -1) {
			ok = false;
			break;
		}

		if (bgn == end)
			goto raise_mst;

		weight = msts[boss].weight;
		forre(j, bgn, end) {
			if (msts[j].weight != weight) continue;
			if (can_eat(j, msts[j].left, bgn, end)) break;
			if (can_eat(j, msts[j].rght, bgn, end)) break;
		}

		if (j > end) {
			ok = false;
			break;
		}

		boss = j;

		//printf("target: %3d: [%3d, %3d]\n", b[i], bgn, end);

		raise_mst:
		bgn = raise(boss, b[i], bgn, end);
		if (bgn == -1) {
			ok = false;
			break;
		}
	}

	if (bgn != n+1)
		ok = false;

	printf("%s\n", ok ? "YES" : "NO");
	if (ok) record_print();
}

int main(void) {
	freopen("Inputs/C", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) solve();

	return 0;
}
