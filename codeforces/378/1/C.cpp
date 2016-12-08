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

#define N 500

int n, m;
int a[N], b[N], c[N];
int rec[N][2], reci;

bool read(void) {
	int i;

	scanf("%d", &n);
	forn(i, n) scanf("%d", &a[i]);

	scanf("%d", &m);
	forn(i, m) scanf("%d", &b[i]);

	forn(i, n) c[i] = i;
	reci = 0;

	return true;
}

void no(void) {
	printf("NO\n");
}

void correct(void) {
	int i, j = 0;
	forn(i, n) c[i] = a[i] > 0 ? j++ : -1;
}

//all parameter indexes are use by a[]
inline void do_eat(int eater, int i) {
	a[eater] += a[i];
	a[i] = -1;

	rec[reci][0] = c[eater];
	rec[reci][1] = i - eater;
	++reci;

	correct();
}

void eat(int eater, int bgn, int end, int direct) {
	int i;
	if (direct < 0) {
		rforre(i, eater-1, bgn) do_eat(eater, i);
		forre(i, eater+1, end) do_eat(eater, i);
	} else if (direct > 0) {
		forre(i, eater+1, end) do_eat(eater, i);
		rforre(i, eater-1, bgn) do_eat(eater, i);
	} else {
		assert(false);
	}
}

int sumto(int target, int bgn, int *arr, int len) {
	int i, sum_sofar;
	sum_sofar = 0;
	forr(i, bgn, n) {
		sum_sofar += arr[i];
		if (sum_sofar == target)
			break;
	}
	return i;
}

void solve(void) {
	int bgn, end, eater;
	int biggest, target;
	int i, k;
	int direct;

	bgn = 0;
	forn(i, m) {
		//printf("i: %d\n", i+1);
		//printa(a, a+n, "a so far: ");
		//printa(c, c+n, "c so far: ");

		target = b[i];
		end = sumto(target, bgn, a, n);
		if (end >= n) return no();

		if (bgn == end) {
			++bgn;
			continue;
		}

		//ok, we get the summary

		//printf("\ttarget: %3d, [%3d, %3d]\n", target, bgn+1, end+1);

		// find the biggest monster
		biggest = a[bgn];
		forre(k, bgn, end) biggest = max(biggest, a[k]);

		// find the eater
		eater = -1;
		direct = 0;
		forre(k, bgn, end) {
			if (a[k] != biggest) continue;
			if (a[k] > a[k-1] && k-1 >= bgn) {
				eater = k;
				direct = -1;
				break;
			}
			if (a[k] > a[k+1] && k+1 <= end) {
				eater = k;
				direct = 1;
				break;
			}
		}

		// no eater
		if (eater == -1) return no();

		//printf("\tbiggest: %3d, eater: %3d\n", biggest, eater+1);

		// let eater eat up all monstars in [bgn, end]
		eat(eater, bgn, end, direct);

		bgn = end + 1;
	}

	if (bgn < n) return no();

	printf("YES\n");
	forn(k, reci) {
		printf("%d %c\n", rec[k][0]+1, rec[k][1] > 0 ? 'R' : 'L');
	}
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
