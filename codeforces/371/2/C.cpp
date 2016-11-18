/**
 * C
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

#define N 20

int nops;

bool read(void) {
	if (scanf("%d", &nops) != 1)
		return false;
	return true;
}

void stdlz(char *s) {
	int i, j;

	//printf("stdliz: %20s -> ", s);
	s[N-1] = 0, j = N-2;
	for (i = strlen(s)-1; i >= 0; --i)
		s[j--] = (odd(s[i]) ? '1' : '0');
	for (; j >= 0; --j) s[j] = '0';
	//printf("%20s\n", s);
}

void solve(void) {
	char cmd[2], str[N];
	map<string, int> mp;

	while (--nops >= 0) {
		scanf("%s%s", cmd, str);
		stdlz(str);
		switch (cmd[0]) {
			case '+':
				++mp[string(str)];
				break;
			case '-':
				--mp[string(str)];
				break;
			case '?':
				printf("%d\n", mp[string(str)]);
				break;
			default:
				assert(false);
		}
	}
}

int main(void) {
	freopen("Inputs/C", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) {
		solve();
		printf("---cut---\n");
	}

	return 0;
}
