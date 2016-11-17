/*
 * 703E
 */
#include <assert.h>
#include <limits.h>
#include <stdio.h>
#include <string.h>

#include "common.h"
#include "math/numth.c"

#define P(x) ((x) == LLONG_MAX ? ((long long)(-1)) : (x))

struct node {
	long long val;
	long long id;
};

#define NA 1000
#define NDIVS 7000
#define E6 1000000
long long na, k;
struct node a[NA];

long long ndivs, divs[NDIVS], divisors[E6 + 1];
#define for_each_didx(i) for ((i) = 0; (i) < ndivs; (i) += 1)
long long dp[NA][NDIVS], sum[NA][NDIVS], gcdk[NA][NDIVS];
char use[NA][NDIVS];

void filldivisors(void)
{
	long long i;

	for (i = (ndivs - 1) / 2; i >= 0; --i)
		divisors[divs[i]] = i;
}

void fillgcdk(void)
{
	long long i, j;

	forn(i, na)
		forn(j, ndivs)
			gcdk[i][j] = numth_gcd(a[i].val, k / divs[j]) * divs[j];
}

long long didx(long long d)
{
	long long middle, partner_idx;

	middle = (ndivs - 1) / 2;
	if (d <= divs[middle])
		return divisors[d];
	partner_idx = divisors[k / d];
	return ndivs - 1 - partner_idx;
} 

void dp_fill_last(long long i, long long j)
{
	long long dp1, dp2, sum1, sum2;
	long long nextd, currd;

	currd = divs[j];
	nextd = gcdk[i][j];

	dp1 = (nextd == k ? 1 : LLONG_MAX);
	sum1 = (nextd == k ? a[i].val : LLONG_MAX);

	dp2 = (currd == k ? 0 : LLONG_MAX);
	sum2 = (currd == k ? 0 : LLONG_MAX);

	if (dp1 == dp2) {
		if (sum1 == sum2) {
			dp[i][j] = dp1;
			sum[i][j] = sum1;
			use[i][j] = 0;
		} else if (sum1 < sum2) {
			dp[i][j] = dp1;
			sum[i][j] = sum1;
			use[i][j] = 1;
		} else {//sum1 > sum2
			dp[i][j] = dp2;
			sum[i][j] = sum2;
			use[i][j] = 0;
		}
	} else if (dp1 < dp2) {
		dp[i][j] = dp1;
		sum[i][j] = sum1;
		use[i][j] = 1;
	} else {//dp1 > dp2
		dp[i][j] = dp2;
		sum[i][j] = sum2;
		use[i][j] = 0;
	}

	//printf("last: (%3lld, %3lld): %3lld, sum: %3lld, use: %d\n", i, j, dp[i][j], sum[i][j], use[i][j]);
}

void dp_fill(long long i, long long j)
{
	long long dp1, dp2, sum1, sum2;
	long long idx, nextd;

	nextd = gcdk[i][j];
	idx = didx(nextd);

	dp1 = 1 + dp[i + 1][idx];
	LLOF(dp1);
	sum1 = a[i].val + sum[i + 1][idx];
	LLOF(sum1);

	dp2 = dp[i + 1][j];
	sum2 = sum[i + 1][j];

	if (dp1 == dp2) {
		dp[i][j] = (sum1 < sum2 ? dp1 : dp2);
		sum[i][j] = (sum1 < sum2 ? sum1 : sum2);
		use[i][j] = (sum1 < sum2 ? 1 : 0);
	} else {
		dp[i][j] = (dp1 < dp2 ? dp1 : dp2);
		sum[i][j] = (dp1 < dp2 ? sum1 : sum2);
		use[i][j] = (dp1 < dp2 ? 1 : 0);
	}

	//printis(0, 0, "(%3lld, %3lld): %3lld, sum: %3lld, use: %d\n", i, j, dp[i][j], sum[i][j], use[i][j]);
}

void dp_fill_all(void);
void print_path(long long start);

int main(void)
{
	long long i;

	freopen("Inputs/703E", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%lld%lld", &na, &k);
	forn(i, na) {
		scanf("%lld", &a[i].val);
		a[i].id = i;
	}

	if (k == 1) {
		forn(i, na) {
			if (a[i].val < a[0].val) {
				a[0].val = a[i].val;
				a[0].id = a[i].id;
			}
		}
		printf("%lld\n%lld\n", LL(1), a[0].id + 1);
		return 0;
	}

	numth_divisors(k, divs, &ndivs);
	fillgcdk();
	filldivisors();
	dp_fill_all();

	forn(i, na)
		if (use[i][0] == 1)
			break;
	if (i < na) {
		printf("%lld\n", dp[i][0]);
		print_path(i);
	} else {
		printf("%lld\n", LL(-1));
	}

	return 0;
}

void print_path(long long start)
{
	long long i, j;

	j = 0;
	forr(i, start, na) {
		if (use[i][j]) {
			printf("%lld ", i + 1);
			j = didx(gcdk[i][j]);
		}
	}
	printf("\n");
}

void dp_fill_all(void)
{
	long long i, j;

	forn(j, ndivs)
		dp_fill_last(na - 1, j);

	for (i = na - 2; i >= 0; --i)
		forn(j, ndivs)
			dp_fill(i, j);
}
