/*
 * Find a way
 * 
 * primary bfs
 */

#include <assert.h>
#include <limits.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "Source/arrayqueue.c"

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

struct queue *nodequeue;

char map[200][200], color[200][200];
int ydst[200][200], mdst[200][200];
int height, width;

const int const dir[] = {1, 0, -1, 0, 0, 1, 0, -1};

void bfs(int start_y, int start_x, int (*dst)[200])
{
	struct node *n;
	struct queue *q;
	int cy, cx, cd, ny, nx;
	int d;

	q = nodequeue;
	q->first = q->last = 0;

	n = node_new(start_y, start_x);
	queue_append(n, q);
	color[start_y][start_x] = 1;
	dst[start_y][start_x] = 0;
	
	while ((n = queue_pop(q)) != NULL) {
		cy = n->y;
		cx = n->x;
		free(n);

		cd = dst[cy][cx];

		//printf("pop:(%d,%d) +%d\n", cy, cx, cd);
		
		for (d = 0; d < 4; ++d) {
			ny = cy + dir[d];
			nx = cx + dir[d + 4];
			
			if (ny < 0 || ny >= height || nx < 0 || nx >= width)
				continue;
			if (map[ny][nx] == '#')
				continue;
			if (color[ny][nx] == 1)
				continue;

			n = node_new(ny, nx);
			queue_append(n, q);
			color[ny][nx] = 1;
			dst[ny][nx] = cd + 1;
		}
	}
}

int main(void)
{
	int y, x, yy, yx, my, mx;
	int sum, best;

	freopen("Inputs/2612", "r", stdin);
	setbuf(stdout, NULL);

	nodequeue = queue_new(40000);

	while (scanf("%d%d%*c", &height, &width) == 2) {
		for (y = 0; y < height; ++y) {
			for (x = 0; x < width; ++x) {
				scanf("%c", &map[y][x]);
				if (map[y][x] == 'Y') {
					yy = y;
					yx = x;
				} else if (map[y][x] == 'M') {
					my = y;
					mx = x;
				}
			}
			scanf("%*c");
		}

		memset(color, 0, sizeof(char) * 200 * 200);
		bfs(yy, yx, ydst);

		memset(color, 0, sizeof(char) * 200 * 200);
		bfs(my, mx, mdst);

		sum = 0;
		best = INT_MAX;
		for (y = 0; y < height; ++y) {
			for (x = 0; x < width; ++x) {
				if (map[y][x] != '@')
					continue;
				sum = ydst[y][x] + mdst[y][x];
				if (sum < best)
					best = sum;
				//printf("(%d,%d): +%d +%d =%d\n", y, x, ydst[y][x], mdst[y][x], sum);
			}
		}
		printf("%d\n", best * 11);
	}

	return 0;
}
