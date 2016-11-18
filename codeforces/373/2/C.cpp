/**
 * C
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <map>
#include <string>
#include <vector>

#include "common.h"

#define printd printf

template <class Iterator, class Function> struct skipper {
	Iterator bgn, end, cur, next;
	Function st;
	inline void init(const Iterator b, const Iterator e) { 
		bgn = b;
		end = e;
		check_from(bgn);
	}
	bool check_from(Iterator from) {
		for (next = from; next != end; ++next) {
			if (st(next))
				break;
		}
		cur = next++;
		return cur != end;
	}
	inline bool nxt(void) {
		return check_from(cur);
	}
	inline bool mov(void) {
		return check_from(next);
	}
	inline int idx(void) const {
		return static_cast<int>(cur - bgn);
	}
};

using namespace std;

#define N 200002

int n, t;
char a[N], s[N];

bool read(void) {
	int i, j;

	a[0] = s[0] = '0';
	if (scanf("%d%d", &n, &t) != 2)
		return false;
	++n;

	scanf("%s", a+1);

	j = 1;
	forr(i, 1, n) if (a[i] != '.') s[j++] = a[i];
	s[j] = 0; //!attention: this is important

	//printd("a: %s\ns: %s\n", a, s);

	return true;
}

inline bool try_round_at(int i) {
	if (s[i] >= '5') {
		//printd("\tround_at: %d\n", i);
		s[i] = 0;
		s[i-1] += 1;
		return true;
	}
	return false;
}

int try_carry_at(int i) {
	//printd("\ttry_carry_at: %d\n", i);
	if (s[i] != 58) {
		assert(48 <= s[i] && s[i] < 58);
		return i;
	}

	assert(i >= 1);

	s[i] = '0';
	s[i-1] += 1;

	return try_carry_at(i-1);
}

void idx_init(int *pidx, int *idx) {
	int i, j;
	forn(i, n) if (a[i] == '.') break;
	forr(j, i, n) if (s[j] >= '5') break;
	*pidx = i;
	*idx = j;

	//printd("idx_init: pidx: %d, idx: %d\n", *pidx, *idx);
}

void printres(void) {
	int i = 0;
	if (s[i] == '0') ++i;
	for(; i < n; ++i) {
		if (s[i] == 0) break;
		if (a[i] == '.') printf(".");
		printf("%c", s[i]);
	}
	printf("\n");
}

void solve(void) {
	int pidx, idx;
	bool bv;

	idx_init(&pidx, &idx);
	if (pidx == n || idx == n) {
		printres();
		return;
	}

	while (t > 0) {
		bv = try_round_at(idx);
		if (bv) {
			idx = try_carry_at(idx-1);
			--t;
		} else {
			--idx;
		}
		if (idx < pidx) break;
	}

	printres();
}

int main(void) {
	freopen("Inputs/C", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) solve();

	return 0;
}
