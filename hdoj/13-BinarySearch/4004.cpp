/**
 * 4004
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"

using namespace std;

#define N 500000
int len, n, m;
int a[N+2];

int skp(const int from, const int thresh) {
	int to;
	for (to = from; to <= n+1 && a[to]-a[from] <= thresh; ++to);
	return to - 1;
}

int check(const int x, int *y) {
	int i, j, nskps;

	i = *y = nskps = 0;
	while (true) {
		j = skp(i, x);
		if (j == i) {
			*y = INT_MAX;
			return INT_MAX;
		}
		++nskps;
		*y = max(*y, a[j]-a[i]);
		if (j == n+1) return nskps;
		i = j;
	}

	assert(false);
	return -1;
}

bool read(void) {
	int i;
	if (scanf("%d%d%d", &len, &n, &m) != 3)
		return false;
	a[0] = 0, a[n+1] = len;
	forre(i, 1, n) scanf("%d", &a[i]);
	sort(a+1, a+n+1);

	//printf("\n\n%d %d %d\n", len, n, m);
	//forre(i, 1, n) printf("%d%s", a[i], tailer(i, n));
	//printf("\n\n");

	return true;
}

void solve(void) {
	int i, j, k, u, v, nskps;

	v = INT_MAX;
	i = 0, j = len;
	while (i <= j) {
		k = (i+j) / 2;
		nskps = check(k, &u);
		//printf("[%6d, %6d]: %6d: nskps: %3d, u: %6d\n", i, j, k, nskps, u);
		if (nskps > m) {
			i = k + 1;
		} else {
			v = min(v, u);
			j = k - 1;
		}
	}

	printf("%d\n", v);
}

int main(void) {
	freopen("Inputs/4004", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) solve();

	return 0;
}
