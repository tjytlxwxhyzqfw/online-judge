/**
 * D
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <cstring>
#include <map>
#include <stack>
#include <string>
#include <vector>

#include "common.h"

using namespace std;

#define N 200002

char t[N], p[N];
int n, m, a[N];

bool read(void) {
	int i;

	scanf("%s", t);
	scanf("%s", p);

	n = strlen(t);
	m = strlen(p);

	forn(i, n) scanf("%d", &a[i]);
	forn(i, n) a[i] -= 1;

	return true;
}

int find(const int bgn, const char tar, const int thresh) {
	int i;
	for (i = bgn; i >= 0; --i) {
		printf("\t\ti: %d, a[i]: %d, t[a[i]]: %c, thresh: %d\n", i, a[i], t[a[i]], thresh);
		if (t[a[i]] == tar and a[i] < thresh)
			break;
	}
	return i;
}

void solve(void) {
	int i;
	char target;
	int thresh, idx;

	printf("n: %d, m: %d\n", n, m);

	idx = n - 1;
	thresh = INT_MAX;
	for (i = m-1; i >= 0; --i) {
		target = p[i];
		idx = find(idx, target, thresh);
		thresh = a[idx];
		printf("\ttarget: %c, idx: %d\n", target, idx);
		--idx;
	}

	printf("%d\n", idx+1);
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
