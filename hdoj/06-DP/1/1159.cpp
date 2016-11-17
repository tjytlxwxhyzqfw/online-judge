/**
 * 1159
 */

#include <algorithm>
#include <array>
#include <cassert>
#include <cstdio>
#include <cstring>
#include <vector>

#include "common.h"

using namespace std;

#define N 10000

array<char, N+1> a, b;
int alen, blen;
array<array<int, N>, N> dp;

void printpath(int i, int j);

bool read(void)
{
	if (scanf("%s%s", &a[0], &b[0]) != 2)
		return false;
	//if (strcmp(a.data(), "...") == 0) return false;

	alen = strlen(a.data());
	blen = strlen(b.data());

	return true;
}

bool cond_1(int i, int j)
{
	return (i+1 < alen and j+1 < blen and dp[i+1][j+1] != INT_MIN);
}

bool cond_2(int i, int j)
{
	return (i+1 < alen and dp[i+1][j] != INT_MIN);
}

bool cond_3(int i, int j)
{
	return (j+1 < blen and dp[i][j+1] != INT_MIN);
}


void solve(void)
{
	int i, j;

	i = alen-1, j = blen-1;
	dp[i][j] = (a[i] == b[j] ? 1 : 0);

	rforre(i, alen-1, 0) {
		rforre(j, blen-1, 0) {
			if (i == alen-1 and j == blen-1) continue;
			dp[i][j] = INT_MIN;
			if (a[i] == b[j]) {
				dp[i][j] = 1;
				if (cond_1(i, j))
					dp[i][j] += dp[i+1][j+1];
			}
			if (cond_2(i, j))
				dp[i][j] = max(dp[i][j], dp[i+1][j]);
			if (cond_3(i, j))
				dp[i][j] = max(dp[i][j], dp[i][j+1]);

			//printf("\ndp[%2d][%2d]: %2d\n", i, j, dp[i][j]);
		}
	}

	printf("%d\n", dp[0][0]);
}

int main(void)
{
	freopen("Inputs/1159", "r", stdin);
	setbuf(stdout, NULL);

	while (read())
		solve();

	return 0;
}
