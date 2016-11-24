/**
 * 1166 敌兵部阵
 */

#include <stdio.h>

#include "common.h"
#include "segtree.h"

void forget(struct seg_node *n)
{
	n->history = 0;
}

void remember(struct seg_node *n, seg_data_t data)
{
	n->history += data;
}

void update(struct seg_node *n)
{
	n->data = n->left->data + n->right->data;
}

void add(struct seg_node *n, seg_data_t data)
{
	n->data += data;
}	

void reduce(struct seg_node *n, seg_data_t *result)
{
	*result += n->data;
}

int main(void)
{
	int cas = 0;
	int b, e, ncaps;
	seg_data_t x, qres;
	int ncases, i;
	char cmd[32];
	struct seg_tree *tree;

	freopen("Inputs/1166", "r", stdin);

	tree = seg_tree_alloc(200000);
	tree->forget = forget;
	tree->remember = remember;
	tree->alter = add;
	tree->reduce = reduce;
	tree->update = update;

	scanf("%d", &ncases);
	while (--ncases >= 0) {
		printf("Case %d:\n", ++cas);
		scanf("%d", &ncaps);

		seg_build(1, ncaps, tree);
		seg_print(tree);
		forre(i, 1, ncaps) {
			scanf("%d", &x);
			seg_alter(i, i, x, tree);
		}

		while (scanf("%s", cmd) == 1) {
			if (*cmd == 'E')
				break;
			switch (*cmd) {
				case 'Q':
					scanf("%d%d", &b, &e);
					qres = 0;
					seg_query(b, e, tree, &qres);
					printf("%d\n", qres);
					break;
				case 'A':
					scanf("%d%d", &b, &x);
					seg_alter(b, b, x, tree);
					break;
				case 'S':
					scanf("%d%d", &b, &x);
					seg_alter(b, b, x * (-1), tree);
					break;
				default:
					assert(0);
			}
			seg_print(tree);
		}
	}
	seg_tree_free(tree);

	return 0;
}



