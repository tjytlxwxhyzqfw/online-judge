/**
 * 3698
 */

#include <algorithm>
#include <array>
#include <cassert>
#include <cstdio>
#include <vector>

#include "common.h"

using namespace std;

#define H 100
#define W 5000

#define SEGN (1<<14)
#define N (SEGN>>1)

const int off = N;

bool read(void)
{
	if (scanf("%d%d", &height, &width) != 2)
		return false;

	forn(y, height) forn(x, width) scanf("%d", &cost[y][x]);
	forn(y, height) forn(x, width) scanf("%d", &f[y][x]);

	return true;
}

void solve(void)
{
	reforre(i, height-1, 0) {
		build(i);
		forn(j, 0, width) {


int main(void)
{
	freopen("Inputs/3698", "r", stdin);
	setbuf(stdout, NULL);

	return 0;
}
