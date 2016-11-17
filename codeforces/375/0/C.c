/*
 * C
 */

#include <stdio.h>
#include <stdlib.h>

#include "common.h"

#define N 2000
#define M 2000

struct node {
	int id;
	int cnt;
};

int n, m;
int seq[N];
struct node bands[N];
int orders[M][N], oi[M];

void init_orders(void)
{
	int i;
	forn(i, M) oi[i] = 0;
}

void add_order(int band, int idx)
{
	orders[band][oi[band]] = idx;
	oi[band] += 1;
}

int del_order(int band)
{
	int ret;

	ret = orders[band][oi[band]-1];
	oi[band] -= 1;

	return ret;
}

void init_bands(void)
{
	int i;
	forn(i, M) {
		bands[i].id = i;
		bands[i].cnt = 0;
	}
}

int nextlow(int start, int avg)
{
	int i;
	forr(i, start, m) {
		//printf("\tbands[%2d]: %2d\n", i, bands[i].cnt);
		if (bands[i].cnt < avg)
			break;
	}
	//printf("nextlow(): %d\n", i+1);
	return i;
}

int nextup(int start, int avg)
{
	int i;
	forr(i, start, m)
		if (bands[i].cnt > avg) break;
	//printf("nextup(): %d\n", i+1);
	return i;
}

#define dislike(bid) ((bid) >= m)
#define like(bid) ((bid) < m)

int main(void)
{
	int i, band, order;
	int nchanges;
	int low, up, avg;

	freopen("Inputs/C", "r", stdin);
	setbuf(stdout, NULL);

	init_orders();
	init_bands();

	scanf("%d%d", &n, &m);
	avg = n / m;
	//printf("n: %2d, m: %2d, avg: %2d\n", n, m, avg);
	forn(i, n) {
		scanf("%d", &band);
		--band;
		if (like(band)) add_order(band, i);
		seq[i] = band;
	}

	//printf("seq:\n");
	//forn(i, n) printf("%d%s", seq[i]+1, (i == n-1 ? "\n" : " "));

	/* init state of bands */
	forn(i, n) {
		band = seq[i];
		if (dislike(band)) continue;
		bands[band].cnt += 1;
	}

	//forn(i, m) printf("%2d: %2d\n", bands[i].id+1, bands[i].cnt);

	nchanges = 0;

	/* deal with dislikes */
	//printf("------dislikes: \n");
	low = nextlow(0, avg);
	forn(i, n) {
		if (low >= m) break;
		band = seq[i];
		if (like(band)) continue;

		//printf("seq[%2d]: %2d --> %2d\n", i+1, seq[i]+1, low+1);
		bands[low].cnt += 1;
		add_order(low, i);
		seq[i] = low;
		++nchanges;

		low = nextlow(low, avg);
	}

	/* avgerize */
	//printf("----likes:\n");
	low = nextlow(0, avg);
	up = nextup(0, avg);
	while (low < m && up < m) {
		bands[low].cnt += 1;
		bands[up].cnt -= 1;

		order = del_order(up);
		add_order(low, order);
		//printf("del_order(%2d): %2d\n", up+1, order+1);

		//printf("seq[%2d]: %2d --> %2d\n", order+1, seq[order]+1, low+1);
		seq[order] = low;
		++nchanges;

		low = nextlow(low, avg);
		up = nextup(up, avg);
	}

	/* print result */
	printf("%d %d\n", avg, nchanges);
	forn(i, n) printf("%d ", seq[i]+1);
	printf("\n");
	
	return 0;
}
