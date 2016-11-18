/**
 * B
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <cstring>
#include <map>
#include <stack>
#include <string>
#include <vector>

#include "common.h"

using namespace std;

#define N 50000

int n;
char a[N+1];
int d[26];

const char query = '?' - 65;
const char biga = 'A' - 65;

bool read(void) {
	int i;
	if (scanf("%s", a) != 1)
		return false;
	//printf("%s\n", a);
	n = strlen(a);
	forn(i, n) a[i] -= 65;
	return true;
}

void solve(void) {
	int i, j, k, flag[26], d[26];
	char ch;
	stack<char> stk, stk2;

	forn(k, 26) d[k] = -1;

	i = 0;
	forn(j, n) {
		if (j-i == 26) break;
		ch = a[j];
		if (ch == query) continue;
		if (d[ch] != -1) {
			//printf("\trepeat: %c: (%2d, %2d)\n", ch+65, d[ch], j);
			assert(i <= d[ch] && d[ch] < j);
			while (i <= d[ch]) d[a[i++]] = -1;
		}
		d[ch] = j;
	}

	//printf("subseq: [%2d, %d)\n", i, j);

	if (j-i < 26) {
		printf("-1\n");
		return;
	}

	forn(k, 26) flag[k] = 0;
	forr(k, i, j) flag[a[k]] = 1;
	forn(k, 26) if (flag[k] == 0) stk.push(k);

	while (!stk.empty()) {
		stk2.push(stk.top());
		stk.pop();
	}

	forr(k, i, j) {
		if (a[k] == query) {
			a[k] = stk2.top();
			stk2.pop();
		}
	}

	forn(k, n) if (a[k] == query) a[k] = 0;
	forn(i, n) putchar(a[i]+65);
	printf("\n");
}

int main(void) {
	freopen("Inputs/B", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) solve();

	return 0;
}
