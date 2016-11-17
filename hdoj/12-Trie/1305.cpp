/**
 * 1305
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"
#include "prftree.h"

struct ptree: public prftree {
	bool prefix(const char *s) {
		int i, idx;
		node *curr = root;

		for (i = 0; s[i]; ++i) {
			idx = s[i] - bas;
			if (!curr->has_next(idx))
				break;
			curr = &curr->nexts[idx];
			if (curr->cnt > 0)
				return true;
		}

		return s[i] == 0;
	}
};

using namespace std;

#define L 1000

void solve(void)
{
	int sid = 0;
	bool prefix = false;

	char str[L];
	ptree trie;

	trie.init('0', 2);
	while (scanf("%s", str) == 1) {
		if (str[0] == '9') {
			//trie.print();
			printf("Set %d is%simmediately decodable\n", ++sid, prefix ? " not " : " ");
			prefix = false;
			trie.clear();
		}
		if (prefix)
			continue;
		prefix = trie.prefix(str);
		trie.add(str);
	}
}
		
	

int main(void)
{
	freopen("Inputs/1305", "r", stdin);
	setbuf(stdout, NULL);

	solve();

	return 0;
}
