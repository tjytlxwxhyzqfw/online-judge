/*
 * 1159 Common Subsequence
 *
 * You just do very little(primary) work,
 * and then turn over the rest to dp.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//#include "Source/debug.c"

char x[4096], y[4096];
int s[4096][4096];

int dfs(const int m, const int n)
{
	int max = 0;

	int res;

	//printis(0, 0, "dfs: %d, %d\n", m, n);

	max = 0;
	if (x[m] == y[n]) {
		res = (m && n ? s[m - 1][n - 1] : 0);
		if (res == -1)
			res = dfs(m - 1, n - 1);
		max = res + 1;
		goto exit;
	}

	res = (m ? s[m - 1][n] : 0);
	if (res == -1)
		res = dfs(m - 1, n);
	if (max < res)
		max = res;

	res = (n ? s[m][n - 1] : 0);
	if (res == -1)
		res = dfs(m, n - 1);
	if (max < res)
		max = res;

	exit:
	s[m][n] = max;
	//printis(0, 0, "dfs: s[%2d][%2d] = %2d\n", m, n, s[m][n]);

	return max;
}

int main(void)
{
	int res;
	int nx, ny, i, j;

	freopen("Inputs/1159", "r", stdin);
	setbuf(stdout, NULL);

	while (scanf("%s%s", x, y) == 2) {

		nx = strlen(x);
		ny = strlen(y);
		for (i = 0; i < nx; ++i)
			for (j = 0; j < ny; ++j)
				s[i][j] = -1;

		res = dfs(nx - 1,  ny - 1);
		printf("%d\n", res);
	}

	return 0;
}
		



