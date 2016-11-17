/**
 * @author wcc
 */

#ifndef _WCC_NUMBER_H
#define _WCC_NUMBER_H

namespace number {
	inline long long correct(long long value, long long mod);

	long long quickpow(long long x, long long n, long long mod);
	long long gcd(long long a, long long b, long long &x, long long &y);
	long long gcd(long long a, long long b);
	/*TODO: implement */
	long long mle(void);

	long long perm(long long n, long long k, long long mod);
	long long fact(long long n, long long mod);
	long long comb(long long n, long long m, long long mod);
	/*TODO: implement */
	long long fib(long long n, long long mod);
	/*TODO: implement */
	long long stl1(long long p, long long k, long long mod);
	/*TODO: implement */
	long long stl2(long long p, long long k, long long mod);
};

class llong {
private:
	long long value, module;
public:
	llong(void);
	llong(const long long value, const long long module);

	long long toll(void) const;
	long long mod(void) const;
	long long pow(long long n) const;
	long long inv(void) const;
	/*TODO: implement */
	long long inverse(void) const;

	operator long long () const;

	llong operator=(long long);
	llong operator+(long long) const;
	llong operator-(long long) const;
	llong operator*(long long) const;
	llong operator+=(long long);
	llong operator-=(long long);
	llong operator*=(long long);
	

	llong operator=(const llong &);
	llong operator+(const llong &) const;
	llong operator-(const llong &) const; 
	llong operator*(const llong &) const;
	llong operator+=(const llong &);
	llong operator-=(const llong &);
	llong operator*=(const llong &);
};

#endif
