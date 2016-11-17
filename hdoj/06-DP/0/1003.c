/*
 * 1003 Max Sum
 */
#include <stdio.h>
#include <stdlib.h>

void max_sum(const int s[], const int n, int *maxsum, int *start, int *end)
{
	int i;
	int sum, st, ed;

	*maxsum = -1000;
	*start = *end = 0;

	st = ed = sum = 0;
	for (i = 0; i < n; ++i) {
		sum += s[ed = i];
		if (sum > *maxsum) {
			*maxsum = sum;
			*start = st;
			*end = ed;
		}
		if (sum < 0) {
			sum = 0;
			st = i + 1;
		}
	}
}

int main(void)
{
	int sequence[100000];

	int ncas, cas;
	int sum, start, end;
	int i, ssize;

	freopen("Inputs/1003", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d", &ncas);
	for (cas = 1; cas <= ncas; ++cas) {
		scanf("%d", &ssize);
		for (i = 0; i < ssize; ++i)
			scanf("%d", sequence + i);
		max_sum(sequence, ssize, &sum, &start, &end);
		printf("Case %d:\n%d %d %d\n\n", cas, sum, start, end);
	}

	return 0;
}
