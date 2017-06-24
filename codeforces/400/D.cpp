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

bool read(void) {
	scanf("%d%d", &n,&m);
	forn(i, n) scanf("%d",&a[i]);
	return true;
}

void solve(void) {
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
