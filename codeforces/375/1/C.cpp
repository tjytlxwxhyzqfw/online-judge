/**
 * C
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <map>
#include <stack>
#include <string>
#include <vector>

#include "common.h"
#include "helpers.h"

using namespace std;

#define N 2000

int n, m, a[N];
stack<int>::size_type avg;
map<int, stack<int> > shows;

bool read(void) {
	int i;

	shows.clear();
	if (scanf("%d%d", &n, &m) != 2)
		return false;
	avg = n / m;
	//printf("avg: %d\n", (int)avg);
	scanfnint(a, n);
	forn(i, n) shows[a[i]].push(i);

	return true;
}

struct issrc: public unary_function<map<int, stack<int> >::iterator, bool> {
	bool operator()(map<int, stack<int> >::iterator it) {
		return (it->first > m && it->second.size() > 0) || it->second.size() > avg;
	}
};

inline void move(stack<int> *s, stack<int> *t) {
	int show = t->top();
	//printf("move: show: %d\n", show);
	t->pop();
	s->push(show);
}

void solve(void) {
	skipper<map<int, stack<int> >::iterator, issrc> src;
	stack<int> *stk;
	map<int, stack<int> >::iterator it;
	int i, show, nmoves;

	src.init(shows.begin(), shows.end());
	nmoves = 0;
	forre(i, 1, m) {
		stk = &shows[i];
		//printf("i: %2d, stk->size(): %d\n", i, (int)stk->size());
		if (stk->size() >= avg) continue;
		while (stk->size() < avg) {
			if (src.cur->second.size() <= avg) src.nxt();
			//printf("src.cur->first: %d\n", src.cur->first);
			move(stk, &src.cur->second);
			++nmoves;
		}
	}

	
	for (it = shows.begin(); it != shows.end(); ++it) {
		stk = &it->second;
		while (!stk->empty()) {
			show = stk->top();
			stk->pop();
			a[show] = it->first;
		}
	}

	printf("%d %d\n", (int)avg, nmoves);
	printnint(a, n);
}

int main(void) {
	freopen("Inputs/C", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) solve();

	return 0;
}
