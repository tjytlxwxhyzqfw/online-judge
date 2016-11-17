/*
 * 2616 - Kill the monster
 *
 * 枚举+打表
 *
 * 典型的FE-DFS
 */
#include <stdio.h>
#include <stdlib.h>

#include "Source/debug.c"

int damage[10], thresh[10], color[10];
int spells, best;

/*
 * 地图问题写多了,这种dfs反而不会写了
 */
void dfs(int used, int s, int hp)
{
	int i;

	//printis(used, 0, "dfs: used: %d, s: [%d], hp: %d\n", used, s, hp);


	color[s] = 1;
	//printi(used, "hp: %d, thresh[%d]: %d\n", hp, s, thresh[s]);
	hp -= (hp <= thresh[s] ? 2 * damage[s] : damage[s]);
	//printis(used, 0, "hp: %d\n", hp);

	if (hp <= 0) {
		if (used < best)
			best = used;
		//printis(used, 2, "best: %d\n", best);
		color[s] = 0;
		return;
	}

	for (i = 0; i < spells; ++i) {
		if (color[i] == 0)
			dfs(used + 1, i, hp);
	}

	color[s] = 0;
}

int main(void)
{
	int hp, i;
	int res;

	freopen("Inputs/2616", "r", stdin);
	setbuf(stdout, NULL);

	while (scanf("%d%d", &spells, &hp) == 2) {
		for (i = 0; i < spells; ++i)
			scanf("%d%d", damage + i, thresh + i);

		res = 10;
		for (i = 0; i < spells; ++i) {
			best = 10; 
			dfs(0, i, hp);
			if (best < res)
				res = best;
		}
		prints(0, "result:%d\n", best == 10 ? -1 : res + 1);
	}

	return 0;
}
