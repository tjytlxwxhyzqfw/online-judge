/**
 * 1069
 */

#include <algorithm>
#include <array>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"

using namespace std;

#define N 30
#define NS 90

struct box {
	int a, b, c;

	void init(int x, int y, int z) {
		a = x, b = y, c = z;
	}

	void print(void) {
		printf("(%2d, %2d): %2d\n", a, b, c);
	}
};

int nboxs, n;
array<box, NS> boxs;
array<int, NS> dp;

bool read(void)
{
	int i, j;
	array<int, 3> d;

	if (scanf("%d", &n) != 1)
 		return false;
	if (n == 0) return false;
	nboxs = 3 * n;

	forn(i, n) {
		scanf("%d%d%d", &d[0], &d[1], &d[2]);
		sort(d.begin(), d.end());

		j = 3 * i;
		boxs[j].init(d[0], d[1], d[2]);
		boxs[j+1].init(d[0], d[2], d[1]);
		boxs[j+2].init(d[1], d[2], d[0]);
	}

	//forn(i, nboxs) boxs[i].print();

	return true;
}

inline bool lt(int i, int j)
{
	return (boxs[i].a < boxs[j].a) and (boxs[i].b < boxs[j].b);
}

bool compare(const box &x, const box &y)
{
	return y.a < x.a;
}

void solve(int caseid)
{
	int i, j;
	int maxv;

	sort(boxs.begin(), boxs.begin()+nboxs, compare);

	//forn(i, nboxs) boxs[i].print();

	rforre(i, nboxs-1, 0) {
		dp[i] = 0;
		forr(j, i+1, nboxs) {
			if (!lt(j, i)) continue;
			//printf("lt: %2d < %2d\n", j, i);
			dp[i] = max(dp[i], dp[j]);
		}
		dp[i] += boxs[i].c;
		//printf("dp[%2d]: %2d\n", i, dp[i]);
	}

	maxv = 0;
	forn(i, nboxs-1) maxv = max(dp[i], maxv);

	printf("Case %d: maximum height = %d\n", caseid, maxv);
}

void solve2(int cid)
{
	int i, j;
	int maxv;

	sort(boxs.begin(), boxs.begin()+nboxs, compare);

	dp[0] = boxs[0].c;
	forr(i, 1, nboxs) {
		dp[i] = 0;
		forn(j, i) if (lt(i, j)) dp[i] = max(dp[i], dp[j]);
		dp[i] += boxs[i].c;
	}

	maxv = 0;
	forn(i, nboxs) if (dp[i] > maxv) maxv = dp[i];

	printf("Case %d: maximum height = %d\n", cid, maxv);
}

int main(void)
{
	int caseid = 0;

	freopen("Inputs/1069", "r", stdin);
	setbuf(stdout, NULL);

	while (read())
		solve(++caseid);

	return 0;
}
