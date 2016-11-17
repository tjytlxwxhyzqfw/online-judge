/*
 * 1536 S-Nim
 *
 * 2016-08-18 20:25: just submit
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int nheaps;
int cands[100], ncands;
int cache[10001];

int init_a_case(void)
{
	int i, j, t;

	for (i = 0; i < 10001; ++i)
		cache[i] = -1;
	//printis(0, 0, "cache refreshed.\n");

	if (scanf("%d", &ncands) != 1)
		return 0;

	if (ncands == 0)
		return 0;

	for (i = 0; i < ncands; ++i)
		scanf("%d", cands + i);

	for (i = 1; i < ncands; ++i) {
		/*FIXME t=i; */
		t = cands[i];
		for (j = i - 1; j >= 0 && cands[j] > t; --j)
			cands[j + 1] = cands[j];
		cands[j + 1] = t;
	}

	/*
	printf("cands: ");
	for (i = 0; i < ncands; ++i)
		printf("%d ", cands[i]);
	printf("\n");
	*/

	return 1;
}

int sg(const int n)
{
	char *occupied;
	int i;
	int res;

	/* cache */
	if (cache[n] != -1)
		return cache[n];

	/* ending situation */
	if (cands[0] > n)
		return 0;

	/* 后继状态sg值的集合 */
	occupied = malloc(sizeof(char) * ncands);
	memset(occupied, 0, sizeof(char) * ncands);
	for (i = 0; i < ncands && n >= cands[i]; ++i) {
		/*FIXME res = n - cands[i] */
		res = sg(n - cands[i]);
		//printis(1, 0, "%d: %d\n", n - cands[i], res);
		if (res < ncands)
			occupied[res] = 1;
	}

	/* mex: 集外最小序数 */
	for (i = 0; i < ncands; ++i)
		if (!occupied[i])
			goto exit;
	exit:
	free(occupied);
	cache[n] = i;
	//printis(1, 0, "cache: %3d: %d\n", n, cache[n]);
	return i;
}

int main(void)
{
	int i, query, nqueries;
	int heap, result, resulti;

	freopen("Inputs/1536", "r", stdin);
	setbuf(stdout, NULL);

	while (init_a_case()) {
		scanf("%d", &nqueries);
		for (query = 0; query < nqueries; ++query) {
			result = 0;
			scanf("%d", &nheaps);
			for (i = 0; i < nheaps; ++i) {
				scanf("%d", &heap);
				resulti = sg(heap);
				result ^= resulti;
				//printis(0, 0, "heap: %d: %d\n", heap, resulti);
			}
			printf("%s", result == 0 ? "L" : "W");
		}
		printf("\n");
	}

	return 0;
}
