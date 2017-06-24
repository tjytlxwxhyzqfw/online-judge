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

#define N 200001

int n;
vector<vector<int>> tree(N);
int colors[N], out[N], colors_par[N];
int color_max;

bool read(void) {
	int i, left, rght;

	scanf("%d", &n);
	forn(i, n-1) {
		scanf("%d%d", &left, &rght);
		--left, --rght;
		tree[left].push_back(rght);
		tree[rght].push_back(left);
	}

	return true;
}

inline void fresh(const int color) {
	if (color > color_max)
		color_max = color;
}

int color_nxt(const int bgn, const int color1, const int color2) {
	int color = bgn;
	while (color == color1 || color == color2)
		++color;
	return color;
}

void bfs(void) {
	stack<int> stk;
	int i, nchls, child, cur;
	int color_par, color_cur, color_chl;


	stk.push(0);
	colors[0] = 0;
	colors_par[0] = -1;

	while (!stk.empty()) {
		cur = stk.top();
		stk.pop();

		out[cur] = 1;

		color_cur = colors[cur];
		color_par = colors_par[cur];
		fresh(color_cur);

		//printf("cur: %3d, color_cur: %3d, color_par: %3d\n", cur, color_cur, color_par);

		color_chl = color_nxt(0, color_cur, color_par);

		nchls = tree[cur].size();
		forn(i, nchls) {
			child = tree[cur][i];
			if (out[child] == 1)
				continue;

			// color this child
			colors[child] = color_chl;
			colors_par[child] = color_cur;
			fresh(color_chl);

			color_chl = color_nxt(++color_chl, color_cur, color_par);
			stk.push(child);
		}
	}
}

void solve(void) {
	int i;

	forn(i, N) out[i] = 0;
	forn(i, N) colors[i] = -1;

	bfs();

	printf("%d\n", color_max+1);
	forn(i, n) printf("%d ", colors[i]+1);
	printf("\n");
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
