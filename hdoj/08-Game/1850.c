/*
 * 1850 Being a Good Boy in Spring Festival
 *
 * Just submit
 * 
 * --
 * Maybe tle
 * --
 * Now, it will be ok
 * --
 * 
 */

#include <stdio.h>

//#include "debug.c"

int nheaps, heaps[100];
int xorvalue;

int init_a_case(void)
{
	int i;

	scanf("%d", &nheaps);
	if (nheaps == 0)
		return 0;

	xorvalue = 0;
	for (i = 0; i < nheaps; ++i) {
		scanf("%d", heaps + i);
		xorvalue ^= heaps[i];
	}

	return 1;
}

int get_wins(const int h)
{
	/*
	 * 就这个DAG图而言,有个很关键的性质,
	 * 它的DAG的顶点和SG值一一对应.
	 * 因此,给定一个SG值,这个DAG最多只有一个顶点拥有这个值,
	 * 即,这个堆最多只有一个状态满足要求
	 */

	int others;

	others = xorvalue ^ heaps[h];

	return heaps[h] > others ? 1 : 0;
}
	

int main(void)
{
	int i, result;

	while (init_a_case()) {
		result = 0;
		if (xorvalue)
			for (i = 0; i < nheaps; ++i)
				result += get_wins(i);
		printf("%d\n", result);
	}

	return 0;
}
