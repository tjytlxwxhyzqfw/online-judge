/**
 * moduler
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"

using namespace std;

static long long do_gcd(const long long, const long long, long long *, long long *);

inline long long gcd(const long long left, const long long rght, long long *x, long long *y) {
	long long a = left, b = rght;
	if (a < 0)
		a *= -1;
	if (b < 0)
		b *= -1;
	if (a == 0 && b == 0)
		return *x = *y = 0;
	if (a < b) {
		std::swap(a, b);
		std::swap(x, y);
	}
	long long d = do_gcd(a, b, x, y);
	return d;
}

inline long long gcd(const long long a, const long long b) {
	long long foo;
	return gcd(a, b, &foo, &foo);
}

template <long long mod> struct moduler {
	inline static long long crc(const long long x) {
		if (x >= 0)
			return x % mod;
		return ((-1*x/mod+1)*mod+x) % mod;
	}

	inline static long long pls(const long long x, const long long y) {
		return ((x%mod)+(y%mod))%mod;
	}

	inline static long long mul(const long long x, const long long y) {
		return ((x%mod)*(y%mod))%mod;
	}

	static long long pow(const long long x, const long long y) {
		long long n = y;
		moduler square, ans;

		ans = 1;
		square = x;
		while (n != 0) {
			if ((n & (long long)1UL) == 1)
				ans = ans * square;
			square = square * square;
			n >>= 1;
		}

		return ans.ll();
	}

	/*
	 * Moduler Linear Equation Solver
	 * 	ax = b (% mod)
	 *
	 * Let n = mod.
 	 * By applying gcd() on (a, n) we get:
	 * 	ax + ny = d
	 * Since d is divisible by b:
	 * 	ax + ny = d = b/m
	 * 	m = b / d 
	 * Thus:
	 * 	amx + nmy = b
	 * Yet we get an solution:
	 * 	x0 = xb/d
	 * Furthermore:
	 * 	xi = x0+i(n/d) (mod n), i = 0, 1, ..., d-1
	 *
	 * Use following loop to for accessing all solutions:
	 *
	 * assert(x0 != -1);
	 * forn(i, d) {
	 * 	x = moduler<mod>::pls(x0+i*(n/d));
	 * 	...
	 * }
	 */
	inline static long long eqt(const long long a, const long long b, long long *d) {
		long long x, y;
		*d = gcd(a, mod, &x, &y);
		if (b % (*d) != 0) {
			printf("b: %lld, *d: %lld\n", b, *d);
			return -1; //no solution
		}
		return crc((x*b/(*d)));
	}

	long long num;

	inline const long long correct(void) {
		return num = crc(num);
	}

	inline const long long operator=(const long long x) {
		return num = crc(x);
	}

	inline const long long operator=(const moduler &x) {
		return num = x.num;
	}

	inline const long long operator+(const long long x) const {
		return pls(num, correct(x));
	}

	inline const long long operator+(const moduler &x) const {
		return pls(num, x.ll());
	}

	inline const long long operator*(const long long x) const {
		return mul(num, correct(x));
	}

	inline const long long operator*(const moduler &x) const {
		return mul(num, x.ll());
	}

	inline const long long ll(void) const {
		return num;
	}
};

const long long mod = 10;

int main(void) {
	moduler<mod> x, y;
	long long u, v;

	while (scanf("%lld%lld", &x.num, &y.num) == 2) {
		printf("gcd: %lld, ", gcd(x.num, y.num, &u, &v));
		printf("check: %lld\n", u*(abs(x.num))+v*abs(y.num));
		x.correct(), y.correct();
		printf("+: %lld (mod: %lld)\n", x + y, mod);
		printf("x: %lld (mod: %lld)\n", x * y, mod);
		printf("^: %lld (mod: %lld)\n", moduler<mod>::pow(x.num, y.num), mod);
		printf("e: %lld\n", moduler<mod>::eqt(x.num, y.num, &u));
		printf("m: %lld\n", moduler<mod>::eqt(x.num, 0, &u));
	}

	return 0;
}

long long do_gcd(const long long a, const long long b, long long *x, long long *y) {
	if (b == 0) {
		*x = 1;
		*y = 0;
		return a;
	}
	long long d = do_gcd(b, a%b, x, y);
	std::swap(*x, *y);
	*y -= a / b * (*x);

	return d;
}
