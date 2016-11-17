/*
 * 1698
 */
#include <stdio.h>

#define N 100000

#include "common.h"
#include "segtree.h"

void forget(struct seg_node *n)
{
	n->history = 0;
}

void remember(struct seg_node *n, seg_data_t d)
{
	n->history = d;
}

void set(struct seg_node *n, seg_data_t d)
{
	n->data = d * (n->end - n->begin + 1);
}

void summary(struct seg_node *n, seg_data_t *res)
{
	*res += n->data;
}

void update(struct seg_node *n)
{
	n->data = n->left->data + n->right->data;
}

int main(void)
{
	int i, nops, ncases, cas = 0;
	int b, e, n;
	seg_data_t v;
	struct seg_tree *tree;

	freopen("Inputs/1698", "r", stdin);

	tree = seg_tree_alloc(4 * N);
	tree->forget = forget;
	tree->remember = remember;
	tree->alter = set;
	tree->reduce = summary;
	tree->update = update;

	scanf("%d", &ncases);
	while (--ncases >= 0) {
		scanf("%d%d", &n, &nops);
		seg_build(1, n, tree);
		seg_alter(1, n, 1, tree);
		forn(i, nops) {
			scanf("%d%d%d", &b, &e, &v);
			seg_alter(b, e, v, tree);
		}
		printf("Case %d: The total value of the hook is %d.\n", ++cas, tree->root->data);
	}

	return 0;
}
