/**
 * B
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <map>
#include <string>
#include <vector>

#include "common.h"

using namespace std;

#define N 1000

int n, m;
int a[N][N], b[N][N];

bool read(void) {
	int i, j;

	if (scanf("%d%d", &n, &m) != 2)
		return false;

	forn(i, n) forn(j, m) scanf("%d", &a[i][j]);
	forn(i, n) forn(j, m) b[j][i] = a[i][j];

	/*
	forn(i, n) {
		forn(j, m)
			printf("%2d ", a[i][j]);
		printf("\n");
	}

	forn(i, m) {
		forn(j, n)
			printf("%2d ", b[i][j]);
		printf("\n");
	}
	*/

	return true;
}

int handle(int *arr, int len) {
	int i;
	int first, last;
	int res;

	//forn(i, len) printf("%d%s", arr[i], tailer(i, len-1));

	forn(first, len) if (arr[first] == 1) break;
	rfor(last, len) if (arr[last] == 1) break;

	//printf("\tfirst: %d, last: %d\n", first, last);

	res = 0;

	forr(i, first, last) {
		if (arr[i] == 0) {
			res += 2;
			//printf("\t\t+2 @ %d\n", i);
		}
	}

	if (first <= last) {
		res += first;
		res += len - last - 1;
		//printf("\t\t+%d, +%d\n", first, len-last-1);
	}

	//printf("\tres: %d\n", res);
	return res;
}

void solve(void) {
	int i;
	int res;

	res = 0;
	forn(i, n) res += handle(a[i], m);
	forn(i, m) res += handle(b[i], n);

	printf("%d\n", res);
}

int main(void) {
	freopen("Inputs/B", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) {
		solve();
	}

	return 0;
}
