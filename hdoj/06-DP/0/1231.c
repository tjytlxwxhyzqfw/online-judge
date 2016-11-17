/*
 * 1231 最大连续子序列
 *
 * I have solved this before using dsaa-algorighm.
 * But this time I am going to solve it with dp.
 */

#include <stdio.h>

//#include "debug.c"

int main(void)
{
	int size, i, start, end,  maxstart, maxend;
	int sum, maxsum, ni, first;

	freopen("Inputs/1231", "r", stdin);
	setbuf(stdout, NULL);

	while (scanf("%d", &size) == 1) {
		if (size == 0)
			break;
		scanf("%d", &first);
		maxsum = sum = first;
		maxstart = maxend = sum;
		for (i = 1; i < size; ++i) {
			scanf("%d", &ni);
			if (sum + ni > ni) {
				sum = sum + ni;
				end = ni;
			} else {
				/* sum + ni <= ni */
				sum = ni;
				start = end = ni;
			}
			if (sum > maxsum) {
				maxsum = sum;
				maxstart = start;
				maxend = end;
			}
		}
		if (maxsum < 0) {
			maxsum = 0;
			maxstart = first;
			maxend = ni;
		}

		printf("%d %d %d\n", maxsum, maxstart, maxend);
	}

	return 0;
}
