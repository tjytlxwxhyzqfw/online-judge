/*
 * 1556
 */

#include <stdio.h>

#include "common.h"
#include "segtree.h"

#define N 100000

void forget(struct seg_node *n)
{
	n->history = 0;
}

void remember(struct seg_node *n, seg_data_t d)
{
	n->history += d;
}

void add(struct seg_node *n, seg_data_t d)
{
	n->data += d;
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
	int i, b, e, n, qres;
	struct seg_tree *tree;

	freopen("Inputs/1556", "r", stdin);

	tree = seg_tree_alloc(4 * N);
	tree->update = update;
	tree->forget = forget;
	tree->remember = remember;
	tree->alter = add;
	tree->reduce = summary;

	while (scanf("%d", &n) == 1) {
		if (n == 0) break;
		seg_build(1, n, tree);
		forn(i, n) {
			scanf("%d%d", &b, &e);
			seg_alter(b, e, 1, tree);
			//seg_print(tree);
		}
		forre(i, 1, n) {
			qres = 0;
			seg_query(i, i, tree, &qres);
			printf("%d%s", qres, i == n ? "\n" : " ");
		}			
	}

	return 0;
}
		
		

