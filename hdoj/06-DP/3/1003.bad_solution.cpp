/**
 * 1003
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"

using namespace std;

#define N 100000

int n;
vector<int> a(N), s(N), start(N);

bool read(void)
{
	int i;

	if (scanf("%d", &n) != 1)
		return false;
	forn(i, n) scanf("%d", &a[i]);
	//forn(i, n) printf("%3d%s", a[i], tailer(i, n-1));
	return true;
}

void solve(int cid)
{
	int i, maxi, maxv;

	s[0] = a[0];
	start[0] = 0;
	//printf("s[%2d]: %2d\n", 0, s[0]);
	forr(i, 1, n) {
		s[i] = max(a[i], s[i-1]+a[i]);
		start[i] = (s[i] == (a[i]+s[i-1]) ? start[i-1] : i);
		//printf("s[%2d]: %2d\n", i, s[i]);
	}

	maxi = -1, maxv = INT_MIN;
	forn(i, n) {
		if (s[i] > maxv) {
			maxv = s[i];
			maxi = i;
		}
	}

	printf("Case %d:\n", cid);
	printf("%d %d %d\n", maxv, start[maxi]+1, maxi+1);
}

int main(void)
{
	int ncases, i;

	freopen("Inputs/1003", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d", &ncases);
	forn(i, ncases) {
		read();
		solve(i+1);
		if (i+1 != ncases) printf("\n");
	}

	return 0;
}
