/**
 * B
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

struct Point {
	double x, v;

	bool operator<(const Point &rival) const {
		return x < rival.x;
	}
};

#define N 60001

int n;
Point points[N];

bool read(void) {
	int i;

	scanf("%d", &n);
	forn(i, n) scanf("%lf", &points[i].x);
	forn(i, n) scanf("%lf", &points[i].v);

	sort(points, points+n);

	return true;
}

void solve(void) {
	int i, j;
	int left, rght;
	double tmax, t, remote;
	double v[N], x[N];

	left = 0, rght = 1;
	tmax = 0;

	forn(i, n) v[i] = points[i].v;
	forn(i, n) x[i] = points[i].x;

	forn(i, n) {
		remote = x[i] + v[i]*t;
		j = upper_bound(v+i, v+n, remote)-(v+i);
		forn(j, n) {
			t = 1.0*abs(x[i]-x[j])/(v[i]+v[j]);
			if (tmax < t) {
				left = min(x[i], x[j]);
				rght = max(x[i], x[j]);
				tmax = t;
			}
		}
	}

	tmax += left - left + rght - rght;
	printf("%lf\n", tmax);
}

int main(void) {
	#ifdef DEBUG
	//freopen("Inputs/B", "r", stdin);
	setbuf(stdout, NULL);
	#endif

	read();
	solve();

	return 0;
}
