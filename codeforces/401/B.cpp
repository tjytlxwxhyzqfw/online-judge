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

#define N 100001

const int top = 1000000001;

int nrows, ncols, nqs;
vector<vector<int>> mat;

int querys[N][2];

bool read(void) {
	int i, j;

	scanf("%d%d", &nrows, &ncols);
	forn(i, nrows) {
		//printf("i: %d\n", i);
		mat.push_back(vector<int>(ncols));
		forn(j, ncols) {
			scanf("%d", &mat[i][j]);
		}
	}

	//printf("matrix: \n");
	//forn(i, nrows) printa(mat[i].begin(), mat[i].end());

	scanf("%d", &nqs);
	forn(i, nqs) scanf("%d%d", &querys[i][0], &querys[i][1]);

	return true;
}

void f(void) {
	int i, j;
	int bgn, end, curr, last;
	int qres[N];

	fore(i, nrows) qres[i] = -1;

	forn(j, ncols) {
		last = top, bgn = 0;
		forn(i, nrows) {
			curr = mat[i][j];
			//printf("[%3d, %3d]: curr: %3d, last: %d\n", i, j, curr, last);
			if (curr < last) {
				qres[bgn+1] = max(qres[bgn+1], i);
				//printf("\tbreak: [%3d, %3d] bgn: %d\n", j+1, i+1, bgn+1);
				bgn = i;
			}
			last = curr;
		}
		//printf("qres[%3d] = %d\n", bgn+1, nrows+1);
		qres[bgn+1] = nrows+1;
	}

	end = -1;
	fore(i, nrows) {
		qres[i] = max(end, qres[i]);
		end = qres[i];
	}

	//printa(qres, qres+nrows+1);

	forn(i, nqs) {
		bgn = querys[i][0];
		end = querys[i][1];
		printf("%s\n", end <= qres[bgn] ? "Yes" : "No");
	}
}

void solve(void) {
	f();
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
