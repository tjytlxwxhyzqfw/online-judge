/**
 * B
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <map>
#include <stack>
#include <string>
#include <vector>

#include "common.h"

using namespace std;

long long n_, l_, r_;

bool read(void) {
	scanf("%lld%lld%lld", &n_, &l_, &r_);
	--l_, --r_;
	return true;
}

long long length(const long long x) {
	long long power = 1LL;
	long long i = 0;

	/* power = 2^i */
	while (power <= x) {
		power <<= 1;
		++i;
	}

	return power-1;
}

long long nones(const long long root, const long long left, const long long rght) {
	if (left > rght)
		return 0;
	if (root == 0)
		return 0;
	if (root == 1)
		return 1;

	long long len, sublen;
	long long a, b, c, d, e;
	long long sum_left, sum_midl, sum_rght;

	//printf("nones: %5lld: [%5lld, %5lld]\n", root, left, rght);
	

	len = length(root);
	sublen = (len-1)/2;

	a = 0, b = sublen-1;
	c = sublen;
	d = sublen+1, e = 2*sublen;

	sum_left = nones(root/2, max(left, a), min(rght, b));
	sum_midl = nones(root%2, max(left, c)-sublen, min(rght, c)-sublen);
	sum_rght = nones(root/2, max(left, d)-sublen-1, min(rght, e)-sublen-1);

	return sum_left + sum_midl + sum_rght;
}

void solve(void) {
	long long ans;

	ans = nones(n_, l_, r_);
	printf("%lld\n", ans);
}

int main(void) {
	#ifdef DEBUG
	//freopen("Inputs/B", "r", stdin);
	setbuf(stdout, NULL);
	#endif

	read();
	solve();

	return 0;
}
