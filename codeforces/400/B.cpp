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
#include "number.h"

using namespace std;

int n;

bool read(void) {
	scanf("%d", &n);
	return true;
}

void solve(void) {
	int i, k, p;

	k = (n >= 3 ? 2 : 1);
	printf("%d\n", k);
	forn(i, n) {
		p = i + 2;
		if (isprime(p)) {
			printf("%d ", 1);
		} else {
			printf("%d ", 2);
		}
	}
	printf("\n");
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
