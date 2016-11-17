/*
 * 1087 Super Jumping! Jumping! Jumping!
 *
 * Russian Doll Problem
 *
 */

#include <stdio.h>
#include <stdlib.h>

int main(void)
{
	int ndolls, i, j;
	int s[1000], best;
	int doll[1000];

	freopen("Inputs/1087", "r", stdin);
	setbuf(stdout, NULL);

	while (scanf("%d", &ndolls) == 1) {
		if (!ndolls)
			break;
		for (i = 0; i < ndolls; ++i)
			scanf("%d", doll + i);
		best = 0;
		for (i = ndolls - 1; i >= 0; --i) {
			s[i] = doll[i];
			for (j = i + 1; j < ndolls; ++j) {
				if (doll[i] < doll[j] && s[i] < doll[i] + s[j])
					s[i] = doll[i] + s[j];
			}
			if (s[i] > best)
				best = s[i];
			//printf("s[%d]: %d\n", i, s[i]);
		}
		printf("%d\n", best);
	}

	return 0;
}


