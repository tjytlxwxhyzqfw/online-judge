#include <stdio.h>
#include <stdlib.h>

#include "common.h"
#include "trie.h"

int main(void)
{
	int nops, i;
	char op, str[20];
	struct trie_node *trie, *node;
	int cnt, len;

	//freopen("Inputs/713A", "r", stdin);
	//setbuf(stdout, NULL);

	trie = trie_node_new(0);

	scanf("%d%*c", &nops);
	while (--nops >= 0) {
		scanf("%c%s%*c", &op, str);
		len = strlen(str);
		forn(i, len) str[i] = (str[i] & 1UL ? '1' : '0');
		fore(i, (len-1)/2) SWP(str[i], str[len-1-i]);
		forr(i, len, 20) str[i] = '0';
		str[19] = 0;
		//printf("op: %c, str: %s\n", op, str);
		switch (op) {
			case '+':
				trie_insert(str, trie);	
				break;
			case '-':
				trie_remove(str, trie);
				break;
			case '?':
				//FIXME: Great!
				//node = find(str, node, &cnt);
				node = trie_find(str, trie);
				cnt = (node ? node->end : 0);
				printf("%d\n", cnt);
				break;
			default:
				assert(0);
		}
	}

	return 0;
}
