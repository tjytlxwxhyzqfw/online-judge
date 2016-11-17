/*
 * 2563 统计问题
 *
 * S[n] 走n步
 * U[n] 向上走n步
 * R[n] 向右走n步
 *
 * S[n] = U[n] + 2 * R[n]
 * U[n] = U[n-1] + 2 * R[n-1];
 * R[n] = R[n-1] + U[n-1]
 */

#include <stdio.h>

int s[21], u[21], r[21];

int main(void)
{
	int n, ncas, i;

	u[1] = 1;
	r[1] = 1;
	s[1] = 3;

	for (i = 2; i <= 20; ++i) {
		u[i] = u[i - 1] + 2 * r[i - 1];
		r[i] = u[i - 1] + r[i - 1];
		s[i] = u[i] + 2 * r[i];
	}

	scanf("%d", &ncas);
	while (ncas--) {
		scanf("%d", &n);
		printf("%d\n", s[n]);
	}

	return 0;
}
