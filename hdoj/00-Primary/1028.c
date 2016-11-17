/*
 * 1028
 */

#include <stdio.h>
#include <string.h>

int dp[121][121];

int main(void)
{
	int i, j, k;

	memset(dp, 0, sizeof(dp));

	for (i = 0; i < 121; ++i) {
		dp[i][0] = 0;
		dp[0][i] = 1;
		dp[i][1] = 1;
		dp[1][i] = 1;
	}

	for (i = 2; i < 121; ++i) {
		for (j = 2; j <= i; ++j) {
			//printf("dp[%d][%d] = %d\n", i, j, dp[i][j]);
			for (k = 1; k <=j; ++k) {
				//printf("+= %d(%d,%d)\n", dp[i - k][k], i - k, k);
				dp[i][j] += dp[i - k][k];
			}
			//printf("dp[%d][%d] = %d\n", i, j, dp[i][j]);
		}
		for (j = i + 1; j < 121; ++j)
			dp[i][j] = dp[i][i];
	}

	while (scanf("%d", &i) != EOF)
		printf("%d\n", dp[i][i]);

	return 0;
}
