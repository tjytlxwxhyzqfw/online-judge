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

using namespace std;

#define N 100000

long long n, n1, n2;
long long a[N];

bool read(void) {
	int i;
	scanf("%lld%lld%lld", &n, &n1, &n2);
	forn(i, n) scanf("%lld", &a[i]);
	return true;
}

void solve(void) {
	int i;
	long long x, z;
	long long *b;

	sort(a, a+n);
	b = a + (n-n1-n2);
	n = n1+n2;

	//printa(b, b+n, "b", 2);

	z = 0;
	forn(i, n) z += b[i];

	x = 0;
	if (n2 > n1) {
		forr(i, n-n1, n) x += b[i];
	} else {
		forn(i, n1) x += b[i];
	}
	//printf("x: %lld\n", x);

	double left = (double)x * (double)(n2-n1);
	double rght = (double)z * (double)n1;
	double bot = (double)n1 * n2;

	printf("%lf\n", (double)(left+rght)/bot);
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
