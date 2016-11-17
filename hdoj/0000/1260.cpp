/**
 * 1260
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <cstring>
#include <vector>

#include "common.h"

using namespace std;

#define N 2000

int n;
int s[N], t[N], b[N];

bool read(void)
{
	int i;
	if (scanf("%d", &n) != 1)
		return false;
	forn(i, n) scanf("%d", t+i);
	forn(i, n-1) scanf("%d", b+i);
	return true;
}

#define z(x) ((x) < 10 ? "0" : "")

void printtime(void)
{
	int sec, mnt, hur;
	char tag[3];

	sec = s[0] % 60;
	mnt = (s[0] / 60) % 60;
	hur = s[0] / 3600;

	hur = (hur+8) % 24;
	strcpy(tag, "am");
	if (hur > 12) hur -= 12;
	if (hur >= 12) strcpy(tag, "pm");

	printf("%s%d:%s%d:%s%d %s\n", z(hur), hur, z(mnt), mnt, z(sec), sec, tag);
}

void solve(void)
{
	int i;
	s[n-1] = t[n-1];
	if (not (n >= 2)) goto print_ans;
	s[n-2] = min(t[n-1]+t[n-2], b[n-2]);
	rfor(i, n-2) s[i] = min(t[i]+s[i+1], b[i]+s[i+2]);

	print_ans:
	printtime();
}

int main(void)
{
	int ncs;

	freopen("Inputs/1260", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d", &ncs);
	while (--ncs >= 0) {
		read();
		solve();
	}

	return 0;
}
