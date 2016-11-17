/**
 * 1316
 *
 * 前缀和
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <vector>

#include "common.h"
#include "lgnum.h"

using namespace std;

#define N 120

char b[N], e[N];

bool read(void) {
	if (scanf("%s%s", b, e) != 2)
		return false;
	if (strcmp(b, "0")==0 && strcmp(e, "0")==0)
		return false;
	return true;
}

void solve(void) {
	lgnum<N> bgn, end, x, y, z;
	int bidx, eidx, i;

	bgn.setv(b), end.setv(e), x.setv("1"), y.setv("2");
	if (end < bgn) swap(bgn, end);

	bidx = -1;
	for (i = 2; ; ++i) {
		lgnum<N>::pls(x, y, z);
		//z.print();
		if (z >= bgn && bidx == -1) {
			bidx = i;
		}
		if (end < z) {
			eidx = i;
			break;
		}
		swap(x, y);
		swap(y, z);
	}

	if (bidx == 2 && strcmp(bgn.num, "3") != 0) {
		if (strcmp(bgn.num, "2") == 0) bidx = 1;
		else bidx = 0;
	}

	if (eidx == 2 && strcmp(end.num, "2") != 0) {
		if (strcmp(end.num, "1") == 0) eidx = 1;
		else eidx = 0;
	}

	printf("%d\n", eidx - bidx);
}

int main(void)
{
	//freopen("Inputs/1316", "r", stdin);
	//setbuf(stdout, NULL);

	while (read()) solve();

	return 0;
}
