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

#define N 5001

int n, t[N];

bool read(void) {
	if (scanf("%d", &n) != 1)
		return false;
	return true;
}

int query(int i, int j) {
	int sum;
	printf("? %d %d\n", i, j);
	fflush(stdout);
	scanf("%d", &sum);
	return sum;
}

void solve(void) {
	int i;
	int x, y, z, a, b, c, s;

	x = query(1, 2);
	y = query(2, 3);
	z = query(3, 1);

	//printf("x: %d, y: %d, z: %d\n", x, y, z);

	a = (x-y+z) / 2;
	b = x - a;
	c = z - a;

	t[1] = a, t[2] = b, t[3] = c;
	forre(i, 4, n) {
		s = query(1, i);
		t[i] = s - a;
	}

	printf("! ");
	forre(i, 1, n) printf("%d%s", t[i], tailer(i, n));
}

int main(void) {
	//freopen("Inputs/C", "r", stdin);
	//setbuf(stdout, NULL);

	read();
	solve();

	return 0;
}
