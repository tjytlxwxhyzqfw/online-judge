/*
 * 1203 I NEED A OFFER!
 *
 * Primary DP
 *
 * A fill-style memory saving dp;
 */
#include <stdio.h>
#include <stdlib.h>

//#include "debug.c"

#define P(p1,p2) (1-(1-(p1))*(1-(p2)))

int nuids, all;
float results1[10001], results2[10001], probs[10001];
int tuits[10001];

void fill_layer(float *currentp, const float *lastp, const uid)
{
	float p, p1, p2;
	int t, i, rest;

	p = probs[uid];
	t = tuits[uid];

	for (i = 0; i <= all; ++i) {
		p1 = lastp[i];
		rest = i - t;
		p2 = (rest < 0 ? 0 : P(p, lastp[rest]));
		currentp[i] = (p1 > p2 ? p1 : p2);
		//printis(0, 0, "uid: %2d, tuition: %3d, prob: %.3f\n", uid, i, currentp[i]);
	}
}
	
int main(void)
{
	int i;
	float *currentp, *lastp, *tmp, p;
	int t;

	freopen("Inputs/1203", "r", stdin);
	setbuf(stdout, NULL);

	while (scanf("%d%d", &all, &nuids)) {
		if (nuids == 0 && all == 0)
			break;

		for (i = 0; i < nuids; ++i) {
			scanf("%d%f", &t, &p);
			tuits[i] = t;
			probs[i] = p;
		}

		
		currentp = results1;
		/* init last layer */
		lastp = results2;
		p = probs[nuids - 1];
		t = tuits[nuids - 1];
		for (i = 0; i < t; ++i)
			lastp[i] = 0;
		for (; i <= all; ++i)
			lastp[i] = p;

		/* fill all other layers */
		for (i = nuids - 2; i >= 0; --i) {
			fill_layer(currentp, lastp, i);
			tmp = lastp;
			lastp = currentp;
			currentp = tmp;
		}

		printf("%.1f%%\n", 100 * lastp[all]);
	}

	return 0;
}
