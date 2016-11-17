/*
 * 1671 Phone List
 */
#include <assert.h>
#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct digit {
	char value;
	char end;
	struct digit **next, **parent;
};

struct digit *digit_new(int value)
{
	struct digit *d;
	int i;

	assert(d = malloc(sizeof(struct digit)));
	d->value = value;
	d->end = 0;
	assert(d->next = malloc(sizeof(struct digit *) * 10));
	for (i = 0; i < 10; ++i)
		d->next[i] = NULL;
	d->parent = NULL;

	return d;
}

void digit_tree_distroy(struct digit *d)
{
	int i;
	if (!d)
		return;

	for (i = 0; i < 10; ++i)
		if (d->next[i])
			digit_tree_distroy(d->next[i]);
	free(d->next);
	free(d);
}

int main(void)
{
	struct digit *root = NULL;

	int ch, ncas, cas;
	struct digit *parent, *current;
	char cover, prefix, res;
	char number[11], *p;

	int i;

	freopen("Inputs/1671", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d", &ncas);
	while (ncas--) {
		scanf("%d%*c", &cas);
		res = 1;
		digit_tree_distroy(root);
		root = digit_new(-1);
		//for (i = 0; i < 10; ++i)
			//printf("%x\n", root->next[i]);
		while (cas--) {
			parent = root;
			current = NULL;
			prefix = 1;
			cover = 0;
			//printf("\nNumber Start\n");
			scanf("%s", number);
			//printf("number = %s\n", number);
			if (!res)
				continue;
			for (p = number; *p; ++p) {
				ch = (int)(*p);
				//printf("ch=%d\n", ch);
				//printf("-\ngetchar:%c\n", (char)ch);
				ch -= 48;
				
				current = parent->next[ch];
				//printf("current:%x\n", current);

				if (!current) {
					//printf("Not Prefix\n");
					current = parent->next[ch] = digit_new(ch);
					prefix = 0;
				} else if (current->end) {
					/* 覆盖了一个号码 */
					//printf("Covered Another Number\n");
					cover = 1;
					break;
				}

				parent = current;
			}
			res = res && !prefix && !cover;
			if (res)
				current->end = 1;
			//printf("Number End\n\n");
		}
		printf("%s\n", res ? "YES" : "NO");
	}

	return 0;
}
				
				
				
				
