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
#include "helpers.h"

using namespace std;

#define N 2000

int n, m, a[N];
stack<int>::size_type avg;
map<int, stack<int>> mp;

bool read(void) {
	int i, band;
	if (scanf("%d%d", &n, &m) != 2)
		return false;
	avg = n / m;
	forn(i, n) {
		scanf("%d", &band);
		a[i] = band;
		mp[band].push(i);
	}
	return true;
}

void clear(void) {
	mp.clear();
}

struct denoting: public unary_function<map<int, stack<int>>::iterator, bool> {
	bool operator()(map<int, stack<int>>::iterator it) {
		return it->second.size() > (it->first <= m ? avg : 0);
	}
};

void solve(void) {
	skipper<map<int, stack<int>>::iterator, denoting> skp;
	map<int, stack<int>>::iterator it, denoter;
	int ntransfer, song, i;

	/* put all bands int mp[] */
	forre(i, 1, m) mp[i];

	skp.init(mp.begin(), mp.end());
	ntransfer = 0;
	foriter(it, mp) {
		while (it->first <= m && it->second.size() < avg) {
			skp.nxt();
			denoter = skp.cur;
			song = denoter->second.top();
			denoter->second.pop();
			it->second.push(song);
			a[song] = it->first;
			++ntransfer;
			//printf("transfer: %2d --- %2d ---> %2d\n", denoter->first, song, it->first);
		}
	}

	printf("%d %d\n", (int)avg, ntransfer);
	forn(i, n) printf("%d%s", a[i], tailer(i, n-1));
}

int main(void) {
	freopen("Inputs/C", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) {
		solve();
		clear();
	}

	return 0;
}
