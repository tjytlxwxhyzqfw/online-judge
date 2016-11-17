/*
 * 717C Potions Homework
 */

#include <algorithm>
#include <iostream>
#include <array>

#include "common.h"

using namespace std;

#define N 100000
#define M 10007

int n;
array<int, N> nums;

int main(void)
{
	int i, ans = 0;

	cin >> n;
	forn(i, n) cin >> nums[i];
	sort(nums.begin(), nums.begin()+n);
	forn(i, n) nums[i] %= M;
	forn(i, n) ans = (ans+(nums[i]*nums[n-1-i])%M) % M;
	cout << ans << endl;

	return 0;
}


