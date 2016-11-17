/*
 * sorting, the most official sorting
 */

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

struct heap {
	int cap, last;
	int *cell;
};

struct heap *heap_new(int cap)
{
	struct heap *h;

	assert(h = malloc(sizeof(struct heap)));
	h->cap = cap;
	h->last = 0;
	assert(h->cell = malloc(sizeof(int) * cap));

	return h;
}

void heap_destroy(struct heap *h)
{
	if (h) {
		free(h->cell);
		free(h);
	}
}

void heap_down(int x, struct heap *h)
{
	int c, d;
	int t;

	while ((c = 2 * x) <= h->last) {
		d = c + 1;
		if (d <= h->last && h->cell[d] < h->cell[c])
			c = d;
		if (h->cell[x] < h->cell[c])
			break;
		t = h->cell[x];
		h->cell[x] = h->cell[c];
		h->cell[c] = t;
		x = c;
	}
}

struct heap *heap_build(int *s, int n)
{
	struct heap *h;
	int i;

	h = heap_new(n + 1);
	for (i = 0; i < n; ++i)
		h->cell[i + 1] = s[i];
	h->last = n;

	for (i = h->last / 2; i > 0; --i)
		heap_down(i, h);

	return h;
}

int heap_delmin(struct heap *h)
{
	int min;

	min = h->cell[1];
	h->cell[1] = h->cell[h->last--];
	heap_down(1, h);

	return min;
}

void heap_print(struct heap *h)
{
	int i;
	for (i = 1; i <= h->last; ++i)
		printf("%d ", h->cell[i]);
	printf("\n");
}

void hsort(int *s, int n)
{
	struct heap *h;
	int i;
	int min;

	h = heap_build(s, n);
	
	while (h->last > 0) {
		min = heap_delmin(h);
		h->cell[h->last + 1] = min;
	}

	for (i = 0; i < n; ++i)
		s[i] = h->cell[n - i];
	
	heap_destroy(h);
}

int main(void)
{
	int ncas, n, s[1000];
	int i;

	setbuf(stdout, NULL);
	freopen("Inputs/1040", "r", stdin);

	scanf("%d", &ncas);
	while (ncas--) {
		scanf("%d", &n);
		for (i = 0; i < n; ++i)
			scanf("%d", s + i);
		hsort(s, n);
		for (i = 0; i < n; ++i)
			printf("%d%s", s[i], i == n - 1 ? "\n" : " ");
	}

	return 0;
}
