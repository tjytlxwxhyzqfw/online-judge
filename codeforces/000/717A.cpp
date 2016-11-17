/**
 * 716C
 */
#include <cstdio>

#include "common.h"
#include "number.h"

using namespace number;

#define M 1000000007

struct irr {
	long long mod;
	llong x, y;
	irr(): mod(M) {
		x = llong(0, M);
		y = llong(0, M);
	}
	irr(long long a, long long b, long long m) {
		mod = m;
		x = llong(a, mod);
		y = llong(b, mod);
	}
	irr operator+(const irr &rhs) const {
		return irr((x + rhs.x).toll(), (y + rhs.y).toll(), mod);
	}
	irr operator-(const irr &rhs) const {
		return irr((x - rhs.x).toll(), (y - rhs.y).toll(), mod);
	}
	irr operator*(const irr &rhs) const {
		llong a(10, mod), b(0, mod);
		a = x * rhs.x + LL(5) * y * rhs.y;
		b = x * rhs.y + y * rhs.x;
		return irr(a.toll(), b.toll(), mod);
	}
	irr operator/(const irr rhs) const {
		llong topx(0, mod), topy(0, mod), bot(0, mod);
		llong a(0, mod), b(0, mod);

		topx = x * rhs.x - LL(5) * y * rhs.y;
		topy = y * rhs.x - x * rhs.y;
		bot = rhs.x * rhs.x - rhs.y * rhs.y * LL(5);

		assert(gcd(bot.toll(), M) == 1);
		a = topx * bot.inv();
		b = topy * bot.inv();

		return irr(a.toll(), b.toll(), mod);
	}
	irr pow(long long n) {
		irr sq = *this, ans = irr(1, 0, mod);
		for (int i = 0; i < 64; ++i) {
			if (odd(n)) ans = ans * sq;
			sq = sq * sq;
			n >>= 1;
		}
		return ans;
	}
};

long long k, l, r;

irr compute_q(long long j, long long m)
{
	irr a(1, 1, M), b(-1, 1, M);
	irr ans, powa, powb;
	llong inverse;

	inverse = llong(llong(2, M).pow(j), M).inv();
	powa = a.pow(j-m);
	powb = b.pow(m);

	printf("compute_q: j: %lld, m: %lld\n", j, m);
	printf("\tinverse: %lld\n", inverse.toll());
	printf("\ta.pow(%lld): (%lld, %lld)\n", j-m, powa.x.toll(), powa.y.toll());
	printf("\tb.pow(%lld): (%lld, %lld)\n", m, powb.x.toll(), powb.y.toll());
	printf("\tans: (%lld, %lld)\n", ans.x.toll(), ans.y.toll());

	ans.x *= inverse;
	ans.y *= inverse;

	return ans;
}

llong cigmaq(long long j, long long m)
{
	irr q, first, top, bot, ans;

	q = compute_q(j, m);
	top = q.pow(r-l+1) - irr(1, 0, M);
	bot = q - irr(1, 0, M);
	first = q.pow(2+l);
	ans = first * top / bot / irr(0, 1, M).pow(j);

	printf("cigmaq: j: %lld, m: %lld\n", j, m);
	printf("\tq: (%lld, %lld)\n", q.x.toll(), q.y.toll());
	printf("\ttop: (%lld, %lld)\n", top.x.toll(), top.y.toll());
	printf("\tbot: (%lld, %lld)\n", bot.x.toll(), bot.y.toll());
	printf("\tfirst: (%lld, %lld)\n", first.x.toll(), first.y.toll());
	printf("\tans: (%lld, %lld)\n", ans.x.toll(), ans.y.toll());

	assert(ans.y.toll() == 0);

	return ans.x;
}

llong cigmaf(long long j)
{
	llong ans(0, M);
	long long m;

	fore(m, j) ans += sgn(m) * comb(j, m, M) * cigmaq(j, m);

	return ans;
}


int main(void)
{
	//scanf("%lld%lld%lld", &k, &l, &r);
	k = l = r = 1;
	printf("%lld\n", cigmaf(k).toll());

	return 0;
}

