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
#include "number.h"

using namespace std;

#define N 101

int n, a[N];

bool read(void) {
	int i;
	scanf("%d", &n);
	forre(i, 1, n) scanf("%d", &a[i]);
	//printa(a+1, a+n+1, "a: ");
	return true;
}

int check(const int k) {
	int b[N], i, s, t, nskps;

	forn(i, N) b[i] = 0;

	//printf("check from: %d\n", k);
	s = k;
	b[s] = 1;
	nskps = 0;
	//printf("%3d --> ", s);
	while (b[a[s]] == 0) {
		b[a[s]] = 1;
		s = a[s];
		//printf("%d --> ", s);
		++nskps;
	}

	t = a[s];
	++nskps;

	//printf("[%3d]\n", t);
	if (k == t)
		return nskps;
	else
		return -1;
}

void solve(void) {
	int nskps, i;
	int skps[N];
	int x, d;
	long long ans;

	ans = 0;
	forre(i, 1, n) {
		nskps = check(i);
		//printf("check return: %d\n", nskps);
		if (nskps == -1) {
			ans = -1;
			break;
		}
		skps[i] = nskps;
	}

	//printa(skps+1, skps+n+1, "skps: ");

	if (ans == -1) {
		printf("-1\n");
		return;
	}

	forre(i, 1, n) if (even(skps[i])) skps[i] /= 2;

	ans = skps[1];
	//printf("ans: %d\n", ans);
	forre(i, 2, n) {
		x = skps[i];
		d = gcd(ans, x);
		ans = ans * x / d;
		//printf("x: %10d, d: %10d, ans: %10lld\n", x, d, ans);
	}

	printf("%lld\n", ans);
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
