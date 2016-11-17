/**
 * D
 */
#include <algorithm>
#include <cassert>
#include <cstdio>
#include <vector>

template<class Number>
struct modops {
	Number mod;

	modops(Number m) { mod = m; };

	Number pls(Number a, Number b) {
		return (a % mod + b % mod) % mod;
	}

	Number sub(Number a, Number b) {
		return ((a % mod) + mod - b % mod) % mod;
	}

	Number mul(Number a, Number b) {
		return ((a % mod) * (b % mod)) % mod;
	}
};

#include "common.h"

using namespace std;

#define T 200
#define N 400000

int a, b, k, t, n;
int delta, leftv, rghtv, leftmst;
int dp[T][N+1];
int sm[2][N+1];
modops<int> mod(1000000007);

#define dpv(i, j) (dp[(i)][(j)-leftmst])

int main(void)
{
	int i, j;
	int sumr, suml;
	int *last, *curr;

	freopen("Inputs/D", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d%d%d%d", &a, &b, &k, &t);
	delta = b - a;
	n = (t<<1);
	leftmst = -1*k*n;

	curr = sm[0], last = sm[1];
	leftv = -1*k*(n-1), rghtv = k*(n-1);
	forre(j, leftv, rghtv) dpv(n-1, j) = max(0, k-max(delta-j, -k-1));
	curr[0] = dpv(n-1, leftv);
	forre(j, leftv+1, rghtv) curr[j-leftv] = mod.pls(dpv(n-1, j),  curr[j-1-leftv]);

	//forre(j, leftv, rghtv) printf("%2d%s", dpv(n-1, j), j==rghtv ? "\n":" ");
	//forre(j, leftv, rghtv) printf("%2d%s", curr[j-leftv], j==rghtv ? "\n":" ");

	for (i = n-2; i >= 0; --i) {
		swap(curr, last);
		forre(j, leftv, rghtv) curr[j-leftv] = 0;
		leftv = -1*k*i, rghtv = k*i;
		for (j = rghtv; j >= leftv; --j) {
			sumr = last[j+k-leftv+k];
			suml = last[j-k-1-leftv+k];
			dpv(i, j) = mod.sub(sumr, suml);
			//printf("dp[%2d][%2d]: %2d\n", i, j, dpv(i, j));
		}
		curr[0] = dpv(i, leftv);
		forre(j, leftv+1, rghtv) curr[j-leftv] = mod.pls(dpv(i, j), curr[j-1-leftv]);

		//forre(j, leftv, rghtv) printf("%2d%s", dpv(i, j), j==rghtv ? "\n" : " ");
		//forre(j, leftv, rghtv) printf("%2d%s", curr[j-leftv], j==rghtv ? "\n" : " ");
	}

	printf("%d\n", dpv(0, 0));

	return 0;
}
