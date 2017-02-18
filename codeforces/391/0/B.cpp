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
//#include "number.h"

using namespace std;

#define N 100001

int n, a[N], b[N];

bool read(void) {
	int i;
	scanf("%d", &n);
	forn(i, n) scanf("%d", &a[i]);
	return true;
}

void record(const int num) {
	int i;

	//printf("num: %d\n", num);

	i = 2;
	while (i*i <= num) {
		if (num % i == 0) {
			b[i] += 1;
			b[num/i] += 1;
			//printf("\tfactor: %6d, %6d\n", i, num/i);
			if (i == num/i) {
				b[i] -= 1;
				//printf("\t-1\n");
			}
		}
		i += 1;
	}
	if (num != 1)
		b[num] += 1;
}

void solve(void) {
	int i, maxv;

	forn(i, N) b[i] = 0;

	forn(i, n) record(a[i]);

	maxv = 0;
	forn(i, N) maxv = max(maxv, b[i]);

	if (maxv == 0)
		maxv = 1;

	printf("%d\n", maxv);
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
