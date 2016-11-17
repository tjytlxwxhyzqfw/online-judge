/*
 * 2020 Sort
 */

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

#define abs(x) ((x < 0 ? (-x) : (x)))

struct heap {
	int cap;
	int last;
	int *cell;
};

struct heap *heap_init(int cap)
{
	struct heap *h;

	assert(h = malloc(sizeof(struct heap)));
	assert(h->cell = malloc(sizeof(int) * cap));
	h->last = 1;
	h->cap = cap;

	return h;
}

void heap_destroy(struct heap *h)
{
	if (h) {
		free(h->cell);
		free(h);
	}
}

void heap_pdown(int x, struct heap *h)
{
	int c, d;
	int tmp;

	while ((c = 2 * x) <= h->last) {
		d = c + 1;
		if (d <= h->last && abs(h->cell[d]) > abs(h->cell[c]))
			c = d;
		if (abs(h->cell[x]) > abs(h->cell[c]))
			break;
		tmp = h->cell[x];
		h->cell[x] = h->cell[c];
		h->cell[c] = tmp;
		x = c;
	}
}

int heap_build(int *s, int n, struct heap *h)
{
	int i;

	if ( n > h->cap - 1)
		return 0;
	for (i = 0; i < n; ++i)
		h->cell[i + 1] = s[i];
	h->last = i;

	for (i = h->last / 2; i > 0; --i)
		heap_pdown(i, h);
	
	return 1;
}

int heap_dmax(struct heap *h)
{
	int max;

	max = h->cell[1];
	h->cell[1] = h->cell[h->last--];
	heap_pdown(1, h);

	return max;
}

void hsort(int *s, int b, int e)
{
	struct heap *h;
	int i, tmp;

	h = heap_init(e - b + 2);
	assert(heap_build(s, e - b + 1, h));
	
	while (h->last != 0) {
		tmp = heap_dmax(h);
		h->cell[h->last + 1] = tmp;
	}

	for (i = b; i <= e; ++i)
		s[i] = h->cell[e + b + 1 - i];

	heap_destroy(h);
}
	
void isort(int *s, int b, int e)
{
	int i, j;
	int t;

	for (i = b + 1; i <= e; ++i) {
		t = s[i];
		for (j = i - 1; j >= b && abs(s[j]) < abs(t); --j)
			s[j + 1] = s[j];
		s[j + 1] = t;
	}
}

int main(void)
{
	int i, n;
	int s[100];

	freopen("2020", "r", stdin);

	while (scanf("%d", &n) != EOF && n) {
		for (i = 0; i < n; ++i)
			scanf("%d", s + i);
			
		hsort(s, 0, n - 1);

		for (i = 0; i < n; ++i)
			printf("%d%s", s[i], i == n - 1 ? "\n" : " ");
	}
	
	return 0;
}

