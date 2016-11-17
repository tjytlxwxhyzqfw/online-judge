/*
 * 1016 Prime Ring Problem
 *
 * new
 */

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int ncands;
char isprime[40], used[20], path[20];

void printa(char *a, int n)
{
	int i;

	for (i = 0; i < n; ++i)
		printf("%d%s", a[i], i == n - 1 ? "" : " ");
	printf("\n");
}

void dfs(int deepth, int cand)
{
	int i, ndeepth;

	/* 约束 */
	/* 不会越界, 没有墙 */
	/* 此路不通 */
	if (used[cand])
		return;

	/* 自定义硬约束 */
	if (deepth != 0 && !isprime[cand + path[deepth - 1]])
		return;

	/*阻塞*/
	used[cand] = 1;

	path[deepth] = cand;
	ndeepth = deepth + 1;

	/* 成功 */
	if (deepth == ncands - 1) { 
		if(isprime[cand + 1])
			printa(path, ndeepth);
		goto exit;
	}

	for (i = 1; i <= ncands; ++i)
		dfs(ndeepth, i);

	exit:

	/*疏通*/
	used[cand] = 0;
}
		

int main(void)
{
	int ncase = 1;

	int i, j;

	memset(isprime, 1, 40);
	isprime[0] = isprime[1] = 0;
	for (i = 2; i < 40; ++i)
		if (isprime[i])
			for (j = i + i; j < 40; j += i)
				isprime[j] = 0;

	while (scanf("%d", &ncands) == 1) {
		memset(used, 0, 20 );
		memset(path, 0, 20);
		printf("Case %d:\n", ncase++);
		if ((ncands & 1UL) == 0)
			dfs(0, 1);
		printf("\n");
	}
	return 0;
}
