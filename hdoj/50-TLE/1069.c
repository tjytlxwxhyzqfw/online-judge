/*
 * 1069 Monkey and Banana
 *
 * Looks like DFS.
 * Is There a DP solution ?
 *
 * Comment:
 * 0.must be tle
 * 1.DFS is slow as expected.
 * 2.How to use dp ?
 *
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "Source/debug.c"

int maxheight;
int x[180], y[180], z[180];
int boxes;
char color[180];

void dfs(const int idx, const int height)
{
	int i, newheight;

	//printis(0, 0, "dfs: idx: %d, height:%d\n", idx, height);

	for (i = 0; i < boxes; ++i) {
		/* redundent */
		if (color[i] == 1)
			continue;
		if (!(x[i] <x[idx] && y[i] < y[idx]))
			continue;
		newheight = height + z[i];
		if (newheight > maxheight)
			maxheight = newheight;
		color[i] = 1;
		dfs(i, newheight);
		color[i] = 0;
	}

	return;
}

int main(void)
{
	int i;
	int xi, yi, zi;

	freopen("Inputs/1069", "r", stdin);
	setbuf(stdout, NULL);

	while (scanf("%d", &boxes) == 1) {
		if (!boxes)
			break;
		boxes *= 6;

		for (i = 0; i < boxes; i += 6) {
			scanf("%d%d%d", &xi, &yi, &zi);
			//printf("xi: %d, yi: %d, zi: %d\n", xi, yi, zi);
			/* ... */
			x[i] = x[i + 1] = y[i + 2] = z[i + 3] = y[i + 4] = z[i + 5] = xi;
			y[i] = z[i + 1] = x[i + 2] = x[i + 3] = z[i + 4] = y[i + 5] = yi;
			z[i] = y[i + 1] = z[i + 2] = y[i + 3] = x[i + 4] = x[i + 5] = zi;
		}	

		memset(color, 0, 180);
		maxheight = 0;
		for (i = 0; i < boxes; ++i) {
			color[i] = 1;
			dfs(i, z[i]);
			color[i] = 0;
		}

		printis(0, 0, "maxheight = %d\n", maxheight);
	}

	return 0;
}
			
		
