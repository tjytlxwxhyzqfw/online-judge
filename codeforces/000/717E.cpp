/*
 * 717E
 *
 * 树上每个节点或黑或粉,访问一个节点必改变其颜色,
 * 给定这个树,求一个访问路径,要求将树涂黑.
 *
 * 如何用数组存储树的边?
 */

#include <array>
#include <cassert>
#include <iostream>
#include <vector>

#include "common.h"

#define boolv(v) ((v) ? (1) : (0))
#define viter(t) vector<t>::iterator

#include <iomanip>
#include <fstream>
#include <string>

using namespace std;

#define N 200000
#define PN 10000000

struct path {
	int idx;
	array<int, PN> p;
	path(): idx(0) { p.fill(-1); }
	
	void add(int i, int j=-1) {
		p[idx++] = i;
		if (j != -1) p[idx++] = j;
	}

	void erase(int len=1) { idx -= len; }

	void print(void) {
		int i;
		forn(i, idx) ++p[i];
		printa(p, i, idx);
	}
};

struct node {
	int id;
	int p, c;
	vector<int> childs;

	node(): id(-1), p(-1), c(0) {}
	void init(int id) { this->id = id; }
	bool pink(void) { return c == -1; }
	bool black(void) { return c == 1; }
	bool leaf(void) { return childs.empty(); }
	bool pless(void) { return p == -1; }
	bool root(void) { return p == -2; }

	void cc(void) {
		if (c == -1) c = 1;
		else if (c == 1) c = -1;
		else assert(0);
	}

	void print(string tag="") {
		cout << tag << "id: " << id+1 << ", color: " << c << ", parent: " << p+1 << endl;
	}
};

int nnodes;
array<node, N> nodes;
array<vector<int>, N> edges;
path route;

int dfs(int i)
{
	//cout << "dfs: ";
	//nodes[i].print();

	int access = 0, a;
	node &n = nodes[i];

	if (n.leaf() && n.black()) return 0;

	if (n.leaf() && n.pink()) {
		route.add(i);
		return 1;
	}

	++access;
	route.add(i);
	for (viter(int) j = n.childs.begin(); j < n.childs.end(); ++j) {
		a = dfs(*j);
		if (a) route.add(i);
		access += a;
	}

	if (access == 1) {
		if (n.black()) {
			route.erase();
			return 0;
		} else return 1;	
	}

	if (odd(access)) n.cc();
	if (n.pink()) {
		route.add(n.p, i);
		return 2;
	} else return 1;
}

void fillpath(void)
{
	node &root = nodes[0];
	int access = 0, a;

	route.add(0);
	for(viter(int) j = root.childs.begin(); j < root.childs.end(); ++j) {
		a = dfs(*j);
		if (a) route.add(0);
		access += a;
	}

	if (odd(access)) root.cc();
	if (root.pink()) {
		int child = root.childs[0];
		route.add(child, 0);
		route.add(child);
	}
}

void mktree(int i)
{
	vector<int> &es = edges[i];
	for (vector<int>::iterator c = es.begin(); c < es.end(); ++c) {
		if (nodes[*c].pless()) {
			nodes[*c].p = i;
			nodes[i].childs.push_back(*c);
			mktree(*c);
		}
	}
}

int main(void)
{
	int i, left, right;
	node &root = nodes[0];

	//ifstream fin("Inputs/717E");

	cin >> nnodes;
	forn(i, nnodes) {
		nodes[i].init(i);
		cin >> nodes[i].c;
	}
	forn(i, nnodes-1) {
		cin >> left >> right;
		--left, --right;
		edges[left].push_back(right);
		edges[right].push_back(left);
	}

	root.p = -2;
	mktree(0);
	fillpath();
	route.print();

	return 0;
}
