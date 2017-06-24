/**
 * A
 *
 * Codeforecs Tutorial
 *
 * 	In this problem it is enough to iterate through the given string from the left to the right 
 * 	and find the longest substring like "ogo...go" from each position of the string. 
 * 	If such substring was founded add "***" and move to the end of this substring.
 * 	In the other case, add current letter to the answer and move to the next position.
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

#define N 101

int n;
char s[N];

bool read(void) {
	scanf("%d", &n);
	scanf("%s", s);
	return true;
}

bool is_start(int i) {
	if (i+2 >= n)
		return false;
	if (!(s[i]=='o' && s[i+1]=='g' && s[i+2]=='o'))
		return false;
	return true;
}

int to_end(int i) {
	int j = i+3;
	while (j < n) {
		if (j+1 >= n || !(s[j]=='g' && s[j+1]=='o'))
			break;
		j += 2;
	}
	return j;
}

void solve(void) {
	vector<char> answer;
	int i = 0;

	while (i < n) {
		if (is_start(i)) {
			//printf("is_start: %d\n", i+1);
			answer.push_back('*');
			answer.push_back('*');
			answer.push_back('*');
			i = to_end(i);
		} else {
			answer.push_back(s[i]);
			++i;
		}
	}

	forn(i, (int)answer.size()) printf("%c", answer[i]);
	printf("\n");
}

int main(void) {
	#ifdef DEBUG
	//freopen("Inputs/A", "r", stdin);
	setbuf(stdout, NULL);
	#endif

	read();
	solve();

	return 0;
}
