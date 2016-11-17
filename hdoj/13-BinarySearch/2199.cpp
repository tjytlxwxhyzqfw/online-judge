/**
 * 2199
 */

#include <algorithm>
#include <cassert>
#include <cmath>
#include <cstdio>
#include <vector>

#include "common.h"

using namespace std;

const double errtol = 0.0000001;

double y;

bool read(void) {
	if (scanf("%lf", &y) != 1)
		return false;
	return true;
}

inline double eqt(double x) {
	return 8 * pow(x, 4) + 7 * pow(x, 3) + 2 * pow(x, 2) + 3 * x + 6 - y;
}

void solve(void) {
	double b, e, m, r;

	if (6 - y > 0 || eqt(100) < 0) {
		printf("No solution!\n");
		return;
	}

	b = 0.0, e = 100.0;
	while (e - b >= errtol) {
		m = (b+e) / 2;
		r = eqt(m);
		if (r > 0) e = m;
		else if(r < 0) b = m;
		else break;
	}

	printf("%.4lf\n", m+0.00005);
}

int main(void)
{
	int ncs;

	freopen("Inputs/2199", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d", &ncs);
	while (--ncs >= 0) {
		read();
		solve();
	}

	return 0;
}
