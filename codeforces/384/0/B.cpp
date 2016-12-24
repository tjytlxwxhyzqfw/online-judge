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

long long n, k;

bool read(void) {
	scanf("%lld%lld", &n, &k);
	return true;
}

long long do_solve(long long n, long long k) {

	//printf("\tsolving: %3lld, %16lld\n", n, k);

	if (n == 1) {
		assert(k == 1);
		return 1;
	}

	long long idx = (((long long)1)<<(n-1));

	if (k == idx)
		return n;

	return do_solve(n-1, k > idx ? k-idx : k);
}

void solve(void) {
	long long ans = do_solve(n, k);
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
