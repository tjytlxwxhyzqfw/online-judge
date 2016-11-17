/*
 * 1728 逃离迷宫
 * BFS: 拐角数作为度量
 */

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "Source/arrayqueue.c"

/* node start */

struct node {
	int y, x;
};

struct node *node_new(int y, int x)
{
	struct node *n;

	assert(n = malloc(sizeof(struct node)));
	n->y = y;
	n->x = x;

	return n;
}

void node_destroy(struct node *n)
{
	if (n)
		free(n);
}

char map[100][100], color[100][100];
int cnt[100][100];
int height, width, end_y, end_x, maxcnt;

const dir[] = {0, 0, 1, -1, 1, -1, 0, 0};

struct queue *nodequeue;

int bfs(int sy, int sx)
{
	int res = 0;

	struct queue *q;
	struct node *n;
	int d;
	int cy, cx, ny, nx;
	int currcnt;

	q = nodequeue;
	q->first = q->last = 0;

	n = node_new(sy, sx);
	queue_append(n, q);
	cnt[sy][sx] = -1;
	color[sy][sx] = 2;

	while ((n = queue_pop(q)) != NULL) {
		cy = n->y;
		cx = n->x;
		node_destroy(n);

		currcnt = cnt[cy][cx] + 1;
		//printf("pop:(%d,%d) +%d\n", cy, cx, cnt[cy][cx]);
		if (currcnt > maxcnt)
			goto exit;

		for (d = 0; d < 4; ++d) {
			ny = cy;
			nx = cx;
			advance:
			ny += dir[d];
			nx += dir[d + 4];
			//printf("test:(%d,%d)\n", ny, nx);
			if (ny < 0 || ny >= height || nx < 0 || nx >= width)
				continue;
			if (map[ny][nx] == '*')
				continue;
			if (color[ny][nx] == 2)
				continue;

			n = node_new(ny, nx);
			queue_append(n, q);
			color[ny][nx] = 2;
			cnt[ny][nx] = currcnt;
			if (ny == end_y && nx == end_x) {
				res = 1;
				goto exit;
			}

			goto advance;
		}
	}

	exit:
	while ((n = queue_pop(q)) != NULL)
		node_destroy(n);		

	return res;
}
			
int main(void)
{
	int ncas;
	int y, x, sy, sx;
	
	freopen("Inputs/1728", "r", stdin);
	setbuf(stdout, NULL);

	nodequeue = queue_new(10000);

	scanf("%d", &ncas);
	while (ncas--) {
		scanf("%d%d%*c", &height, &width);
		for (y = 0; y < height; ++y) {
			for (x = 0; x < width; ++x)
				scanf("%c", &map[y][x]);
			scanf("%*c");
		}
		scanf("%d%d%d%d%d", &maxcnt, &sx, &sy, &end_x, &end_y);
		--sx;
		--sy;
		--end_x;
		--end_y;
		memset(color, 0, sizeof(char) * 10000);
		printf("%s\n", bfs(sy, sx) ? "yes" : "no");
	}

	return 0;
}			
