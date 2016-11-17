/**
 * 1251
 */

#include <algorithm>
#include <cassert>
#include <cctype>
#include <cstdio>
#include <cstring>
#include <vector>

#include "common.h"

struct prftree {
	static const int bas = 97;
	static const int len = 26;

	struct node {
		int cnt;
		node *nexts;

		node(): cnt(-1), nexts(NULL) {}
		inline void init(int cnt) { this->cnt = cnt; }
		inline bool has_next(int idx) const { return nexts != NULL && nexts[idx].cnt > 0; }
		inline void alloc_nexts(void) { nexts = new node[len]; }
		inline void print(int cidx) const { printf("%c(%2d)\n", cidx+bas, cnt); }
	};

	node *root;

	void init(void) { root = new node(); }

	int spread(const char *s, node **parent, bool mod) {
		int i, idx;
		node *curr = root;

		for (i = 0; s[i] != 0; ++i) {
			idx = s[i] - bas;
			if (!curr->has_next(idx))
				break;
			if (mod)
				++curr->cnt;
			curr = &curr->nexts[idx];
		}
		if (mod)
			++curr->cnt;

		*parent = curr;
		return i;
	}

	void add(const char *s) {
		node *curr;
		int idx;

		int i = spread(s, &curr, true);
		for (; s[i] != 0; ++i) {
			idx = s[i] - bas;
			if (curr->nexts == NULL)
				curr->alloc_nexts();
			curr->nexts[idx].init(1);
			curr = &curr->nexts[idx];
		}
	}

	inline node* find(const char *s) {
		node *curr;
		int i = spread(s, &curr, false);
		return s[i] == 0 ? curr : NULL;
	}

	void print(node *rt, int idx, int deepth) const {
		int i;
		forn(i, deepth) printf("\t");
		rt->print(idx);
		forn(i, len) if (rt->has_next(i)) print(&rt->nexts[i], i, deepth+1);
	}

};

using namespace std;

#define LEN 12

void solve(void)
{
	char str[LEN];
	prftree trie;
	prftree::node *node;

	trie.init();
	while (fgets(str, LEN, stdin)) {
		if (isspace(str[0]))
			break;
		str[strlen(str)-1] = 0;
		//printf("str: %s\n", str);
		trie.add(str);
		//trie.print(trie.root, 'a', 0);
	}

	while (scanf("%s", str) == 1) {
		node = trie.find(str);
		printf("%d\n", node == NULL ? 0 : node->cnt);
	}
}

int main(void)
{
	freopen("Inputs/1251", "r", stdin);
	setbuf(stdout, NULL);

	solve();

	return 0;
}
