/**
 * 1003
 */

#include <algorithm>
#include <array>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"

#define rfor(_i, _n) for((_i)=(_n)-1; ((_i) >= 0); (_i) -= 1)

using namespace std;

#define N 100000

array<int, N> dp, start, endi;
array<int, N> nums;

int main(void)
{
	int c, ncs;
	int i, begin, n;
	int maxv;

	freopen("Inputs/1003", "r", stdin);
	scanf("%d", &ncs);
	forn(c, ncs) {
		scanf("%d", &n);
		forn(i, n) scanf("%d", &nums[i]);

		dp[n-1] = nums[n-1];
		endi[n-1] = n-1;
		rfor(i, n-1) {
			dp[i] = nums[i] + max(0, dp[i+1]);
			endi[i] = (dp[i] == nums[i] ? i : endi[i+1]);
		}

		maxv = INT_MIN;
		forn(i, n) {
			if (maxv < dp[i]) {
				maxv = dp[i];
				begin = i;
			}
		}

		printf("Case %d:\n", c+1);
		printf("%d %d %d\n", maxv, begin+1, endi[begin]+1);
		if (c+1 != ncs) printf("\n");
	}

	return 0;
}

int main2(void)
{
	int c, ncs;
	int i, n;
	int maxv, maxi;

	freopen("Inputs/1003", "r", stdin);
	scanf("%d", &ncs);
	forn(c, ncs) {
		scanf("%d", &n);
		forn(i, n) scanf("%d", &nums[i]);

		dp[0] = nums[0];
		start[0] = 0;
		forr(i, 1, n) {
			dp[i] = max(nums[i], dp[i-1] + nums[i]);
			if (dp[i] == dp[i-1] + nums[i]) start[i] = start[i-1];
			else start[i] = i;
		}

		maxv = INT_MIN, maxi = -1;
		forn(i, n) {
			if (dp[i] > maxv) {
				maxv = dp[i];
				maxi = i;
			}
		}

		printf("Case %d:\n", c+1);
		printf("%d %d %d\n", maxv, start[maxi]+1, maxi+1);
	}
	return 0;
}
