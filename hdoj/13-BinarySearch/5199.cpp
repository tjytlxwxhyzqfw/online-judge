/**
 * 5199
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <map>
#include <vector>

#include "common.h"

using namespace std;

#define N 1000000

int n, m;
map<int, int> mp;

bool read(void) {
	int height, i;
	if (scanf("%d%d", &n, &m) != 2)
		return false;
	mp.clear();
	forn(i, n) {
		scanf("%d", &height);
		++mp[height];
	}
	return true;
}

void solve(void) {
	int q;
	map<int, int>::iterator it;

	while (--m >= 0) {
		scanf("%d", &q);
		it = mp.find(q);
		if (it == mp.end()) {
			printf("0\n");
			continue;
		}
		printf("%d\n", it->second);
		it->second = 0;
	}
}

int main(void) {
	freopen("Inputs/5199", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) solve();

	return 0;
}
