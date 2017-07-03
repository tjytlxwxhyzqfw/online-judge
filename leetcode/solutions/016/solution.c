/*
 * 16
 *
 * 找到三个数的和,它们想加最接近target
 */
#include <stdio.h>
#include <stdlib.h>

#include "common.h"

#define N 1000
#define V 1000

int *nums, taridx, target;
int s[N][4][V], t[N][4][V];
int leftmost, maxidx, length;

void dpFillLast();
void dpFill(int i, int j, int k);
int ongte(int greater, int lesser);
void printinfo(int i, int j, int k);


int threeSumClosest(int *ns, int len, int tar)
{
	int minv, maxv, it;
	int i, j, k;

	if (len < 3) return -1;

	nums = ns;
	length = len;
	target = tar;

	minv = INT_MAX, maxv = INT_MIN;
	forn(it, length) {
		if (nums[it] > maxv) maxv = nums[it];
		if (nums[it] < minv) minv = nums[it];
	}
	leftmost = min(0, 3 * minv);
	maxidx = max(0, 3 * maxv) - leftmost;
	taridx = target - leftmost;

	dpFillLast();
	for (i = length-2; i >= 0; --i)
		for (j = 0; j < 4; ++j)
			for (k = 0; k <= maxidx; ++k)
				dpFill(i, j, k);
	int zero = -1 * leftmost;
	if (s[0][3][zero] < t[0][3][zero])
		return target - s[0][3][zero];
	else
		return target + t[0][3][zero];
}

void dpFillLast()
{
	int i, j, k;

	i = length - 1;
	for (j = 0; j < 4; ++j) {
		for (k = 0; k <= maxidx; ++k) {
			if (j == 2 || j == 3) {
				s[i][j][k] = t[i][j][k] = INT_MAX;
			 } else if (j == 1) {
				s[i][j][k] = ongte(taridx, k + nums[i]);
				t[i][j][k] = ongte(k + nums[i], taridx);
			} else if (j == 0) {
				s[i][j][k] = ongte(taridx, k);
				t[i][j][k] = ongte(k, taridx);
			} else assert(0);
			//printinfo(i, j, k);
		}
	}

}

void dpFill(int i, int j, int k)
{
	int use, skp;
	int ob = 0;

	/* s for not-greater-than */
	if (!ir(j-1, 0, 4) || !ire(k+nums[i], 0, maxidx))
		ob = 1;

	use = ob ? INT_MAX : s[i+1][j-1][k+nums[i]];
	skp = s[i+1][j][k];
	if (k + nums[i] > taridx)
		use = INT_MAX;
	s[i][j][k] = min(use, skp);


	/* t for not-less-than */
	use = ob ? INT_MAX : t[i+1][j-1][k+nums[i]];
	skp = t[i+1][j][k];
	t[i][j][k] = min(use, skp);

	//printinfo(i, j, k);
}

int ongte(int greater, int lesser)
{
	if (greater < lesser)
		return INT_MAX;
	return greater - lesser;
}

void printinfo(int i, int j, int k) {
	printf("\ns[%2d][%2d][%2d]: %2d\n", i, j, k, s[i][j][k]);
	printf("t[%2d][%2d][%2d]: %2d\n\n", i, j, k, t[i][j][k]);
}

int main(void)
{
	int nums0[] = {13,34,8,91,0,-47,52,23,76,14,0,-9,22,49,-1,68,49,-83,-34,5,38,3,47,-2,-73,-29,19,-4,-3,-16,89,52,18,27,40,88,-84,-68,84,53,52,28,-86,-80,18,-93,11,77,11,-83,69,-29,-26,-83,32,65,-49,-88,-24,-56,95,-82,-25,-69,-27,20,-87,-49,78,89,100,26,45,-15,47,77,86,46,82,-80,-31,72,-82,-63,-50,35,-36,-30,-40,82,83,-61,-49,-11,88,73,-23,2,63,29,-82,95,-91,31,-35,-84,37,-86,-17,-84,-54,-89,32,13,-21,73,-73,53,-57,-60,62,-43,54,52,91,-7,23,-53,53,-82,-75,43,21,76,45,-2,-46,-39,-39,-3,24,6,-73,34,58,-67,35,45,-72,-67,-57,-22,-81,68,-84,-15,14,-87,14,-45,-68,4,-88,-25,-36,-74,-27,27,-23,26,-99,-47,97,32,53,82,-89,91,-1,-67,-74,-97,-36,7,-51,-100,-74,28,-12,-46,-37,87,80,-33,-58,51,5};
	//int nums0[] = {1, 2, 3};
	int targetcase = 10000;
	int size = sizeof(nums0) / sizeof(int);
	printf("result: %d\n", threeSumClosest(nums0, size, targetcase));
	return 0;
}
