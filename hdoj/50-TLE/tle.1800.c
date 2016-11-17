/*
 * 1800 Flying to the Mars
 */

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "Source/heap.c"

int cmp(void *x, void *y)
{
	int res;

	res = strlen(x) - strlen(y);
	if (!res)
		res = strcmp((char *)x, (char *)y);

	return res;
}

int main(void)
{
	int ncas, cas;
	int i, j;
	int bss, ch;
	char *level, *current;

	struct heap *h = heap_new(3000);
	h->cmp = cmp;

	freopen("Inputs/1800", "r", stdin);
	setbuf(stdout, NULL);

	while (scanf("%d%*c", &ncas) != EOF) {
		/* 开始一个测试用例 */
		h->last = 0;
		for (cas = 0; cas < ncas; ++cas) {
			/* 接收一个数字 */
			level = malloc(30);
			i = 0;
			while ((ch = getchar()) != (int)'\n') {
				if (ch == '0' && !i)
					continue;
				//printf("ch = %d\n", ch);
				level[i++] = (char)ch;
			}
			if (!i)
				level[i++] = '0';
			level[i] = 0;
			//printf("new number:%s\n", level);
			heap_insert(level, h);
		}

		/* heap sort */
		while ((level = heap_del(h)) != NULL)
			h->cell[h->last + 1] = level;

		bss = 0;
		for (i = 1; i <= ncas; ++i) {
			/* 找个头 */
			current = h->cell[i];
			if (!current)
				continue;

			++bss;
			//printf("%s ", current);

			for (j = i + 1; j <= ncas; ++j) {
				/* 顺着头向下走 */
				level = h->cell[j];
				if (!level || cmp(level, current) == 0)
					continue;
				h->cell[j] = NULL;
				free(current);
				current = level;
				//printf("%s ", current);
			}
		
			//printf("\n");

			free(current);
		}

		printf("%d\n", bss);
	}

	return 0;
}

