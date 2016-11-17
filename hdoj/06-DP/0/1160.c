/*
 * 1160 FatMouse's Speed
 * 
 * Russian Doll Problem
 */

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

//include "Source/debug.c"
#include "Source/heap.c"
#include "Source/wheels.c"


struct mouse {
	int id;
	int weight;
	int speed;
	struct mouse *next;
};

struct mouse *mouse_new(int id, int weight, int speed)
{
	static struct mouse mouses[1000];
	static struct mouse *next = mouses, *ret;

	ret = next++;

	ret->id = id;
	ret->weight = weight;
	ret->speed = speed;
	ret->next = NULL;


	return ret;
}

void *printmouse(int i, const void *x)
{
	const struct mouse *mouse = *((const struct mouse **)x);

	printf("mouse(%2d): id: %2d, weight: %4d, speed: %4d\n",
			i, mouse->id, mouse->weight, mouse->speed);

	return NULL;
}

int mousecmp(const void *x, const void *y) {
	return ((struct mouse *)y)->weight - ((struct mouse *)x)->weight;
}

int s[1000];

int main(void)
{
	int nmouses = 0;

	int i, j;
	int maxlen;
	int weight, speed;
	struct mouse *mouse, **mouses;
	struct heap *h;

	freopen("Inputs/1160", "r", stdin);
	setbuf(stdout, NULL);
	
	h = heap_new(1000, mousecmp);

	while (scanf("%d%d", &weight, &speed) != EOF) {
		mouse = mouse_new(++nmouses, weight, speed);
		//printis(1, 0, "mouse: id: %d, weight: %d, speed: %d\n", nmouses, weight, speed);
		heap_insert(mouse, h);
	}

	/* heap sort */
	while ((mouse = heap_del(h)) != NULL)
		h->cell[h->last + 1] = mouse;

	/* debug function foreach */
	foreach(h->cell + 1, nmouses, sizeof(void *), printmouse, NULL, 0);

	mouses = (struct mouse **)(h->cell + 1);
	for (i = nmouses - 1; i >= 0; --i) {
		maxlen = 0;
		for (j = i + 1; j < nmouses; ++j) {
			if (mouses[j]->weight <= mouses[i]->weight)
				continue;
			if (mouses[j]->speed >= mouses[i]->speed)
				continue;
			if (maxlen < s[j]) {
				maxlen = s[j];
				mouses[i]->next = mouses[j];
			}
		}
		s[i] = maxlen + 1;
		//printis(0, 0, "mouse: maxlen: %d, id: %d, next: %d\n",
				//s[i], mouses[i]->id, mouses[i]->next);
	}

	maxlen = 0;
	for (i = 0; i < nmouses; ++i) {
		if (maxlen < s[i]) {
			maxlen = s[i];
			mouse = mouses[i];
		}
	}

	printf("%d\n", maxlen);
	while (mouse) {
		printf("%d\n", mouse->id);
		mouse = mouse->next;
	}

	return 0;
}
