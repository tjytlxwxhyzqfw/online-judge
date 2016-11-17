/*
 * 1037
 */

#include <stdio.h>

int main(void)
{
	int x, i;

	setbuf(stdout, NULL);
	setbuf(stdin, NULL);

	for (i = 0; i < 3; ++i) {
		scanf("%d", &x);
		if (x <= 168) {
			printf("CRASH %d\n", x);
			return 0;
		}
	}

	printf("NO CRASH\n");

	return 0;
}
