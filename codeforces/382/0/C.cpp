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

long long n;
long long s[N];

bool read(void) {
	scanf("%lld", &n);
	return true;
}

void solve(void) {
	int i, maxi;

	s[1] = 2;
	s[2] = 3;


	maxi = N;
	forr(i, 3, maxi) {
		s[i] = s[i-1] + s[i-2];
		if (s[i] < s[i-1] || s[i] < s[i-2]) {
			s[i] = -1;
			break;
		}
	}

	forr(i, 1, maxi) {
		//printf("quik to %5d games need %5lld players\n", i, s[i]);
		if (s[i] < 0)
			break;
		if (s[i] > n)
			break;
	}

	printf("%d\n", i-1);
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
