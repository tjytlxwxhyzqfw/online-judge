/*
 * 711E
 */

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

//TODO: remember: 1UL is 32-bit
#define ULL1 ((unsigned long long )1)
#define numth_bit(b,x) (b & (ULL1 << (x)))

long long numth_modular_exponentiation(long long a, long long b, long long n)
{
	long long square = 1;
	int i;

	assert(b >= 0 && n > 0);

	for (i = 63; i >= 0 && numth_bit(b, i) == 0; --i);
	for (; i >= 0; --i) {
		square = (square * square) % n;
		if (numth_bit(b, i))
			square = (square * a) % n;
	}

	return square;
}

#include "debug.c"

#define MAGIC 1000003
#define correct(p) (*(p) = (*(p) < 0 ? *(p) + MAGIC : *(p)))
#define me(a, b) (numth_modular_exponentiation((a), (b), MAGIC))

void compute1(long long k, long long *x, long long *y)
{
	long long pow = 1;
	long long i;

	assert(k > 0);

	for (i = 0; i < 64 && k % pow == 0; ++i)
		pow *= 2;

	*x = i - 1;
	*y = k / (pow / 2);
}
	

long long modular_a2(long long n, long long k, long long *exp)
{
	long long i, x, m;
	long long left, right, term, result;

	assert(k >= 2);

	*exp  = n;
	printis(0, 0, "*exp: %lld\n", *exp);
	for (m = k - 1; m > 0; *exp += (m /= 2))
		printis(1, 0, "m: %lld\n", m);
	assert(exp > 0);

	/* TODO: remeber!!!
 	 * if k - 1 >= MAGIC, then (2^n - 1)(2^n - 2)...(2^n - k + 1) consists of at least MAGIC
 	 * numbers, amoung these numbers there must be a multipile of MAGIC, so we just return 0
 	 */
	if (k - 1 >= MAGIC)
		return 0;

	result = 1;
	for (i = 1; i < k; ++i) {
		compute1(i, &x, &right);
		left = me(2, n - x); 
		right %= MAGIC;
		term = left - right;
		correct(&term);

		result = (result * term) % MAGIC;
	}

	printis(0, 0, "result: %lld\n", result);

	return result;
}

long long modular_pow2(long long n, long long k, long long m)
{
	long long r1, r2, r3, r4;
	long long i;

	assert(k >= 2);

	//FIXME: very subtile, easily lead to TLE
	if (n == 1) {
		assert(k == 2 && m == 1);
		r2 = 2;
		r3 = 1;
	} else if (n >= k) {
		r1 = me(2, n);
		r2 = me(r1, k - 2);
		r3 = me(2, 2 * n - m);
	} else {
		r1 = me(2, k);
		r2 = me(r1, n - 2);
		r3 = me(2, 2 * k - m);
	}

	r4 = (r2 * r3) % MAGIC;

	return r4;
}

int main(void)
{
	long long n, k;
	long long top, bottom, exp;

	scanf("%lld%lld", &n, &k);
	if (n < 63 && (ULL1 << n) < k)
		top = bottom  = 1;
	else {
		top = modular_a2(n, k, &exp);
		bottom = modular_pow2(n, k, exp);
		top = bottom - top;
		correct(&top);
	}

	printf("%lld %lld\n", top, bottom);
}
