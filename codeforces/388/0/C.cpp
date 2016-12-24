/**
 * C
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <map>
#include <stack>
#include <string>
#include <vector>

#include "common.h"

using namespace std;

#define N 200001

int n;
char a[N];
int f[N];

bool read(void) {
	scanf("%d", &n);
	scanf("%s", a);

	//printa(a, a+n, "a: ");
	return true;
}

int find(const char ch, const int from) {
	int i;

	forr(i, from+1, n) {
		if (a[i] == ch)
			return i;
	}

	return n;
}

int shrink(const int nskpd, const int nskpr) {
	bool haved = false, haver = false;
	int dcnt = nskpd, rcnt = nskpr;
	int i, j;

	//printf("shrink: nskpd: %3d, nskpr: %3d\n", nskpd, nskpr);

	j = 0;
	forn(i, n) {
		if (a[i] == 'D' && --dcnt < 0) {
			a[j++] = 'D';
			haved = true;
		}
		if (a[i] == 'R' && --rcnt < 0) {
			a[j++] = 'R';
			haver = true;
		}
	}
	n = j;

	//printa(a, a+n, "after shrink: ");

	if (haved && haver)
		return 0;
	else if (haved)
		return 'D';
	else if (haver)
		return 'R';

	assert(false);
	return 0;
}

void solve(void) {
	int i, di, ri;
	char curr;
	int nskpd, nskpr, shrink_res;

	start:
	ri = di = -1;
	nskpd = nskpr = 0;
	forn(i, n) {
		curr = a[i];
		if (curr != 'D' && curr != 'R')
			continue;
		if (curr == 'D') {
			ri = find('R', max(i, ri));
			if (ri == n)
				++nskpr;
			else
				a[ri] = 'r';
		} else if (curr == 'R') {
			di = find('D', max(i, di));
			if (di == n)
				++nskpd;
			else
				a[di] = 'd';
		} else assert(false);
	}

	shrink_res = shrink(nskpd, nskpr);

	if (shrink_res == 0)
		goto start;
	printf("%c\n", (char)shrink_res);
}

int main(void) {
	#ifdef DEBUG
	//freopen("Inputs/C", "r", stdin);
	setbuf(stdout, NULL);
	#endif

	read();
	solve();

	return 0;
}
