/**
 * D
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

long long n;
long long table[] = {0, 1, 1, 1, 2, 1, 2};

bool read(void) {
	scanf("%lld", &n);
	return true;
}

bool isprime(long long cand) {
	long long i = 1;

	for (i = 2; i * i  <= cand; ++i) {
		//printf("%lld %% %lld: %lld\n", cand, i, cand % i);
		if (cand % i == 0)
			return false;
	}
	return true;
}

void solve(void) {
	if (1 <= n && n <= 6) {
		printf("%lld\n", table[n]);
	} else if (odd(n)) {
		if (isprime(n)) {
			printf("1\n");
		} else if (isprime(n-2)) {
			printf("2\n");
		} else {
			printf("3\n");
		}
	} else {
		printf("2\n");
	}
}

int main(void) {
	#ifdef DEBUG
	//freopen("Inputs/D", "r", stdin);
	setbuf(stdout, NULL);
	#endif

	read();
	solve();

	return 0;
}
