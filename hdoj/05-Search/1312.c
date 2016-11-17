/*
 * 1312 - Red and Black
 *
 * DFS simple
 */

#include <stdio.h>
#include <stdlib.h>

char map[20][20];
short height, width, color[20][20];
int count;

const short dir[] = {0, 0, 1, -1, 1, -1, 0, 0};

void dfs(short y, short x)
{
	short ny, nx, d;

	printf("dfs:(%d,%d) (%d)\n", y, x, color[y][x]);
	sleep(1);

	if (y <  0 || y >= height || x < 0 || x >= width)
		return;

	if (map[y][x] == '#')
		return;

	if (color[y][x] == 1)
		return;

	//printf("+1\n");

	color[y][x] = 1;
	++count;

	for (d = 0; d < 4; ++d) {
		ny = y + dir[d];
		nx = x + dir[d + 4];
		dfs(ny, nx);
	}
}

int main(void)
{
	short sy, sx, y, x;

	freopen("Inputs/1312", "r", stdin);
	setbuf(stdout, NULL);

	while (scanf("%d%d%*c", &width, &height) != EOF) {
		if (!height)
			break;
		for (y = 0; y < height; ++y) {
			for (x = 0; x < width; ++x) {
				scanf("%c", &map[y][x]);
				//printf("map[%d][%d]=%c\n", y, x, map[y][x]);
				//sleep(1);
				color[y][x] = 0;
				if (map[y][x] == '@') {
					sy = y;
					sx = x;
				}
			}
			scanf("%*c");
		}
		count = 0;
		dfs(sy, sx);
		printf("result = %d\n", count);
	}

	return 0;
}
