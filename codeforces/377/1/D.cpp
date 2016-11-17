/**
 * 377D
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <map>
#include <string>
#include <vector>

#include "common.h"

void printa(int *b, int *e) {
	int *p;
	for (p = b; p != e; ++p)
		printf("%d ", *p);
	printf("\n");
}

using namespace std;

#define N 100000

int n, m;
int a[N], b[N+1], c[N];
bool t[N+1];

int skp(const int b, const int tar) {
	int nds, j;

	if (tar == 0) return b;

	nds = 0;
	rfore(j, b) {
		if (c[j] != 0) continue;
		c[j] = -1;
		++nds;
		if (nds == tar) break;
	}

	return j;
}


bool check(const int k) {
	int i, j, eid;

	copy(a, a+k+1, c);
	fill(t+1, t+m+1, false);

	//printf("check: k: %d\n", k);
	//printa(c, c+k);

	rfore(i, k) {
		eid = c[i];
		if (eid <= 0) continue;
		t[eid] ? c[i] = 0 : t[eid] = true;
	}
	forre(i, 1, m) if (!t[i]) return false;

	j = k;
	rfore(i, k) {
		eid = c[i];
		if (eid <= 0) continue;
		j = skp(min(j, i), b[eid]);
		if (j < 0) break;
	}
	return i == -1;
}

bool read(void) {
	int i;
	if (scanf("%d%d", &n, &m) != 2)
		return false;
	forn(i, n) scanf("%d", &a[i]);
	forre(i, 1, m) scanf("%d", &b[i]);
	return true;
}

void solve(void) {
	int i, j, k, ans;
	bool ck;

	i = 0, j = n-1, ans = -2;
	while (i <= j) {
		k = (i+j) / 2;
		ck = check(k);
		//printf("[%2d, %2d]: %2d: %s\n", i, j, k, blstr(ck));
		if (ck) {
			ans = k;
			j = k - 1;
		} else {
			i = k + 1;
		}
	}

	printf("%d\n", ans+1);
}

int main(void) {
	freopen("Inputs/D", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) solve();

	return 0;
}
