/**
 * 1004
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <map>
#include <string>
#include <vector>

#include "common.h"

using namespace std;

int n;
map<string, int> mp;

bool read(void) {
	int i;
	char str[16];

	if (scanf("%d", &n) != 1)
		return false;
	if (n == 0)
		return false;

	mp.clear();
	forn(i, n) {
		scanf("%s", str);
		mp[string(str)] += 1;
	}

	return true;
}

void solve(void) {
	map<string, int>::iterator it;
	const string *first = NULL;
	int second = 0;

	for (it = mp.begin(); it != mp.end(); ++it) {
		//printf("color: %15s, quantity: %3d\n", it->first.c_str(), it->second);
		if (it->second > second) {
			first = &it->first;
			second = it->second;
		}
	}

	printf("%s\n", first->c_str());
}
	

int main(void) {
	freopen("Inputs/1004", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) solve();

	return 0;
}
