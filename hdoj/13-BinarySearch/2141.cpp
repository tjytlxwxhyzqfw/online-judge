/**
 * 2141
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"

using namespace std;

#define N 500
int n[3], m;
long long a[3][N], b[N*N];

bool read(void) {
	int i, j;
	forn(i, 3) if (scanf("%d", &n[i]) != 1) return false;
	forn(i, 3) forn(j, n[i]) scanf("%lld", &a[i][j]);

	m = 0;
	forn(i, n[0]) forn(j, n[1]) b[m++] = a[0][i] + a[1][j];
	sort(b, b+m);

	i = 0;
	forr(j, 1, m) if (b[j] != b[i]) b[++i] = b[j];
	m = i+1;

	return true;
}

bool check(long long t, long long x) {
	int i, j, k;
	long long y;

	i = 0, j = m-1;
	while (i <= j) {
		k = (i+j) / 2;
		y = x + b[k];
		//printf("\t[%2d, %2d]: %2d: %d\n", i, j, k, y);
		if (y > t) j = k - 1;
		else if (y < t) i = k + 1;
		else return true;
	}

	return false;
}

void solve(int cid) {
	long long target;
	int nqs, i;
	bool ck;

	printf("Case %d:\n", cid);

	scanf("%d", &nqs);
	while (--nqs >= 0) {
		scanf("%lld", &target);
		//printf("target: %d\n", target);
		ck = false;
		for (i = 0; i < n[2]; ++i) {
			//printf("\tbase sum: %lld\n", a[2][i]);
			ck = check(target, a[2][i]);
			if (ck) break;
		}
		printf("%s\n", ck ? "YES" : "NO");
	}
}

int main(void) {
	int cid;

	freopen("Inputs/2141", "r", stdin);
	setbuf(stdout, NULL);

	cid = 0;
	while (read()) solve(++cid);

	return 0;
}
