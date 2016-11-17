/**
 * 1171
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"

using namespace std;

#define N 50
#define V 125001

int total, avg, n;
int q[N], v[N];
int s[N][V+1];

bool read(void)
{
	int i;

	if (scanf("%d", &n) != 1)
		return false;
	if (n < 0) return false;
	forn(i, n) scanf("%d%d", v+i, q+i);

	total = 0;
	forn(i, n) total += v[i] * q[i];
	avg = total / 2;

	return true;
}

void solve(void)
{
	int i, j;

	fore(j, avg) s[n-1][j] = ((avg-j)/v[n-1]) * v[n-1] + j;
	rfor(i, n-1) {
		rfore(j, avg) {
			s[i][j] = s[i+1][j];
			if (min((avg-j)/v[i], q[i]) >= 1)
				s[i][j] = max(s[i][j], s[i][j+v[i]]);
		}
	}
	printf("%d %d\n", total-s[0][0], s[0][0]);
}

int main(void)
{
	freopen("Inputs/1171", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) solve();

	return 0;
}
