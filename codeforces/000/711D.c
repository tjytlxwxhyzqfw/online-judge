/*
 * 711D
 */

#include <assert.h>
#include <stdio.h>
#include <stdlib.h>

#define MAGIC 1000000007
//FIXME: #define correct(p) (*(p) = (*(p) < 0 ? *(p) + MAGIC : *(p)))
#define correct(p) (*(p) = (*(p) <= 0 ? *(p) + MAGIC : *(p)))

#include "debug.c"

struct node {
	int color;
	int nid;
	int nextid;
	int circle;
};

int nnodes;
#define for_each_node(i) for((i) = 1; (i) <= (nnodes); (i) += 1)

struct node nodes[200001];

int dfs(int nid, int dfsid)
{
	int nextid;

	assert(nodes[nid].color == 0);
	nodes[nid].color = dfsid;
	nextid = nodes[nid].nextid;
	if (nodes[nextid].color == dfsid)
		nodes[nextid].circle = 1;
	else if (nodes[nextid].color == 0)
		dfs(nextid, dfsid);
}

int tag_a_circle(int nid)
{
	int ncnodes = 0;
	int i;

	printis(1, 0, "circle: %d, ", nid);

	assert(nodes[nid].circle == 1);
	nodes[nid].circle = -1;
	++ncnodes;
	for (i = nodes[nid].nextid; nodes[i].circle == 0; i = nodes[i].nextid) {
		printis(0, 0, "%d, ", i);
		nodes[i].circle = -1;
		++ncnodes;
	}

	printis(0, 0, "\n");
	printis(1, 0, "ncnodes: %d\n", ncnodes);

	return ncnodes;
}

long long pow2mod(int n)
{
	long long power = 1;
	while (n-- > 0)
		power = (power * 2) % MAGIC;
	return power;
}
		
long long compute(void)
{
	int i, nouters, ninners;
	long long result = 1, nways, power;

	nouters = nnodes;
	for_each_node(i) {
		if (nodes[i].circle == 1) {
			ninners = tag_a_circle(i);
			nouters -= ninners;

			//FIXME: nways = pow2mod(ninners) - 2;
			nways = pow2mod(ninners) - 2;
			correct(&nways);
			//FIXME: result = (result * nways) % MAGIC;
			result = (result * nways) % MAGIC;
		}
	}

	result = (pow2mod(nouters) * result) % MAGIC;
	return result;
}

int main(void)
{
	int i;

	freopen("Inputs/711D", "r", stdin);
	setbuf(stdout, NULL);

	scanf("%d", &nnodes);
	printis(0, 0, "nnodes: %d\n", nnodes);
	for_each_node(i) {
		scanf("%d", &nodes[i].nextid);
		nodes[i].color = 0;
		nodes[i].nid = i;
		nodes[i].circle = 0;
	}

	for_each_node(i) {
		if (nodes[i].color == 0)
			dfs(i, i);
	}

	printf("%lld\n", compute());

	return 0;
}

/*
* int dfs(int nid)
* {
* 	int nextid;
* 
* 	assert(nodes[nid].color == 0);
*
* 	nodes[nid].color = 1;
* 	nextid = nodes[nid].nextid;
*
* 	//不亏, 图中找环的算法
* 	printis(2, 0, "dfs: nid: %3d, nextid: %3d\n", nid, nextid);
* 	if (nodes[nextid].color > 0) {
* 		//FIXME!!!!!!!!!!!!!!!!: ++nodes[nextid].color;
* 		++nodes[nextid].color;
* 		return;
* 	}
* 	dfs(nextid);
* }
*/
