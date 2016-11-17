/*
 * 721C
 *
 * Maximal accessed nodes from 1 to n with cost less than T
 */
#include <array>
#include <cassert>
#include <cstdio>
#include <stack>
#include <vector>

#include "common.h"
#define foreach(_i, _c) for((_i) = (_c).begin(); (_i) != (_c).end(); ++(_i))

using namespace std;

#define T 1000000001
#define N 5000

struct succer {
	int id;
	int cost;

	succer(int idx, int cst) {
		id = idx;
		cost = cst;
	}
};

int n, m, t;
array<array<int, N+1>, N> dp;
array<array<int, N+1>, N> path;
array<vector<succer>, N> nexts;
array<int, N> top;

void topsort(void)
{
	array<int, N> indg;
	stack<int> stk;
	veciter(succer) it;
	int topi, i, next;

	//FIXME: GREATE BUG!!!!!!!!!!!!!
	//forn(i, n) foreach(it, nexts[i]) indg[it->id] = 0;

	forn(i, n) indg[i] = 0;
	forn(i, n) foreach(it, nexts[i]) indg[it->id] += 1;
	forn(i, n) if (indg[i] == 0) stk.push(i);

	topi = 0;
	while (not stk.empty()) {
		i = stk.top();
		stk.pop();
		top[topi++] = i;
		foreach(it, nexts[i]) {
			next = it->id;
			--indg[next];
			if (indg[next] == 0)
				stk.push(next);
		}
	}

	assert(topi == n);
	//forn(i, n) printf("%d%s", top[i], tailer(i, n-1));
}

int main(void)
{
	int i, j, k, ti, to;
	int left, right;
	int cost;
	veciter(succer) it;

	freopen("Inputs/721C", "r", stdin);

	/* read */

	scanf("%d%d%d", &n, &m, &t);
	forn(i, m) {
		scanf("%d%d%d", &left, &right, &cost);
		--left, --right;
		nexts[left].push_back(succer(right, cost));
	}

	/* top sort */

	topsort();

	/* do dp */

	rforre(ti, n-1, 0) {
		i = top[ti];
		dp[i][1] = (i == n-1 ? 0 : T);
		forre(j, 2, n) {
			dp[i][j] = T;
			to = -1;
			foreach(it, nexts[i]) {
				k = it->id;
				if (dp[k][j-1] < T) {
					cost = it->cost + dp[k][j-1];
					if (dp[i][j] > cost) {
						dp[i][j] = cost;
						to = k;
					}
				}
			}
			path[i][j] = to;
			//printf("dp[%2d][%2d]: %4d, to: %2d\n", i+1, j, dp[i][j], to+1);
		}
	}

	/* compute answer */

	i = 0;
	rforre(j, n, 1)
		if (dp[i][j] <= t) break;
	assert(j >= 1);
	printf("%d\n", j);

	/* print path */

	while (i != n-1) {
		printf("%d ", i+1);
		i = path[i][j];
		--j;
	}
	printf("%d\n", n);

	return 0;
}
