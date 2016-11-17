/*
 * 1029
 * using heap
 */

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

struct heap {
	int cap, last;
	int *cells;
};

struct heap *heap_new(int cap)
{
	struct heap *h;

	assert(h = malloc(sizeof(struct heap)));
	assert(h->cells = malloc(sizeof(int) * cap));
	h->cap = cap;
	h->last = 0;

	return h;
}

void heap_destroy(struct heap *h)
{
	if (h) {
		free(h->cells);
		free(h);
	}
}

void heap_up(int x, struct heap *h)
{
	int t, p;

	while (p = x / 2) {
		//printf("x[%d] = %d, p[%d] = %d\n", x, h->cells[x], p, h->cells[p]);
		if (h->cells[p] <= h->cells[x])
			break;
		t = h->cells[p];
		h->cells[p] = h->cells[x];
		h->cells[x] = t;
		x = p;
	}
}	

void heap_down(int x, struct heap *h)
{
	int c, d;
	int t;

	while ((c = 2 * x) <= h->last) {
		d = c + 1;
		if (d <= h->last && h->cells[d] < h->cells[c])
			c = d;
		if (h->cells[x] < h->cells[c])
			break;
		t = h->cells[x];
		h->cells[x] = h->cells[c];
		h->cells[c] = t;
		x = c;
	}
}

void heap_insert(int e, struct heap *h)
{
	h->cells[++h->last] = e;
	heap_up(h->last, h);
}

int heap_delmin(struct heap *h)
{
	int min;

	min = h->cells[1];
	h->cells[1] = h->cells[h->last--];
	heap_down(1, h);

	return min;
}

void heap_print(struct heap *h)
{
	int i;

	for (i = 1; i <= h->last; ++i)
		printf("%d%s", h->cells[i], i == h->last ? "\n" : " ");
}

int main(void)
{
	int n, i;
	int e;
	struct heap *h;

	freopen("Inputs/1029", "r", stdin);

	while (scanf("%d", &n) != EOF) {
		h = heap_new(n + 1);
		for (i = 0; i < n; ++i) {
			scanf("%d", &e);
			heap_insert(e, h);
		}
		n = n / 2 + 1;
		while (n--)
			e = heap_delmin(h);
		printf("%d\n", e);
		heap_destroy(h);
	}

	return 0;
}
