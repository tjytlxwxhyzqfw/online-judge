/*
 * 2102 A 计划
 *
 * MWS, BFS
 */

#include <assert.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "Source/arrayqueue.c"


struct node {
	int y, x, f;
};

struct node *node_new(int y, int x, int f)
{
	struct node *n;

	assert(n = malloc(sizeof(struct node)));
	n->y = y;
	n->x = x;
	n->f = f;

	return n;
}

char map[10][10][2], color[10][10][2];
int dist[10][10][2];

int height, width, maxdist;

int dir[] = {-1, 0, 1, 0, 0, 1, 0, -1};

struct queue *nodequeue;

int bfs(void)
{
	int res = 0;

	int cy, cx, cf, ny, nx, nf;
	int cd, nd;
	int d;

	struct node *n;
	struct queue *q;

	q = nodequeue;
	q->first = q->last = 0;

	n = node_new(0, 0, 0);
	queue_append(n, q);
	color[0][0][0] = 1;
	dist[0][0][0] = 0;
	
	while ((n = queue_pop(q)) != NULL) {
		cy = n->y;
		cx = n->x;
		cf = n->f;
		free(n);

		//printf("pop(%d,%d,%d)\n", cy, cx, cf);
		//sleep(1);

		cd = dist[cy][cx][cf];
		if (cd >= maxdist)
			goto exit;
		nd = cd + 1;

		for (d = 0; d < 4; ++d) {
			ny = cy + dir[d];
			nx = cx + dir[d + 4];
			nf = cf;

			//printf("\tmoveto:(%d,%d,%d)\n", ny, nx, nf);
			
			if (ny < 0 || ny >= height || nx < 0 || nx >= width)
				continue;

			if (map[ny][nx][nf] == '*')
				continue;

			if (color[ny][nx][nf] == 1)
				continue;

			if (map[ny][nx][nf] == 'P') {
				res = 1;
				goto exit;
			}

			/* never enqueue */
			if (map[ny][nx][nf] == '#') {
				color[ny][nx][nf] = 1;
				nf = (nf ? 0: 1);	
				if (map[ny][nx][nf] == '#')
					continue;
				/* TODO optimize */
				if (map[ny][nx][nf] == '*')
					continue;
				if (color[ny][nx][nf] == 1)
					continue;
				if(map[ny][nx][nf] == 'P') {
					res = 1;
					goto exit;
				}
			}

			n = node_new(ny, nx, nf);
			queue_append(n, q);
			color[ny][nx][nf] = 1;
			dist[ny][nx][nf] = nd;

			//printf("\tenqueue:(%d,%d,%d)\n", ny, nx, nf);
		}
	}

	exit:
	while ((n = queue_pop(q)) != NULL)
		free(n);

	return res;
}

int main(void)
{
	int ncas;

	int y, x, f;
	int ch;

	freopen("Inputs/2102", "r", stdin);
	setbuf(stdout, NULL);

	nodequeue = queue_new(2000);

	scanf("%d", &ncas);
	//printf("ncas=%d\n", ncas);
	while (ncas--) {
		scanf("%d%d%d%*c",&height, &width, &maxdist); 
		//printf("(%d,%d,%d)\n", height, width, maxdist);
		for (f = 0; f < 2; ++f) {
			for (y = 0; y < height; ++y) {
				for (x = 0; x < width; ++x) {
					scanf("%c", &map[y][x][f]);
					//printf("(%d,%d,%d):%c\n", y, x, f, map[y][x][f]);
				}
				scanf("%*c");
			}
			while ((ch = getchar()) != EOF) {
				if (!isspace(ch)) {
					ungetc(ch, stdin);
					break;
				}
			}
		}

		memset(color, 0, sizeof(char) * 10 * 10 * 2);
		printf("%s\n", bfs() ? "YES" : "NO");
	}

	return 0;
}
					

			
