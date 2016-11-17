/*
 * 2084 数塔问题
 *
 * Primary DP, DFS-DP
 */

#include <stdio.h>
#include <stdlib.h>

int s[100][100], tower[100][100];
int layers;

int fill_s(const int lay, const int id)
{
	int next, down, right;
	int max;

	if (lay == layers - 1) {
		max = tower[lay][id];
		goto complete;
	}

	next = lay + 1;
	down = s[next][id];
	right = s[next][id + 1];

	max = (down > right ? down : right);
	max += tower[lay][id];

	complete:
	s[lay][id] = max;
	return max;
}

int main(void)
{
	int ncas;
	int i, j;

	freopen("Inputs/2084", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d", &ncas);
	while (ncas--) {
		scanf("%d", &layers);
		for (i = 0; i < layers; ++i)
			for (j = 0; j <= i; ++j)
				scanf("%d", &tower[i][j]);
		
		for (i = layers - 1; i >= 0; --i)
			for (j = 0; j <= i; ++j)
				fill_s(i, j);
		printf("%d\n", s[0][0]);
	}

	return 0;
}
