/*
 * 1242 Rescue
 *
 * 算法:Dijkstra
 *
 * 最短路径问题,但是不能用基本BFS求解,
 * 也不愿意用DFS求解,仔细思考,发现本质是带权最短路径
 *
 * 一种用DIJKSTRA+HEAP的思路,只不过只不过最坏性能从O(n)降低到了O(4n)
 *
 * @lesson 忘掉初始化: h->last
 *
 */

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

#include "Source/heap.c"

struct node {
	int len;
	int top, left;
};

int node_cmp(void *x, void *y)
{
	return ((struct node*)x)->len - ((struct node *)y)->len;
}

struct node *node_new(int top, int left, int len)
{
	struct node *n;

	assert(n = malloc(sizeof(struct node)));
	n->top = top;
	n->left = left;
	n->len = len;

	return n;
}

void node_destroy(struct node *n) {
	if (n)
		free(n);
}

char map[200][200];
int height, width, min[200][200];
char dir[] = {0, 0 ,1, -1, 1, -1, 0, 0};

int dijkstra(int top, int left, int dt, int dl)
{
	int d, nt, nl, nlen;
	int err;

	struct node *n;
	struct heap *h;

	h = heap_new(160000);
	h->cmp = node_cmp;
	n = node_new(top, left, 0);
	heap_insert(n, h);

	while ((n = (struct node *)heap_del(h)) != NULL) {
		/*
		printf("heap_del: (%d,%d) + %d\n", n->top, n->left, n->len);
		sleep(1);
		*/
		if (min[n->top][n->left] < n->len)
			goto loopend;
		map[n->top][n->left] = '#';
		if (n->top == dt && n->left == dl)
			return n->len;
		for (d = 0; d < 4; ++d) {
			nt = n->top + dir[d];
			nl = n->left + dir[d + 4];
			if (nt < 0 || nt >= height || nl < 0 || nl >= width)
				continue;
			if (map[nt][nl] == '#')
				continue;
			nlen = n->len + 1;
			if (map[nt][nl] == 'x')
				nlen += 1;
			if (nlen < min[nt][nl]) {
				min[nt][nl] = nlen;
				err = heap_insert(node_new(nt, nl, nlen), h);
				if (err == -1)
					while(1);
			}
		}
		loopend:
		node_destroy(n);
	}
	
	return INT_MAX; 

}

int main(void)
{
	int start_y, start_x, end_y, end_x, result, x, y;

	freopen("Inputs/1242", "r", stdin);
	setbuf(stdout, NULL);

	while (scanf("%d%d%*c", &height, &width) != EOF) {
		for (y = 0; y < height; ++y) {
			for (x = 0; x < width; ++x) {
				scanf("%c", &map[y][x]);
				min[y][x] = INT_MAX;
				if (map[y][x] == 'a') {
					start_y = y;
					start_x = x;
				}
				if (map[y][x] == 'r') {
					end_y = y;
					end_x = x;
				}
			}
			scanf("%*c");
		}
		result = dijkstra(start_y, start_x, end_y, end_x);
		if (result != INT_MAX)
			printf("%d\n", result);
		else
			puts("Poor ANGEL has to stay in the prison all his life.");
	}
	
	return 0;
}


