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

#define N 200000

int n;
char a[N+1];
int b[128];

bool read(void) {
	scanf("%d", &n);
	scanf("%s", a);
	return true;
}

int opidx(const int idx) {
	switch(idx) {
		case 'D':
			return 'U';
		case 'U':
			return 'D';
		case 'L':
			return 'R';
		case 'R':
			return 'L';
		default:
			assert(false);
	}

	return -1;
}

void clearb(void) {
	for(int i = 0; i < 128; ++i)
		b[i] = 0;
}

void solve(void) {
	int ans, idx, opi;
	int i;

	assert(n >= 1);

	ans = 1;
	clearb();
	b[(int)a[n-1]] = 1;
	//printf("end char: %c: %d\n", a[n-1], n-1);
	rfor(i, n-1) {
		idx = a[i];
		opi = opidx(idx);
		if (b[opi] == 1) {
			//printf("end char: %c: %d\n", a[i], i);
			++ans;
			clearb();
		}
		b[idx] = 1;
	}

	printf("%d\n", ans);
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
