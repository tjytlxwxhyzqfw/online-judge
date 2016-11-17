/*
 * 1171 Big Event In HDU
 *
 * I've got a wrong greedy solution which was accepted on OJ. (Trash/1171.c)
 *
 * I am going to solve this problem with dp.
 *
 * [06-24 14:20] s[50][101][63750] is not unrealistic.And I am going to try s[50][63750]
 * [06-25 16:25] s[50][63750] is tle, and I am going to try s[50][101];
 * [06-25 17:38] it doesn't matter with 63750, I made  a mistick.
 *
 */

#include <assert.h>
#include <limits.h>
#include <stdio.h>
#include <stdlib.h>

//#include "debug.c"

int total, vals[50], nums[50];
int s[50][63751];

/* debug */
//int ident;
//int slpsec;

/*
 * @assert s must be -1
 * @assert parameters are correct
 */
int dfs(int idx, int target)
{
	int i, num;
	int baseval, newtar, res, value, best;

	//printis(0, 0, "dfs: idx: %2d, target: %5d\n", idx, target);

	best = INT_MAX;

	if (idx == total - 1) {
		for (num = 0; num <= nums[idx]; ++num) {
			res = vals[idx] * num;
			if (res >= target) {
				best = res;
				break;
			}
		}
		goto complete;
	}

	for (num = 0; num <= nums[idx]; ++num) {
		baseval = vals[idx] * num;
		newtar = target - baseval;
		if (newtar < 0)
			break;

		/* mistick */
		/*for (i = idx + 1; i == idx + 1; ++i) */

		i = idx + 1;
		res = s[i][newtar];
		if (res == -1)
			res = dfs(i, newtar);
		if (res == INT_MAX)
			continue;
		value = res + baseval;
		if (value >= target && value < best)
			best = value;
	}


	complete:
	s[idx][target] = best;
	//printis(0, 0, "s[%d][%d]: %d\n", idx, target, s[idx][target]);
	return best;
}

int main(void)
{
	int sum, avg, best;
	int i, j, k;

	freopen("Inputs/1171", "r", stdin);
	setbuf(stdout, NULL);

	while (scanf("%d", &total) != EOF) {
		if (total < 0)
			break;

		/* debug */
		//ident = 0;
		//slpsec = 0;

		sum = 0;
		for (i = 0; i < total; ++i) {
			scanf("%d%d", vals + i, nums + i);
			sum += vals[i] * nums[i];
			//printis(0, 0, "val: %d, num: %d\n", vals[i], nums[i]);
		}
		avg = (sum + 1) / 2;
		//printis(0, 1, "avg: %d, sum: %d\n", avg, sum);

		/* s */
		for (i = 0; i < total; ++i)
			for (j = 0; j <= avg; ++j)
				s[i][j] = -1;

		best = dfs(0, avg);

		printf("%d %d\n", best, sum - best);
	}

	return 0;
}
