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

using namespace std;

struct suc {
	int id;
	int cost;

	suc(const int i, const int c) {
		init(i, c);
	}

	void init(const int i, const int c) {
		id = i;
		cost = c;
	}
};

#define T 1000000000
#define N 5000

int n, m, t;
int p[N][N+1];
int s[N][N+1];

vector<suc> g[N];
vector<int> topa;

#define printd printf

bool read(void) {
	int i;
	int l, r, c;

	if (scanf("%d%d%d", &n, &m, &t) != 3)
		return false;

	forn(i, m) {
		scanf("%d%d%d", &l, &r, &c);
		--l, --r;
		g[l].push_back(suc(r, c));
	}

	return true;
}

void initpath(void) {
	int i, j;
	forn(i, N) forn(j, N+1) p[i][j] = -1;
}

void printpath(int i, int j) {
	//printd("printpath: (%2d, %2d)\n", i, j);
	if (i == -1) {
		printf("\n");
		return;
	}
	assert(j >= 1);
	printf("%d ", i+1);
	printpath(p[i][j], j-1);
}

void topsort(void) {
	int nin[N], i, left;
	stack<int> stk;
	vector<suc>::iterator it;

	forn(i, n) nin[i] = 0;
	forn(i, n) foriter(it, g[i]) ++nin[it->id];
	forn(i, n) if (nin[i] == 0) stk.push(i);

	while (!stk.empty()) {
		left = stk.top();
		stk.pop();
		topa.push_back(left);
		foriter(it, g[left]) if (--nin[it->id] == 0) stk.push(it->id);
	}

	assert((int)topa.size() == n);
	//printd("topsort:\n");
	//forn(i, n) printd("%2d%s", topa[i]+1, tailer(i, n-1));
}

inline void prints(int i, int j) {
	printf("dp: (%3d, %3d): %d\n", i+1, j, s[i][j]);
}

void solve(void) {
	const int thresh = T + 1;
	int left, rght, to;
	int cost, via;
	vector<suc>::iterator it;
	int i, j;

	topsort();

	initpath();
	rfor(i, n) {
		left = topa[i];

		s[left][1] = (left == n-1 ? 0 : thresh);
		//prints(left, 1);

		forre(j, 2, n) {
			s[left][j] = thresh;
			to = -1;
			foriter(it, g[left]) {
				rght = it->id, cost = it->cost;
				via = cost + s[rght][j-1];
				if (via < s[left][j]) {
					s[left][j] = via;
					to = rght;
				}
			}
			//prints(left, j);
			p[left][j] = to;
		}
	}

	rforre(j, n, 1) if (s[0][j] <= t) break;
	assert(j >= 1);
	printf("%d\n", j);
	printpath(0, j);
}

void clear(void) {
	int i;
	forn(i, N) g[i].clear();
	topa.clear();
	initpath();
}

int main(void) {
	freopen("Inputs/C", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) {
		solve();
		clear();
	}

	return 0;
}
