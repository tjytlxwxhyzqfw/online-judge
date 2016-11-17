/*
 * 710D
 */

#include <stdio.h>

void egcd(long long greater, long long lesser, 
	long long *d, long long *x, long long *y)
{
	long long tx, ty;

	if (lesser == 0) {
		*d = greater;
		*x = 1;
		*y = 0;
		return;
	}

	egcd(lesser, greater % lesser, d, x, y);

	tx = *y;
	ty = *x - (greater / lesser) * (*y);
	*x = tx;
	*y = ty;
}
	

long long gcd(long greater, long lesser)
{
	if (greater % lesser == 0)
		return lesser;
	return gcd(lesser, greater % lesser);
}

long long left, right, a1, a2, b1, b2;

int main(void)
{
	long long delta, lefti, righti;
	long long d, c1, c2;
	double left0, right0, right1, right2, right3;

	scanf("%lld%lld%lld%lld%lld%lld", &a1, &b1, &a2, &b2, &left, &right);

	if (a2 >= a1)
		egcd(a2, a1, &d, &c2, &c1);	
	else
		egcd(a1, a2, &d, &c1, &c2);
	//printf("(a1: %lld, c1: %lld), (a2: %lld, c2: %lld), d: %lld\n", a1, c1, a2, c2, d);

	delta = b2 - b1;
	//printf("delta: %lld\n", b2 - b1);

	if (delta % d) {
		printf("0\n");
		return 0;
	}

	/* x >= 0 */
	right1 = 1.0 * delta * c1 / a2;
	//printf("right1: %f\n", right1);
	//printf("right1, right1 + 1: %f, %f\n", 1.0 / d * (delta * c1 - right1 * a2), 1.0 / d * (delta * c1 - (right1 + 1) * a2));

	/* y >= 0 */
	right2 = -1.0 * delta * c2 / a1;
	//printf("right2: %f\n", right2);
	//printf("right2, right2 + 1: %f, %f\n", -1.0 / d * (delta * c2 + right2 * a1), -1.0 / d * (delta * c2 + (right2 + 1) * a1));

	/* left <= value <= right */
	left0 = 1.0 * delta * c1 / a2 - 1.0 * d * (right - b1) / (1.0 * a1 * a2);
	right3 = 1.0 * delta * c1 / a2 - 1.0 * d * (left - b1) / (1.0 * a1 * a2);
	//printf("[left0, right3]: [%f, %f]\n", left0, right3);

	right0 = right1 < right2 ? right1 : right2;
	right0 = right0 < right3 ? right0 : right3;

	lefti = (long long)left0;
	if (left0 > 0 && lefti != left0)
		lefti += 1;	

	righti = (long long)right0;
	if (right0 < 0 && righti != right0)
		righti -= 1;

	//printf("[lefti, righti]: [%lld, %lld]\n", lefti, righti);

	printf("%lld\n", lefti > righti ? 0 : righti - lefti + 1);

	return 0;
}
