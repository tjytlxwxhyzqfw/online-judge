/*
 * 2188 选拔志愿者
 *
 * m-nim
 *
 * Just submit
 */

#include <stdio.h>

int main(void)
{
	int n, m;
	int ncases;

	scanf("%d", &ncases);
	while (--ncases >= 0) {
		scanf("%d%d", &n, &m);
		printf("%s\n", n%(m+1) ? "Grass" : "Rabbit");
	}

	return 0;
}

