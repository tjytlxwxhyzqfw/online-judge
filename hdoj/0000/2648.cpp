/**
 * 2648
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <cstring>
#include <map>
#include <string>
#include <vector>

#include "common.h"

using namespace std;

#define N 10000

int n, m;
map<string, int> mp;

bool read(void) {
	char str[32];
	int i;

	mp.clear();

	if (scanf("%d", &n) != 1)
		return false;

	forn(i, n) {
		scanf("%s", str);
		mp[string(str)] = 0;
	}

	return true;
}

void print_rank(void) {
	int i, j, prices[N], rank;
	map<string, int>::iterator it;

	i = 0;
	for (it = mp.begin(); it != mp.end(); ++it) {
		prices[i++] = it->second;
		if (strcmp(it->first.c_str(), "memory") == 0)
			j = it->second;
	}
	assert(i == n);

	sort(prices, prices+n);
	//printf("j: %d\n", j);
	//forn(i, n) printf("%d%s", prices[i], tailer(i, n-1));
	rank = (int)(upper_bound(prices, prices+n, j) - prices);
	printf("%d\n", n-rank+1);
}

void solve(void) {
	int i, j, inc;
	char str[32];
	map<string, int>::iterator it;

	scanf("%d", &m);
	forn(i, m) {
		forn(j, n) {
			scanf("%d%s", &inc, str);
			/*TODO: optimize */
			//mp[string(str)] += inc;
			it = mp.find(string(str));
			it->second += inc;
		}
		print_rank();
	}
}

int main(void) {
	freopen("Inputs/2648", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) solve();

	return 0;
}
