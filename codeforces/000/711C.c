/*
 * 711C
 */

#include <assert.h>
#include <limits.h>
#include <stdio.h>
#include <stdlib.h>

#include "debug.c"

struct tree {
	int tid;
	int color;
	long long costs[101][101];
	long paints[101];
	struct tree *next;
};

int ntrees, ncolors, ntcolors;
struct tree trees[100];

#define for_each_color(i) for((i) = 1; (i) <= ncolors; (i) += 1)
#define for_each_nocc(i) for ((i) = 1; (i) <= ntcolors; (i) += 1)

#define getcost(t,n,l) ((n) > ntcolors ? LLONG_MAX : (t)->costs[(n)][(l)])
#define correct(x) (*(x) = (*(x) < 0 ? LLONG_MAX : *(x)))

#define inrange(x) ((x) == LLONG_MAX ? (-1) : (x))
//void print_tree(int tid, int indent);

void fill_last(const int nocc, const int lc)
{
	struct tree *last = &trees[ntrees - 1];
	int color, colormin;

	if (last->color == 0) {
		if (ntcolors == nocc) {
			last->costs[nocc][lc] = last->paints[lc];
		} else if (ntcolors - nocc == 1) {
			last->costs[nocc][lc] = LLONG_MAX;
			for_each_color(color) {
				if (color == lc)
					continue;
				if (last->paints[color] < last->costs[nocc][lc])
					last->costs[nocc][lc] = last->paints[color];
			}
		} else {
			last->costs[nocc][lc] = LLONG_MAX;
		}
	} else {
		if (ntcolors == nocc) {
			if (last->color == lc)
				last->costs[nocc][lc] = 0;
			else
				last->costs[nocc][lc] = LLONG_MAX;
		} else if (ntcolors - nocc == 1) {
			if (last->color == lc)
				last->costs[nocc][lc] = LLONG_MAX;
			else
				last->costs[nocc][lc] = 0;
		} else {
			last->costs[nocc][lc] = LLONG_MAX;
		}
	}
}

void dp_prepare(void)
{
	int i, j;

	for_each_nocc(i)
		for_each_color(j)
			fill_last(i, j);
}

void fill(struct tree *tree, const int nocc, const int lc)
{
	struct tree *next = tree->next;
	long long cost1, cost2, cost;
	int color, colormin;

	if (tree->color == 0) {
		printis(1, 0, "fill: (%3d, %3d): uncolored\n", nocc, lc);
		cost1 = tree->paints[lc] + next->costs[nocc][lc];
		correct(&cost1);
		printis(1, 0, "fill: cost1: %6lld, color: %3d, -> (%3d, %3d)\n", inrange(cost1), lc, nocc, lc);
		cost2 = LLONG_MAX;
		if (tree->tid != 0) {
			for_each_color(color) {
				if (color == lc)
					continue;
				cost = tree->paints[color] + getcost(next, nocc + 1, color);
				correct(&cost);
				if (cost <= cost2) {
					cost2 = cost;
					colormin = color;
				}
			}
		}
		printis(1, 0, "fill: cost2: %6lld, color: %3d, -> (%3d, %3d)\n", inrange(cost2), colormin, nocc + 1, colormin);
		tree->costs[nocc][lc] = (cost1 < cost2 ? cost1 : cost2);
	} else {
		printis(0, 0, "fill: (%3d, %3d): colored\n", nocc, lc);
		if (lc == tree->color) {
			printis(1, 0, "fill: same color, --> (%3d, %3d)\n", nocc ,lc);
			tree->costs[nocc][lc] = next->costs[nocc][lc];
		} else {
			printis(1, 0, "fill: different color, --> (%3d, %3d)\n", nocc + 1, tree->color);
			tree->costs[nocc][lc] = getcost(next, nocc + 1, tree->color);
		}
	}
	cost = tree->costs[nocc][lc];
	printis(1, 0, "fill: (%3d, %3d): %6lld\n", nocc, lc, inrange(cost));
}

void dp(void)
{
	int tid, nocc, lc;

	for (tid = ntrees - 2; tid >= 0; --tid) {
		printis(0, 0, "\ndp: %3d\n", tid);
		for_each_nocc(nocc) {
			for_each_color(lc) {
				fill(&trees[tid], nocc, lc);
			}
		}
		//print_tree(tid, 0);
	}
}


int main(void)
{
	int i, j;
	long long cost;

	freopen("Inputs/711C", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d%d%d", &ntrees, &ncolors, &ntcolors);
	for (i = 0; i < ntrees; ++i) {
		trees[i].tid = i;
		scanf("%d", &trees[i].color);
	}
	for (i = 0; i < ntrees; ++i)
		for_each_color(j) 
			scanf("%lld", trees[i].paints + j);

	for (i = 0; i < ntrees - 1; ++i)
		trees[i].next = &trees[i + 1];
	trees[ntrees - 1].next = NULL;

	dp_prepare();
	//print_tree(ntrees - 1, 0);
	dp();
	cost = LLONG_MAX;
	if (trees[0].color == 0) {
		for_each_color(i) {
			if (trees[0].costs[1][i] < cost) {
				cost = trees[0].costs[1][i];
			}
		}
		printf("%lld\n", inrange(cost));
	} else {
		printf("%lld\n", inrange(trees[0].costs[1][trees[0].color]));
	}
		

	return 0;
}


void print_tree(int tid, int ident)
{
	struct tree *tree = &trees[tid];
	int nocc, color;
	long long cost;

	printis(ident, 0, "tid: %3d\n", tid);
	printis(ident, 0, "color: %3d\n", tree->color);
	printis(ident, 0, "costs\n");
	printis(ident, 0, "   ");
	for_each_color(color)
		printf("%6d", color);
	puts("");
	for_each_nocc(nocc) {
		printis(ident, 0, "%3d", nocc);
		for_each_color(color) {
			cost = getcost(tree, nocc, color);
			printf("%6lld", inrange(cost));
		}
		printf("\n");
	}
	printis(ident, 0, "paints:\n");
	printis(ident, 0, "");
	for_each_color(color)
		printf("%6d", color);
	printf("\n");
	printis(ident, 0, "");
	for_each_color(color)
		printf("%6lld", inrange(tree->paints[color]));
	printf("\n");
}
