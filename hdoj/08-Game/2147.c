/**
 * 2147
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "common.h"

#define S 2000

char sg[S+1][S+1];
int height, width;

int main(void)
{
	int i, j, k;
	int f[3];

	height = width = S;
	forre(i, 1, height) sg[i][1] = (i-1) % 2;
	forre(i, 1, width) sg[1][i] = (i-1) % 2;
	forre(i, 2, height) {
		forre(j, 2, width) {
			memset(f, 0, sizeof(f));
			if (sg[i-1][j] < 3) f[sg[i-1][j]] = 1;
			if (sg[i][j-1] < 3) f[sg[i][j-1]] = 1;
			if (sg[i-1][j-1] < 3) f[sg[i-1][j-1]] = 1;
			forn(k, 3) if (f[k] == 0) break;
			sg[i][j] = k;
		}
	}
	while (scanf("%d%d", &height, &width) == 2) {
		if (height == 0 && width == 0) break;
		printf("%s\n", sg[height][width] ? "Wonderful!" : "What a pity!");
	}

	return 0;
}
