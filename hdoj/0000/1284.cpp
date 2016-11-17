/**
 * 1284
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"

using namespace std;

int main(void)
{
	int i, s, n;

	while (scanf("%d", &n) == 1) {
		s = 0;
		fore(i, n/3) s += ((n-3*i)>>1) + 1;
		printf("%d\n", s);
	}

	return 0;
}
