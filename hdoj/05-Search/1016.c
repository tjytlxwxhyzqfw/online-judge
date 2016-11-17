/*
 * 1016.Prime Ring Problem
 */

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int ncandidates;
char isprime[40], cand[20], path[20];

void printa(char *a, int n)
{
	int i;

	for (i = 0; i < n; ++i)
		printf("%d%s", a[i], i == n - 1 ? "" : " ");
	printf("\n");
}

void dfs(int deepth, int last) {
	int i, last2;

	//printf("deepth = %d, ");
	//printa(path, ncandidates - 1);
	//printa(cand, ncandidates + 1);
	if (deepth == ncandidates - 1 && isprime[last + 1]) {
		printf("1 ");
		printa(path, ncandidates - 1);
		return;
	}

	for (i = 2; i <= ncandidates; ++i) {
		if (cand[i] || !isprime[i + last])
			continue;
		path[deepth] = i;
		cand[i] = 1;
		dfs(deepth + 1, i);
		cand[i] = 0;
	}
}
		

int main(void)
{
	int ncase = 1;

	int i, j;
	char path[20], cand[20];
	struct listnode *list;

	memset(isprime, 1, 40);
	isprime[0] = isprime[1] = 0;
	for (i = 2; i < 40; ++i)
		if (isprime[i])
			for (j = i + i; j < 40; j += i)
				isprime[j] = 0;
	//printa(isprime, 40);

	while (scanf("%d", &ncandidates) != -1) {
		memset(cand, 0, 20 );
		memset(path, 0, 20);
		printf("Case %d:\n", ncase++);
		if (!(ncandidates % 2))
			dfs(0, 1);
		printf("\n");
	}
	return 0;
}
