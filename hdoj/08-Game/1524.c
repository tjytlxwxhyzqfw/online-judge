/*
 * 1524 A Chess Game
 *
 * Partly Buffer
 *
 * SG Function
 *
 * 2016-08-18 20:10: just submit
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int nvertices;
int nneibors[1000], neibors[1000];
int graph[1000][1000];
int cache[1000];

int sg(const int n)
{
	int i, nns, sgv;
	char *succeeds;

	if (cache[n] != -1)
		return cache[n];

	nns = nneibors[n];

	if (nns == 0)
		return 0;

	succeeds = malloc(nns);
	memset(succeeds, 0, nns);
	for (i = 0; i < nns; ++i) {
		//printis(1, 0, "vertex: %2d, i: %2d, neibor: %2d\n", n, i, graph[n][i]);
		sgv = sg(graph[n][i]);
		if (sgv < nns)
			succeeds[sgv] = 1;
	}

	for (i = 0; i < nns; ++i)
		if (succeeds[i] == 0)
			goto exit;

	exit:
	free(succeeds);
	cache[n] = i;
	//printf("cached: %2d: %2d\n", n, cache[n]);
	return i;
}

int init_a_case()
{
	int i, j;

	for (i = 0; i < 1000; ++i)
		cache[i] = -1;

	if (scanf("%d", &nvertices) != 1)
		return 0;

	for (i = 0; i < nvertices; ++i) {
		scanf("%d", nneibors + i);
		for (j = 0; j < nneibors[i]; ++j)
			scanf("%d", &graph[i][j]);
	}

	return 1;
}

int main(void)
{
	int i, result;
	int nqs, heap;

	freopen("Inputs/1524", "r", stdin);
	setbuf(stdout, NULL);

	while (init_a_case()) {
		while (scanf("%d", &nqs) == 1) {
			if (!nqs)
				break;
			result = 0;
			for (i = 0; i < nqs; ++i) {
				scanf("%d", &heap);
				//printis(0, 0, "heap: %d\n", heap);
				result ^= sg(heap);
			}
			printf("%s\n", result ? "WIN" : "LOSE");
		}
	}

	return 0;
}
