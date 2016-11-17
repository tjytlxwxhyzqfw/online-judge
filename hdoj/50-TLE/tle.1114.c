/*
 * 1114 Piggy-Bank
 *
 * DFS-DP
 */

#include <limits.h>
#include <stdio.h>
#include <stdlib.h>

#include "Source/debug.c"

int s[500][10001];
int ncoins, vals[500], weights[500];

int dfs(const int coin, const int weight)
{
	int minval, value;
	int k;
	int weight2;

	//printis(0, 0, "dfs: coin: %d, weight: %d\n", coin, weight);

	weight2 = weight - weights[coin];
	if (weight2 < 0)
		return INT_MAX;

	minval = INT_MAX;
	for (k = 0; k < ncoins; ++k) {
		/* This is vary harmful */
		/* value = s[coin][weight]; */
		value = s[k][weight2];
		if (value == -1)
			value = dfs(k, weight2);
		if (value < minval)
			minval = value;
	}

	s[coin][weight] = (minval == INT_MAX ? INT_MAX : minval + vals[coin]); 

	//printis(0, 0, "return: s[%d][%d]: %d\n", coin, weight, s[coin][weight]);
	return s[coin][weight];
}
		

int main(void)
{
	int ncas, i, j;
	int full, empty;
	int minval, val, total, weight;

	freopen("Inputs/1114", "r", stdin);
	setbuf(stdout, NULL);


	scanf("%d", &ncas);
	while (ncas--) {
		scanf("%d%d", &empty, &full);
		scanf("%d", &ncoins);
		for (i = 0; i < ncoins; ++i)
			scanf("%d%d", vals + i, weights + i);
		total = full - empty;

		for (i = 0; i < ncoins; ++i) {
			weight = weights[i];
			for (j = 1; j < weight; ++j)
				s[i][j] = INT_MAX;
			s[i][weight] = vals[i];
			for (j = weight + 1; j <= total; ++j)
				s[i][j] = -1;
		}

		minval = INT_MAX;
		for (i = 0; i < ncoins; ++i) {
			val = dfs(i, total);
			if (val < minval)
				minval = val;
		}

		if (minval == INT_MAX) {
			printf("This is impossible.\n");
		} else {
			printf("The minimum amount of money in the piggy-bank is %d.\n", minval);
		}

	}

	return 0;
}
