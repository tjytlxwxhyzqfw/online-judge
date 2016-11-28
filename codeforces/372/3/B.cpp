/**
 * B
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

#define N 50000

int n;
char s[N+1];

bool read(void) {
	if (scanf("%s", s) != 1)
		return false;
	n = strlen(s);
	//printf("n : %d\n", n);
	return true;
}

void solve(void) {
	//one drives another
	int i, j, k, l;
	int a[128];

	//forn(i, n) printf("%c: %d\n", s[i], i);

	forn(i, 128) a[i] = -1;

	j = 0;
	forn(i, n) {
		if (i-j == 26) break;
		if (s[i] == '?') continue;

		if (a[(int)s[i]] >= 0) {
			k = a[(int)s[i]];
			assert(ir(k, j, i));
			for (; j <= k; ++j) a[(int)s[j]] = -1;
		}
		a[(int)s[i]] = i;
	}

	//printf("j: %d, i: %d\n", j, i);

	if (i-j < 26) {
		printf("-1\n");
		return;
	}

	forre(k, 'A', 'Z') a[k] = -1;
	forr(k, j, i) a[(int)s[k]] = 0;

	forr(k, j, i) {
		if (s[k] != '?') continue;
		forre(l, 'A', 'Z') {
			if (a[l] == -1) {
				//printf("l: %c\n", l);
				a[l] = 0;
				s[k] = l;
				break;
			}
		}
	}

	forn(k, n) if (s[k] == '?') s[k] = 'A';
	printf("%s\n", s);
}

int main(void) {
	#ifdef DEBUG
	//freopen("Inputs/B", "r", stdin);
	setbuf(stdout, NULL);
	#endif

	while (read()) {
		solve();
	}

	return 0;
}
