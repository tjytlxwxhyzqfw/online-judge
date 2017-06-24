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

#define N 10000

int heap;
vector<int> cand[2];
int cache[N];
bool used[N];


bool read(void) {
	int i, k, x;


	scanf("%d", &heap);

	scanf("%d", &k);
	forn(i, k) {
		scanf("%d", &x);
		cand[0].push_back(x);
	}

	scanf("%d", &k);
	forn(i, k) {
		scanf("%d", &x);
		cand[1].push_back(x);
	}

	return true;
}

int sg(const int n, const int turn) {
	int i, ncands, res, nxt;
	vector<bool> occupied;
	vector<int> &cands = cand[turn];

	printf("sg: %d\n", n);

	if (cache[n] != -1)
		return cache[n];

	if (used[n])
		return -1;
	used[n] = true;

	if (n == 0) {
		used[0] = true;
		cache[0] = 0;
		return 0;
	}

	ncands = cands.size();
	assert(ncands > 0);

	//if (cands[0] > n)
		//return 0;

	occupied.resize(ncands);
	fill(occupied.begin(), occupied.end(), false);

	//for (i = 0; i < ncands && n >= cands[i]; ++i) {
	forn(i, ncands) {
		nxt = n-cands[i];
		if (nxt < 0)
			nxt += heap;

		res = sg(nxt, 1-turn);
		if (res < 0)
			return -1;
		if (res < ncands)
			occupied[res] = true;
	}

	for (i = 0; i < ncands; ++i)
		if (!occupied[i])
			break;

	printf("\t\tsg[%3d]: %3d\n", n, i);
	cache[n] = i;
	return i;
}

void game(const int first) {
	int i, heapsize, result;

	forn(i, heap-1) {
		heapsize = heap-1-i;
		printf("heapsize: %d\n", heapsize);
		fill(used, used+N, false);
		result = sg(heapsize, first);
		printf("-------> result: %d\n", result);
		if (result < 0) {
			printf("Loop\n");
		} else {
			printf("%s\n", result == 0 ? "Lose" : "Win");
		}
	}
}

void solve(void) {
	fill(cache, cache+N, -1);
	game(0);
	fill(cache, cache+N, -1);
	game(1);
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
