/*
 * 710B
 *
 * 大整数超过限制
 * wrong answer 9997976
 * 用long long是好的方法.
 * 好好检查错误,估计还是越界之类的问题
 * !!!!!!!!!!!!!!!!!!!!!
 * 不能信!!!!!!!!!!!!!!!!!!
 * !!!!!!!!!!!!!!!!!!
 */

#include <stdio.h>

#include "heap.c"

//#include "debug.c"

struct node {
	int i, v;
};

struct node a[300000];
long long sums[300001], results[300001];

int compare(const void *x, const void *y)
{
	return ((struct node *)y)->v - ((struct node *)x)->v;
}

int main(void)
{
	long long sum, distance;
	int n, i, minv, val;
	int delta;
	struct heap *h;
	struct node *node;

	h = heap_new(300000, compare);

	scanf("%d", &n);
	for (i = 0; i < n; ++i) {
		a[i].i = i;
		scanf("%d", &a[i].v);
		heap_insert(a + i, h);
	}

	while ((node = heap_del(h)) != NULL)
		h->cell[h->last + 1] = node;

	minv = ((struct node *)h->cell[1])->v;
	sum = 0;
	for (i = 1; i <= n; ++i) {
		node = h->cell[i];
		sum += node->v - minv;
		sums[i] = sum;
		//printis(0, 0, "sums: %3d: %d\n", i, sums[i]);
	}

	for (i = 1; i <= n; ++i) {
		node = h->cell[i];
		delta = node->v - minv;
		/*!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
		/*大整数越界, 原因是delta * (2 * i - n)的时候没有做大整形提升!!!! */
		/* delta -> (long long)delta!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
		results[i] = (long long)delta * (2 * i - n) + sums[n] - 2 * sums[i];
		//printis(0, 0, "results: %3d: %d\n", i, results[i]);
	}

	distance = results[1];
	val = ((struct node *)h->cell[1])->v;
	for (i = 1; i <= n; ++i) {
		node = h->cell[i];
		//printis(0, 0, "(%d, #%d, %d)\n", results[i], node->i, node->v);
		if (results[i] < distance || (results[i] == distance && node->v < val)) {
			//printis(0, 0, "(%d, %d) -> (%d, %d)\n", distance, val, results[i], node->v);
			distance = results[i];
			val = node->v;
		}
	}

	printf("%d", val);

	return 0;
}
