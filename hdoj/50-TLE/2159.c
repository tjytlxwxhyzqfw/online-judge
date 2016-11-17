/*
 * 2159 FATE
 *
 * Primary DP
 *
 * ... My ans is the the most exps one can gain, 
 * while the most remainder of durability is required.
 */

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

//#include "debug.c"

struct function {
	void *private;
	int (*apply)(const struct function *function, const void *params);
};

struct function *function_new(void *private, int (*apply)(const struct function *, const void *))
{
	struct function *f;

	assert(f = malloc(sizeof(struct function)));
	f->private = private;
	f->apply = apply;

	return f;
}

#define function_call(fp,p) ((fp->apply)(fp,p));

struct state {
	int mid;
	int exp;
	int kil;
};

struct globals {
	int kills;
	int *drop;
        int *exp;
        int kils;
        int exps;
        int durabs;
        int mids;
};

struct globals *globals;
int s[100][100][100];

int next_mid(const struct function *f, const void *p)
{
	const struct globals *globals = f->private;
	const struct state *current = p;

	//printis(0, 0, "next_mid: %3d\n", current->mid + 1);
	return current->mid + 1;

}

int next_exp(const struct function *f, const void *p)
{
	const struct globals *globals = f->private;
	const struct state *current = p;

	int this_exp, increment, total, base;
	int kills;

	kills = globals->kills;
	this_exp = globals->exp[current->mid];
	base = current->exp;

	increment = kills * this_exp;
	total = base + increment;

	//printis(0, 0, "next_exp: %3d\n", total);
	return total;
}

int next_kil(const struct function *f, const void *p)
{
	const struct globals *g = f->private;
	const struct state *s = p;

	int kills, extra;

	kills = g->kills;
	extra = s->kil;
	
	//printis(0, 0, "next_kil: %3d\n", kills + extra);
	return extra + kills;
}

void next_state(struct state *next, const struct state *current, const int decision)
{
	struct function *f0, *f1, *f2;

	f0 = function_new(globals, next_mid);
	f1 = function_new(globals, next_exp);
	f2 = function_new(globals, next_kil);

	globals->kills = decision;

	next->mid = function_call(f0, current);
	next->exp = function_call(f1, current);
	next->kil = function_call(f2, current);

	free(f0);
	free(f1);
	free(f2);
}

int value_trivial(const struct state *current)
{
	int mid;
	int exp, need, this_exp;
	int extra, kil, kills;
	int this_drop, min;
	float precise;

	mid = current->mid;
	exp = current->exp;
	kil = current->kil;

	this_exp = globals->exp[current->mid];
	this_drop = globals->drop[current->mid];

	need = globals->exps - exp;
	if (need <= 0) {
		min = 0;
		goto complete;
	}

	precise = 1.0 * need / this_exp;
	extra = need / this_exp;
	if (extra != precise)
		extra += 1;
	
	kills = extra + kil;
	if (kills > globals->kils) {
		min = 100;
		goto complete;
	}

	min = this_drop * extra;

	complete:
	//printis(0, 0, "trivial: mid: %2d, exp: %3d, kil: %2d mindrop: %3d\n", mid, exp, kil, min);
	s[mid][exp][kil] = min;
	return min;
}

int value_general(const struct state *current)
{
	struct state next;
	int mid;
	int exp;
	int kil, maxkil, k;
	int this_drop, drops, total, next_drops, min;

	mid = current->mid;
	exp = current->exp;
	kil = current->kil;

	this_drop = globals->drop[mid];
	this_exp = globals->exp[mid];

	//printis(0, 0, "\ngeneral: mid: %2d, exp: %3d, kil: %2d\n", mid, exp, kil);

	maxkil = globals->kils - kil;
	min = 100;
	for (k = 0; k <= maxkil; ++k) {
		//printis(1, 0, "k: %2d\n", k);
		next_state(&next, current, k);
		drops = this_drop * k;
		//printis(1, 0, "drops: %3d\n", drops);
		next_drops = s[next.mid][next.exp][next.kil];
		//printis(1, 0, "next: mid: %2d, exp: %2d, kil: %2d, mindrops: %3d\n",
				//next.mid, next.exp, next.kil, next_drops);
		total = drops + next_drops;
		//printis(1, 0, "total: %3d\n", total);
		if (total < min)
			min = total;
		//printis(1, 0, "min: %3d\n\n", min);
	}

	s[mid][exp][kil] = min;
	//printis(0, 0, "general: mid: %2d, exp: %3d, kil: %2d mindrop: %3d\n", mid, exp, kil, min);
	return min;
}

int fill_s(struct state *st)
{
	if (st->mid == globals->mids - 1)
		return value_trivial(st);
	return value_general(st);
}

int main(void)
{
	int i, j, k;
	int exps, durabs, mids, kils;
	int exp[100], drop[100];
	int mindrops;
	struct state st;
	struct globals glbs;

	freopen("Inputs/2159", "r", stdin);
	setbuf(stdout, NULL);

	globals = &glbs;

	while (scanf("%d%d%d%d", &exps, &durabs, &mids, &kils) == 4) {
		for (i = 0; i < mids; ++i)
			scanf("%d%d", exp + i, drop + i);

		globals->drop = drop;
		globals->exp = exp;
		globals->kils = kils;
		globals->exps = exps;
		globals->durabs = durabs;
		globals->mids = mids;

		for (i = mids - 1; i >= 1; --i) {
			st.mid = i;
			for (j = 0; j <= exps; ++j) {
				st.exp = j;
				for (k = 0; k <= kils; ++k) {
					st.kil = k;
					//printis(0, 0, "\nfill_s: (%2d, %2d, %2d)\n", i, j, k);
					fill_s(&st);
				}
			}
		}

		st.mid = 0;
		st.exp = 0;
		st.kil = 0;
		fill_s(&st);

		mindrops = s[0][0][0];
		printf("%d\n", mindrops <= durabs ? durabs - mindrops : -1);
	}

	return 0;
}
