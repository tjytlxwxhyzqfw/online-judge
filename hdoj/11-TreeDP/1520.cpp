/**
 * 1520
 *
 * 在一个树上选择若干节点,选父亲就不能选儿子,求选择方式,使权值最大.
 */
#include <array>
#include <cassert>
#include <cstdio>
#include <iostream>
#include <vector>

#include "common.h"

#define vsz(t) vector<t>::size_type

using namespace std;

#define N 6000

struct node {
	int idx;
	int w, p;
	vector<int> cs;
	int use, skp, max;

	node():idx(-1),  p(-2) {}

	void init(int i) {
		idx = i; 
		p = -2;
		w = use = skp = max = 0;
		cs.clear();
	}

	bool leaf(void) { return cs.empty(); };
	bool root(void) { return p == -2; };

	void print(void) {
		printf("%2d: w: %4d, p: %4d, max: %4d, use: %4d, skp: %4d\n", idx+1, w, p, max, use, skp);
	}
};

int n;
array<node, N> nd;

void dfs(int rid)
{
	node &rhs = nd[rid];
	vsz(int) i;

	//nd[rid].print();

	if (rhs.leaf()) {
		rhs.use = rhs.w;
		rhs.skp = 0;
		rhs.max = max(rhs.use, rhs.skp);
		return;
	}

	forn(i, rhs.cs.size()) dfs(rhs.cs[i]);

	rhs.use = rhs.w;
	rhs.skp = 0;
	forn(i, rhs.cs.size()) rhs.use += nd[rhs.cs[i]].skp;
	forn(i, rhs.cs.size()) rhs.skp += nd[rhs.cs[i]].max;
	rhs.max = max(rhs.use, rhs.skp);

	//nd[rid].print();
}


int solve(void)
{
	int prt, chl, i, rid;
	
	if (scanf("%d", &n) != 1) return -1;

	forn(i, n) nd[i].init(i);
	forn(i, n) cin >> nd[i].w;
	while(true) {
		cin >> chl >> prt;
		if (prt == 0 && chl == 0) break;
		--chl, --prt;
		nd[prt].cs.push_back(chl);
		nd[chl].p = prt;
	}

	rid = -1;
	forn(i, n) {
		if (nd[i].root()) {
			rid = i;
			break;
		}
	}
	assert(ir(rid, 0, n));

	dfs(rid);

	cout << nd[rid].max << endl;

	return 0;
}

int main(void)
{
	freopen("Inputs/1520", "r", stdin);

	while (solve() == 0);	

	return 0;
}
