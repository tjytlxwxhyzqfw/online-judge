/*
 * 691F
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define printnint(s, n, i) for((i) = 0; (i) < (n); (i) += 1) printf("%d%s", (s)[(i)], (i) == ((n) - 1) ? "\n" : ", ")
#define print2int(a,b,sa,sb) printf("%s: %4d, %s: %4d\n", (sa), (a), (sb), (b))

#include "common.h"

#define V 3000000
#define N 1000000

int ac[V], an, amx, a, b;
int qs[N], qn, qmx, q;
//FIXME: int s[v];
long long s[V];

int main(void)
{
	int i;
	long long x;

	freopen("Inputs/691F", "r", stdin);
	setbuf(stdout, NULL);

	memset(ac, 0, sizeof(ac));
	memset(qs, 0, sizeof(qs));
	memset(s, 0, sizeof(s));

	scanf("%d", &an);
	amx = INT_MIN;
	forn(i, an) {
		scanf("%d", &a);
		++ac[a];
		if (a > amx)
			amx = a;
	}

	scanf("%d", &qn);
	qmx = INT_MIN;
	forn(i, qn) {
		scanf("%d", qs + i);
		if (qs[i] > qmx)
			qmx = qs[i];
	}

	forre(a, 1, amx) {
		if (ac[a] == 0)
			continue;
		for (b = a; b < qmx; b += a) {
			s[b] += LL(1) * ac[a] * ac[b / a];
			//FIXME: s[b] -= a; !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			if (a == b / a)
				s[b] -= LL(1) * ac[a];
		}
	}

	x = 0;
	forre(q, 1, qmx) {
		SWP(s[q], x);
		s[q] += s[q - 1];
	}

	forn(i, qn)
		printf("%lld\n", LL(1) * an * (an - 1) - s[qs[i]]);

	return 0;
}
