/**
 * 715B
 *
 * 错误:
 * 1.只给g[u][v]赋值, 不给g[v][u]赋值
 * 2.缓冲区数组vs的下标vi忘记初始化
 * 3.N 写成 sizeof(N) ??????? 有毛病
 */

#include <stdio.h>

#include "common.h"
#include "heap.h"

int compare_t(const void *x, const void *y);
int compare_s(const void *x, const void *y);
void sethi(void *x, const int i);

void print_edge();

#define E 10000
#define N 1000

int n, m, L, s, t;
int g[N][N], e[E][3];

struct heap *h;
char color[N];
int dt[N], ds[N], hidx[N+1];
int vs[N], vi = 0;

int *inta(int x)
{
	vs[vi] = x;
	return &vs[vi++];
}

void dijkstra_t(int start)
{
	//printf("dijkstra_t\n");

	int u, v, i, *p, cost;

	vi = 0;
	h->cmp = compare_t;
	h->last = 0;
	memset(color, 0, sizeof(color));
	forn(i, n) dt[i] = INT_MAX;

	heap_insert(inta(start), h);
	color[start] = 1;
	dt[start] = 0;

	while ((p = heap_del(h)) != NULL) {
		color[u = *p] = 2;
		forn(v, n) {
			if (g[u][v] == 0) continue;
			cost = dt[u] + g[u][v];
			IOF(cost);
			if (dt[v] <= cost) continue;

			//printf("%2d: %d -> %d\n", v, dt[v], cost);

			dt[v] = cost;
			if (color[v] == 0) {
				heap_insert(inta(v), h);
				color[v] = 1;
			} else heap_pup(hidx[v], h);
		}
	}

	//forn(i, n) printf("%2d: %d\n", i, dt[i]);
}

void dijkstra_s(int start)
{
	//printf("dijkstra_s\n");

	int u, v, i, *p, cost;

	vi = 0;
	h->cmp = compare_s;
	h->last = 0;
	memset(color, 0, sizeof(color));
	forn(i, n) ds[i] = INT_MAX;

	heap_insert(inta(start), h);
	color[start] = 1;
	ds[start] = 0;

	while ((p = heap_del(h)) != NULL) {
		color[u = *p] = 2;
		forn(v, n) {
			if (g[u][v] == 0) continue;
			if (g[u][v] == INT_MAX) {
				//FIXME: change BOTH edges please!!!
				g[v][u] = g[u][v] = L - dt[v] - ds[u];
				if (g[u][v] < 1) g[v][u] = g[u][v] = 1;
			}
			cost = ds[u] + g[u][v];
			IOF(cost);
			if (ds[v] <= cost) continue;

			//printf("%2d: %d -> %d\n", v, ds[v], cost);

			ds[v] = cost;
			if (color[v] == 0) {
				heap_insert(inta(v), h);
				color[v] = 1;
			} else heap_pup(hidx[v], h);
		}
	}

	//forn(i, n) printf("%2d: %d\n", i, ds[i]);
}

int main(void)
{
	int i, u, v, d;

	h = heap_alloc(N, NULL, sethi);
	memset(g, 0, sizeof(g));

	freopen("Inputs/715B", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d%d%d%d%d", &n, &m, &L, &s, &t);
	forn(i, m) {
		scanf("%d%d%d", &u, &v, &d);
		if (d == 0) d = INT_MAX;
		g[u][v] = g[v][u] = d;
		e[i][0] = u, e[i][1] = v, e[i][2] = d;
	}

	dijkstra_t(t);

	if (dt[s] < L) {
		printf("NO\n");
		return 0;
	}

	dijkstra_s(s);

	if (ds[t] != L) {
		printf("NO\n");
	} else {
		printf("YES\n");
		print_edge();
	}

	return 0;
}

void print_edge()
{
	int i, u, v, d;

	forn(i, m) {
		u = e[i][0], v = e[i][1];
		d = (e[i][2] == INT_MAX ? g[u][v] : e[i][2]);
		printf("%d %d %d\n", u, v, d);
	}
}

int compare_t(const void *x, const void *y)
{
	return dt[*(int*)x] - dt[*(int*)y];
}

int compare_s(const void *x, const void *y)
{
	return ds[*(int*)x] - ds[*(int*)y];
}

void sethi(void *x, const int i)
{
	hidx[*(int*)x] = i;
}

