/*
 * 1848 Fibonacci Again and Again
 *
 * 用例反正是过了,稍微测试一下估计就可以提交拉
 *
 * DAG报告:
 * 节点ID: 石子数目n
 * 最大节点数: 1000
 * 状态转移方式: -fi
 * 出度: x, x是第一个严格大于n的Fibonacci数的下标
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int fibonacci[20], nfs;
int h1, h2, h3;
int cache[1001];

int sg(const int n)
{
	int sgv;
	char *map;
	int i;

	if (cache[n] != -1)
		return cache[n];

	if (n == 0)
		return 0;

	map = malloc(nfs);
	memset(map, 0, nfs);
	for (i = 0; i < nfs && fibonacci[i] <= n; ++i) {
		sgv = sg(n - fibonacci[i]);
		if (sgv < nfs)
			map[sgv] = 1;
	}

	for (i = 0; i < nfs; ++i)
		if (map[i] == 0)
			goto exit;

	exit:
	free(map);
	cache[n] = i;
	return i;
}

void init_fibonacci(void)
{
	int i;

	fibonacci[0] = 1;
	fibonacci[1] = 2;
	nfs = 2;

	for (i = 2; i < 20; ++i) {
		fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
		++nfs;
		if (fibonacci[i] > 1000)
			break;
	}
}

int init_a_case(void)
{
	scanf("%d%d%d", &h1, &h2, &h3);
	if (!h1 && !h2 && !h3)
		return 0;
	return 1;
}

void init_cache(void)
{
	int i;

	for (i = 0; i < 1001; ++i)
		cache[i] = -1;
}

int main(void)
{
	int result;

	init_fibonacci();
	init_cache();

	while (init_a_case()) {
		result = sg(h1) ^ sg(h2) ^ sg(h3);
		printf("%s\n", result ? "Fibo" : "Nacci");
	}

	return 0;
}
