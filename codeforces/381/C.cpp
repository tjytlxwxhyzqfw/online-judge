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

#define N 200000

int n, m;
int mex;
int a[N];

bool read(void) {
	int i;
	int left, rght;

	if (scanf("%d%d", &n, &m) != 2)
		return false;

	mex = 2*N;
	forn(i, m) {
		scanf("%d%d", &left, &rght);
		if (left > rght)
			swap(left, rght);
		mex = min(mex, rght-left+1);
	}

	return true;
}

void solve(void) {
	int i, current;

	current = 0;
	forn(i, n) {
		a[i] = current;
		current = (current+1) % mex;
	}

	printf("%d\n", mex);
	forn(i, n) printf("%d%s", a[i], tailer(i, n-1));
}

int main(void) {
	freopen("Inputs/C", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) {
		solve();
	}

	return 0;
}
