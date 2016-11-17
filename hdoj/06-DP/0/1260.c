/*
 * 1260 Tickets
 * 
 * Primary DP, DFS-DP
 */

#include <limits.h>
#include <stdio.h>
#include <stdlib.h>

//#include "debug.c"

int s[2000][2], single[2000], bond[2000];
int total;

void timeadd(int seconds, int hour, int min, int sec,
		int *hp, int *mp, int *sp)
{
	int carry = seconds;

	*sp = sec + carry;
	carry = *sp / 60;
	*sp %= 60;

	*mp = min + carry;
	carry = *mp / 60;
	*mp %= 60;

	*hp = hour + carry;
	*hp %= 24;
}



int fill_s(int pid, int mth)
{
	int psec, secs1, secs2, secs;
	int next;

	if (mth == 0) {
		psec = single[pid];
		next = pid + 1;
	} else {
		psec = bond[pid];
		next = pid + 2;
	}

	if (next >= total) {
		secs1 = secs2 = 0;
	} else {
		secs1 = s[next][0];
		secs2 = s[next][1];
	}
	secs = psec + (secs1 < secs2 ? secs1 : secs2);

	//printis(0, 0, "s[%2d][%2d]: %3d\n", pid, mth, secs);

	s[pid][mth] = secs;
	return secs;
}

int main(void)
{
	int ncas, i;
	int minsecs;
	int hour, min, sec;

	freopen("Inputs/1260", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d", &ncas);
	while (ncas--) {
		scanf("%d", &total);
		for (i = 0; i < total; ++i)
			scanf("%d", single + i);
		for (i = 1; i < total; ++i)
			scanf("%d", bond + i - 1);
		bond[total - 1] = INT_MAX;

		for (i = total - 1; i >= 0; --i) {
			fill_s(i, 0);
			fill_s(i, 1);
		}

		minsecs = s[0][0] < s[0][1] ? s[0][0] : s[0][1];
		//printf("result: %d\n", minsecs);
		timeadd(minsecs, 8, 0, 0, &hour, &min, &sec);
		printf("%s%d:%s%d:%s%d %sm\n", 
				hour < 10 ? "0" : "",
				hour >= 13 ? hour - 12 : hour,
				min < 10 ? "0" : "",
				min,
				sec < 10 ? "0" : "",
				sec,
				hour >= 12 ? "p" : "a");
	}

	return 0;
}
