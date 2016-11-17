/*
 * 1032
 * bfs ?
 */

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

#define offset(t,m) ((unsigned long)&(((t*)0)->m))
#define contof(a,t,m) ((t*)((unsigned long)a - offset(t,m)))

#define RB_BLACK 1
#define RB_RED 0

struct rb_node;

struct rb_tree {
	struct rb_node *root;
};

struct rb_node {
	struct rb_node *left, *right, *parent;
	char color;
};

struct rb_tree *rb_tree_new(void)
{
	struct rb_tree *t;

	assert(t = malloc(sizeof(struct rb_tree)));
	t->root = NULL;
	
	return t;
}

void rb_node_init(struct rb_node *n)
{
	n->left = n->right = n->parent = NULL;
	n->color = RB_RED;
}

void rb_rotate_right(struct rb_node *x, struct rb_tree *t)
{
	struct rb_node *y, *p;

	if (!x || !x->left)
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
		t->root = y;
	else if (x == p->left)
		p->left = y;
	else
		p->right = y;
}

void rb_rotate_left(struct rb_node *x, struct rb_tree *t)
{
	struct rb_node *y, *p;

	if (!x || !x->right)
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
		t->root = y;
	else if (x == p->left)
		p->left = y;
	else
		p->right = y;
}

void rb_insert_balance(struct rb_node *x, struct rb_tree *t)
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
				rb_rotate_left(p, t);
				x = p;
			} else {
				rb_rotate_right(g, t);
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
				rb_rotate_right(p, t);
				x = p;
			} else {
				rb_rotate_left(g, t);
				g->color = RB_RED;
				p->color = RB_BLACK;
			}
		}
	}

	if (t->root)
		t->root->color = RB_BLACK;
}

struct node {
	struct rb_node rb;
	int num, count;
};

struct node *node_new(int i, int count)
{
	struct node *n;

	assert(n = malloc(sizeof(struct node)));
	n->num = i;
	n->count = count;
	rb_node_init(&n->rb);

	return n;
}

void rb_insert(struct node *n, struct rb_tree *t)
{
	struct rb_node *x, *p;
	struct node *no;
	char left;

	p = NULL;
	x = t->root;
	while (x) {
		p = x;
		no = contof(x, struct node, rb);
		if (n->num < no->num) {
			x = x->left;
			left = 1;
		} else if (n->num > no->num) {
			x = x->right;
			left = 0;
		} else {
			free(n);
			return;
		}
	}

	if (!p)
		t->root = &n->rb;
	else if (left)
		p->left = &n->rb;
	else
		p->right = &n->rb;
	n->rb.parent = p;

	n->rb.color = RB_RED;
	rb_insert_balance(&n->rb, t);
}

struct node *rb_find(int n, struct rb_tree *t)
{
	struct rb_node *x;
	struct node *no;

	x = t->root;
	while (x) {
		no = contof(x, struct node, rb);
		if (n < no->num)
			x = x->left;
		else if (n > no->num)
			x = x->right;
		else
			return no;
	}
	
	return NULL;
}

void rb_destroy(struct rb_node *x)
{
	struct node *n;
	
	n = contof(x, struct node, rb);
	if (x) {
		rb_destroy(x->left);
		rb_destroy(x->right);
		free(n);
	}
}

void rb_tree_destroy(struct rb_tree *t)
{
	if (t) {
		rb_destroy(t->root);
		free(contof(t, struct node, rb));
	}
}
	
int main(void)
{
	unsigned int begin, end, i, n;
	int max, count;
	struct node *p;
	struct rb_tree *rbtree;
	int ncal, nfound;

	freopen("Inputs/1032", "r", stdin);

	while (scanf("%d%d", &begin, &end) != EOF) {
		rbtree = rb_tree_new();
		rb_insert(node_new(1,1), rbtree);
		ncal = nfound = max = 0;
		for (i = 1; i <= end; ++i) {
			n = i;
			count = 1;
			while (n != 1) {
				//printf("n = %d count=%d\n", n, count);
				if (n < 0)
					sleep(10);
				n = ((n & 1) ? (3 * n + 1) : (n >> 1));
				p = rb_find(n, rbtree);
				if (p) {
					count += p->count;
					nfound += p->count;
					break;
				} else {
					++count;
					ncal += 1;
				}
			}
			rb_insert(node_new(i, count), rbtree);
			//printf("new:%d count=%d\n", i, count);
			if (max < count && begin <= i && i <= end)
				max = count;
		}
		printf("result: %d %d %d\n", begin,  end, max);
		printf("ncal=%d, nfound=%d\n", ncal, nfound);
		rb_tree_destroy(rbtree);
	}
	
	return 0;
}
