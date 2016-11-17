/**
 * C
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <map>
#include <string>
#include <vector>

#include "common.h"

using namespace std;

#define N 200000

int n, m, k;
int c[N];

struct dsjset {
	int *a;
	map<int, int> *b;

	dsjset(void):a(NULL), b(NULL){}

	void init(void) {
		int i;
		if (a == NULL) a = new int[N];
		if (b == NULL) b = new map<int, int>[N];
		forn(i, N) a[i] = -1;
		forn(i, N) b[i].clear();
	}

	int find(int x) {
		if (a[x] < 0) return x;
		//FIXME: There was a but
		return a[x] = find(a[x]);
	}

	void un(int x, int y) {
		int less, great;

		less = find(x);
		great = find(y);

		//FIXME: There was a but
		if (less == great)
			return;

		if (less > great) swap(less, great);
		a[great] += a[less];
		a[less] = great;

		//printf("un: %d, %d: %d\n", x+1, y+1, great+1);
	}

	void tomap(void) {
		int i, sid, clr, rid;

		forn(i, n) {
			//FIXME: twab
			sid = i;
			clr = c[sid];
			rid = find(sid);
			//printf("sid: %2d, rid: %2d, clr: %2d\n", sid+1, rid+1, clr);
			b[rid][clr] += 1;
		}

		//forn(i, n) printf("map: %3d, size: %d\n", i+1, (int)b[i].size());
	}
};

bool read(void) {
	int i;

	if (scanf("%d%d%d", &n, &m, &k) != 3)
		return false;
	//printf("n: %d, m: %d, k: %d\n", n, m, k);

	forn(i, n) scanf("%d", &c[i]);
	return true;
}

int noths(map<int, int> *mp) {
	map<int, int>::iterator it;
	int maxc, total;

	total = maxc = 0;
	for (it = mp->begin(); it != mp->end(); ++it) {
		maxc = max(maxc, it->second);
		total += it->second;
	}

	return total - maxc;
}

void solve(void) {
	dsjset set; 
	map<int, int> *mp;
	int i, left, rght, total, maxv;

	set.init();

	forn(i, m) {
		scanf("%d%d", &left, &rght);
		--left, --rght;
		set.un(left, rght);
	}

	set.tomap();

	total = 0;
	forn(i, n) {
		mp = &set.b[i];
		if (mp->empty()) continue;
		maxv = noths(mp);
		//printf("map: %3d, size: %3d, maxv: %d\n", i, (int)mp->size(), maxv);
		total += maxv;
	}

	printf("%d\n", total);
}

int main(void) {
	freopen("Inputs/C", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) solve();

	return 0;
}
