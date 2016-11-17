/**
 * 5878
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"

using namespace std;

#define B 32
#define N 1000000000

int m;
int a[B*B*B*B];

void init(void) {
	int i, j, k, l;
	long long ipow, jpow, kpow, lpow;

	m = 0;
	forn(i, 32) {
		ipow = (i == 0 ? 1 : ipow*2);
		if (ipow < 0 || ipow > N) break;
		forn(j, 32) {
			jpow = (j == 0 ? ipow : jpow*3);
			if (jpow < 0 || jpow > N) break;
			forn(k, 32) {
				kpow = (k == 0 ? jpow : kpow*5);
				if (kpow < 0 || kpow > N) break;
				forn(l, 32) {
					lpow = (l == 0 ? kpow : lpow*7);
					if (lpow < 0 || lpow > N) break;
					a[m++] = (int)lpow;
				}
			}
		}
	}

	sort(a, a+m);
	//forn(i, 100) printf("%d\n", a[i]);
	//forr(i, m-100, m) printf("%d\n", a[i]);
}

void solve(void) {
	int i, j, k, x, idx;

	scanf("%d", &x);

	i = 0, j = m - 1;
	while (i <= j) {
		k = (i+j) / 2;
		if (a[k] < x) {
			i = k + 1;
		} else {
			idx = k;
			j = k - 1;
		}
	}

	printf("%d\n", a[idx]);
}

int main(void) {
	int ncs;

	//freopen("Inputs/5878", "r", stdin);
	//setbuf(stdout, NULL);
	init();
	scanf("%d", &ncs);
	while (--ncs >= 0) solve();

	return 0;
}
