/**
 * D
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <map>
#include <string>
#include <vector>

#include "common.h"

#define BU 0
#define TD 1
#define LR 2
#define RL 3

#define SP -1
#define SO 0

using namespace std;

int bs(int x1, int y1, int x2, int y2, int bgn, int end, int mode, int type);

int size;

bool read(void) {
	if (scanf("%d", &size) != 1)
		return false;
	return true;
}

int query(const int x1, const int y1, const int x2, const int y2) {
	int ans;
	printf("? %d %d %d %d\n", x1, y1, x2, y2);
	fflush(stdout);
	scanf("%d", &ans);
	return ans;
}

void split(int *alpha, int *beta, int *gamma, int *delta) {
	int y, n;
	y = bs(1, 0, size, size, 1, size, SP, BU);
	n = (y == -1 ? -1 : query(1, 1, size, y-1));
	if (n == 1) {
		*alpha = 1;
		*beta = y;
		*gamma = size;
		*delta = *beta - 1;
	} else {
		*alpha = bs(0, 1, size, size, 1, size, SP, LR);
		*beta = 1;
		*gamma = *alpha - 1;
		*delta = size;
	}

	//printf("split: (%2d, %2d) (%2d, %2d)\n", *alpha, *beta, *gamma, *delta);
}

void solver(int x1, int y1, int x2, int y2, int *ans) {
	int left, bot, rght, top;

	//printf("solver entered\n");
	//printf("(%d, %d) (%d, %d)\n", x1, y1, x2, y2);

	left = bs(0, y1, x2, y2, x1, x2, SO, LR);
	//printf("\tsolver: left: %d\n", left);
	rght = bs(x1, y1, 0, y2, left, x2, SO, RL);
	//printf("\tsolver: rght: %d\n", rght);
	bot = bs(left, 0, rght, y2, y1, y2, SO, BU);
	//printf("\tsolver: bot: %d\n", bot);
	top = bs(left, bot, rght, 0, bot, y2, SO, TD);
	//printf("\tsolver: top: %d\n", top);
	ans[0] = left, ans[1] = bot, ans[2] = rght, ans[3] = top;

	//printf("solver quit\n");
}

void solve(void) {
	int alpha, beta, gamma, delta;
	int ans[8], i;
	split(&alpha, &beta, &gamma, &delta);	
	solver(1, 1, gamma, delta, ans);
	solver(alpha, beta, size, size, ans+4);
	printf("! ");
	forn(i, 8) printf("%d%s", ans[i], tailer(i, 7));
	fflush(stdout);
}

int main(void) {
	//freopen("Inputs/D", "r", stdin);
	//setbuf(stdout, NULL);

	while (read()) solve();

	return 0;
}

void expand(const int m, const int type, int *b, int *e) {
	if (type == BU || type == LR) {
		*e = m - 1;
	} else {
		assert(type == TD || type == RL);
		*b = m + 1;
	}
}

void shrink(const int m, const int type, int *b, int *e) {
	if (type == BU || type == LR) {
		*b = m + 1;
	} else {
		assert(type == TD || type == RL);
		*e = m - 1;
	}
}

/**
 * @param - mode: -1: split, oth: solve
 * @param - type: 0: bot->up, 1: top->down, 2: left->rght, 3: rght->left
 */
int bs(int x1, int y1, int x2, int y2, int bgn, int end, int mode, int type) {
	int q[4], i, n;
	int b, e;
	int ret;

	q[0] = x1, q[1] = y1, q[2] = x2, q[3] = y2;
	forn(i, 4) if (q[i] == 0) break;
	assert(i < 4);
	switch(type) {
	case BU:
		assert(i == 1);
		break;
	case TD:
		assert(i == 3);
		break;
	case LR:
		assert(i == 0);
		break;
	case RL:
		assert(i == 2);
		break;
	default:
		assert(false);
	}

	//printf("bs: mode: %2d, type: %2d, (%2d, %2d, %2d, %2d) (%2d, %2d) i: %2d\n", mode, type, x1, y1, x2, y2, bgn, end, i);

	ret = -1;
	b = bgn, e = end;	
	while (b <= e) {
		q[i] = (b+e) / 2;
		n = query(q[0], q[1], q[2], q[3]);
		//printf("\t[%2d, %2d]: %2d: %d\n", b, e, q[i], n);
		if (mode == SP) {
			assert(type == BU || type == LR);
			if (n == 0) {
				expand(q[i], type, &b, &e);
			} else if (n == 1) {
				ret = q[i];
				shrink(q[i], type, &b, &e);
			} else {
				assert(n == 2);
				shrink(q[i], type, &b, &e);
			}
		} else {
			assert(mode == SO);
			if (n == 0) {
				expand(q[i], type, &b, &e);
			} else if (n == 1) {
				ret = q[i];
				shrink(q[i], type, &b, &e);
			} else {
				assert(false);
			}
		}
	}

	return ret;
}
