/*
 * 1525 Euclid's Game
 *
 * rbtree is not necessary
 *
 * 2016-08-18 20:20: just submit
 */
#include <stdio.h>

#include "address.h"
//#include "debug.c"
#include "rbtree.c"

#define N 0
#define P 1
#define U 2

struct node {
	int lesser, greater;
	int result;
	struct rb_node rb;
};

#define cont(a) container_of(a,struct node,rb)

struct node *node_new(int left, int right)
{
	struct node *node;

	assert(node = malloc(sizeof(struct node)));
	node->lesser = (left < right ? left : right);
	node->greater = left + right - node->lesser;
	node->result = U;
	rb_node_init(&node->rb);

	return node;
}

int node_compare(const void *node, const void *rbnode)
{
	struct node *node1, *node2;

	node1 = (struct node *)node;
	node2 = cont(rbnode);
	if (node1->greater == node2->greater)
		return node1->lesser - node2->lesser;
	return node1->greater - node2->greater;
}

void insert(struct node *node, struct rb_tree *tree)
{
	struct rb_node *parent;
	int delta;

	rb_general_find(node, tree, node_compare, &parent, &delta);

	if (!parent)
		tree->root = &node->rb;
	else if (delta < 0)
		parent->left = &node->rb;
	else if (delta > 0)
		parent->right = &node->rb;
	else
		return;
	node->rb.parent = parent;
}

struct node *find(struct node *node, struct rb_tree *tree)
{
	struct rb_node *parent;
	int delta;

	rb_general_find(node, tree, node_compare, &parent, &delta);

	if (parent && !delta)
		return cont(parent);
	return NULL;
}

struct rb_tree *tree;

int dfs(int deepth, int left, int right)
{
	int i, result, ret;
	struct node *node, *cached;

	//printis(0, 0, "(%3d, %3d)\n", left, right);

	if (left < 0 || right < 0)
		return U;

	if (!left || !right)
		return P;

	node = node_new(left, right);
	cached = find(node, tree);
	if (cached) {
		//printis(deepth, 1, "hit: %s\n", cached->result == N ? "N" : "P");
		result = cached->result;
		free(node);
		return result;
	}

	/* we have to calculate */
	//printis(deepth, 1, "miss\n");
	result = dfs(deepth + 1, node->greater % node->lesser, node->lesser);
	if (result == P) {
		ret = N;
	} else if (node->greater / node->lesser == 1) {
		/* 
 		 * (remainder, lesser) = N,
 		 * so (lesser, lesser + remainder) = P
 		 */
		ret = P;
	} else {
		/*
		 * (remainder, lesser + remainder) = P,
		 * so (lesser, n*lesser + remainder) = N,
		 * n >= 2;
		 */
		ret = N;
	}

	node->result = ret;
	insert(node, tree);
	//printis(deepth, 1, "%s\n", ret == N ? "N" : "P");
	return ret;

}
		
int main(void)
{
	int result;
	int left0, right0;

	tree = rb_tree_new();

	while (scanf("%d%d", &left0, &right0) == 2) {
		if (!left0 && !right0)
			break;
		result = dfs(0, left0, right0);
		printf("%s wins\n", result == N ? "Stan" : "Ollie");
	}

	return 0;
}
