/**
 * 712D
 */
#include "common.h"
#include "numth.h"

#define M 1000000007
#define T 100
#define K 1000

#define pls(a, b) NUMTH_MODPLS((a), (b), M)

int dp[2*T][4*T*K+1];
int a, b, t, k;
int delta, delta_min, delta_max, offset, index_max;

int dp_fill(int i, int j)
{
	int x;

	if (j == index_max-k) {
		dp[i][j] = 0;
		forre(x, j-k, j+k) dp[i][j] = pls(dp[i][j], dp[i+1][x]);
	} else {
		dp[i][j] = pls(pls(dp[i+1][j-k], dp[i][j+1]), -1*dp[i+1][j+k+1]);
		NUMTH_CORRECT(dp[i][j], M);
	}

	//print("[i][i]: i", i, j, dp[i][j]);
	return dp[i][j];
}

int main(void)
{
	int i, j; 

	//scanf("%d%d%d%d", &a, &b, &t, &k);
	a = 1, b = 1, k = 1, t = 2; //31
	//a = 1, b = 2, k = 2, t = 1; //6
	//a = 2, b = 12, k = 3, t = 1;

	t <<= 1;
	delta = a - b;
	delta_min = delta - t * k;
	delta_max = delta + t * k;
	offset = -1 * delta_min;
	index_max = delta_max + offset;

	forre(i, 0, index_max) dp[t-1][i] = max(0, k-max(-1*k, offset-i+1)+1);
	//forre(i, 0, index_max) print("[i][i]: i", t-1, i, dp[t-1][i]);

	/* FIXME:
	 * There are TWO positions that will use delta_max + offset!!!
	 *
	 * i = t-2, j = delta_max-k+offset;
	 * dp[i][j] = 0;
	 * forre(x, index_max-2*k, index_max) dp[i][j] = pls(dp[i][j], dp[i+1][x]);
	 * print("[i][i]: i", i, j, dp[i][j]);
	 */

	for (i = t-2; i >= 0; --i)
		for (j = index_max-k; j >= k; --j)
			dp_fill(i, j);

	printf("%d\n", dp[0][delta+offset]);
	return 0;
}
