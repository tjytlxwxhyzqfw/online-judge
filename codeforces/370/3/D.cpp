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
#include "number.h"

using namespace std;

#define M 1000000007

#define K 1000
#define T 100

const int lm = -2 * T * K;

int a, b, k, t;
int d, leftv, rghtv;
int cur[4*T*K+1], lst[4*T*K+1];

bool read(void) {
	if (scanf("%d%d%d%d", &a, &b, &k, &t) != 4)
		return false;
	d = b - a;
	return true;
}

inline void initjrange(const int i) {
	rghtv = i * k;
	leftv = -1 * rghtv;
}

void mkps(int *a, const int bv, const int ev) {
	int j;
	forre(j, bv+1, ev) a[j-lm] = moduler<M>::pls(a[j-lm], a[j-1-lm]);
}

void prints(int i, int j) {
	printf("s[%2d][%5d]: %d\n", i, j, cur[j-lm]);
}

void solve(void) {
	int i, j;

	initjrange(2*t-1);
	forre(j, leftv, rghtv) {
		cur[j-lm] = min(2*k+1, max(0, k+j-d));
		//prints(2*t-1, j);
	}
	mkps(cur, leftv, rghtv);

	rfor(i, 2*t-1) {
		swap(cur, lst);
		initjrange(i);
		cur[leftv-lm] = lst[leftv+k-lm];
		//prints(i, leftv);
		forre(j, leftv+1, rghtv) {
			cur[j-lm] = lst[j+k-lm] - lst[j-k-1-lm];
			cur[j-lm] = moduler<M>::crc(cur[j-lm]);
			//prints(i, j);
		}
		mkps(cur, leftv, rghtv);
	}

	printf("%d\n", cur[0-lm]);
}

int main(void) {
	freopen("Inputs/D", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) solve();

	return 0;
}
