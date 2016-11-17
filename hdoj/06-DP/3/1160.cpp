/**
 * 1160
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"

using namespace std;

#define N 1000

struct mouse {
	static bool compare(const mouse &x, const mouse &y) {
		return y.w < x.w;
	}
	int idx, w, s;
	void print(void) { printf("(%4d, %4d)\n", w, s); }
	bool operator<(const mouse &m) {
		return w < m.w and s > m.s;
	}
};

int n;
vector<mouse> ms(N);
int s[N], nxt[N];

bool read(void)
{
	int w, s;

	n = 0;
	while (scanf("%d%d", &w, &s) == 2) {
		ms[n].idx = n;
		ms[n].w = w, ms[n].s = s;
		++n;
	}
	sort(ms.begin(), ms.begin()+n, mouse::compare);
	return true;
}

void printpath(int i)
{
	if (i == -1)  return;
	printpath(nxt[i]);
	printf("%d\n", ms[i].idx+1);
	//ms[i].print();
}

void solve(void)
{
	int i, j;
	int maxv, maxi;

	s[n-1] = 1, nxt[n-1] = -1;
	rfor(i, n-1) {
		s[i] = 0, nxt[i] = -1;
		forr(j, i+1, n) {
			if (ms[j] < ms[i]) {
				if (s[j] > s[i]) {
					s[i] = s[j];
					nxt[i] = j;
				}
			}
		}
		s[i] = 1 + s[i];
	}

	maxv = 0, maxi = -1;
	forn(i, n) {
		if (s[i] > maxv) {
			maxv = s[i];
			maxi = i;
		}
	}

	printf("%d\n", maxv);
	printpath(maxi);
}

int main(void)
{
	freopen("Inputs/1160", "r", stdin);
	setbuf(stdout, NULL);

	read();
	solve();

	return 0;
}
