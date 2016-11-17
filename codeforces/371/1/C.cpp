/**
 * C
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <cstring>
#include <vector>

#include "common.h"
#include "prftree.h"

using namespace std;

#define N 20

int nqs;

bool read(void) {
	if (scanf("%d", &nqs) != 1)
		return false;
	return true;
}

void pad(char s[N]) {
	int i, j;

	for(i = strlen(s), j = N-1; i >= 0; --i, --j)
		s[j] = s[i];
	fore(i, j) s[i] = '0';
	rfor(i, strlen(s)) s[i] = (s[i]&1UL)+'0';

	assert(strlen(s) == N-1);
}

void solve(void) {
	prftree trie;
	int i;
	char cmd[2], str[N];
	prftree::node *node;

	trie.init('0', 10);
	forn(i, nqs) {
		scanf("%s%s", cmd, str);
		pad(str);
		switch (cmd[0]) {
		case '+':
			trie.add(str);
			break;
		case '-':
			--trie.find(str)->cnt;
			break;
		case '?':
			node = trie.find(str);
			printf("%d\n", node == NULL ? 0 : node->cnt);
			break;
		default:
			assert(false);
		}
	}
}

int main(void) {
	freopen("Inputs/C", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) solve();
	return 0;
}

int test(void) {
	prftree trie;
	char str[N];
	prftree::node *node;

	trie.init('0', 10);
	while (scanf("%s", str) == 1) {
		trie.add(str);
		trie.print(trie.root, 0);
	}

	printf("finding mode:\n");
	while (scanf("%s", str) == 1) {
		node = trie.find(str);
		printf("%d\n", node == NULL ? 0 : node->cnt);
	}

	return 0;
}
