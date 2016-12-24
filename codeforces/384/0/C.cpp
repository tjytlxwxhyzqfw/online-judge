/**
 * C
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

int n;

bool read(void) {
	scanf("%d", &n);
	return true;
}

void solve(void) {
	if (n == 1)
		printf("-1\n");
	else
		printf("%d %d %d\n", n, n+1, n*(n+1));
}

int main(void) {
	#ifdef DEBUG
	//freopen("Inputs/C", "r", stdin);
	setbuf(stdout, NULL);
	#endif

	read();
	solve();

	return 0;
}
