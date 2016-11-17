/*
 * 1253 - 胜利大逃亡
 *
 * @firstglance ordinary bfs
 * @type bfs 
 *
 * 第一次提交以后需要优化内存: 因为我没有释放队列队列呀亲!!
 *
 * @lession BFS队列元素入队的时候就要立即变黑!!!!!!!!!!不要等到出队才变黑!!
 */

#include <assert.h>
#include <limits.h>
#include <stdio.h>
#include <stdlib.h>

/* queue */

#include "Source/arrayqueue.c"

/* end queue */

struct node {
	char a, b, c;
};

struct node *node_new(char a, char b, char c)
{
	struct node *n;

	assert(n = malloc(sizeof(struct node)));
	n->a = a;
	n->b = b;
	n->c = c;
	
	return n;
}

void queue_free(struct queue *q)
{
	int i;
	struct node *n;
	
	for (i = q->first; i != q->last; i = (i + 1) % q->cap) {
		n = q->cell[i];
		if (n)
			free(n);
	}

	queue_destroy(q);
}

char const dir[] = {1, -1, 0, 0, 0, 0,/**/ 0, 0, 1, -1, 0, 0, /**/ 0, 0, 0, 0, 1, -1};

int amax, bmax, cmax, dmax;
int dist[50][50][50];
char map[50][50][50], color[50][50][50];

int bfs(void)
{
	/*
	 * gloabls should have been initialized
	 */
	
	int dst = -1;
	int outcnt = 0;

	struct node *n;
	struct queue *q;
	char na, nb, nc, d;

	q = queue_new(100000);

	n = node_new(0, 0, 0);
	dist[0][0][0] = 0;
	color[0][0][0] = 1;
	queue_append(n, q);

	while ((n = queue_pop(q)) != NULL) {
		if (dist[n->a][n->b][n->c] >= dmax)
			break;
		/*printf("(%d %d %d) +%d (%d) \n", n->a, n->b, n->c, dist[n->a][n->b][n->c], ++outcnt);*/
		/*sleep(1);*/
		for (d = 0; d < 6; ++d) {
			na = n->a + dir[d];
			nb = n->b + dir[d + 6];
			nc = n->c + dir[d + 12];
			if (na < 0 || na >= amax || nb < 0 || nb >= bmax || nc < 0 || nc >= cmax)
				continue;
			if (map[na][nb][nc] == 1)
				continue;
			if (color[na][nb][nc] == 2)
				continue;
			dist[na][nb][nc] = dist[n->a][n->b][n->c] + 1;
			if (na == amax - 1 && nb == bmax - 1 && nc == cmax - 1) {
				dst = dist[na][nb][nc];
				/* 小心预防内存泄漏 */
				free(n);
				goto exit;
			}
			/* 第一次被发现的元素以后已经处在最佳状态,不必经历"考验期"(color=1)了 */
			queue_append(node_new(na, nb, nc), q);
			/*!!!
			 * 同一个地方摔倒过两次.
			 !!!*/
			color[na][nb][nc] = 2;
			/*printf("queue_append:(%d,%d,%d)\n", na, nb, nc);*/
		}
		/* 小心预防内存泄漏 */
		free(n);
	}

	exit:
	/* 小心预防内存泄漏 */
	queue_free(q);
	return dst;
}
			
int main(void)
{
	int i, j, k, dst, ncas;

	freopen("Inputs/1253", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d", &ncas);
	while (ncas--) {
		scanf("%d%d%d%d", &amax, &bmax, &cmax, &dmax);
		/*amax = 50, bmax = 50, cmax = 50, dmax = 1000;*/
		for (i = 0; i < amax; ++i) {
			for (j = 0; j < bmax; ++j) {
				for (k = 0; k < cmax; ++k) {
					scanf("%d", &map[i][j][k]);
					/*map[i][j][k] = 0;*/
					color[i][j][k] = 0;
					dist[i][j][k] = INT_MAX;
				}
			}
		}
		printf("%d\n", bfs());
	}

	return 0;
}
