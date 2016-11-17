/*
 * 1983 Kaitou Kid - The Phantom Thief
 *
 * 看上去很难,思考一会儿就好了
 */

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "Source/arrayqueue.c"

/* node start */

struct node {
	int y, x;
	int jrl;
};

struct  node *node_new(int y, int x, int j)
{
	struct node *n;

	assert(n = malloc(sizeof(struct node)));
	n->y = y;
	n->x = x;
	n->jrl = j;

	return n;
}

char map[8][8], color[8][8][2];
int dist[8][8];
int height, width, start_y, start_x, end_y, end_x;
int maxdst;
int dir[] = {0, 0, 1, -1, 1, -1, 0, 0};

struct queue *nodequeue;

int complete()
{
	int res = 0;

	int ny, nx, cy, cx, cdst;
	int jrl, nj;
	int d;

	struct node *n;
	struct queue *q;

	q = nodequeue;
	q->first = q->last = 0;

	memset(color, 0, sizeof(char) * 8 * 8 * 2);

	n = node_new(start_y, start_x, 0);	
	queue_append(n, q);
	color[start_y][start_x][0] = 2;
	dist[start_y][start_x] = 0;

	while ((n = queue_pop(q)) != NULL) {
		/* 到达一个新的位置 */
		cy = n->y;
		cx = n->x;
		jrl = n->jrl;

		/*
		printf("pop:(%d,%d)%d +%d, [%d][%d]\n", 
			cy, cx, jrl,  dist[cy][cx], color[cy][cx][0], color[cy][cx][1]);
		*/

		free(n);

		cdst = dist[cy][cx] + 1;
		if (cdst > maxdst)
			goto exit;
		
		for (d = 0; d < 4; ++d) {
			/* 向某个方向踏出一步 */
			ny = cy + dir[d];
			nx = cx + dir[d + 4];
			nj = jrl;

			if (ny < 0 || ny >= height || nx < 0 || nx >= width)
				continue;
			if (map[ny][nx] == '#')
				continue;
			if (color[ny][nx][nj] == 2)
				continue;

			if (ny == end_y && nx == end_x && nj == 1) {
				res = 1;
				goto exit;
			}

			if (map[ny][nx] == 'J') {
				color[ny][nx][0] = 2;
				nj = 1;
			}

			n = node_new(ny, nx, nj);
			queue_append(n, q);
			color[ny][nx][nj] = 2;
			dist[ny][nx] = cdst;
		}
	}

	exit:
	while ((n = queue_pop(q)) != NULL)
		free(n);

	return res;
}

int main(void)
{
	int y, x, d;
	int blocks;
	int ncas;

	nodequeue = queue_new(128);

	freopen("Inputs/1983", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d", &ncas);
	while (ncas--) {
		scanf("%d%d%d%*c", &height, &width, &maxdst);
		for (y = 0; y < height; ++y) {
			for (x = 0; x < width; ++x) {
				scanf("%c", &map[y][x]);
				if (map[y][x] == 'S') {
					start_y = y;
					start_x = x;
				} else if (map[y][x] == 'E') {
					end_y = y;
					end_x = x;
				}
			}
			scanf("%*c");
		}
		
		blocks = 0;
		d = -1;
		test:
		//printf("block: %d\n", d);
		if (complete()) {
			for (++d; d < 4; ++d) {
				y = start_y + dir[d];
				x = start_x + dir[d + 4];
				if (map[y][x] == 'J' || map[y][x] == '.') {
					++blocks;
					map[y][x] = '#';
					goto test;
				}
			}
		}
		
		printf("%d\n", blocks);
	}

	return 0;
}
