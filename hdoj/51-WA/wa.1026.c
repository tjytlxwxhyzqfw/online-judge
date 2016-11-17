/*
 * 1026.Ignatius and the Princess I
 */

#include <assert.h>
#include <limits.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct node {
	int x, y;
	int px, py;
	char in;
	int ds;
};

char map[100][101];
struct node nodes[100][100];

int M, N, best;
int dir[10] = {0, 0, 0, -1, 1, 0, -1, 1, 0, 0};

void printnode(struct node *n)
{
	printf("(%d,%d)[%d] +%d (%d,%d)\n", n->x, n->y, n->in, n->ds,
			n->px, n->py);
}

void printds() {
	int i, j;

	for (i = 0; i < M; ++i) {
		for (j = 0; j < N; ++j)
			printf("%10d ", nodes[i][j].ds);
		printf("\n");
	}
	printf("\n");
}

struct heap {
	int last;
	struct node **cell;
};

struct heap *h;

void printheap()
{
	int i;
	for (i = 1; i <= h->last; ++i)
		printf("%d ", h->cell[i]->ds);
	printf("\n");
}

struct heap * heap_init()
{
	struct heap *h;
	
	assert(h = malloc(sizeof(struct heap)));
	h->last = 0;
	assert(h->cell = malloc(sizeof(struct node *) * 10001));
	memset(h->cell, 0, sizeof(h->cell));
	
	return h;
}

void heap_destroy()
{
	free(h->cell);
	free(h);
}

int heap_isempty()
{
	return h->last == 0;
}

void heap_bottomup(int i)
{
	struct node *t;
	int p;

	for (p = i / 2; p && h->cell[i]->ds < h->cell[p]->ds; 
			i = p, p /= 2) {
		t = h->cell[i];
		h->cell[i] = h->cell[p];
		h->cell[p] = t;
	}
}

void heap_insert(struct node *n)
{
	h->cell[++h->last] = n;
	heap_bottomup(h->last);
}

void heap_topdown(int i)
{
	struct node *t;
	int c;
	
	while (i * 2 <= h->last) {
		c = i * 2;
		if (c + 1 <= h->last && h->cell[c + 1]->ds < h->cell[c]->ds)
			c += 1;
		if (h->cell[i]->ds < h->cell[c]->ds)
			break;
		t = h->cell[i];
		h->cell[i] = h->cell[c];
		h->cell[c] = t;
		i = c;
	}
}

struct node *heap_del()
{
	struct node *ret;

	ret = h->cell[1];
	h->cell[1] = h->cell[h->last--];
	heap_topdown(1);

	return ret;
}

int bfs(void)
{
	int i, j, d;
	struct node *n, *n2;
	int x, y, nx, ny;
	int ds, ds2, ds_old;

	for (i = 0; i < 100; ++i)
		for (j = 0; j < 100; ++j) {
			n = &nodes[i][j];
			n->x = i;
			n->y = j;
			n->px = n->py = 0;
			n->in = 0;
			n->ds = INT_MAX;
		}
	
	h = heap_init();
	nodes[0][0].ds = 0;
	heap_insert(&nodes[0][0]);
	while (!heap_isempty()) {
		//printds();
		n = heap_del();
		n->in = 1;
		ds = n->ds;
		x = n->x;
		y = n->y;
		for (d = 1; d <= 4; ++d) {
			nx = x + dir[d];
			ny = y + dir[d + 5];
			if (nx < 0 || nx >= M || ny < 0 || ny >= N
					|| map[nx][ny] == 'X')
				continue;
			n2 = &nodes[nx][ny];
			ds_old = 0;
			ds2 = ds + map[nx][ny] - 47;
			if (ds2 < n2->ds) {
				n2->px = x;
				n2->py = y;
				ds_old = n2->ds;
				n2->ds = ds2;
			}
			if (ds_old == INT_MAX)
				heap_insert(n2);
		}
	}
	heap_destroy();	
	return 0;
}

void printway(int sec, int x, int y)
{
	struct node *n;
	int px, py;
	int hp;

	n = &nodes[x][y];
	px = n->px;
	py = n->py;
	if (px == 0 && py == 0) {
		printf("It takes %d seconds to reach the target position,"
				" let me show you the way.\n", 
				nodes[M - 1][N - 1].ds);
		printf("%ds:(%d,%d)->(%d,%d)\n", sec++, px, py, x, y);
		return;
	}
	hp = map[x][y] - 48;
	sec -= hp;
	printway(sec - 1, px, py);
	printf("%ds:(%d,%d)->(%d,%d)\n", sec++, px, py, x, y);
	while (hp--)
		printf("%ds:FIGHT AT (%d,%d)\n", sec++, x, y);
}

int main(void)
{
	int i, j, sec;

	freopen("Inputs/1026", "r", stdin);
	setbuf(stdout, NULL);

	while (scanf("%d%d", &M, &N) != -1) {
		memset(map, 0, sizeof(map));
		best = INT_MAX;
		for (i = 0; i < M; ++i)
			scanf("%s", map[i]);
		for (i = 0; i < M; ++i)
			for (j = 0; j < N; ++j)
				if (map[i][j] == '.')
					map[i][j] = '0';
		for (i = 0; i < M; ++i)
			puts(map[i]);
		bfs();
		sec = nodes[M-1][N-1].ds;
		if (sec == INT_MAX)
			printf("God please help our poor hero.\n");
		else
			printway(sec, M - 1, N - 1);
		printf("FINISH\n");
	}
	return 0;
}
