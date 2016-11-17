/*
 * 712E
 */

#include <assert.h>
#include <stdio.h>
#include <math.h>
#include "common.h"

#define N 100000

double p[N], q[N];

double correct(int a, int b, int n)
{
	double res = 0;
	int i;

	forre(i, a, b) res += pow(q[i], n);

	return res;
}

double dominate(int a, int b)
{
	double basic = 1.0, r = 0;
	int i;

	forre(i, a, b) basic *= p[i];
	forn(i, 10) r += correct(a+1, b, i+1);
	printf("basic: %f, r: %f\n", basic, r);
	basic *= 1 + r;

	return basic;
}


int main(void)
{
	int a, b, n, qr;
	int i, j, cmd;

	setbuf(stdout, NULL);
	freopen("Inputs/712E", "r", stdin);

	scanf("%d%d", &n, &qr);
	forn(i, n) {
		scanf("%d%d", &a, &b);
		p[i] = (double)a / b;
		q[i] = (i > 0 ? p[i-1] * (1 - p[i]) : 0);
	}
	forn(i, qr) {
		scanf("%d", &cmd);
		switch (cmd) {
			case 1:
				scanf("%d%d%d", &j, &a, &b);
				--j;
				p[j] = (double)a / b;
				q[j] = (j > 0 ? p[j-1]*(1-p[j]) : 0);
				break;
			case 2:
				scanf("%d%d", &a, &b);
				--a, --b;
				printf("%f\n", dominate(a, b));
				break;
			default:
				assert(0);
		}
	}

	return 0;
}
