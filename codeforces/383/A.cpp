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

#define N 10

using namespace std;

int n;

bool read(void) {
	scanf("%d", &n);
	return true;
}

void solve(void) {
	printf("%d\n", (int)(moduler<N>::pow(1378, n)));
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
