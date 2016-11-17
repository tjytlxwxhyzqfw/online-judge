/*
 * 710C
 */

#include <stdio.h>

int map[49][49];
int num;

int cy = 24, cx = 24;

void round2(int m)
{
	int n, x, y;
	int ul_x, ul_y, ur_x, ur_y, dr_x, dr_y, dl_x, dl_y;
	int current;

	if (m == 1) {
		map[cy][cx] = 1;
		return;
	}

	n = (m - 1) / 2;
	current = (2 * n - 1) * (2 * n - 1) + 1;
	ul_y = cy - n, ul_x = cx - n;
	ur_y = cy - n, ur_x = cx + n;
	dr_y = cy + n, dr_x = cx + n;
	dl_y = cy + n, dl_x = cx - n;

	//printf("current: %d, (%d, %d), (%d, %d), (%d, %d), (%d, %d)\n",
		//current, ul_x, ul_y, ur_x, ur_y, dr_x, dr_y, dl_x, dl_y);

	map[ul_y][ul_x] = (n & 1UL ? current++ : m * m);

	y = ul_y;
	x = ul_x + 1;
	//printf("(%d, %d) -> (%d, %d)\n", y, x, y, ur_x);
	while (x <= ur_x)
		map[y][x++] = current++;

	y = ur_y + 1;
	x = ur_x;
	//printf("(%d, %d) -> (%d, %d)\n", y, x, dr_y, x);
	while (y <= dr_y)
		map[y++][x] = current++;

	y = dr_y;
	x = dr_x - 1;
	//printf("(%d, %d) -> (%d, %d)\n", y, x, y, dl_x);
	while (x >= dl_x)
		map[y][x--] = current++;

	y = dl_y - 1;
	x = dl_x;
	//printf("(%d, %d) -> (%d, %d)\n", y, x, ul_y, x);
	while (y > ul_y)
		map[y--][x] = current++;

}

void print(void)
{
	int i, j, n;

	n = (num - 1) / 2;

	for (i = cy - n; i <= cy + n ; ++i) {
		for (j = cy - n; j <= cy + n; ++j) {
			printf("%4d ", map[i][j]);
		}
		printf("\n");
	}
}
	

int main(void)
{
	int i, j;

	scanf("%d", &num);
	for (i = 1; i <= num; i += 2)
		round2(i);

	print();

	return 0;
}
