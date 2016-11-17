/**
 * 1176
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"

using namespace std;

#define N 100000

int n;
int v[N][11], s[N][11];

bool read(void)
{
	int i, nps;
	int x, t;

	if (scanf("%d", &nps) != 1)
		return false;
	if (nps == 0) return false;

	forn(t, N) fore(x, 10) v[t][x] = 0;

	n = 0;
	forn(i, nps) {
		scanf("%d%d", &x, &t);
		n = max(n, t);
		--t;
		v[t][x] += 1;
	}

	return true;
}

void solve(void)
{
	int i, j;

	fore(j, 10) {
		s[n-1][j] = v[n-1][j];
		if (j >= 1) s[n-1][j] = max(s[n-1][j], v[n-1][j-1]);
		if (j < 10) s[n-1][j] = max(s[n-1][j], v[n-1][j+1]);
	}

	rfor(i, n-1) {
		fore(j, 10) {
			s[i][j] = v[i][j] + s[i+1][j];
			if (j >= 1) s[i][j] = max(s[i][j], v[i][j-1]+s[i+1][j-1]);
			if (j < 10) s[i][j] = max(s[i][j], v[i][j+1]+s[i+1][j+1]);
		}
	}

	printf("%d\n", s[0][5]);
}

int main(void)
{
	freopen("Inputs/1176", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) solve();

	return 0;
}
