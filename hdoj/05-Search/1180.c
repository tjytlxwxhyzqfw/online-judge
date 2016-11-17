/*
 * 1180.c
 * 这次用BFS来写
 */

#include <assert.h>
#include <limits.h>
#include <stdio.h>
#include <stdlib.h>

struct queue {
	void **cell;
	int first, last;
	int size;
};

struct queue *queue_new(int size)
{
	struct queue *q;

	
	assert(q = malloc(sizeof(struct queue)));
	assert(q->cell = malloc(sizeof(void *) * size));
	q->size = size;
	q->first = q->last = 0;
	
	return q;
}

void queue_destroy(struct queue *q)
{
	if (q)
		free(q->cell);
	free(q);
}

void queue_realloc(struct queue *q);
void queue_append(void *x, struct queue *q)
{
	if ((q->last + 1) % q->size == q->first)
		queue_realloc(q);
	q->cell[q->last++] = x;
	q->last %= q->size;
}

void queue_realloc(struct queue *q)
{
	void **cell;
	int i, size;
	
	cell = q->cell;

	assert(q->cell = malloc(sizeof(void *) * q->size * 2));
	for (i = 0, size = q->size - 1; i < size; ++i) {
		q->cell[i] = cell[q->first++];
		q->first %= q->size;
	}
	q->first = 0;
	q->last = size;
	q->size <<= 1;

	free(cell);

	//printf("*** queue size doubled to %d(%d - %d)\n", q->size, q->first, q->last);
	//sleep(1);
}

void *queue_pop(struct queue *q)
{
	void *ret;

	ret = q->cell[q->first++];
	q->first %= q->size;

	return ret;
}

struct node {
	int x, y;
	int deepth;
};

struct node *node_new(int x, int y)
{
	struct node *n;
	
	assert(n = malloc(sizeof(struct node)));
	n->x = x;
	n->y = y;

	return n;
}

void node_destroy(struct node *n)
{
	if (n)
		free((struct node *)n);
}

void node_queue_destroy(struct queue *q) 
{
	while (q->first != q->last) {
		node_destroy(q->cell[q->first++]);
		q->first %= q->size;
	}
	queue_destroy(q);
}

int height, width;
char map_0[20][20], map_odd[20][20];
char dir[10] = {0, 1, -1, 0, 0, 0, 0, 0, 1, -1};
int visited[20][20][2];

int bfs(int start_x, int start_y)
{
	int deepth = 0;
	struct node *last_c = NULL;
	
	int x, y, d;
	char (*map)[20], content;

	struct node *n, *c;
	struct queue *q;

	for (x = 0; x < height; ++x)
		for (y = 0; y < width; ++y)
			visited[x][y][0] = visited[x][y][1] = INT_MAX;
	
	//printf("start_x = %d, start_y = %d\n", start_x, start_y);
	n = node_new(start_x, start_y);
	n->deepth = 0;

	/*
 	 * 0.05s 70000
 	 * 0.23s 2
 	 */
	q = queue_new(1);
	queue_append(n, q);
	visited[start_x][start_y][0] = 0;

dive:
	//printf("dive: deepth=%d, q->first=%d, q->last=%d\n", deepth, q->first, q->last);
	//sleep(1);
	map = ((deepth & 1UL) ? map_odd : map_0);
	queue_append(NULL, q);
	while ((c = (struct node*)queue_pop(q))) {
		//printf("c:(%d,%d,+%d)\n", c->x, c->y, c->deepth);
		node_destroy(last_c);
		last_c = c;
		//printf("deepth=%d, current=(%d,%d,+%d)\n", deepth, c->x, c->y, c->deepth);
		//sleep(1);
		/*
		 * May Be Wrong
		 */
		/* visited[c->x][c->y][deepth & 1UL] = c->deepth; */
		for (d = 0; d < 5; ++d) {
			x = c->x + dir[d];
			y = c->y + dir[d + 5];
			//printf("\tadj (%d, %d)\n", x, y);
			//sleep(1);
		slide:
			//printf("boundary check...\n");
			if (x < 0 || x >= height || y < 0 || y >= width)
				continue;
			content = map[x][y];
			//printf("T check...\n");
			if (content == 'T') {
				node_queue_destroy(q);
				node_destroy(c);
				return ++deepth;
			}
			//printf("*|- check ...\n");
			if (content == '*'
					|| (content == '|' && dir[d + 5])
					|| (content == '-' && dir[d]))
				continue;
			//printf("slicd check ...\n");
			if ((content == '|' && dir[d])
					|| content == '-' && dir[d + 5]) {
				x += dir[d];
				y += dir[d + 5];
				goto slide;
			}
			//printf("visited(%d,%d) 0=%d,1=%d,deepth=%d\n", x, y, visited[x][y][0], visited[x][y][1], deepth);
			if (visited[x][y][(deepth + 1) & 1UL] <= deepth + 1)
				continue;

			n = node_new(x, y);
			//printf("\tappend:(%d,%d,+%d)\n", n->x, n->y, deepth + 1);
			//sleep(1);
			queue_append(n, q);
			visited[x][y][(deepth + 1) & 1UL] = deepth + 1;
		}
	}
	//printf("out first=%d, last=%d\n", q->first, q->last);
	if (q->last != q->first) {
		++deepth;
		goto dive;
	}
}

int main(void)
{
	int x, y, start_x, start_y;

	freopen("Inputs/1180", "r", stdin);
	setbuf(stdout, NULL);

	while (scanf("%d%d%*c", &height, &width) != EOF) {
		for (x = 0; x < height; ++x) {
			for (y = 0; y < width; ++y) {
				scanf("%c", &map_0[x][y]);
				if (map_0[x][y] == 'S') {
					start_x = x;
					start_y = y;
				}
			}
			scanf("%*c");
		}
		
		//printf("map_0:\n");
		for (x = 0; x < height; ++x) {
			for (y = 0; y < width; ++y) {
				if (map_0[x][y] == '|')
					map_odd[x][y] = '-';
				else if(map_0[x][y] == '-')
					map_odd[x][y] = '|';
				else
					map_odd[x][y] = map_0[x][y];
			//printf("%c", map_0[x][y]);
			}
			//printf("\n");
		}

		printf("%d\n", bfs(start_x, start_y));
	}

	return 0;
}
