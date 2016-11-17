/**
 * 1729
 */
#include <stdio.h>
#include "common.h"

#define B 50
#define S 1000001

int n, s[B], c[B];
int sg[S];

void sg_fill(int b, int m)
{
	int z;

	if (m >= 1000) {
		sg[m] = s[b]-m;
		return;
	}
	z = sg[m+1] + m + 1;
	//printf("z: %d\n", z);
	assert(sg[z] == 0);
	if (m+m*m >= z)
		sg[m] = sg[m+1] + 1;
	else
		sg[m] = 0;
}

void sg_compute(int b)
{
	int i;
	//printf("sg_compute: %d\n", b);

	sg[s[b]] = 0;
	for (i = s[b]-1; i >= c[b]; --i) {
		sg_fill(b, i);
		//printf("sg: %d: %d\n", i, sg[i]);
	}
}

int main(void)
{
	int casid = 0;
	int i;
	int result;

	freopen("Inputs/1729", "r", stdin);
	setbuf(stdout, NULL);

	while (scanf("%d", &n) == 1) {
		if (n == 0) break;
		forn(i, n) scanf("%d%d", s+i, c+i);

		result = 0;
		forn(i, n) {
			sg_compute(i);
			result ^= sg[c[i]];
		}

		printf("Case %d:\n%s\n", ++casid, result ? "Yes" : "No");
	}

	return 0;
}
