/*
 * 1181 变形课
 *
 * 深度优先搜索
 */

#include <assert.h>
#include <ctype.h>
#include <limits.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char map[26][26], block[26];

/*
 * 深度优先搜索
 *
 * @return - 当前字母到最终字母的长度
 * INT_MAX: 不可达
 *
 * @param deepth - 当前深度
 *
 * @param current - 当前字母
 *
 */
int dfs(int deepth, char current)
{
	int best = INT_MAX;

	int len, i, ret;

	//printf("dfs: %d %c\n", deepth, current + 97);
	//sleep(1);

	if (current == 'm' - 97)
		return 0;
	if (block[current])
		return INT_MAX;

	block[current] = 1;
	//printf("block %c\n", current + 97);
	//sleep(1);
	for (i = 0; i < 26; ++i) {
		if (map[current][i] == 1 && !block[i]) {
			len = dfs(deepth + 1, i);
			if (len < best)
				best = len;
		}
	}
	block[current] = 0;

	ret = best + 1;
	//printf("return %c->%d\n", current + 97, ret);
	//sleep(1);
	return ret < best ? best : ret;
}
	

int main(void)
{
	char last = -1, first = -1;

	char ch;

	freopen("Inputs/1181", "r", stdin);
	setbuf(stdout, NULL);

	memset(map, 0, sizeof(map));
	memset(block, 0, sizeof(block));

	while ((ch = getchar()) != EOF) {
		if (ch == '0') {
			printf("%s.\n", dfs(0,1) == INT_MAX ? "No" : "Yes");
			memset(map, 0, sizeof(map));
			memset(block, 0, sizeof(block));
			last = first = -1;
		} else if (ch == '\n' && last != -1 && first != -1) {
			//printf("map[%c][%c] = 1\n", first + 97, last + 97);
			map[first][last] = 1;
			first = last = -1;
		} else if (islower(ch)) {
			last = ch - 97;
			if (first == -1)
				first = last;
		}
	}

	return 0;
}
	
