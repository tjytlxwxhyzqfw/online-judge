/**
 * C
 * 从一个数列中不断删除元素,求最大连续子串和
 */

#include <array>
#include <cassert>
#include <cstdio>
#include <cstdlib>

#include "arraytree.h"
#include "common.h"

#include <iostream>

#define N 200000
#define SEGN (1UL << 19)

using namespace std;
using namespace arraytree;

int n;
array<long long, N> seg;
array<int, N> order;

array<long long, SEGN> lmax, rmax, maxv;
array<int, SEGN> nzrs, lv, rv;
const int off = SEGN / 2;


void printinfo(int i)
{
	printf("%2d: lmax: %2lld, rmax: %2lld, maxv: %2lld, nzrs: %2d\n", i, lmax[i], rmax[i], maxv[i], nzrs[i]);
}

void printtree(void)
{
	int i;

	forn(i, n+off) printinfo(i);
}

void update(int p)
{
	int left, right;

	left = lc(p), right = rc(p);

	lv[p] = lv[left];
	rv[p] = rv[right];

	lmax[p] = lmax[left];
	if (nzrs[left] == 0) lmax[p] += lmax[right];

	rmax[p] = rmax[right];
	if (nzrs[right] == 0) rmax[p] += rmax[left];

	nzrs[p] = nzrs[left] + nzrs[right];
	maxv[p] = max(maxv[left], maxv[right]);
	if (seg[rv[left]] != 0 and seg[lv[right]] != 0)
		maxv[p] = max(maxv[p], rmax[left] + lmax[right]);
}

void build(void)
{
	int i, j;

	forn(i, n) {
		j = i + off;
		lmax[j] = rmax[j] = maxv[j] = seg[i];
		nzrs[j] = 0;
		//printinfo(j);
	}

	j = prt(n+off-1);
	rforre(i, j, 1) {
		update(i);
		//printinfo(i);
	}

}

void remove(int i)
{
	i += off;

	lmax[i] = rmax[i] = maxv[i] = 0;
	nzrs[i] = 1;

	for (i = prt(i); valiad(i); i = prt(i))
		update(i);
}


using namespace std;

int main(void)
{
	int i;

	//freopen("Inputs/C", "r", stdin);

	scanf("%d", &n);
	forn(i, n) scanf("%lld", &seg[i]);
	forn(i, n) scanf("%d", &order[i]);

	build();

	forn(i, n) {
		remove(order[i]-1);
		printf("%lld\n", maxv[1]);
	}

	return 0;
}
