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

struct Price {
	int a, b;
	int inc;

	bool operator<(const Price &rival) const {
		return inc < rival.inc;
	}
};

#define N 200001

int n, k;
vector<Price> vec;

bool read(void) {
	int i;

	scanf("%d%d", &n, &k);

	vec.resize(n);
	forn(i, n) scanf("%d", &vec[i].a);
	forn(i, n) scanf("%d", &vec[i].b);

	//vector<Price>::const_iterator it;
	//foriter(it, vec) printf("(%3d, %3d)\n", it->a, it->b);
	//printf("----\n");

	return true;
}

void solve(void) {
	int na, i;
	int ans;


	ans = 0;
	na = 0;

	forn(i, n) {
		if (vec[i].a <= vec[i].b) {
			ans += vec[i].a;
			vec[i].inc = INT_MAX;
			++na;
		} else {
			ans += vec[i].b;
			vec[i].inc = vec[i].a - vec[i].b;
		}
	}

	sort(vec.begin(), vec.end());

	//vector<Price>::const_iterator it;
	//foriter(it, vec) printf("(%3d, %3d): %d\n", it->a, it->b, it->inc);

	forr(i, na, k) {
		ans += vec[i-na].inc;
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
