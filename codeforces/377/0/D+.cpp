/**
 * 377D
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"

using namespace std;

#define N 100000

#define blstr(v) ((v) ? "true" : "false")

int n, m;
int a[N], b[N];
int f[N+1], c[N+1];

int _d;

int use(const int total, const int from) {
	int used, i;

	if (total == 0) return from;

	used = 0;
	rfore(i, from) {
		if (b[i] != 0) continue;
		b[i] = -1;
		++used;
		if (used == total)
			break;
	}

	return i;
}

bool check(const int end) {
	int i, j;
	int exm;

	fill(f, f+m+1, false);
	copy(a, a+n, b);

	rfore(i, end) {
		exm = b[i];
		if (exm <= 0) continue;
		if (f[exm]) b[i] = 0;
		else f[exm] = true;
	}

	assert(f[0] == false);
	forre(i, 1, m) if (!f[i]) return false;

	j = end;
	rfore(i, end) {
		exm = b[i];
		if (exm <= 0) continue;
		j = min(j, i);
		//_d = j; //print
		j = use(c[exm], j);
		//printf("\texm: %2d, j: %2d --> %2d\n", exm, _d, j);
		if (j < 0) return false;
	}

	return true;
}

bool read(void)
{
	int i;
	if (scanf("%d%d", &n, &m) != 2)
		return false;
	//printf("n: %d, m: %d\n", n, m);
	forn(i, n) scanf("%d", &a[i]);
	forn(i, m) scanf("%d", &c[i+1]);
	return true;
}

void solve(void)
{
	int ans = -2;
	int b, e, m;
	bool ck;

	b = 0, e = n-1;
	while (b <= e) {
		m = (b+e) / 2;
		//printf("m: %2d\n", m);
		ck = check(m);
		//printf("\tck: %2d: %s\n", m, blstr(ck));
		if (ck) {
			e = m-1;
			ans = m;
		} else {
			b = m+1;
		}
	}

	printf("%d\n", ans+1);
}

int main(void) {
	freopen("Inputs/377D", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) solve();

	return 0;
}
