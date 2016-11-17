/**
 * 1203
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"

using namespace std;

#define N 10000
#define M 10000

int n, m;
int c[N];
double p[N], curr[M+1], last[M+1];

bool read(void)
{
	int i;

	if (scanf("%d%d", &m, &n) != 2)
		return false;
	if (m == 0 and n == 0) return false;
	forn(i, n) scanf("%d%lf", c+i, p+i);
	return true;
}

void solve(void)
{
	int i, j;

	if (n == 0) {
		printf("0.0%%\n");
		return;
	}

	fore(j, m) curr[j] = (j < c[n-1] ? 0 : p[n-1]);
	rfor(i, n-1) {
		swap(curr, last);
		fore(j, m) {
			curr[j] = last[j];
			if (j >= c[i]) curr[j] = max(curr[j], 1-(1-p[i])*(1-last[j-c[i]]));
		}
	}

	printf("%.1lf%%\n", curr[m]*100);
}

int main(void)
{
	freopen("Inputs/1203", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) solve();

	return 0;
}
