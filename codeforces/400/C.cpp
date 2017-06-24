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

#define N 100000

const long long top = 1000000000000000;

long long a[N+1], s[N+1], ss[N+1];
long long n, k;

bool read(void) {
	long long i;

	scanf("%lld%lld",&n, &k);
	forn(i, n) scanf("%lld", &a[i]);

	s[0] = a[0];
	forr(i, 1, n) s[i] = a[i] + s[i-1];

	forn(i, n) ss[i] = s[i];
	sort(ss, ss+n);

	//printf("s && ss:\n");
	//printa(s, s+n);
	//printa(ss, ss+n);

	return true;
}

long long f(const long long x) {
	long long i;
	long long target, ans;
	long long d[N+1], *left, *rght;


	forn(i, n) d[i] = s[i] - x;

	//printf("x: %lld\n", x);
	//printa(d, d+n);

	ans = 0;
	forn(i, n) {
		target = d[i];
		//printf("\ttarget: %5lld, i: %5lld\n", target, i);
		if (target == 0) {
			ans += 1;
			continue;
		}
		if (i == 0)
			continue;

		left = lower_bound(ss, ss+i, target);
		rght = upper_bound(ss, ss+i, target);
		//printf("\tleft: %5lld, rght: %5lld\n", *left, *rght);

		if (left == ss+i || *left != target)
			continue;
		ans += rght - left;
		if (rght != ss+i && *rght == target)
			ans += 1;
	}

	//printf("\tans: %lld\n", ans);
	return ans;
}

void solve(void) {
	long long ans, power;

	ans = 0;
	power = 1;
	while(true) {
		ans += f(power);
		power *= k;
		if (abs(power) > top)
			break;
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
