/**
 * A
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
	int i;
	forre(i, 1, 1000) {
		if (!isprime(n*i+1))
			break;
	}

	printf("%d\n", i);
}

int main(void) {
	#ifdef DEBUG
	//freopen("Inputs/A", "r", stdin);
	setbuf(stdout, NULL);
	#endif

	read();
	solve();

	return 0;
}
