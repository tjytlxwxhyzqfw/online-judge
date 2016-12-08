/**
 * D
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

struct Face {
	int id;
	int x, y;

	Face(int id, int x, int y) {
		this->id = id;
		this->x = x;
		this->y = y;
	}

	const bool operator<(const Face &rhs) const {
		return x < rhs.x || y < rhs.y;
	}

	void print(void) const {
		printf("face: %3d: (%3d, %3d)\n", id, x, y);
	}
};

int n;
map<Face, vector<Face> > mp;


bool read(void) {
	int i;
	int x, y, z;

	scanf("%d", &n);
	forn(i, n) {
		scanf("%d%d%d", &x, &y, &z);
		if (x > y) swap(x, y);
		if (x > z) swap(x, z);
		if (y > z) swap(y, z);
		assert(x <= y && y <= z);
		if (x == y && y == z)
			mp[Face(i, x, y)].push_back(Face(i, z, z));
		else if (x == y) {
			mp[Face(i, x, y)].push_back(Face(i, z, z));
			mp[Face(i, y, z)].push_back(Face(i, x, x));
		} else if (y == z) {
			mp[Face(i, x, y)].push_back(Face(i, z, z));
			mp[Face(i, y, z)].push_back(Face(i, x, x));
		} else {
			mp[Face(i, x, y)].push_back(Face(i, z, z));
			mp[Face(i, y, z)].push_back(Face(i, x, x));
			mp[Face(i, x, z)].push_back(Face(i, y, y));
		}
	}

	return true;
}

void solve(void) {
	map<Face, vector<Face>>::iterator it;
	vector<Face> *vec;
	int x, y, z;
	int rec1, rec2, di, maxdi, len;
	int maxz, subz, maxi, subi;

	maxdi = 0;
	rec1 = rec2 = -1;

	foriter(it, mp) {
		//printf("current face: ");
		//it->first.print();
		x = it->first.x;
		y = it->first.y;

		vec = &it->second;
		sort(vec->begin(), vec->end());

		//printb(vec->begin(), vec->end(), "sorted vec:", 2);

		len = vec->size();
		maxz = vec->at(len-1).x;
		maxi = vec->at(len-1).id;
		subz = len >= 2 ? vec->at(len-2).x : 0;
		subi = len >= 2 ? vec->at(len-2).id : -1;
		z = maxz + subz;

		//printf("\tmaxz: %3d, maxi: %3d, subz: %3d, subi: %3d\n", maxz, maxi, subz, subi);

		di = min(min(x, y), z);
		if (di > maxdi) {
			//printf("\tmaxdi: %3d ---> %3d\n", maxdi, di);
			maxdi = di;
			rec1 = maxi;
			rec2 = subi;
		}
	}

	++rec1, ++rec2;
	if (rec2 == 0) {
		printf("1\n");
		printf("%d\n", rec1);
	} else {
		printf("2\n");
		printf("%d %d\n", rec1, rec2);
	}
}

int main(void) {
	#ifdef DEBUG
	//freopen("Inputs/D", "r", stdin);
	setbuf(stdout, NULL);
	#endif

	read();
	solve();

	return 0;
}
