/*
 * 2059 龟兔赛跑
 *
 * Primary DP, DFS-DP
 * Assume C, the distance Gui can rush after fully charged,
 * is less than 100,000
 */

#include <stdio.h>
#include <stdlib.h>

//#include "debug.c"

float s[100][100001][2];
int position[100];
int destination;
int stations, distance;
float charge, v0, v1, v2;

#define T(delta,rush) ((rush)>(delta)?(delta)/v1:((rush)/v1)+((delta)-(rush))/v2)
#define R(delta,rush) ((rush)>(delta)?((rush)-(delta)):0)

float fill_s(const int sid, int rush, const int stop)
{
	float time, time0, time1, time2, extra;
	int remainder, delta;
	int next;

	//printis(0, 0, "fill_s: sid: %2d, rush: %3d, stop: %d\n", sid, rush, stop);

	if (sid == stations - 1) {
		delta = destination - position[sid];
		time = (stop ? charge + T(delta, (rush = distance)) : T(delta,rush));
		goto complete;
	}

	next = sid + 1;
	delta = position[next] - position[sid];
	extra = 0.0;
	if (stop) {
		rush = distance;
		extra = charge;
	}

	time0 = extra + T(delta, rush);
	//printis(0, 0, "time0: extra: %6.3f, T: %6.3f\n", extra, T(delta, rush));
	remainder = R(delta, rush);
	time1 = s[next][remainder][0];
	//printis(0, 0, "time1: next: %2d, remainder: %3d, stop: 0, %6.3f\n", next, remainder, 0, time1);
	time2 = s[next][distance][1];
	//if (time2 > time1)
		//printis(0, 0, "charge @: %2d\n", next);
	//printis(0, 0, "time2: next: %2d, remainder: %3d, stop: 0, %6.3f\n", next, distance, 1, time2);
	time = time0 + (time1 < time2 ? time1 :time2);

	complete:
	//printf("s[%2d][%2d][%d]: %6.3f\n", sid, rush, stop, time);
	s[sid][rush][stop] = time;
	return time;
}

int main(void)
{
	int i, j;
	float time;
	int remainder;

	freopen("Inputs/2059", "r", stdin);
	setbuf(stdout, NULL);

	while (scanf("%d", &destination) == 1) {
		scanf("%d%d%f", &stations, &distance, &charge);
		scanf("%f%f%f", &v0, &v1, &v2);
		for (i = 0; i < stations; ++i)
			scanf("%d", position + i);
		for (i = stations - 1; i >= 0; --i) {
			for (j = 0; j <= distance; ++j) {
				fill_s(i, j, 0);
				fill_s(i, j, 1);
			}
		}

		remainder = R(position[0], distance);
		time = s[0][remainder][0];
		if (time > s[0][distance][1])
			time = s[0][distance][1];
		time += T(position[0], distance);
		//printf("remainder: %d base: %6.3f\n", remainder, T(position[0], distance));

		//printf("tortoise: %6.3f, rbbit: %6.3f\n", time, destination / v0);
		printf("%s\n", time > destination / v0 ? "Good job,rabbit!" : "What a pity rabbit!");
	}

	return 0;
}
				
