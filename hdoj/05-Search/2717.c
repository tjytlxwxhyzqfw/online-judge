/*
 * 2717 - Catch That Cow
 *
 * Simple Elevator Seaching Problem
 *
 *多组数据!!!!
 * BUG:
 * 1.fjx == cowx时要特别处理
 * 2.WIDTH是100001 不是100000
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "Source/arrayqueue.c"
#include "Source/debug.c"

#define WIDTH 100000

int *intp(int x)
{
	int *p = malloc(sizeof(int));
	*p = x;

	return p;
}

int best, cowx;
char color[100000];

/*
 * DFS is TOO slow
 */

#if 0
void dfs(int deepth, int x)
{
	//printis(deepth, 0, "x:%d\n",  x);

	if (x < 0 || x >= WIDTH)
		return;
	if (color[x] == 1)
		return;

	if (deepth >= best)
		return;

	if (x == cowx) {
		/* deepth must be less than best */
		best = deepth;
		return;
	}

	color[x] = 1;
	
	if (x < cowx) {
		dfs(deepth + 1, 2 * x);
		dfs(deepth + 1, x + 1);
	}
	dfs(deepth + 1, x - 1);

	color[x] = 0;
}
#endif

/*
 * BFS
 *
 * 神搜和广搜差别真大!!!
 */
struct queue *intqueue;
int deepth[100000];

int bfs(int start)
{
	int res = 100000;

	struct queue *q;
	int *p;
	int x, nx, ndpt, xs[3];
	int idxmax, i;

	q = intqueue;
	q->first = q->last = 0;

	queue_append(intp(start), q);
	color[start] = 1;
	deepth[start] = 0;

	while ((p = queue_pop(q)) != NULL) {
		x = *p;
		free(p);

		ndpt = deepth[x] + 1;

		//printis(ndpt, 0, "bfs: x: %d, ndeepth: %d\n", x, ndpt);

		xs[0] = x - 1;
		idxmax = 1;
		if (x < cowx) {
			xs[1] = x + 1;
			xs[2] = 2 * x;
			idxmax = 3;
		}

		for (i = 0; i < idxmax; ++i) {
			nx = xs[i];
			if (nx < 0 || nx >= WIDTH)
				continue;
			if (color[nx] == 1)
				continue;
			if (nx == cowx) {
				res = ndpt;
				goto end;
			}

			queue_append(intp(nx), q);
			color[nx] = 1;
			deepth[nx] = ndpt;
		}
	}

	end:
	while ((p = queue_pop(q)) != NULL)
		free(p);

	return res;
}
	
int main(void)
{
	int fjx;

	intqueue = queue_new(100000);

	scanf("%d%d", &fjx, &cowx);
	//fjx = 1, cowx = 97777;
	memset(color, 0, sizeof(color));
	best = bfs(fjx);
	printf("%d\n", best);
	return 0;
}
