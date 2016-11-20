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

using namespace std;

struct car {

	static bool compare(const car &x, const car &y) {
		return x.rent < y.rent;
	}

	long long rent, cap;
	bool deleted;

	car(long long r, long long c) {
		rent = r;
		cap = c;
		deleted = false;
	}

	void print(void) {
		printf("%3lld, %3lld, %s\n", rent, cap, blstr(deleted));
	}
};

#define N 200000

long long n, k, s, t;
vector<car> all;
vector<car>::iterator cars[N];
long long ncars;
long long st[N+2];

bool read(void) {
	long long i;
	long long rent, cap;
	long long current;
	vector<car>::iterator it;

	if (scanf("%lld%lld%lld%lld", &n, &k, &s, &t) != 4)
		return false;
	forn(i, n) {
		scanf("%lld%lld", &rent, &cap);
		all.push_back(car(rent, cap));
	}
	forre(i, 1, k) scanf("%lld", &st[i]);

	//pre-proprecess
	sort(all.begin(), all.end(), car::compare);
	st[0] = 0;
	st[k+1] = s;
	sort(st, st+k+2);

	//check 1
	/*
	printf("--- raw in---\n");
	forn(i, n) all[i].print();
	forn(i, k+2) printf("%lld%s", st[i], tailer(i, k+1));
	printf("--- end raw in ---\n");
	*/

	current = all[0].cap;
	forr(i, 1, n) {;
		if (all[i].cap < current)
			all[i].deleted = true;
		else
			current = all[i].cap;
	}

	/*
	printf("deleted:\n");
	forn(i, n) all[i].print();
	printf("--- end --- deleted\n");
	*/

	ncars = 0;
	for(it = all.begin(); it != all.end(); ++it)
		if (!it->deleted) cars[ncars++] = it;

	/*
	printf("filtered: ncars: %lld\n", ncars);
	forn(i, ncars) cars[i]->print();
	printf("--- end filtered ---\n");
	*/

	return true;
}

inline long long cal(long long dist, long long cap) {
	//printf("\t\t\tcal: dist: %lld, cap: %lld\n", dist, cap);
	if (dist > cap) {
		//printf("\t\t\tcal: return: %lld\n", LL(-1));
		return -1;
	}
	if (2*dist <= cap) {
		//printf("\t\t\tcal: return: %lld\n", dist);
		return dist;
	}
	//printf("\t\t\tcal: return: %lld\n", 3*dist-cap);
	return 3 * dist - cap;

}

bool check(const long long cap) {
	long long i, time, total;

	//printf("\tcheck: %lld\n", cap);
	total = 0;
	forre(i, 1, k+1) {
		time = cal(st[i]-st[i-1], cap);
		if (time < 0)
			return false;
		total += time;
	}

	//printf("\tcheck: total: %lld, t: %lld\n", total, t);
	return total <= t;
}

void solve(void) {
	long long bgn, end, mid;
	long long best;
	bool ck;

	best = LLONG_MAX;
	bgn = 0, end = ncars - 1;
	while (bgn <= end) {
		mid = (bgn+end) / 2;
		ck = check(cars[mid]->cap);
		//printf("check: %2lld: %s\n", mid, blstr(ck));
		if (ck) {
			best = cars[mid]->rent;
			end = mid - 1;
		} else {
			bgn = mid + 1;
		}
	}

	//printf("----------> %lld\n", best);
	printf("%lld\n", best == LLONG_MAX ? -1 : best);
}

void clear(void) {
	all.clear();
}

int main(void) {
	freopen("Inputs/C", "r", stdin);
	setbuf(stdout, NULL);

	while (read()) {
		solve();
		clear();
	}

	return 0;
}
