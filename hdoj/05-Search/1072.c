/*
 * 1072 Nightmare
 * BFS
 */

#include <assert.h>
#include <limits.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int M, N;
int lab[8][8];
int dir[] = {0, 0, -1, 1, -1, 1, 0, 0};

struct node {
	int x, y;
	int left;
};

struct node *null;

struct node *node_init(int x, int y, int left)
{
	struct node *n;

	assert(n = malloc(sizeof(struct node)));
	n->x = x;
	n->y = y;
	n->left = left;

	return n;
}

struct node *node_destroy(struct node *n)
{
	//printf("ready to free: %d %d %d\n", n->x, n->y, n->left);
	free(n);
	//puts("freed\n");
}

struct queue{
	int size;
	int first, tail;
	struct node **cell;
};

struct queue *queue_init(int size)
{
	struct queue *q;

	assert(q = malloc(sizeof(struct queue)));
	assert(q->cell = malloc(sizeof(struct node *) * size));
	q->size = size;
	q->first = q->tail = 0;

	return q;
}

void queue_destroy(struct queue *q)
{
	if (q) {
		free(q->cell);
		free(q);
	}
}

void queue_print(struct queue *q)
{
	int i;

	for (i = q->first; i < q->tail; ++i)
		printf("%2d: (%d,%d)[%d]\n", i, q->cell[i]->x, q->cell[i]->y, 
				q->cell[i]->left);
}

void queue_append(struct node *n, struct queue *q)
{
	printf("app[%d, %d]: (%d, %d) [%d]\n", q->first, q->tail, n->x, n->y, n->left);
	q->cell[q->tail++] = n;
	q->tail %= q->size;
}

struct node *queue_pop(struct queue *q)
{
	struct node *n;

	n = q->cell[q->first++];
	q->first %= q->size;
	
	printf("pop(%d, %d): (%d, %d) [%d]\n", q->first, q->tail, n->x, n->y, n->left);
	return n;
}

int queue_isempty(struct queue *q)
{
	return q->tail == q->first;
}

int bfs(int bx, int by)
{
	struct queue *q;
	struct node *nn, *new;
	int x, y, left, d, time;

	q = queue_init(50000);
	nn = node_init(bx, by, 6);
	null = node_init(-1, -1, -1);

	time = 0;
	queue_append(nn, q);
	queue_append(null, q);
	printf("time = 0\n");
	sleep(1);
	while (1) {
		nn = queue_pop(q);
		if (queue_isempty(q))
			break;
		if (nn->x == -1 && nn->y == -1) {
			assert(nn == null);
			time += 1;
			printf("time = %d\n", time);
			sleep(1);
			queue_append(nn, q);
			continue;
		}
		for (d = 0; d < 4; ++d) {
			x = nn->x + dir[d];
			y = nn->y + dir[d + 4];
			left = nn->left - 1;
			if (x >= 0 && x < M && y >= 0 && y < N
					&& lab[x][y] != 0) {
				if (lab[x][y] == 3 && left > 0) {
					queue_destroy(q);
					return time + 1;
				}
				if (lab[x][y] == 4) {
					left = 6;
					/* 4是不必重复走的 */
					lab[x][y] = 0;
				}
				if (left > 0) {
					new = node_init(x, y, left);
					queue_append(new, q);
				}
			}
		}
		node_destroy(nn);
	}
	return -1;
}

int main(void)
{
	int i, j;
	int ncas;
	int time;
	int bx, by, ex, ey;
	int **lab2;

	setbuf(stdout, NULL);
	freopen("Inputs/1072", "r", stdin);

	scanf("%d", &ncas);
	while (ncas--) {
		scanf("%d%d", &M, &N);
		for (i = 0; i < M; ++i)
			for (j = 0; j < N; ++j) {
				scanf("%d", &lab[i][j]);
				if (lab[i][j] == 2) {
					bx = i;
					by = j;
				}
			}
		time = bfs(bx, by);
		printf("%d\n", time);
		sleep(5);
	}
					
	return 0;
}
