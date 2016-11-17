/**
 * D
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"

using namespace std;

#define N 100000

int a[N], b[N], c[N];
bool f[N+1];

bool read(void) {
	if (scanf("%d%d", &n, &m) != 2)
		return false;
	forn(i, n) scanf("%d", &a[i]);
	forn(i, m) scanf("%d", &b[i]);
	return true;
}

void solve(void) {
	fill(f, f+m+1, false);
	rfor(i, n) {
		if (a[i] == 0) continue;
		if (f[a[i]]) a[i] = 0; 
		else f[a[i]] = true;
	}
}

int main(void)
{
	freopen("Inputs/D", "r", stdin);
	setbuf(stdout, NULL);

	return 0;
}
