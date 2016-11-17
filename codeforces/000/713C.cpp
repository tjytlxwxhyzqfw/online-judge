/**
 * 713C
 */

#include <algorithm>
#include <array>
#include <iostream>

#include "common.h"

using namespace std;

#define N 3000

int n;
array<long long, N> nums;
array<long long, N> vals;
array<array<long long, N>, N> dp; 

#define gte(i, j) (nums[(i)] >= vals[(j)])
#define dif(i, j) (abv((nums[i]) - vals[(j)]))
#define suf(i, j) (((i) == (j)) ? "\n" : " ")

void dpfill(int i, int j)
{
	long long next, res;

	next = dif(i, j) + dp[i+1][j];
	//FIXME: 要算以下才好!!!
	res = (j == n-1 ? LLONG_MAX : dp[i][j+1]);

	dp[i][j] = min(res, next);
	//cout << "dp(" << i+1 << ", " << j+1 << "): " << dp[i][j] << endl;
}

int main(void)
{
	int i, j;
	long long ans;

	cin >> n;
	forn(i, n) {
		cin >> vals[i];
		vals[i] -= i;
		nums[i] = vals[i];
	}
	sort(vals.begin(), vals.begin()+n);

	//forn(i, n) cout << nums[i] << suf(i, n-1);
	//forn(i, n) cout << vals[i] << suf(i, n-1);

	i = n - 1;
	forn(j, n) dp[i][j] = (gte(i, j) ? 0 : dif(i, j));
	//forn(j, n) cout << "dplast: (" << i+1 << ", " << j+1 << "): " << dp[i][j] << endl;

	for(i = n-2; i >= 0; --i)
		for(j = n-1; j >= 0; --j)
			dpfill(i, j);

	ans = LLONG_MAX;
	forn(i, n)
		if (dp[0][i] < ans) ans = dp[0][i];
	cout << ans << endl;

	return 0;
}
