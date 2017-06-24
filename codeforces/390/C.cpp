/**
 * C
 */

#include <algorithm>
#include <cassert>
#include <cstdio>
#include <cstring>
#include <map>
#include <stack>
#include <string>
#include <vector>

#include "common.h"

using namespace std;

#define U 12
#define W 102
#define N 101

int ncases;
int nnames, nchatings;
vector<string> names;
vector<pair<int, string>> chatings;
map<string, int> mapid;
int unk;

int split(char *chars, const char *sep, vector<string> &res) {
	char *p;
	int n;

	n = 0;
	p = strtok(chars, sep);
	while (p) {
		printf("get string: %s\n", p);
		res.push_back(string(p));
		p = strtok(NULL, sep);
	}

	return n;
}

int readline(char *const buf, const int buf_len) {
	fgets(buf, buf_len, stdin);
	int len = strlen(buf);
	assert(buf[len-1] == '\n');
	buf[len-1] = 0;
	return len-2;
}

bool read(void) {
	int i;
	char buf[1024];
	vector<string> tmp;
	int strid;

	scanf("%d", &ncases);
	scanf("%d%*c", &nnames);

	readline(buf, 1024);
	split(buf, " ", names);
	names.push_back("?");
	for(auto str: names) printf("%s\n", str.c_str());

	strid = 0;
	for(auto str : names) mapid[str] = strid++;
	for(auto it : mapid) printf("%10s: %d\n", it.first.c_str(), it.second);

	scanf("%d%*c", &nchatings);
	forn(i, nchatings) {
		readline(buf, 1024);
		tmp.clear();
		split(buf, ":", tmp);
		chatings.push_back({mapid[tmp[0]], tmp[1]});
	}
	for (auto pa : chatings) printf("%3d: %s\n", pa.first, pa.second.c_str());
	
	return true;
}

int s[N][N], path[N];

bool dfs(int chatid, int speaker) {
	path[chatid] = speaker;
	nxt = chatid + 1;

	if (nxt == chatings) return true;

	mat[nxt][speaker] = 1;

	s[chatid][speaker] = false;
	forn(i, nnames)
		if (s[nxt][i] == 0)
			s[chatid][speaker] ||= dfs(nxt, i);

	// recover
	mat[nxt][speaker] = 0;

	return s[chatid][speaker];
}

void solve(void) {
	int mat[N][N];

	forn(i, nchatings) {
		uid = chatings[i].first;
		sen = chatings[i].second.c_str();
		if (uid != unk) {
			// a known name
			forn(j, names) mat[i][j] = 1;
			mat[i][uid] = 0;
		} else {
			// an unknown name
			forn(j, nnames) {
				pos = strstr(sen, names[j].c_str());
				mat[i][j] = (pos == NULL ? 0 : 1);
			}
		}
		mat[i][unk] = 1;
	}
}

int main(void) {
	#ifdef DEBUG
	freopen("Inputs/C", "r", stdin);
	setbuf(stdout, NULL);
	#endif

	read();
	solve();

	return 0;
}
