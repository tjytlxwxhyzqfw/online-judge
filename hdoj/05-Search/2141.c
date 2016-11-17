/*
 * 2141 Can you find it ?
 *
 * 枚举->搜索
 * 举个例子笔划一下就出来了
 */

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

#include "Source/heap.c"

unsigned int *new_int(unsigned int n)
{
	unsigned int *x;

	assert(x = malloc(sizeof(unsigned int)));
	*x = n;

	return x;
}

int ncands[3];
struct heap *hp[3];

int cmp(void *x, void *y)
{
	unsigned int m, n;
	
	m = *((unsigned int *)x);
	n = *((unsigned int *)y);

	if (m == n)
		return 0;
	if (m < n)
		return 1;
	if (m > n)
		return -1;
}

/*
 * 在深度为deepth的层上,以cv为初始值,以dv为目标值,
 * 返回最接近dv的值;
 *
 * 二分搜索:
 * 1) 根据搜索范围确定位置
 * 2) 获取这个位置的值
 * 3) 将这个位置的值与目标值比较
 * 4) 根据比较结果调整范围
 * 5) 回到1
 *
 * 这个二分搜索将(2)一般化了:
 * 	虽然获取到了当前位置x的值y = s[x],
 * 	但y并不能直接与目标值比较,
 * 	y需要做进一步处理.
 *
 * 	处理过程刚好就是自身.
 */
int search(int deepth, unsigned int cv, unsigned int dv)
{
	int i;
	int start, end, x;
	unsigned int val, bestval;

	start = 1;
	end = ncands[deepth];

	//for (i = 0; i < deepth; ++i)
		//printf("\t");

	//printf("deepth=%d, cv=%d, dv=%d\n", deepth, cv, dv);

	while (start <= end) {
		x = (start + end) / 2;
		val = cv + *((unsigned int*)(hp[deepth]->cell[x]));
		//for (i = 0; i < deepth; ++i)
			//printf("\t");
		//printf("x = %d, val = %d\n", x, val);
		if (deepth == 2)
			bestval = val;
		else
			bestval = search(deepth + 1, val, dv); 
		if (bestval < dv)
			start = x + 1;
		else if (bestval > dv)
			end = x - 1;
		else
			break;
	}

	return bestval;
}

int main(void)
{
	int casidx = 1;

	int i, j, ncas;
	unsigned int n, destval, bestval, *np;
	struct heap *h;

	freopen("Inputs/2141", "r", stdin);
	setbuf(stdout, NULL);

	for (i = 0; i < 3; ++i) {
		hp[i] = heap_new(500);
		hp[i]->cmp = cmp;
	}

	while (scanf("%d%d%d", ncands, ncands + 1, ncands + 2) == 3) {
		printf("Case %d:\n", casidx++);

		for (i = 0; i < 3; ++i) {
			h = hp[i];
			for (j = 0; j < ncands[i]; ++j) {
				scanf("%d", &n);
				heap_insert(new_int(n), h);
			}
		}
		
		/* heap sort */
		for (i = 0; i < 3; ++i) {
			h = hp[i];
			while ((np = heap_del(h)) != NULL)
				h->cell[h->last + 1] = np;
		}

		scanf("%d", &ncas);
		while (ncas--) {
			scanf("%d", &destval);
			bestval = search(0, 0, destval);
			printf("%s\n", bestval == destval ? "YES" : "NO");
		}

		for (i = 0; i < 3; ++i) {
			h = hp[i];
			for (j = 1; j <= ncands[i]; ++j)
				free(h->cell[j]);
			h->last = 0;
		}
	}

	return 0;
}
