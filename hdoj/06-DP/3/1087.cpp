/**
 * 1087
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"

#define N 1000

using namespace std;

int n;
vector<int> s(N), a(N);

bool read(void)
{
	int i;
	if (scanf("%d", &n) != 1)
		return false;
	if (n == 0) return false;
	forn(i, n) scanf("%d", &a[i]);
	return true;
}

void solve(void)
{
	int i, j;
	int maxv;

	s[n-1] = a[n-1];
	rfor(i, n-1) {
		s[i] = 0;
		forr(j, i+1, n) if (a[j] > a[i]) s[i] = max(s[i], s[j]);
		s[i] += a[i];
	}

	maxv = 0;
	forn(i, n) maxv = max(maxv, s[i]);
	printf("%d\n", maxv);
}


int main(void)
{
	freopen("Inputs/1087", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) solve();

	return 0;
}
