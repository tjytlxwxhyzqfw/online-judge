/**
 * 1024
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"

using namespace std;

#define N 1000000
#define M 100

int n, m;
vector<int> a(N);
vector<vector<int> > s(N), t(N);

bool read(void)
{
	int i;

	if (scanf("%d%d", &m, &n) != 2)
		return false;
	forn(i, n) scanf("%d", &a[i]);

	printf("\nn: %2d, m: %2d\n", n, m);
	forn(i, n) printf("%3d%s", a[i], tailer(i, n-1));
	return true;
}

void prints(int i, int j)
{
	printf("[%2d][%2d]: s: %3d, t: %3d\n", i, j, s[i][j], t[i][j]);
}

void solve(void)
{
	int i, j;
	int maxv;

	s[0][1] = a[0];
	t[0][2] = INT_MIN;
	prints(0, 1);
	forr(i, 1, n) {
		s[i][1] = a[i];
		prints(i, 1);
		forre(j, 2, i+1) {
			t[i][j] = max(s[i-1][j-1], t[i-1][j]);
			s[i][j] = a[i] + max(s[i-1][j], t[i][j]);
			prints(i, j);
		}
	}

	maxv = INT_MIN;
	forr(i, m-1, n) maxv = max(maxv, s[i][m]);

	printf("result: %d\n\n", maxv);
}

int main(void)
{
	int i;

	freopen("Inputs/1024", "r", stdin);
	setbuf(stdout, NULL);

	forn(i, N) s[i].reserve(M+1);
	forn(i, N) t[i].reserve(M+1);

	while (read()) solve();

	return 0;
}
