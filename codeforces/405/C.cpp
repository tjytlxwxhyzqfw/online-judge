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

#define N 60

int n, m, k;
int a[N], v[N];
long long masks[N];
char names[900][3];

bool read(void) {
	int i;
	char vedict[10];

	scanf("%d%d", &n, &k);
	m = n - k + 1;
	forn(i, m) {
		scanf("%s", vedict);
		v[i] = (vedict[0] == 'Y' ? true : false);
	}
	return true;
}

int ind(const long long bitmask) {
	int i;
	forn(i, 64)
		if ((bitmask & (ULL1 << i)) != 0)
			return i;
	assert(false);
}

long long fstz(const long long mask) {
	int i;
	forn(i, 64)
		if ((mask & (ULL1 << i)) == 0)
			return (ULL1 << i);
	assert(false);
}

void makediff(const int bgn, const int end) {
	int i, j;
	long long cand, mask;
	int val;

	printf("makdif: [%3d, %3d]\n", bgn, end);

	forre(i, bgn, end) {
		printf("\t---> i = %d\n", i);
		mask = masks[i];
		rfor(j, bgn) {
			cand = (ULL1 << a[j]);
			if ((mask & cand) == 0)
				break;
		}
		if (j == -1) {
			printf("j === -1\n");
			cand = fstz(mask);
		}

		printf("\tcand bit: %3d, cand: %lld\n", ind(cand), cand);

		val = ind(cand);
		a[i] = val;
		forre(j, i+1, end)
			masks[j] |= cand;
	}
}

void solve(void) {
	int i, j;

	i = 0;
	for(j = 'a'; j <= 'z'; ++j) {
		names[i][0] = 'A';
		names[i][1] = (char)j;
		names[i][2] = 0;
		++i;
	}
	for(j = 'a'; j <= 'z'; ++j) {
		names[i][0] = 'B';
		names[i][1] = (char)j;
		names[i][2] = 0;
		++i;
	}

	forn(i, m) masks[i] = 0;
	forn(i, m) a[i] = 0;
	forn(i, m)
		if (v[i] == true)
			makediff(i, i+k-1);

	forn(i, n) printf("%s ", names[a[i]]);
	printf("\n");
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
