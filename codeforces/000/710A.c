/*
 * 710A
 */

#include <stdio.h>

char dir[] = {-1, 1, 0, 0, -1, 1, 1, -1, 0, 0, -1, 1, -1, -1, 1, 1};

int main(void)
{
	char ch;
	int x, y, nx, ny;
	int nmoves;
	int i;

	scanf("%c%d", &ch, &y);
	x = (int)ch;
	nmoves = 0;
	for (i = 0; i < 8; ++i) {
		nx = x + dir[i];
		ny = y + dir[i + 8];
		//printf("%d,%d\n", nx, ny);
		if (nx >= 'a' && nx <= 'h' && ny >= 1 && ny <= 8)
			++nmoves;
	}
	printf("%d", nmoves);

	return 0;
}
