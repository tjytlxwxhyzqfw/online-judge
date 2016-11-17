/*
 * 1A Theatre Square
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//#include "debug.c"

#define BITS 20

void to_string(int n, char *s)
{
	int i = 0;

	int lowest;

	//printis(0, 0, "to_string: %10d: ", n);

	memset(s, 0, BITS);

	do {
		lowest = n % 10;
		s[i++] = lowest + '0';
		n /= 10;
	} while (n);

	//printis(0, 0, "%s\n", s);
}

void mul(char *x, char *y, char *res)
{
	int carry = 0;

	int nx, ny, base;
	int result1, result2, high1, low1, high2, low2;
	int i, j;

	nx = strlen(x);
	ny = strlen(y);

	memset(res, '0', BITS);

	for (i = 0; i < nx; ++i) {
		for (j = 0; j < ny; ++j) {
			base = i + j;
			//printis(1, 0, "%c * %c\n", x[i], y[j]);

			result1 = (x[i] - '0') * (y[j] - '0');
			high1 = result1 / 10;
			low1 = result1 % 10;
			//printis(2, 0, "result1: %d, high1: %d, low1: %d\n", result1, high1, low1);

			result2 = res[base] + low1 + carry - 48;
			high2 = result2 / 10;
			low2 = result2 % 10;
			//printis(2, 0, "result2: %d, high2: %d, low2: %d\n", result2, high2, low2);

			carry = high1 + high2;
			res[base] = low2 + 48;
			//printis(1, 0, "res[%2d]: %c, carry: %d\n", base, res[base], carry);
		}
		while (carry) {
			++base;
			result2 = res[base] + carry - 48;
			res[base] = result2 % 10 + 48;
			carry = result2 / 10;
		}
	}
	res[++base] = 0;

	//printis(0, 0, "mul: %s, %s: %s\n", x, y, res);
}

void printlnum(char *s)
{
	int i;

	for (i = strlen(s) - 1; i >= 0; --i)
		putchar(s[i]);
	printf("\n");
}


int main(void)
{
	int h, w, a, nh, nw;
	char hstr[BITS], wstr[BITS], result[BITS];

	while (scanf("%d%d%d", &h, &w, &a) == 3) {
		nh = h / a + (h % a ? 1 : 0);
		nw = w / a + (w % a ? 1 : 0);

		to_string(nh, hstr);
		to_string(nw, wstr);
		mul(hstr, wstr, result);

		printlnum(result);
	}

	return 0;
}
