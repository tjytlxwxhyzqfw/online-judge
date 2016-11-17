/*
 * N皇后问题
 * 枚举,数据量不大, 10
 * 打表可过
 * 不打表,加缓存可过
 * 不打表,不加缓存过不了,TLE
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define DIM 10 

int map[DIM][DIM];
int dim, nplace;

void set(int v, int y, int x)
{
	if (y >= 0 && x >= 0 && y < dim && x < dim && !map[y][x])
		map[y][x] = v;
}

void clear(int v, int y, int x)
{	if (y >= 0 && x >= 0 && y < dim && x < dim && map[y][x] == v)
		map[y][x] = 0;
}

void tag(int v, int y, int x, int s)
{
	int i, ny, nx;

	/*
	if (s)
		printf("block:(%d,%d)\n", y, x);
	else
		printf("unblk:(%d,%d)\n", y, x);
	*/

	//getchar();
	
	for (i = 0; i < dim; ++i) {
	
		if (!s) {
			clear(v, y, i);
			clear(v, i, x);
			clear(v, y + i, x + i);
			clear(v, y - i, x - i);
			clear(v, y + i, x - i);
			clear(v, y - i, x + i);
		} else {
			set(v, y, i);
			set(v, i, x);
			set(v, y - i, x - i);
			set(v, y - i, x + i);
			set(v, y + i, x - i);
			set(v, y + i, x + i);
		}
	}
}

void dfs(int deepth)
{
	int y, x;

	if (dim < deepth) {
		++nplace;
		//printf("%d return, nplace=%d\n", dim, nplace);
		//getchar();
		return;
	}

	y = deepth - 1;
	for (x = 0; x < dim; ++x) {
		if (map[y][x])
			continue;
		tag(deepth, y, x, 1);
		dfs(deepth + 1);
		tag(deepth, y, x, 0);
	}
}
			

int main(void)
{
	int s[11];
	for (dim = 0; dim < 100; ++dim)
		s[dim] = -1;
	while (scanf("%d", &dim) != EOF) {
		if (dim == 0)
			break;
		if (s[dim] == -1) {
			nplace = 0;
			memset(map, 0, sizeof(int) * DIM * DIM);
			dfs(1);
			s[dim] = nplace;
		}
		printf("%d\n", s[dim]);
	}

	return 0;
}

