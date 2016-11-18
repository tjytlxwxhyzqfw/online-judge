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

#define N 100000

int n, m, a[N], b[N+1];

bool read(void) {
	int i;
	if (scanf("%d%d", &n, &m) != 2)
		return false;
	//! forn(i, 7) ????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
	forn(i, n) scanf("%d", &a[i]);
	forre(i, 1, m) scanf("%d", &b[i]);
	return true;
}

bool check(const int k) {
	bool f[N+1];
	int c[N]; //copy of a
	int i, j, ndays;

	//puts("\tcheck weather all exams attend");
	fill(f, f+N+1, false);
	fore(i, k) {
		//printf("a[%d]: %d\n", i, a[i]);
		f[a[i]] = true;
	}
	forre(i, 1, m) {
		//printf("i: %d\n", i);
		if (f[i] == false) {
			//printf("\tmissing exam: %d\n", i);
			return false;
		}
	}

	//puts("\tloading c[]");

	fill(f, f+N+1, false);
	fill(c, c+N, 0);
	rfore(i, k) {
		if (a[i] == 0) continue;
		//printf("a[%2d]: %2d\n", i, a[i]);
		if (f[a[i]] == false) {
			//printf("copy c[%2d]: %2d\n", i, a[i]);
			c[i] = a[i];
			f[a[i]] = true;
		}
	}

	//printf("\t");
	//fore(i, k) printf("%d%s", c[i], tailer(i, k));

	//printf("\ti, j, cover\n");

	j = k+1; //j: day, c[j]: exam/rest
	rfore(i, k) { // i: day, c[i]: exam/rest
		if (c[i] == 0) continue;
		//printf("\ti: %2d, j: %2d\n", i, j);
		ndays = b[a[i]];
		if (ndays == 0) continue;
		//printf("\tndays: %d\n", ndays);
		for (j = min(j-1, i-1); j >= 0; --j) {
			//! IF (J == 0) !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			if (c[j] == 0) {
				--ndays;
				if (ndays == 0)
					break;
			}
		}
		if (j < 0) return false;
	}

	return true;
}

void solve(void) {
	int i, j, k, ans;
	bool ck;

	//printf("solve begin\n");

	ans = -2;

	i = 0, j = n-1;
	while (i <= j) {
		k = (i+j) / 2;
		//printf("check: k: %d\n", k);
		ck = check(k);
		//printf("[%2d, %2d]: %2d: %s\n", i, j, k, blstr(ck));
		if (ck) {
			ans = k;
			j = k - 1;
		} else {
			i = k + 1;
		}
	}

	//printf("---------------------------------------->%d\n", ans+1);
	printf("%d\n", ans+1);
}

int main(void) {
	freopen("Inputs/D", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) {
		solve();
	}

	return 0;
}
