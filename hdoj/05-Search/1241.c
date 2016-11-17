/*
 * 1241 Oil Deposits
 * 
 * 算法:深度优先搜索
 *
 *
 * 不能用BFS,仔细体会.
 *
 * 对DFS的理解还是不够深刻.比较简单,但是我思考太慢,思维成问题!
 *
 */

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char map[100][100];
int height, width;

char dir[] = {0,1,0,-1,1,1,-1,-1,1,0,-1,0,1,-1,1,-1};

void map_print(char *title)
{
	int top, left;
	
	puts(title);
	for (top = 0; top < height; ++top) {
		for (left = 0; left < width; ++left)
			printf("%c", map[top][left]);
		printf("\n");
	}

	sleep(1);
}

void dfs(int top, int left)
{
	int d, nl, nt;

	//printf("dfs:(%d,%d)\n", top, left);

	if (left < 0 || left >= width || top < 0 || top >= height)
		return;

	//printf("dfs:%c\n", map[top][left]);

	if (map[top][left] != '@')
		return;
		
	map_print("current_map:");
	printf("pockets:(%d,%d)\n", top, left);
	sleep(1);

	map[top][left] = '$';
	for (d = 0; d < 8; ++d) {
		nt = top + dir[d];
		nl = left + dir[d + 8];
		dfs(nt, nl);
	}
}

int main(void)
{
	int top, left, ndps;

	freopen("Inputs/1241", "r", stdin);
	setbuf(stdout, NULL);

	while (scanf("%d%d%*c", &height, &width) != EOF && height) {
		ndps = 0;
		for (top = 0; top < height; ++top) {
			for (left = 0; left < width; ++left)
				scanf("%c", &map[top][left]);
			scanf("%*c");
		}

		for (top = 0; top < height; ++top) {
			for (left = 0; left < width; ++left) {
				if (map[top][left] == '@') {
					++ndps;
					dfs(top, left);
				}
			}
		}

		printf("result:%d\n", ndps);
	}
	
	return 0;
}
