/*
 * 1548 A strange list
 *
 * bfs: s
 */

#include <stdio.h>
#include <stdlib.h>

#include "Source/arrayqueue.c"

int *int_new(x)
{
	int *n;
	
	n = malloc(sizeof(int));
	*n = x;

	return n;
} 

int height, k[200], dist[200];
char color[200];
struct queue *qfloors;

int bfs(int start, int end)
{
	int res = -1;

	int f, nf, d;
	struct queue *q;
	int *n;

	if (start == end)
		return 0;

	q = qfloors;
	q->first = q->last = 0;

	queue_append(int_new(start), q);
	color[start] = 2;
	dist[start] = 0;

	while ((n = queue_pop(q)) != NULL) {
		f = *n;
		printf("pop:%d\n", f);
		sleep(1);
		for (d = 0; d < 2; ++d) {
			nf = (d ? f + k[f] : f - k[f]);
			if (nf < 0 || nf >= height)
				continue;
			if (color[nf] == 2)
				continue;

			queue_append(int_new(nf), q);
			color[nf] = 2;
			dist[nf] = dist[f] + 1;

			if (nf == end) {
				res = dist[nf];
				goto exit;
			}
		}
		
		free(n);
	}

	exit:
	while ((n = queue_pop(q)) != NULL)
		free(n);

	return res;
}

int main(void)
{
	int start, end, i;

	freopen("Inputs/1548", "r", stdin);
	setbuf(stdout, NULL);

	qfloors = queue_new(200);

	while (scanf("%d%d%d", &height, &start, &end) == 3) {
		--start;
		--end;
		for (i = 0; i < height; ++i) {
			scanf("%d", k + i);
			color[i] = 0;
		}

		printf("result = %d\n", bfs(start, end));
	}

	return 0;
}
