/*
 * 1075 What Are You Talking About ?
 */

#include <assert.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Prending There Is a red-black tree

#define offset(t,m) ((unsigned long)(&((t*)0)->m))
#define container_of(a,t,m) \
	((t*)((unsigned long)a-offset(t,m)))

#define RB_BLACK 0
#define RB_RED 1

struct rb_node;
struct rb_tree;

#define cont(a) container_of(a, struct word, rb)

struct rb_tree {
	struct rb_node *root;
};

struct rb_tree *rb_tree_init(void)
{
	struct rb_tree *t;

	t = malloc(sizeof(struct rb_tree));
	t->root = NULL;

	return t;
}

struct rb_node {
	struct rb_node *parent;
	struct rb_node *left, *right;
	int color;
};

void rb_node_init(struct rb_node *rb)
{
	rb->left = rb->right = rb->parent = NULL;
	rb->color = RB_RED;
}

struct word { 
	char word[11]; 
	char mars[11]; 
	struct rb_node rb; 
}; 

void rb_right_rotate(struct rb_node *x, struct rb_tree *tree)
{
	struct rb_node *y, *p;

	if (!x || !x->left)
		return;
	p = x->parent;
	y = x->left;
	x->left = y->right;
	y->right = x;
	
	y->parent = p;
	if (!p)
		tree->root = y;
	else if (x == p->left)
		p->left = y;
	else
		p->right = y;
	x->parent = y;
	if (x->left)
		x->left->parent = x;
}

void rb_left_rotate(struct rb_node *x, struct rb_tree *tree)
{
	struct rb_node *y, *p;

	if (!x || !x->right)
		return;
	p = x->parent;
	y = x->right;
	x->right = y->left;
	y->left = x;

	y->parent = p;
	if (!p)
		tree->root = y;
	else if (x == p->left)
		p->left = y;
	else
		p->right = y;
	x->parent = y;
	if (x->right)
		x->left->parent = x;
}

void rb_insert_rebalance(struct rb_node *x, struct rb_tree *tree)
{
	struct rb_node *p, *u, *g;

	while (x->parent && x->parent->color == RB_RED) {
		p = x->parent;
		g = x->parent->parent;	
		cont(x);
		cont(p);
		cont(g);
		//printf("x:%s p:%s g:%s\n", cont(x)->mars, cont(p)->mars,
		//		cont(g)->mars);
		//sleep(1);
		if (p == g->left) {
			u = g->right;
			if (!u || u->color == RB_BLACK) {
				if (x == p->right) {
					rb_left_rotate(p, tree);
					x = p;
				} else {
		//			printf("I'm here\n");
					rb_right_rotate(g, tree);
					p->color = RB_BLACK;
				}	g->color = RB_RED;
			} else {
				g->color = RB_RED;
				p->color = u->color = RB_BLACK;
				x = g; 
			}
		} else {
			u = g->left;
			if (!u || u->color == RB_BLACK) {
				if (x == p->left) {
					rb_right_rotate(p, tree);
					x = p;
				} else {
					rb_left_rotate(g, tree);
					p->color = RB_BLACK;
					g->color = RB_RED;
				}
			} else {
				g->color = RB_RED;
				p->color = u->color = RB_BLACK;
				x = g;
			}
		}
	}

	tree->root->color = RB_BLACK;
}

//struct word { 
//	char word[11]; 
//	char mars[11]; 
//	struct rb_node rb; 
//}; 

struct word *word_init(char *mars, char *word) 
{ 
	struct word *w;
 
	assert(w = malloc(sizeof(struct word)));
	strcpy(w->mars, mars);
	strcpy(w->word, word);
	rb_node_init(&w->rb);

	//printf("new word: (%s,%s) @%ld\n", w->mars, w->word, w);
	
	return w;
}

void rb_print(struct rb_node *root)
{
	if (!root)
		return;
	printf("%s [L]%s [R]%s\n",
		cont(root)->mars,
		root->left ? cont(root->left)->mars: "~",
		root->right ? cont(root->right)->mars: "~");
	rb_print(root->left);
	rb_print(root->right);
}

void rb_insert(struct word *w, struct rb_tree *tree)
{
	struct rb_node *p, *x;
	int delta, left;

	p = NULL;
	x = tree->root;
	while (x) {
		delta = strcmp(w->mars, cont(x)->mars);
		p = x;
		if (delta < 0) {
			x = x->left;
			left = 1;
		} else if (delta > 0) {
			x = x->right;
			left = 0;
		} else {
			return;
		}
	}

	w->rb.parent = p;
	if (p == NULL)
		tree->root = &w->rb;
	else if (left)
		p->left = &w->rb;
	else
		p->right = &w->rb;
	w->rb.color = RB_RED;
	//printf("insert: (%s,%s)\n", w->mars, w->word);
	rb_insert_rebalance(&w->rb, tree);
	//rb_print(tree->root);
}

struct word *rb_find(char *mars, struct rb_tree *tree)
{
	struct rb_node *x;
	int delta;

	x = tree->root;
	while (x) {
		delta = strcmp(mars, cont(x)->mars);
		if (delta < 0)
			x = x->left;
		else if (delta > 0)
			x = x->right;
		else
			return cont(x);
	}
	return NULL;
}

int main(void)
{
	int i;
	char ch, word[11], mars[11];
	char sentence[3001];
	struct word *w;
	struct rb_tree *tree;
	
	setbuf(stdout, NULL);
	freopen("Inputs/1075", "r", stdin);

	tree = rb_tree_init();
	scanf("%s", word);
	assert(!strcmp(word, "START"));
	while (1) {
		scanf("%s%s", word, mars);
		//printf("%s %s\n", mars, word);
		if (!strcmp(word, "END"))
			break;
		w = word_init(mars, word);
		rb_insert(w, tree);
	}

	//rb_print(tree->root);

	assert(!strcmp(mars, "START"));
	scanf("%*c");// \n
	while (scanf("%c", &ch) != EOF) {
		//printf("ch = [%c]\n", ch);
		if (isupper(ch)) {
			assert(ch == 'E');
			break;
		}
		if (!islower(ch)){
			mars[i] = 0;
			//printf("mars:%s\n", mars);
			w = rb_find(mars, tree);
			if (w)
				printf("%s", w->word);
			else
				printf("%s", mars);
			i = 0;
			printf("%c", ch);
		} else {
			mars[i++] = ch;
		}
	}

	return 0;
}
