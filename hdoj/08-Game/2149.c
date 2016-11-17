/*
 * 2149 Public Sale
 *
 * Just submit
 *
 * M-Nim
 * DAG分析报告:
 * 节点: v: 剩余石子数
 * SG值可以直接求: v mod (M + 1) 
 * 推论: P值分布: 0, M+1, 2M+2, ..., iM+i
 */

#include <stdio.h>

int main(void)
{
	int n, m, sgv;
	int i;

	while (scanf("%d%d", &n, &m) == 2) {
		if (m >= n) {
			for (i = n; i <= m; ++i)
				printf("%d%s", i, i == m ? "\n" : " ");
			continue;
		}

		sgv = n % (m + 1);
		sgv ? printf("%d\n", sgv) : printf("none\n");
	}

	return 0;
}
