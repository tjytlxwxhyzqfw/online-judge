/**
 * C
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <cstring>
#include <map>
#include <stack>
#include <string>
#include <vector>

#include "common.h"
#include "prftree.h"

using namespace std;

#define N 20

int n;

bool read(void) {
	scanf("%d", &n);
	return true;
}

void correct(char *s) {
	int len = strlen(s);
	char *i, *j;

	//printf("correct: %20s ------> ", s);

	s[N] = 0;
	j = s + N - 1;

	for (i = s+len-1; i >= s; --i)
		*j-- = *i;
	for (; j >= s; --j) *j = '0';

	for (i = s; *i; ++i)
		*i = odd(*i) ? '1' : '0';

	//printf("%s\n", s);
}

void solve(void) {
	Prftree trie;
	Prftree::Node *node;
	char str[N+1], cmd[2];

	trie.build('0', 2);
	while (--n >= 0) {
		scanf("%s%s", cmd, str);
		correct(str);

		switch(cmd[0]) {
		case '+':
			trie.add(str);
			break;
		case '?':
			node = trie.find(str);
			printf("%d\n", node ? node->cnt : 0);
			break;
		case '-':
			node = trie.find(str);
			--node->cnt;
			break;
		default:
			assert(false);
		}
	}
}

int main(void) {
	#ifdef DEBUG
	//freopen("Inputs/C", "r", stdin);
	setbuf(stdout, NULL);
	#endif

	read();
	solve();

	return 0;
}
