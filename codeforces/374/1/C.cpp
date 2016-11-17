/**
 * 374
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

const int large = 1000000001;

struct append {
	int id, cost;

	append(int i, int c) {
		id = i;
		cost = c;
	}
};

int n, m, t;
vector<vector<append>> graph(N);
vector<int> top;
int s[N][N+1], x[N][N+1];

void topsort(void) {
	int i, inds[N];
	stack<int> stk;
	vector<append>::iterator nxt;
	vector<append> *nxts;

	forn(i, n) inds[i] = 0;
	forn(i, n) {
		nxts = &graph[i];
		for (nxt = nxts->begin(); nxt != nxts->end(); ++nxt) ++inds[nxt->id];
	}
	forn(i, n) if (inds[i] == 0) stk.push(i);

	while (!stk.empty()) {
		i = stk.top();
		stk.pop();
		top.push_back(i);
		nxts = &graph[i];
		for (nxt = nxts->begin(); nxt != nxts->end(); ++nxt) {
			--inds[nxt->id];
			if (inds[nxt->id] == 0) stk.push(nxt->id);
		}
	}

	//printf("topsort\n");
	//forn(i, n) printf("%2d%s", top[i]+1, tailer(i, n-1));
}
		
bool read(void) {
	int i, j, left, rght, cost;

	forn(i, n) fore(j, m) x[i][j] = -1;
	forn(i, N) graph[i].clear();
	top.clear();

	if (scanf("%d%d%d", &n, &m, &t) != 3)
		return false;
	forn(i, m) {
		scanf("%d%d%d", &left, &rght, &cost);
		--left, --rght;
		graph[left].push_back(append(rght, cost));
	}

	return true;
}

void print_path(int i, int j) {
	if (i == -1 || j <= 0) {
		printf("\n");
		return;
	}
	printf("%d ", i+1);
	print_path(x[i][j], j-1);
}

inline void print_s(int i, int j) {
	//printf("s[%2d][%2d]: %2d\n", i+1, j, s[i][j]);
}

void solve(void) {
	int i, j, u, c;
	vector<append>::iterator v;
	vector<append> *nxts;

	s[n-1][1] = 0;
	forre(j, 2, m) s[n-1][j] = large;
	forre(j, 1, m) print_s(n-1, j);

	topsort();

	rfor(i, n) {
		u = top[i];
		if (u == n-1) continue;
		s[u][1] = large;
		print_s(u, 1);
		forre(j, 2, m) {
			s[u][j] = large;
			nxts = &graph[u];
			for (v = nxts->begin(); v != nxts->end(); ++v) {
				c = v->cost + s[v->id][j-1];
				if (s[u][j] > c) s[u][j] = c, x[u][j] = v->id;
			}
			print_s(u, j);
		}
	}

	//printf("---cut---\n");
	rforre(j, m, 1) {
		if (n == 1000 && m == 999) forn(i, n) printf("%d%s", s[0][j], tailer(i, n-1));
		print_s(0, j);
		if (s[0][j] <= t) break;
	}
	printf("%d\n", j);
	if (j >= 0) print_path(0, j);
}

int main(void) {
	freopen("Inputs/374", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) solve();

	return 0;
}
