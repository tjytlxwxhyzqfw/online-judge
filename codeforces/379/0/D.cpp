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

using namespace std;

#define A 1000000000

int n, x, y;
int  d[8];
char t[8];

bool read(void) {
	if (scanf("%d", &n) != 1)
		return false;
	scanf("%d%d", &x, &y);
	//printf("(x, y): (%d, %d)\n", x, y);
	return true;
}

void updated(int drt, int cord, char ch, bool small) {
	bool bval;

	if (small)
		bval = cord < d[drt];
	else
		bval = cord > d[drt];

	if (bval) {
		//printf("\tupdate: d[%2d]: %2d ---> ", drt, d[drt]);
		d[drt] = cord;
		t[drt] = ch;
		//printf("%2d\n", d[drt]);
	}
}

void solve(void) {
	int i, j, k;
	char c[2];
	bool check;

	forn(k, 8) t[k] = 0;
	d[0] = d[2] = d[4] = d[6] = -1 * (A+1);
	d[1] = d[3] = d[5] = d[7] = A+1;


	forn(k, n) {
		scanf("%s%d%d", c, &i, &j);
		//printf("read: %c %2d %2d\n", c[0], i, j);

		if (j == y) {
			//left
			if (i < x) updated(0, i, c[0], false);
			//rght
			if (i > x) updated(1, i, c[0], true);
		}
		if (i == x) {
			//down
			if (j < y) updated(2, j, c[0], false);
			//up
			if (j > y) updated(3, j, c[0], true);
		}
		if (i+j == x+y) {
			//left-up
			if (i < x) updated(4, i, c[0], false);
			//rght-down
			if (i > x) updated(5, i, c[0], true);
		}
		if (i-j == x-y) {
			//left-down
			if (j < y) updated(6, j, c[0], false);
			//rgbht-up
			if (j > y) updated(7, j, c[0], true);
		}
	}

	check = false;
	forn(i, 8) if (t[i] == 'Q') check = true;
	forn(i, 4) if (t[i] == 'R') check = true;
	forr(i, 4, 8) if (t[i] == 'B') check = true;

	printf("%s\n", check ? "YES" : "NO");
}

int main(void) {
	freopen("Inputs/D", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) {
		solve();
	}

	return 0;
}
