/**
 * 1517 A Multiplication Game
 */

#include <stdio.h>
#include <stdlib.h>

#define STAN 0
#define OLLIE 1

#define U -1
#define N 0
#define P 1

#define NMAX 4294967295U

unsigned number;
unsigned states[2000000];
int pns[2000000];
int nstates;

void store(unsigned state, int res)
{
	states[nstates] = state;
	pns[nstates] = res;
	++nstates;
}

int find(unsigned state)
{
	int i;

	for (i = 0; i < nstates; ++i) {
		if (states[i] == state)
			return pns[i];
	}

	return U;
}

int dfs(int deepth, unsigned state)
{
	int i, ndeepth;
	unsigned nstate;
	int ret;

	//printf("dfs: %3d, %10u\n", deepth, state);
	//getchar();

	if (state >= number)
		return P;

	ret = find(state);
	if (ret != U)
		return ret;

	ndeepth = deepth + 1;
	for (i = 2; i <= 9; ++i) {
		nstate = state * i;
		if (nstate < state)
			nstate = NMAX;
		if (dfs(ndeepth, nstate) == P) {
			store(state, N);
			return N; 
		}
	}

	store(state, P);
	return P;
}

int init_a_case()
{
	nstates = 0;

	if (scanf("%d", &number) != 1)
		return 0;

	if (number < 0) {
		printf ("sorry\n");
		return 0;
	}

	return 1;
}

int main(void) {	
	int winner;

	//freopen("Inputs/1517", "r", stdin);
	setbuf(stdout, NULL);

	while (init_a_case()) {	
		winner = dfs(0, 1);	
		printf("%s wins.\n", winner == STAN ? "Stan" : "Ollie");
	}

	return 0;
}
		
