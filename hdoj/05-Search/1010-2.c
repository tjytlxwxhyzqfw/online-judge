/*
 * 1010 Tempter of The Bone
 */
#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

char direction[] = {1, 0, -1, 0, 0, 1, 0, -1};

char map[7][7];
int height, width;
int start_y, start_x, end_y, end_x;
int time;

void printmap()
{
	int y, x;

	for (y = 0; y < height; ++y) {
		for (x = 0; x < width; ++x)
			printf("%c", map[y][x]);
		printf("\n");
	}
	printf("\n");
}

/*
 * debug: thank python
 */
int dfs_2(int deepth, int y, int x)
{
	int d, ny, nx, ndeepth;

	//printf("dfs: %d, %d: +%2d\n", y, x, deepth);

	ndeepth = deepth + 1;
	/* 剪枝
	 * 剪枝并非固定一定在外面,
	 * 剪枝在里面或者外面是由剪枝的特点决定的
	 */
	if (ndeepth > time)
		return -4;

	/* 加锁
  	 * 加锁和解锁应该是固定在外面的
  	 */
	map[y][x] = 'O';

	for (d = 0; d < 4; ++d) {
		ny = y + direction[d];
		nx = x + direction[d + 4];

		//printf("\t(%d, %d): %2d\n", ny, nx, ndeepth);

		/* 约束 */
		/* 越界 */
		if (ny >= height || ny < 0 || nx >= width || nx < 0)
			continue;
		/* 碰壁 */
		if (map[ny][nx] == 'X')
			continue;
		/* 上锁 */
		if (map[ny][nx] == 'O')
			continue;

		/* 成功 */
		if (ny == end_y && nx == end_x && time == ndeepth)
			return 0;

		if (dfs_2(ndeepth, ny, nx) == 0)
			return 0;
	}

	/* 解锁 */
	map[y][x] = '.';

	return -5;
}

/*
 * params y, x - 当前坐标
 * params deepth - (y, x)在当前路径上的序号,从0开始
 * return - 0: 搜索成功, 1: 搜索继续
 */
int dfs(int deepth, int y, int x)
{
	int d, ny, nx;

	/* 约束 */
	/* 越界 */
	if (y >= height || y < 0 || x >= width || x < 0)
		return -1;
	/* 碰壁 */
	if (map[y][x] == 'X')
		return -2;
	/* 上锁 */
	if (map[y][x] == 'O')
		return -3;

	//printf("dfs: %d, %d: +%2d\n", y, x, deepth);

	/* 剪枝 */
	if (deepth > time)
		return -4;

	/* 成功 */
	if (y == end_y && x == end_x && time == deepth)
		return 0;
	
	/* 加锁 */
	map[y][x] = 'O';

	for (d = 0; d < 4; ++d) {
		ny = y + direction[d];
		nx = x + direction[d + 4];
		if (dfs(deepth + 1, ny, nx) == 0)
			return 0;
	}

	/* 解锁 */
	map[y][x] = '.';

	return -5;
}
	
#define ABS(x) ((x)>0?(x):(-x))
int main(void)
{
	int y, x;
	int delta;

	//setbuf(stdout, NULL);
	//freopen("Inputs/1010", "r", stdin);

	while (scanf("%d%d%d", &height, &width, &time) == 3) {
		if (height == 0 && width == 0 && time == 0)
			break;
		scanf("%*c");
		for (y = 0; y < height; ++y) {
			for (x = 0; x < width; ++x) {
				scanf("%c", &map[y][x]);
				if (map[y][x] == 'S') {
					start_y = y;
					start_x = x;
				}
				if (map[y][x] == 'D') {
					end_y = y;
					end_x = x;
				}
			}
			scanf("%*c");
		}
		//printmap();
		delta = ABS(end_y - start_y) + ABS(end_x - start_x);
		if ((delta & 1UL) != (time & 1UL))
			printf("NO\n");
		else
			printf("%s\n", dfs_2(0, start_y, start_x) == 0 ? "YES" : "NO");
	}

	return 0;
}
