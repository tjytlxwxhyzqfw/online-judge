/*
 * 709B
 */

#include <algorithm>
#include <array>
#include <iostream>

#include "common.h"

using namespace std;

#define off (1000000)
#define N 100000
#define M 2000001

int n, a;
array<int, N> nums;
array<int, M> cnt;

#define nr ((cnt[nums[n-1]]))
#define nl ((cnt[nums[0]]))

#include <cstdio>

int compute(int start, int first, int second)
{
	return abs(first - start) + abs(first - second);
}

int main(void)
{
	array<int, 6> ans;
	int left, right;
	int i;

	freopen("Inputs/709B", "r", stdin);

	cin >> n >> a;
	forn(i, n) cin >> nums[i];

	a += off;
	forn(i, n) nums[i] += off;

	sort(nums.begin(), nums.begin()+n);
	cnt.fill(0);
	forn(i, n) ++cnt[nums[i]];

	if (n == 1) {
		cout << 0 << endl;
		return 0;
	}

	if (a <= nums[0]) {
		if (nr > 1) cout << nums[n-1] - a << endl;
		else cout << nums[n-2] - a << endl;
		return 0;
	}

	if (a >= nums[n-1]) {
		if (nl > 1) cout << a - nums[0] << endl;
		else cout << a - nums[1] << endl;
		return 0;
	}

	/* ig. left */
	right = nums[n-1];
	left = nums[1];
	ans[0] = compute(a, right, left);
	ans[1] = compute(a, left, right);
	if (nl > 1) ans[0] = ans[1] = INT_MAX;

	/* ig. right */
	right = nums[n-2];
	left = nums[0];
	ans[2] = compute(a, right, left);
	ans[3] = compute(a, left, right);
	if (nr > 1) ans[2] = ans[3] = INT_MAX;

	/* entire */
	right = nums[n-1];
	left = nums[0];
	ans[4] = compute(a, right, left);
	ans[5] = compute(a, left, right);

	sort(ans.begin(), ans.end());

	cout << ans[0] << endl;

	return 0;
}
