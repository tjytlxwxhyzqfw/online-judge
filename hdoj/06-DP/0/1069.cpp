/*
 * 1069 Monkey And Banana
 *
 * Stack up boxes to get maximul height
 */

#include <algorithm>
#include <array>
#include <cstdio>

#include "common.h"

using namespace std;

#define N 90 

void solve(int);

struct box {
	int a, b, c;

	void init(int x, int y, int z) {
		a = x;
		b = y;
		c = z;
	}

	void print(int i) {
		printf("%2d: (%2d, %2d, %2d)\n", i, a, b, c);
	}
};

int n, nboxs;
array<box, N> boxs;

array<int, N> dp;

bool read(void)
{
	int i, j;
	array<int, 3> d;

	if (scanf("%d", &n) != 1)
		return false;
	if (n == 0) return false;

	forn(i, n) {
		scanf("%d%d%d", &d[0], &d[1], &d[2]);
		sort(d.begin(), d.end());
		j = 3 * i;
		boxs[j].init(d[0], d[1], d[2]);
		boxs[j+1].init(d[0], d[2], d[1]);
		boxs[j+2].init(d[1], d[2], d[0]);
	}

	nboxs = 3 * n;

	return true;
}

int main(void)
{
	int caseid = 0;

	freopen("Inputs/1069", "r", stdin);

	while (read())
		solve(++caseid);
	return 0;
}

bool compare(const box &b1, const box &b2)
{
	if (b1.a == b2.a)
		return b1.b > b2.b;
	return b1.a > b2.a;
}

#define gt(x,y) ((boxs[(x)].a > boxs[(y)].a) and (boxs[(x)].b > boxs[(y)].b))
#define access(_a, _i, _n, _obv) (ir((_i), 0, (_n)) ? (_a)[(_i)] : (_obv)) 

void solve(int caseid)
{
	int i, j;
	int height, maxv;

	//printf("solve(): nboxs: %3d\n", nboxs);
	sort(boxs.begin(), boxs.begin()+nboxs, compare);
	//forn(i, nboxs) boxs[i].print(i);

	i = nboxs - 1;
	dp[i] = boxs[i].c;
	//printf("dp[%2d]: %2d\n", i, dp[i]);

	rforre(i, nboxs-2, 0) {
		maxv = 0;
		forr(j, i+1, nboxs) {
			//printf("i: %2d, j: %2d\n", i, j);
			if (!gt(i, j)) continue;
			height = dp[j];
			if (height > maxv) maxv = height;
		}
		dp[i] = maxv + boxs[i].c;
		//printf("dp[%2d]: %2d\n\n", i, dp[i]);
	}

	maxv = 0;
	forn(i, nboxs) maxv = max(maxv, dp[i]);

	printf("Case %d: maximum height = %d\n", caseid, maxv);
}
