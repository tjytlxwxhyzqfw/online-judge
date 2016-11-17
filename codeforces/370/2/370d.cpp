/**
 * 370d
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"

template<class Number>
struct modops {
	Number mod;

	modops(Number m) { mod = m; };

	Number pls(const Number a, const Number b) const {
		return (a % mod + b % mod) % mod;
	}

	Number mns(const Number a, const Number b) const {
		return ((a % mod) + mod - b % mod) % mod;
	}

	Number mul(const Number a, const Number b) const {
		return ((a % mod) * (b % mod)) % mod;
	}

	Number add(Number &a, const Number b) const {
		return a = pls(a, b);
	}

	Number sub(Number &a, const Number b) const {
		return a = mns(a, b);
	}
};

using namespace std;

const modops<int> mod(1000000007);
const int left = (-200000);

#define N 200
#define D 400000

int k, n, d;
int curr[D+1], last[D+1];

bool read(void)
{
	int a, b, t;

	if (scanf("%d%d%d%d", &a, &b, &k, &t) != 4)
		return false;

	d = b - a;
	n = 2 * t;

	return true;
}

void mkpsum(int *a, int b, int e)
{
	int i;
	forre(i, b+1, e) mod.add(a[i], a[i-1]);//a[i] += a[i-1]; //
}

void printc(int i, int j)
{
	printf("(%2d, %2d): %4d\n", i, j, curr[j-left]);
}

void solve(void)
{
	int i, j;

	forre(j, (1-n)*k, (n-1)*k) curr[j-left] = max(0, k-max(d-j, -1*k-1));
	mkpsum(curr, (1-n)*k-left, (n-1)*k-left);

	rfor(i, n-1) {
		swap(curr, last);
		forre(j, -1*i*k, i*k) {
			curr[j-left] = last[j+k-left];
			//BUG!!!!
			if (j >= 1-i*k) mod.sub(curr[j-left], last[j-k-1-left]);//curr[j-left] -= last[j-k-1-left]; //
		}
		mkpsum(curr, -1*i*k-left, i*k-left);
	}

	printf("%d\n", curr[0-left]);
}

int main(void)
{
	freopen("Inputs/370d", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) solve();

	return 0;
}
