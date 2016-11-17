/*
 * 1254 - 推箱子
 *
 * BFS:箱子最少要移动的位置,所以只不过是在移动到下一点之前多了一次过滤而以
 * 写程序的时候把j写成i,把x写成y,m写成n这种笔误你是很难查出来的!!!!
 */

#include <assert.h>
#include <limits.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "Source/arrayqueue.c"

/*
 * node
 */

struct node {
	short y, x, py, px;
};

struct node *node_new(short y, short x, short py, short px)
{
	struct node *r;

	assert(r = malloc(sizeof(struct node)));
	r->y = y;
	r->x = x;
	r->py = py;
	r->px = px;
	
	return r;
}

void node_destroy(struct node *n) {
	if (n)
		free(n);
}

void node_destroy_all(struct queue *q)
{
	struct node *n;

	while ((n = queue_pop(q)) != NULL) {
		free(n);
	}
		
}

/*
 * node end
 */

short height, width, map[7][7], colorp[7][7], edge[7][7][7][7];
int dist[7][7];
struct queue *qbox, *qperson;
short dir[] = {0 ,0, 1, -1, 1, -1, 0, 0};

short can_move_to(int ey, int ex, int sy, int sx)
{
	short res = 0;

	struct node *n;
	struct queue *q;
	short cy, cx, ny, nx;
	short d;

	/* 初始化一定要放在最前面 */
	q = qperson;
	q->first = q->last = 0;

	memset(colorp, 0, 49 * sizeof(short));

	if (sy < 0 || sy >= height || sx < 0 || sx >= width)
		goto exit;
	if (map[sy][sx] == 1 || map[sy][sx] == 2)
		goto exit;
	if (ey == sy && ex == sx) {
		res = 1;
		goto exit;
	}


	
	queue_append(node_new(sy, sx, 0, 0), q);
	colorp[sy][sx] = 2;

	while ((n = queue_pop(q)) != NULL) {
		cy = n->y;
		cx = n->x;
		//printf("person pop: (%d, %d)\n", cy, cx);
		for (d = 0; d < 4; ++d) {
			ny = cy + dir[d];
			nx = cx + dir[d + 4];
			if (ny < 0 || ny >= height || nx < 0 || nx >= width)
				continue;
			//printf("person: m[1][1] = %d\n", map[1][1]);
			if (map[ny][nx] == 1 || map[ny][nx] == 2)
				continue;
			if (colorp[ny][nx] == 2)
				continue;
			if (ny == ey && nx == ex) {
				res = 1;
				goto exit;
			}
			colorp[ny][nx] = 2;
			queue_append(node_new(ny, nx, 0, 0), q);
		}
		node_destroy(n);
	}

	exit:
	printf("can_move_to:%d (%d,%d)->(%d,%d)\n", res, sy, sx, ey, ex);
	//sleep(1);
	node_destroy_all(q);

	return res;
}

int bfs(int sy, int sx, int py, int px)
{
	int res = -1;

	int cy, cx, ny, nx, npy, npx;
	int d;
	struct node *n;
	struct queue *q;

	q = qbox;
	q->first = q->last = 0;

	memset(edge, 0, 7 * 7 * 7 * 7 * sizeof(short));

	queue_append(node_new(sy, sx, py, px), q);
	dist[sy][sx] = 0;


	while ((n = queue_pop(q)) != NULL) { 
		cy = n->y;
		cx = n->x;
		py = n->py;
		px = n->px;
		map[cy][cx] = 2;
		map[py][px] = 4;
		printf("box pop:(%d,%d)\n", cy, cx);
		//sleep(1);
		for (d = 0; d < 4; ++d) {
			ny = cy + dir[d];
			nx = cx + dir[d + 4];
			printf("dest:(%d,%d)\n", ny, nx);
			//printf("m[1][1] = %d\n", map[1][1]);
			//sleep(1);
			if (ny < 0 || ny >= height || nx < 0 || nx >= width)
				continue;
			if (map[ny][nx] == 1)
				continue;
			if (edge[ny][nx][cy][cx] == 1)
				continue;
			npy = cy - dir[d];
			npx = cx - dir[d + 4];
			if (!can_move_to(npy, npx, py, px))
				continue;
			dist[ny][nx] = dist[cy][cx] + 1;
			if (map[ny][nx] == 3) {
				res = dist[ny][nx];
				goto exit;
			}
			edge[ny][nx][cy][cx] = 1;
			queue_append(node_new(ny, nx, cy, cx), q);
		}
		node_destroy(n);
		map[cy][cx] = 0;
		map[py][px] = 0;
	}

	exit:
	node_destroy_all(q);

	return res;
}

int main(void)
{
	short ncas;
	short y, x, sy, sx, py, px;

	freopen("Inputs/1254", "r", stdin);
	setbuf(stdout, NULL);

	qperson = queue_new(50);
	qbox = queue_new(50);

	scanf("%d", &ncas);
	while (ncas--) {
		scanf("%d%d", &height, &width);
		for (y = 0; y < height; ++y) {
			for (x = 0; x < width; ++x) {
				scanf("%d", &map[y][x]);
				dist[y][x] = INT_MAX;
				if (map[y][x] == 2) {
					sy = y;
					sx = x;
				} else if(map[y][x] == 4) {
					py = y;
					px = x;
				}
				printf("map: (%d,%d) = %d\n", y, x, map[y][x]);
			}
		}
		printf("(sy, sx) = (%d, %d)\n", sy, sx);
		printf("result:%d\n", bfs(sy, sx, py, px));
	}

	return 0;
}
	
