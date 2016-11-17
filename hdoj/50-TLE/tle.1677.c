/*
 * 1677 Nested Dolls
 */

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

#include "Source/heap.c"

struct doll {
	int y, x;
};

struct doll *doll_new(int y, int x)
{
	struct doll *d;

	assert(d = malloc(sizeof(struct doll)));
	d->y = y;
	d->x = x;

	return d;
}

void doll_destroy(struct doll *d)
{
	if (d)
		free(d);
}

int cmp(void *x, void *y)
{
	return ((struct doll *)x)->y - ((struct doll *)y)->y;
}

int main(void)
{

	int ncas, n, y, x, i, dolls, start;
	struct doll *d, *current;

	struct heap *h = heap_new(20000);
	h->cmp = cmp;

	freopen("Inputs/1677", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d", &ncas);
	while (ncas--) {
		scanf("%d", &n);
		for (i = 0; i < n; ++i) {
			scanf("%d%d", &y, &x);
			d = doll_new(y, x);
			heap_insert(d, h);
			//h->cell[++h->last] = d;
		}

		/* heap building */
		//printf("h->last = %d\n", h->last);
		/*
		for (i = h->last / 2; i > 0; --i)
			heap_pdown(i, h);
		*/

		/* heap sort */
		while ((d = heap_del(h)) != NULL) {
			//printf("%d ", d->y);
			h->cell[h->last + 1] = d;
		}
		//printf("\n");
		//sleep(10000);

		dolls = 0;
		for (start = 1; start <= n; ++start) {
			current = h->cell[start];
			if (!current)
				continue;

			h->cell[start] = NULL;
			++dolls;
			printf("(%d,%d)", current->y, current->x);
			
			for (i = start + 1; i <= n; ++i) {
				d = h->cell[i];
				if (d && d->y != current->y && d->x < current->x) {
					h->cell[i] = NULL;
					doll_destroy(current);
					current = d;
					printf("(%d,%d)", current->y, current->x);
				}
			}

			printf("\n");
			//sleep(1);
		}

		printf("%d\n", dolls);
		////sleep(1);
	}

	return 0;
}
