/*
 * 711B.c
 *
 * 错了一次,long long 写成了in
 *
 * 善用宏!!!!!!!!!!!!!!!!
 */

#include <stdio.h>
#include <stdlib.h>

long long map[500][500];
int size, ey, ex;

//FIXME: long long !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//int magic, delta, ey, ex;
long long magic, delta;

#define same(y, x) (((y) == ey || (x) == ex))
#define wrong_delta(s) ((magic - (s) != delta))
#define wrong_magic(s) (magic != (s))
#define wrong(y, x, s) ((same(y, x) && wrong_delta(s)) || (!same(y, x) && wrong_magic(s)))
#define ON_DIAG_0 ((ey == ex))
#define ON_DIAG_1 ((ey + ex == size - 1))

int main(void)
{
	int y, x;	
	long long diag0, diag1, sum;

	freopen("Inputs/711B", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d", &size);
	for (y = 0; y < size; ++y) {
		for (x  = 0; x < size; ++x) {
			scanf("%lld", &map[y][x]);
			if (map[y][x] == 0) {
				ey = y;
				ex = x;
			}
		}
	}

	diag0 = diag1 = 0;
	for (y = 0; y < size; ++y) {
		diag0 += map[y][y];
		diag1 += map[y][size - 1 - y];
	}

	if (ON_DIAG_0 && !ON_DIAG_1) {
		magic = diag1;
		delta = magic - diag0;
	} else if (ON_DIAG_1 && !ON_DIAG_0) {
		magic = diag0;
		delta = magic - diag1;
	} else if (ON_DIAG_1 && ON_DIAG_0) {
		if (size == 1)
			goto trival_answer;
		if (diag0 != diag1)
			goto no_answer;
		magic = 0;
		for (x = 0; x < size; ++x)
			magic += map[0][x];
		delta = magic - diag0;
	} else {
		if (diag0 != diag1)
			goto no_answer;
		delta = magic = diag0;
		for (x = 0; x < size; ++x)
			delta -= map[ey][x];
	}
			
	//XXX
	if (delta <= 0)
		goto no_answer;

	for (y = 0; y < size; ++y) {
		sum = 0;
		for (x = 0; x < size; ++x)
			sum += map[y][x];
		if (wrong(y, x, sum))
			goto no_answer;
	}

	for (x = 0; x < size; ++x) {
		sum = 0;
		for (y = 0; y < size; ++y)
			sum += map[y][x];
		if (wrong(y, x, sum))
			goto no_answer;
	}

	printf("%lld\n", delta);
	return 0;

	no_answer:
	printf("-1\n");
	return 0;

	trival_answer:
	printf("1\n");
	return 0;
}
