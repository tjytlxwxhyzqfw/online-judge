/*
 * 1372 Knight Moves
 *
 * BFS: S
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

void node_destroyall(struct queue *q)
{
	struct node *n;
	
	while ((n = queue_pop(q)) != NULL)
		node_destroy(n);
}

/* node end */


char map[8][8], color[8][8];
int dist[8][8];
struct queue *nodequeue;

const char dir[] = {-2, -2, -1, 1, 2, 2, 1, -1,/**/ -1, 1, 2, 2, 1, -1, -2, -2}; 

int bfs(int sy, int sx, int ey, int ex)
{
	int res = 0;

	int cy, cx, ny, nx, d;
	struct queue *q;
	struct node *n;

	if (sy == ey && sx == ex)
		return 0;

	q = nodequeue;
	q->first = q->last = 0;

	queue_append(node_new(sy, sx), q);
	color[sy][sx] = 2;
	dist[sy][sx] = 0;

	while ((n = queue_pop(q)) != NULL) {
		//printf("pop:%c%d\n", n->y + 97, n->x);
		cy = n->y;
		cx = n->x;
		for (d = 0; d < 8; ++d) {
			ny = cy + dir[d];
			nx = cx + dir[d + 8];
			//printf("moveto:%c%d\n", ny + 97, nx);
			if (ny < 0 || ny >= 8 || nx < 0 || nx >= 8)
				continue;
			if (color[ny][nx] == 2)
				continue;
			queue_append(node_new(ny, nx), q);
			color[ny][nx] = 2;
			dist[ny][nx] = dist[cy][cx] + 1;
			if (ny == ey && nx == ex) {
				res = dist[ny][nx];
				goto exit;
			}
		}
		node_destroy(n);
	}

	exit:
	node_destroyall(q);
	
	return res;
}

int main(void)
{
	int sy, sx, ey, ex;

	freopen("Inputs/1372", "r", stdin);
	setbuf(stdout, NULL);

	nodequeue = queue_new(64);

	while (scanf("%c%d%*c%c%d%*c", &sy, &sx, &ey, &ex) == 4) {
		sy = (char)sy - 97;
		sx -= 1;
		ey = (char)ey - 97;
		ex -= 1;
		memset(color, 0, sizeof(char) * 8 * 8);
		printf("To get from %c%d to %c%d takes %d knight moves.\n", 
			sy + 97, sx + 1, ey + 97, ex + 1, bfs(sy, sx, ey, ex));
	}

	return 0;
}
