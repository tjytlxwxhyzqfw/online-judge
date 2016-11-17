/*
 * Aho-Corasick Automathine
 *
 * need:
 * 	queue.c
 * 	trie.c
 */


#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

#include "clist.c"
#include "debug.c"
#include "trie.c"
#include "aho_corasick.c"

void trie_clean(struct trie_node *trie)
{
	int i;

	trie->failed = NULL;
	trie->ends = 0;

	//TODO: trie_for_each_child
	for (i = 0; i < TRIE_ASZ; ++i)
		if (trie->nexts[i])
			trie_clean(trie->nexts[i]);
}
	
#define N 300001

void print_trie(int deepth, const struct trie_node *x);
void print_path(const struct trie_node *x, const char *next);
void print_appends(const struct trie_node *x, const char *next);
void do_print_path(const struct trie_node *x);
char *string_new(const char *s);

int nmatches;

int onmatch(const struct trie_node *node)
{
	nmatches += node->ends;

	printis(3, 0, "match: ");
	//print_path(node, ": ");
	//print_appends(node, "\n");
	return 0;
}

char buffer[2 * N];
char *current = buffer;

int main(void)
{
	struct trie_node *trie;
	int cmd, last, ncases;

	freopen("Inputs/710F", "r", stdin);
	setbuf(stdout, NULL);

	trie = trie_node_new(0);

	last = -1;
	scanf("%d", &ncases);
	while (--ncases >= 0) {
		scanf("%d%s", &cmd, current);
		switch (cmd) {
			case 1:
				printis(1, 0, "insert: %s\n", current);
				trie_insert(current, trie);
				current += strlen(current) + 1;
				break;
			case 2:
				printis(1, 0, "remove: %s\n", current);
				trie_remove(current, trie);
				break;
			case 3:
				printis(1, 0, "query: %s\n", current);
				nmatches = 0;
				/* 这居然是关键性的代码,你敢信*/
				if (last != 3) {
					trie_clean(trie);
					aho_corasick_fill(trie);
				}
				//print_trie(0, trie);
				aho_corasick_simple(current, trie, onmatch);
				printf("%d\n", nmatches);
				break;
			default:
				assert(0);
		}
		last = cmd;
	}

	return 0;
}

/*
void do_print_path(const struct trie_node *x)
{
	if (x->parent == x || x->parent == NULL)
		return;
	do_print_path(x->parent);
	printis(0, 0, "%c", x->key);
}

void print_path(const struct trie_node *x, const char *next)
{
	do_print_path(x);
	printis(0, 0, "%s", next);
}

void print_appends(const struct trie_node *x, const char *next)
{
	int i;

	clist_for_each(x->ends)
		print_path(clist_data(x->ends, struct trie_node *), ", ");

	printis(0, 0, "%s", next);
}

void print_trie(int deepth, const struct trie_node *x)
{
	int i;

	printis(deepth, 0, "");

	print_path(x, "");
	printis(0, 0, "->");
	print_path(x->failed, ": ");

	print_appends(x, "\n");

	for (i = 0; i < TRIE_ASZ; ++i)
		if (x->nexts[i])
			print_trie(deepth + 1, x->nexts[i]);
}
*/
