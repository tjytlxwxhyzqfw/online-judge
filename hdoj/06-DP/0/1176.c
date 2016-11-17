/*
 * 1176 免费馅饼
 *
 * Primary DP
 *
 * [06-26 11:50] RE: Too many recursions, I guess. (Correct)
 * [06-26 11:50] WA: I do not figure out what fill_s() meaned.
 */

#include <stdio.h>
#include <stdlib.h>

//#include "debug.c"

int t[200000][11], s[200000][11];
int seconds;

/* debug */
//int ind = 0;
//int slp = 0;

int fill_s(const int sec, const int loc)
{
	int max, total, freshpies, oldpies;
	int i;

	//printis(ind++, 0, "dfs: sec: %2d, loc: %2d\n", sec, loc);

	max = 0;
	for (i = loc - 1; i <= loc + 1; ++i) {
		if (i < 0)
			continue;
		if (i > 10)
			break;
		freshpies = t[sec][i];

		if (sec == seconds)
			oldpies = 0;
		else
			oldpies = s[sec + 1][i];

		total = freshpies + oldpies;
		if (total > max)
			max = total;
	}

	complete:
	s[sec][loc] = max; 
	//printis(--ind, 0, "s[%2d][%2d]: %4d\n", sec, loc, s[sec][loc]);
	return max;
}
	
int main(void)
{
	int ncas, i, j;
	int sec, loc;
	int res;

	freopen("Inputs/1176", "r", stdin);
	setbuf(stdout, NULL);

	while (scanf("%d", &ncas) == 1) {
		if (ncas == 0)
			break;

		/* init t[][] & s[][] */
		for (i = 0; i < 200000; ++i) {
			for (j = 0; j < 11; ++j) {
				s[i][j] = -1;
				t[i][j] = 0;
			}
		}

		seconds = 0;
		for (i = 0; i < ncas; ++i) {
			scanf("%d%d", &loc, &sec);
			t[sec][loc] += 1;
			if (sec > seconds)
				seconds = sec;
		}

		for (i = seconds; i >= 0; --i) {
			for (j = 0; j < 11; ++j) {
				fill_s(i, j);
			}
		}

		printf("%d\n", s[1][5]);
	}

	return 0;
}
