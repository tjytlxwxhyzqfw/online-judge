/*
 * 1010 Tempter of The Bone
 */
#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

void printmaze(char **maze, int m, int n)
{
	int i, j;

	for (i = 0; i < m; ++i) {
		for (j = 0; j < n; ++j)
			printf("%c", maze[i][j]);
		printf("\n");
	}
	printf("\n");
}

void printpath(char *path, int n)
{
	int i;
	for (i = 0; i < n; ++i)
		printf("%d ", path[i]);
	printf("\n");
}

int is_doggie_alive(int deepth, char **maze, int m, int n, int t, int sx, int sy)
{
	static char direction[] = {0, 0, -1, 1, -1, 1, 0, 0};

	int x, y, i, ret;
	
	if (deepth > t)
		return 0;
	maze[sx][sy] = 'O';
	//printmaze(maze, m, n);
	for (i = 0; i < 4; ++i) {
		x = sx + direction[i];
		y = sy + direction[i + 4];
		if (x >= 0 && x < m && y >= 0 && y < n) {
			if (maze[x][y] == 'D')
				if (deepth == t - 1)
					return 1;
				else
					continue;
			if (maze[x][y] == '.' &&
					is_doggie_alive(
					deepth + 1, maze, m, n, t, x, y))
				return 1;
		}
	}
	maze[sx][sy] = '.';
	return 0;
}
	

int main(void)
{
	int m, n, t, i, j, sx, sy, ex, ey;
	int delta;
	char **maze;

	//setbuf(stdout, NULL);
	//freopen("Inputs/1010", "r", stdin);

	while (scanf("%d%d%d", &m, &n, &t) == 3) {
		if (m == 0 && n == 0 && t == 0)
			break;
		scanf("%*c");
		assert(maze = malloc(sizeof(char*) * m));
		for (i = 0; i < n; ++i)
			assert(maze[i] = malloc(sizeof(char) * n));
		for (i = 0; i < m; ++i) {
			for (j = 0; j < n; ++j) {
				scanf("%c", &maze[i][j]);
				if (maze[i][j] == 'S') {
					sx = i;
					sy = j;
				}
				if (maze[i][j] == 'D') {
					ex = i;
					ey = j;
				}
			}
			scanf("%*c");
		}
		//printmaze(maze, m, n);

		delta = ((ex - sx) + (ey - sy) - t);
		if (delta % 2)
			printf("NO\n");
		else
			printf("%s\n", is_doggie_alive(0, maze, m, n, t, sx, sy)
					? "YES" : "NO");
	}

	return 0;
}
