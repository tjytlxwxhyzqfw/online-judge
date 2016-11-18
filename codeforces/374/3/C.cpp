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

#define N 5000
const int huge = 1000000001;

struct suc {
	int id, cost;

	suc(int i, int c) {
		id = i;
		cost = c;
	}
};

int n, m, t;
vector<suc> g[N];
int to[N][N+1], s[N][N+1];

bool read(void) {
	int i, left, rght, cost;
	if (scanf("%d%d%d", &n, &m, &t) != 3)
		return false;
	forn(i, m) {
		scanf("%d%d%d", &left, &rght, &cost);
		--left, --rght;
		g[left].push_back(suc(rght, cost));
	}
	return true;
}

int topord[N];

void topsort(void) {
	stack<int> topstk;
	int nin[N];
	int i, topi;
	vector<suc>::iterator it;

	forn(i, n) nin[i] = 0;
	forn(i, n) foriter(it, g[i]) ++nin[it->id];
	forn(i, n) if (nin[i] == 0) topstk.push(i);

	topi = 0;
	while (!topstk.empty()) {
		i = topstk.top();
		topstk.pop();
		topord[topi++] = i;

		foriter(it, g[i]) {
			--nin[it->id];
			if (nin[it->id] == 0)
				topstk.push(it->id);
		}
	}

	//printnint(topord, topi, "topord:");
}

void printpath(int i, int j) {
	if (i == -1) {
		printf("\n");
		return;
	}
	printf("%d ", i+1);
	printpath(to[i][j], j-1);
}

void prints(int i, int j) {
	printf("[%2d][%2d]: %d\n", i+1, j, s[i][j]);
}

void solve(void) {
	int left, rght, cost, use;
	int i, j;
	vector<suc>::iterator it;

	topsort();
	rfor(i, n) {
		left = topord[i];
		s[left][1] = (left == n-1 ? 0 : huge);
		//prints(left, 1);
		to[left][1] = -1;
		forre(j, 2, n) {
			s[left][j] = huge;
			to[left][j] = -1;
			foriter(it, g[left]) {
				rght = it->id;
				cost = it->cost;

				use = s[rght][j-1] + cost;
				if (use < s[left][j]) {
					s[left][j] = use;
					to[left][j] = rght;
				}
			}
			//prints(left, j);
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
