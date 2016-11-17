/**
 * 1421
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"

using namespace std;

#define N 2000

int n, k, w[N];
int s[N][N+1];

#define c(i, j) ((w[i]-w[j]) * (w[i]-w[j]))

bool read(void)
{
	int i;

	if (scanf("%d%d", &n, &k) != 2)
		return false;
	forn(i, n) scanf("%d", w+i);
	sort(w, w+n);
	return true;
}

void solve(void)
{
	int i, j;

	assert(n >= 2);
	s[n-1][k] = 0;
	forn(j, k) s[n-1][j] = INT_MAX;
	s[n-2][k] = 0;
	s[n-2][k-1] = c(n-2, n-1);
	forn(j, k-1) s[n-2][j] = INT_MAX;

	rfor(i, n-2) {
		s[i][k] = 0;
		forn(j, k) {
			s[i][j] = s[i+1][j];
			if (s[i+2][j+1] != INT_MAX)
				s[i][j] = min(s[i][j], s[i+2][j+1]+c(i, i+1));
		}
	}

	printf("%d\n", s[0][0]);
}

int main(void)
{
	freopen("Inputs/1421", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) solve();

	return 0;
}
