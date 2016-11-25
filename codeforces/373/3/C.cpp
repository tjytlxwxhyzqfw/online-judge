/**
 * C
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <cstring>
#include <map>
#include <string>
#include <vector>

#include "common.h"

using namespace std;

#define N 200000

int n, m;
char s[N+2];

bool read(void) {
	if (scanf("%d%d", &n, &m) != 2)
		return false;
	++n;
	s[0] = '0';
	scanf("%s", s+1);
	return true;
}

void round(const int i) {
	assert(i >= 0);
	if (ire(s[i], '0', '4'))
		return;
	assert(ire(s[i], '5', '9'));
	--m;
	if (s[i-1] == '.') {
		assert(i-2 >= 0);
		s[i-2] += 1;
	} else {
		assert(i-1 >= 0);
		s[i-1] += 1;
	}
	s[i] = 0;
}

int correct(const int i) {
	int j, carry;

	assert(i >= 0);
	if (ire(s[i], '0', '9'))
		return i;
	assert(s[i] == '9'+1);
	s[i] = '0';

	carry = 1;
	for (j = i-1; j >= 0; --j) {
		if (s[j] == '.') continue;
		s[j] += carry;
		carry = (s[j]-'0') / 10;
		s[j] = (s[j]-'0') % 10 + '0';
		if (carry == 1)
			assert(s[j] == '0');
		if (carry == 0)
			break;
	}

	return j;
}

void printans(void) {
	char *b, *e, *p;
	int len = strlen(s);

	for (b = s; *b == '0'; ++b);
	if (*b == '.') putchar(48);

	for (e = s+len-1; *e == '0'; --e);
	if (*e == '.') --e;

	//printt("answer", 2);
	if (b <= e) {
		for (p = b; p <= e; ++p)
			putchar(*p);
	} else {
		putchar(48);
	}
	printf("\n");
}

void solve(void) {
	char *t;
	int i, rght;
	int len;

	//printt("begin", 3);
	//printf("s: %s\n", s);
	//printf("m: %d\n", m);

	for (t = s; *t && *t != '.'; ++t);

	if (*t == 0) {
		printans();
		return;
	}

	++t;

	//printf("t: %s\n", t);

	len = strlen(t);
	for (i = 0; i < len; ++i) {
		if (ire(t[i], '5', '9'))
			break;
	}

	if (i == len) {
		printans();
		return;
	}

	rght = i;
	//printf("rght: %d\n", rght);

	i = rght + n - len;
	while (true) {
		if (m <= 0) break;
		//printf("i: %d\n", i);
		round(i);
		if (s[i-1] == '.')
			i = correct(i-2);
		else
			i = correct(i-1);
		if (s + i < t)
			break;
	}

	printans();
}

int main(void) {
	freopen("Inputs/C", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) {
		solve();
	}

	return 0;
}
