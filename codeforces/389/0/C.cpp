/**
 * C
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <map>
#include <stack>
#include <string>
#include <vector>

#include "common.h"

using namespace std;

#define N 200001

int n;
char a[N];
int left_arr[N], rght_arr[N], up_arr[N], dn_arr[N];

void printf4(void) {
	printa(left_arr, left_arr+n, "left_arr: ");
	printa(rght_arr, rght_arr+n, "rght_arr: ");
	printa(up_arr, up_arr+n, "up_arr: ");
	printa(dn_arr, dn_arr+n, "dn_arr: ");
}

bool read(void) {
	int i;
	char ch;

	scanf("%d", &n);
	scanf("%s", a);

	//printa(a, a+n, "a: ");

	forn(i, n) {
		ch = a[i];
		switch(ch) {
		case 'L':
			up_arr[i] = dn_arr[i] = 1;
			left_arr[i] = 1;
			rght_arr[i] = -1;
			break;
		case 'R':
			up_arr[i] = dn_arr[i] = 1;
			left_arr[i] = -1;
			rght_arr[i] = 1;
			break;
		case 'U':
			left_arr[i] = rght_arr[i] = 1;
			up_arr[i] = 1;
			dn_arr[i] = -1;
			break;
		case 'D':
			left_arr[i] = rght_arr[i] = 1;
			up_arr[i] = -1;
			dn_arr[i] = 1;
			break;
		default:
			assert(false);
		}
	}

	//printf4();

	forr(i, 1, n) {
		left_arr[i] += left_arr[i-1];
		rght_arr[i] += rght_arr[i-1];
		up_arr[i] += up_arr[i-1];
		dn_arr[i] += dn_arr[i-1];
	}

	//printf4();


	return true;
}

bool repeat(const int bgn, const int end) {
	int left_sum, rght_sum, up_sum, dn_sum;
	int length;
	bool lr_rpt = true, ud_rpt = true , res;

	if (bgn == end)
		return false;

	
	length = end - bgn + 1;

	left_sum = left_arr[end];
	rght_sum = rght_arr[end];
	up_sum = up_arr[end];
	dn_sum = dn_arr[end];

	if (bgn != 0) {
		left_sum -= left_arr[bgn-1];
		rght_sum -= rght_arr[bgn-1];
		up_sum -= up_arr[bgn-1];
		dn_sum -= dn_arr[bgn-1];
	}

	if (left_sum == length || rght_sum == length)
		lr_rpt = false;
	if (up_sum == length || dn_sum == length)
		ud_rpt = false;

	res = lr_rpt || ud_rpt;

	//printf("\t\trepeat: [%3d, %3d]: %s\n", bgn, end, blstr(res));

	return res;
}

int leftmost(const int end) {
	int i, j, k;
	int bgn;
	bool rpt;

	//printf("leftmost: end: %3d\n", end);

	i = 0, j = end;
	while (i <= j) {
		k = (i+j) / 2;
		rpt = repeat(k, end);
		//printf("\t[%3d, %3d]: %3d: %s\n", i, j, k, blstr(rpt));
		if (rpt) {
			i = k + 1;
		} else {
			j = k - 1;
			bgn = k;
		}
	}

	//printf("leftmost: bgn: %3d\n", bgn);

	return bgn;
}

void solve(void) {
	int ans, keypt;

	ans = 0;
	keypt = n - 1;
	while (keypt >= 0) {
		keypt = leftmost(keypt);
		++ans;
		--keypt;
	}

	//printf("---------> %d\n", ans);
	printf("%d\n", ans);
}

int main(void) {
	#ifdef DEBUG
	//freopen("Inputs/C", "r", stdin);
	setbuf(stdout, NULL);
	#endif

	read();
	solve();

	return 0;
}
