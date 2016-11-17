/*
 * 713B
 */

#include <assert.h>
#include <stdio.h>
#include "common.h"

#define N 65536

int param[4], record[N];
int n;

void setparam(int a, int b, int c, int d)
{
	param[0] = a;
	param[1] = b;
	param[2] = c;
	param[3] = d;
}

int query(void)
{
	int i, res;

	if (!ir(param[0], 0, n)) return -1;
	if (!ir(param[1], 0, n)) return -1;
	if (!ir(param[2], 0, n)) return -1;
	if (!ir(param[3], 0, n)) return -1;

	printf("? ");
	fflush(stdout);
	forn(i, 4) printf("%d%s", param[i]+1, i == 3 ? "\n" : " ");
	fflush(stdout);
	scanf("%d%*c", &res);

	return res;
}

void shrink(int *begin, int *end, int middle, int inc)
{
	if (inc == 1) *end = middle - 1;
	else if (inc == -1) *begin = middle + 1;
	else assert(0);
}

void expand(int *begin, int *end, int middle, int inc)
{
	if (inc == 1) *begin = middle + 1;
	else if (inc == -1) *end = middle - 1;
	else assert(0);
}

int locate(int begin, int end, int witch, int inc)
{
	int i, middle;	
	int res, inner, outer;
	int b = begin, e = end;

	forn(i, n) record[i] = -1;
	while (end >= begin) {
		//printf("locate: [%2d, %2d]\n", begin+1, end+1);
		middle = (begin + end) / 2;
		assert(ire(middle, 0, n-1));
		param[witch] = middle;
		res = query();
		record[middle] = res;

		outer = middle + inc;
		inner = middle - inc;

		//FIXME: Bug: res == 2
		if (res == 1 || res == 2) {
			if (!ire(inner, b, e) || record[inner] == 0)
				return middle;
			shrink(&begin, &end, middle, inc);
		} else if (res == 0) {
			//FIXME: Big Bug: record[outer] == 2
			if (!ire(outer, b, e) || record[outer] == 1 || record[outer] == 2)
				return outer;
			expand(&begin, &end, middle, inc);
		} else assert(0);
	}

	assert(0);
}

int locatev(void)
{
	//printf("locatev()\n");
	int x, res;

	setparam(0, 0, -1, n-1);
	x = locate(0, n-1, 2, 1);

	setparam(x+1, 0, n-1, n-1);
	res = query();

	if (res == 1) return x;
	return -1;
}

int locateh(void)
{
	//printf("locateh()\n");
	int y, res;

	setparam(0, 0, n-1, -1);
	y = locate(0, n-1, 3, 1);

	setparam(0, y+1, n-1, n-1);
	res = query();

	if (res == 1) return y;
	return -1;
}

int main(void)
{
	int i;
	int a, b, c, d, x, y;
	int res[8];

	scanf("%d", &n);
	x = locatev();
	if (x == -1) {
		y = locateh();
		a = n-1, b = y;
		c = 0, d = b + 1;
	} else {
		a = x, b = n-1;
		c = a + 1, d = 0;
	}

	//printf("(a, b): (%2d, %2d), (c, d): (%2d, %2d)\n", a+1, b+1, c+1, d+1);

	/* second */

	setparam(c, d, n-1, n-1);
	res[4] = locate(c, n-1, 0, -1);
	//printf("res[4]: %d\n", res[4]+1);

	setparam(res[4], d, n-1, n-1);
	res[5] = locate(d, n-1, 1, -1);
	//printf("res[5]: %d\n", res[5]+1);

	setparam(res[4], res[5], n-1, n-1);
	res[6] = locate(res[4], n-1, 2, 1);
	//printf("res[6]: %d\n", res[6]+1);

	setparam(res[4], res[5], res[6], b);
	res[7] = locate(res[5], n-1, 3, 1);
	//printf("res[7]: %d\n", res[7]+1);

	/* first */

	setparam(0, 0, a, b);
	res[0] = locate(0, a, 0, -1);
	//printf("res[0]: %d\n", res[0]+1);

	setparam(res[0], 0, a, b);
	res[1] = locate(0, b, 1, -1);
	//printf("res[1]: %d\n", res[1]+1);

	setparam(res[0], res[1], a, b);
	res[2] = locate(res[0], a, 2, 1);
	//printf("res[2]: %d\n", res[2]+1);

	setparam(res[0], res[1], res[2], b);
	res[3] = locate(res[1], b, 3, 1);
	//printf("res[3]: %d\n", res[3]+1);


	printf("! ");
	fflush(stdout);
	forn(i, 8) printf("%d%s", res[i]+1, i == 7 ? "\n" : " ");
	fflush(stdout);

	return 0;
}
