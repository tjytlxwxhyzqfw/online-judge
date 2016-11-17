/*
 * DanCiShu
 * We consider this problem as a huge-input problem
 */

#include <assert.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define RB_RED 0
#define RB_BLACK 1

#define offset_of(t,m) ((unsigned long)&(((t*)0)->m))
#define container_of(a,t,m) \
		((t*)((unsigned long)a-offset_of(t,m)))

struct rb_node;

struct rb_tree {
	struct rb_node *root;
};

struct rb_node {
	char  color;
	struct rb_node *parent, *left, *right;
};

struct rb_tree *rb_tree_new(void)
{
	struct rb_tree *tree;
	assert(tree = malloc(sizeof(struct rb_tree)));
	tree->root = NULL;
	return tree;
}

void rb_do_destroy(struct rb_node *node)
{
	if (!node)
		return;
	if (node->left)
		rb_do_destroy(node->left);
	if (node->right)
		rb_do_destroy(node->right);
	free(node);
}

void rb_destroy(struct rb_tree *tree)
{
	if (tree)
		rb_do_destroy(tree->root);
	free(tree);
}

void rb_node_init(struct rb_node *n) {
	n->parent = n->left = n->right = NULL;
	n->color = RB_RED;
}

void rb_left_rotation(struct rb_node *x, struct rb_tree *tree)
{
	struct rb_node *p, *y;

	if (!x->right)
		return;

	y = x->right;
	x->right = y->left;
	y->left = x;

	p = x->parent;
	x->parent = y;
	y->parent = p;
	if (x->right)
		x->right->parent = x;
	if (!p)
		tree->root = y;
	else if (x == p->left)
		p->left = y;
	else
		p->right = y;
}

void rb_right_rotation(struct rb_node *x, struct rb_tree *tree)
{
	struct rb_node *p, *y;

	if (!x->left)
		return;
	
	y = x->left;
	x->left = y->right;
	y->right = x;

	p = x->parent;
	x->parent = y;
	y->parent = p;
	if (x->left)
		x->left->parent = x;
	if (!p)
		tree->root = y;
	else if (x == p->left)
		p->left = y;
	else
		p->right = y;
}

void rb_insert_balance(struct rb_node *x, struct rb_tree *tree)
{
	struct rb_node *p, *u, *g;

	while (x->parent && x->parent->color == RB_RED) {
		p = x->parent;
		g = p->parent;
		if (p == g->left) {
			u = g->right;
			if (u && u->color == RB_RED) {
				p->color = u->color = RB_BLACK;
				g->color = RB_RED;
				x = g;
			} else if (x == p->right) {
				rb_left_rotation(p, tree);
				x = p;
			} else {
				rb_right_rotation(g, tree);
				g->color = RB_RED;
				p->color = RB_BLACK;
			}
		} else {
			u = g->left;
			if (u && u->color == RB_RED) {
				p->color = u->color = RB_BLACK;
				g->color = RB_RED;
				x = g;
			} else if (x == p->left) {
				rb_right_rotation(p, tree);
				x = p;
			} else {
				rb_left_rotation(g, tree);
				g->color = RB_RED;
				p->color = RB_BLACK;
			}
		}
	}

	if (tree->root)
		tree->root->color = RB_BLACK;
}

struct word {
	struct rb_node rb;
	char word[100];
};

struct word *word_new(char *str)
{
	struct word *w;
	assert(w = malloc(sizeof(struct word)));
	rb_node_init(&w->rb);
	strcpy(w->word, str);
	return w;
} 

int insert(char *str, struct rb_tree *tree)
{
	struct rb_node *x, *p;
	struct word *w;
	int left, delta;

	p = NULL;
	x = tree->root;
	while (x) {
		p = x;
		delta = strcmp(str, container_of(x, struct word, rb)
				->word);
		if (delta < 0) {
			x = x->left;
			left = 1;
		} else if (delta > 0) {
			x = x->right;
			left = 0;
		} else {
			return 0;
		}
	}

	w = word_new(str);
	if (!p) {
		tree->root = &w->rb;
	} else if (left) {
		p->left = &w->rb;
		w->rb.parent = p;
	} else {
		p->right = &w->rb;
		w->rb.parent = p;
	}

	rb_insert_balance(&w->rb, tree);
	
	return 1;
}

int main(void)
{

	int i, wc, ch, a;
	struct rb_tree *rbtree;
	char str[100];
	
	i = 0;
	wc = 0;
	rbtree = rb_tree_new();
	while ((ch = getchar()) != EOF) {
		if (ch == '#') {
			break;
		} else if (isspace(ch) && i > 0) {
			str[i] = 0;
			i = 0;
			a = insert(str, rbtree);
			//printf("%s:%d\n", str, a);
			wc += a;
		} else if (!isspace(ch)){
			str[i++] = ch;
		}
	
		if (ch == '\n') {
			printf("%d\n", wc);
			wc = 0;
			rb_destroy(rbtree);
			rbtree = rb_tree_new();
		}
				
	}
	
	return 0;
}
