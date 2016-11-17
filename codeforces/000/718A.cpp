/*
 * 718A Efim and Strange Grade
 */
#include <algorithm>
#include <array>
#include <string>

#include <cstdio>
#include "common.h"

using namespace std;

#define N 200000

int n, t;
array<char, N+2> num;

int carry(int pos)
{
	int ret = -1, left;
	char res, c = 1;
	bool right = true;

	//printf("num[%d]: %d\n", pos, num[pos]);
	assert(num[pos] >= '5' && num[pos] <= '9');
	for(; pos >= 0; --pos) {
		if (right) {
			if (num[pos] == '.') right = false;
			num[pos] = 0;
		}
		left = pos - 1;
		if (num[left] == '.') continue;
		res = num[left] + c - '0';
		num[left] = (res % 10) + '0';
		if (right && num[left] >= '5') ret = left;
		c = res / 10;

		if (c == 0) break;
	}

	return ret;
}

int main(void)
{
	int first, point, i;
	string answer;

	//freopen("Inputs/718A", "r", stdin);

	scanf("%d%d%*c", &n, &t);
	scanf("%s", num.data()+1);
	num[0] = '0';
	++n;

	forn(point, n) if (num[point] == '.') break;
	++point;
	forr(first, point, n) if (num[first] >= '5') break;
	if (first >= n) first = -1;
	//printf("point: %d, first: %d\n", point, first);

	while (--t >= 0 && first != -1) {
		first = carry(first);
	}

	i = (num[0] == '0' ? 1 : 0);
	for(; i < n && num[i] != 0; ++i)
		printf("%c", num[i]);
	printf("\n");

	return 0;
}
