/*
 * 2196
 *
 * 找出一棵树上,每个点的最远族人与该点的距离
 */
#include <array>
#include <cassert>
#include <cstdio>
#include <string>
#include <vector>

#include "common.h"
#define foreach(i, c) for((i) = (c).begin(); (i) != (c).end(); ++(i))

using namespace std;

#define N 10000

struct node {
	int idx;
	int p;
	int h, opt, optc;
	vector<int> cs;
	vector<int> optec;
	array<node, N> *repo;

	node() { init(-1, NULL); }

	void init(int idx, array<node, N> *repo) {
		this->idx = idx;
		this->repo = repo;
		h = INT_MIN;
		p = -2;
		opt = optc = INT_MIN;
		cs.clear();
		optec.clear();
	}

	int except(int cid) {
		veciter(int) it;
		vecsize(int) idx;
		it = lower_bound(cs.begin(), cs.end(), cid);
		assert(*it == cid);
		idx = it - cs.begin();
		return optec[idx];
	}

	bool leaf(void) { return cs.empty(); }
	bool root(void) { return p == -2; }
	node &nth_chl(int ci) { return (*repo)[cs[ci]]; }
	node &parent(void) { return (*repo)[p]; }

	void print(string tag="") {
		veciter(int) i;
		printf("%s: %3d: p: %2d, h: %4d, (%4d, %4d)\n", tag.c_str(), idx+1, p+1, h, opt, optc);
		foreach(i, optec) printf("%d%s", *i, tailer(i, optec.end()-1));
		
	}
};

int n;
array<node, N> nd;


bool read(void)
{
	int i;
	int prt, hdl;

	if (scanf("%d", &n) != 1)
		return false;

	nd[0].init(0, &nd);
	forr(i, 1, n) {
		scanf("%d%d", &prt, &hdl);
		--prt;
		nd[i].init(i, &nd);
		nd[i].p = prt;
		nd[i].h = hdl;
		nd[prt].cs.push_back(i);
		nd[prt].optec.push_back(0);
		//nd[i].print();
	}

	return true;
}

void dfs1(int rid)
{
	node &root = nd[rid];
	node chl;
	vecsize(int) i;
	veciter(int) cid;
	int maxd, submax, d;

	//root.print();

	if (root.leaf()) {
		//printf("leaf: %d\n", root.idx+1);
		root.optc = 0;
		return;
	}

	foreach(cid, root.cs) dfs1(*cid);

	/* cal optc */
	maxd = submax = 0;
	forn(i, root.cs.size()) {
		chl = root.nth_chl(i); 
		d = chl.h + chl.optc;
		if (d >= maxd) {
			submax = maxd;
			maxd = d;
		} else if (d >= submax) {
			submax = d;
		}
	}
	root.optc = maxd;

	//printf("\tmaxd: %d, submax: %d\n", maxd, submax);

	/* cal optec */
	forn(i, root.cs.size()) {
		chl = root.nth_chl(i);
		d = chl.h + chl.optc;
		if (d == maxd) root.optec[i] = submax;
		else root.optec[i] = maxd;
	}

	//root.print("return");
}

void dfs2(int rid)
{
	node &root = nd[rid];
	node chl;
	int thrprt;
	vecsize(int) i;
	veciter(int) cid;

	if (root.root()) {
		root.opt = root.optc;
		/* root.optec has been correct */
	} else {
		thrprt = root.parent().except(root.idx) + root.h;
		root.opt = max(root.optc, thrprt);
		forn(i, root.cs.size()) {
			chl = root.nth_chl(i);
			root.optec[i] = max(root.optec[i], thrprt);
		}
	}
	foreach(cid, root.cs) dfs2(*cid);
}

int solve(void)
{
	int i, rid;

	forn(rid, n)
		if (nd[rid].root()) break;
	assert(ir(0, rid, n));

	dfs1(rid);
	dfs2(rid);

	forn(i, n) printf("%d\n", nd[i].opt);

	return 0;
}
int main(void)
{
	freopen("Inputs/2196", "r", stdin);
	while (read()) solve();
	return 0;
}
