/*
 * 710E
 *
 * Anser of this problem could be regarded as a path consisting of +,-,x.
 *
 * Then, could a '+' and a '-' be adjecent to each other ? No.
 *
 * Consider a path like this: '???+-???', then we can eliminite '+-' from the path
 * with no side affect but making it cost less.
 *
 * Furthermore, could a path be like this: '???--???' ? No.
 *
 * Firstly, as '+' and '-' are not adjecent, '?' before '--' must be a 'x', therefore
 * the path is '??x--???'. Secondly, note that 'x--' can be replaced with '-x', the path
 * then becomes '??-x-???'. Finally, we can repeatly use '+-' rule and '--x' rule until
 * the path has no '+-'/'-+' and '--';
 *
 * Consequencely, if you got a '-' then your next step must be 'x'.
 *
 */

#include <limits.h>
#include <stdio.h>
#include <stdlib.h>

#include "debug.c"

#define LEN 10000001

long long len;
#define OOS(x) ((x) < 0 || (x) > len)
long long x, y;
long long nodes[LEN][2];

void dp(void)
{
	/*
	 * You can go to next by plus, mutiply, and minus-multiply.
	 */
	long long plus_to_next, mul_to_next, submul_to_next;
	int i;

	nodes[len][0] = nodes[len][1] = 0;
	for (i = len - 1; i >= 0; --i) {
		plus_to_next = x + nodes[i + 1][0];

		//XXX: Big Bug Here!!!
		if (i == 0)
			mul_to_next = LLONG_MAX;
		else if (2 * i > len)
			mul_to_next = y + (2 * i - len) * x;
		else
			mul_to_next = y + nodes[2 * i][1];

		if (i == 0 || i == 1 || i == 2)
			submul_to_next = LLONG_MAX;
		else if (2 * i - 2 > len)
			submul_to_next = x + y + (2 * i - 2 - len) * x;
		else
			submul_to_next = x + y + nodes[2 * i - 2][1];

		//from plus, to: plus, copy
		nodes[i][0] = (plus_to_next < mul_to_next ? plus_to_next : mul_to_next);
		//from copy, to plus, copy, minus-copy
		nodes[i][1] = (nodes[i][0] < submul_to_next ? nodes[i][0] : submul_to_next);

		//printis(0, 0, "%3d: 0: %3d, 1: %3d\n", i, nodes[i][0], nodes[i][1]);
	}
}

int main(void)
{
	long long i;

	setbuf(stdout, NULL);

	scanf("%lld%lld%lld", &len, &x, &y);
	dp();
	printf("%lld\n", nodes[0][0]);

	return 0;
}
