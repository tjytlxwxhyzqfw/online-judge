/**
 * 1159
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <cstring>
#include <vector>

#include "common.h"

using namespace std;

#define N 10000

int la, lb;
int s[N][N+1];
char a[N+1], b[N+1];

bool appear(char x, char *s, int b, int e)
{
	int i;
	forr(i, b, e) if (s[i] == x) break;
	return i != e;
}

bool read(void)
{
	if (scanf("%s%s", a, b) != 2)
		return false;
	la = strlen(a), lb = strlen(b);

	//printf("a(%3d): %s\n", la, a);
	//printf("b(%3d): %s\n", lb, b);

	return true;
}

void solve(void)
{
	int i, j;

	forn(i, la) s[i][lb-1] = (appear(b[lb-1], a, i, la) ? 1 : 0);
	forn(j, lb) s[la-1][j] = (appear(a[la-1], b, j, lb) ? 1 : 0);

	rfor(i, la-1) {
		rfor(j, lb-1) {
			s[i][j] = (a[i] == b[j] ? 1+s[i+1][j+1] : 0);
			s[i][j] = max3(s[i][j], s[i][j+1], s[i+1][j]);
		}
	}

	printf("%d\n", s[0][0]);
}

int main(void)
{
	freopen("Inputs/1159", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) solve();

	return 0;
}
