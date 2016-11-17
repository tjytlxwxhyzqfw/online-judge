/*
 * 1175 LianLianKan
 */

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

int map[1000][1000];
int dir[8] = {0, 0, -1, 1, -1, 1, 0, 0};
int M, N;

int dfs(int deepth, int conner, int direction,
		int bx, int by, int ex, int ey)
{
	int conner2, d, nx, ny;
	int ret, save;
	
	save = map[bx][by];
	map[bx][by] = 51;
	for (d = 0; d < 4; ++d) {
		conner2 = conner;
		if (d != direction)
			if (++conner2 > 2)
				continue;
		nx = bx + dir[d];
		ny = by + dir[d + 4];
		
		if (nx == ex && ny == ey) {
			ret = 1;
			goto exit;
		}
		if (nx >= 0 && nx < M && ny >= 0 && ny < N
				&& !map[nx][ny]
				&& dfs(deepth + 1, conner2, d,nx, ny, ex, ey)) {
			ret = 1;
			goto exit;
		}
	}
	ret = 0;
exit:
	map[bx][by] = save;
	return ret;
} 
int main(void)
{
	int i, j, bx, by, ex, ey, nq;

	setbuf(stdout, NULL);
	freopen("Inputs/1175", "r", stdin);

	while (1) {
		scanf("%d%d", &M, &N);
		if (!M && !N)
			break;
		for (i = 0; i < M; ++i)
			for (j = 0; j < N; ++j)
				scanf("%d", &map[i][j]);

		scanf("%d", &nq);
		while (nq--) {
			scanf("%d%d%d%d", &bx, &by, &ex, &ey);
			if (map[--bx][--by] == map[--ex][--ey] &&
					map[bx][by] &&
					dfs(0, -1, -1,  bx, by, ex, ey))
				printf("YES\n");
			else
				printf("NO\n");
		}
	}
	
	return 0;
}
