/**
 * B
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

#define N 10020

int n, m;
vector<int> groups[N];
vector<int> buf;

bool read(void) {
	int i, j, k;
	int person;

	scanf("%d%d", &n, &m);
	forn(i, m) {
		scanf("%d", &k);
		forn(j, k) {
			scanf("%d", &person);
			groups[i].push_back(person);
		}
	}

	return true;
}

bool check(const vector<int> &group) {
	int i, person;
	int size;
	vector<int>::const_iterator it;

	size = (int)group.size();
	if (size == 0)
		return true;

	buf.clear();
	forn(i, size) {
		person = group[i];
		if (person > 0)
			buf.push_back(person);
	}

	if (buf.empty())
		return false;

	sort(buf.begin(), buf.end());

	forn(i, size) {
		person = group[i];
		if (person > 0)
			continue;

		person *= -1;
		it = lower_bound(buf.begin(), buf.end(), person);
		if (*it == person) {
			//printf("\t match: pos: %3d, person: %3d\n", i, person);
			return true;
		}
	}

	return false;
}

void solve(void) {
	int i, ok;

	forn(i, m) {
		//printf("handling group #%d:\n", i+1);
		//printa(groups[i].begin(), groups[i].end());
		ok = check(groups[i]);
		if (!ok) {
			printf("YES\n");
			return;
		}
	}

	printf("NO\n");
}

int main(void) {
	#ifdef DEBUG
	//freopen("Inputs/B", "r", stdin);
	setbuf(stdout, NULL);
	#endif

	read();
	solve();

	return 0;
}
