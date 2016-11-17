/*
 * 682A
 */

#include <stdio.h>
#include <stdlib.h>

#include "common.h"

int main(void)
{
	long long left, right, i, nfives;
	scanf("%lld%lld", &left, &right);
	nfives = 0;
	forre(i, 1, left)
		nfives += (right + i) / 5 - i / 5;
	printf("%lld\n", nfives);
	return 0;
}
