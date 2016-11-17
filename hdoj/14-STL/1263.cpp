/**
 * 1263
 *
 * map 与排序
 * 1, 默认顺序
 * 2. 排序"函数"
 * 3. 更改顺序
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <map>
#include <string>
#include <vector>

#include "common.h"

using namespace std;

#define N 81

/* use a class */
template <class T> struct great: public binary_function<T, T, bool> {
	bool operator() (const T &x, const T &y) {
		return x > y;
	}
};

/* can use function */
bool gt(const string &x, const string &y)
{
	return x > y;
}

map<string, map<string, int> > mp;

bool read(void)
{
	char s[N], t[N];
	int i, n, q;

	mp.clear();
	if (scanf("%d", &n) != 1)
		return false;

	forn(i, n) {
		scanf("%s%s%d", s, t, &q);
		mp[string(t)][string(s)] += q;
	}

	return true;
}

void solve(void)
{
	map<string, map<string, int> >::iterator it;
	map<string, int>::iterator jt;

	for (it = mp.begin(); it != mp.end(); ++it) {
		printf("%s\n", it->first.c_str());
		for (jt = it->second.begin(); jt != it->second.end(); ++jt)
			printf("   |----%s(%d)\n", jt->first.c_str(), jt->second);
	}
}

int main(void)
{
	int ncs;

	freopen("Inputs/1263", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d", &ncs);
	while (--ncs >= 0) {
		read();
		solve();
	}

	return 0;
}
