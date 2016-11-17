/*
 * 1847 Good Luck in CET-4 Everybody !
 *
 * 估计可以直接交
 *
 * DAG分析报告:
 * 节点ID: 剩余牌数: n
 * 节点数上界: 1001
 * 状态转移: current => -2^x => next
 * 出度上界: 10 
 *
 * 这个分析能帮你熟悉问题,
 * 但是编码的时候,里面的数据要慎重使用.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define ODMAX 10

int cache[1001];

int sg(const int n)
{
	int i, sgv;
	char *sgmap;

	if (cache[n] != -1)
		return cache[n];

	if (n == 0)
		return 0;

	sgmap = malloc(ODMAX);
	memset(sgmap, 0, ODMAX);

	for (i = 1; i <= n; i <<= 1) {
		sgv = sg(n - i);
		if (sgv < ODMAX)
			sgmap[sgv] = 1;
	}

	for (i = 0; i < ODMAX; ++i)
		if (sgmap[i] == 0)
			goto exit;

	exit:
	free(sgmap);
	cache[n] = i;
	return i;
}
		

int main(void)
{
	int n, sgvh, i;

	for (i = 0; i < 1001; ++i)
		cache[i] = -1;

	while (scanf("%d", &n) == 1) {
		sgvh = sg(n);
		printf("%s\n", sgvh ? "Kiki" : "Cici");
	}

	return 0;
}
		
