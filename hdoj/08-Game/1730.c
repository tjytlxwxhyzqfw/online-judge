/*
 * 1730 Northcott Game
 *
 * 直接提交就可以了;
 *
 * 首先,题目是很巧妙的,思路是有困难的,而我是不行的.
 *
 * 将两个棋子中间的空格视为尼姆堆.
 * 聪明人是不会主动拉大空格距离的:
 * 1.如果初始状态我必胜,那么我绝不会蠢到去拉大空格
 * 	(除非拉大到一个我必胜的状态,但是,何必呢?),
 * 	如果对手拉大空格,我只要跟上就可以了.
 * 2.如果初始状态我必败,那么我拉大空格是没用的,
 * 	因为对手只要跟上我就可以了.
 */

#include <stdio.h>

#define wabs(x) ((x)<0?(-(x)):(x))

int main(void)
{
	int height, width, black, white;
	int y, sgvh, result;

	while (scanf("%d%d", &height, &width) == 2) {
		result = 0;
		for (y = 0; y < height; ++y) {
			scanf("%d%d", &black, &white);
			sgvh = wabs(black - white) - 1;
			result ^= sgvh;
		}
		printf("%s!\n", result ? "I WIN" : "BAD LUCK");
	}

	return 0;
}
