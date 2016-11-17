/*
 * Safecracker
 */

#include <assert.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char candidates[13];
int ncandidates, target;

void isort(char *s, int b, int e)
{
	int i, j;
	char tmp;

	for (i = b + 1; i <= e; ++i) {
		tmp = s[i];
		for (j = i - 1; j >= b && tmp > s[j]; --j)
			s[j + 1] = s[j];
		s[j + 1] = tmp;
	}
}

int dfs(int deepth, char *word, int res, char *path)
{
	int i, res2;

	if (deepth == 5)
		return res == target;

	for (i = 0; i < ncandidates; ++i) {
		if (word[i])
			continue;
		word[i] = 1;
		path[deepth] = candidates[i];
		res2 = res + pow(-1, deepth) * pow(candidates[i] - 64, deepth + 1);
		//printf("[%d]%c, res = %d\n", deepth, candidates[i], res2);
		//sleep(0.1);
		if (dfs(deepth + 1, word, res2, path))
			return 1;
		word[i] = 0;
	}
	return 0;
}

int main(void)
{
	char word[13], path[5];
	int i;

	setbuf(stdout, NULL);
	freopen("Inputs/1015", "r", stdin);

	memset(candidates, 0, 13);
	memset(word, 0, 13);
	while (scanf("%d%s", &target, candidates) != -1) {
		if (!target && !strcmp(candidates, "END"))
			break;
		ncandidates = strlen(candidates);
		isort(candidates, 0, ncandidates - 1);
		if (dfs(0, word, 0, path)) {
			for (i = 0; i < 5; ++i)
				putchar(path[i]);
			printf("\n");
		} else {
			printf("no solution\n");
		}
		memset(candidates, 0, 13);
		memset(word, 0, 13);
	}
	return 0;
}
