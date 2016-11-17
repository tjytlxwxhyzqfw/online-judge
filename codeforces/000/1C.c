/*
 * 1C
 *
 * hard math !!!
 */

#include <math.h>
#include <stdio.h>

#include "comgeo.c"

double atan2_relative_to(const double x, const double y,
	const double x0, const double y0)
{
	return atan2(x - x0, y - y0);
}

double px1, py1, px2, py2, px3, py3;
double center_x, center_y;

void rotate_on_parallel(double xa, double ya, 
	double xb, double yb,
	double xc, double yc)
{
	return;
}

int check(int n)
{
	double target, delta0, delta;

	delta0 = atan2_relative_to(xa, ya, center_x, center_y);

	return 1;
}

int main(void)
{
	double k1, b1, k2, b2;
	int n;

	while (scanf("%f%f%f%f%f%f", &px1, &py1, &px2, &py2, &px3, &py3) == 6) {
		rotate_on_parallel();
		perpendicular_bisector(px1, py1, px2, py2, &k1, &b1);
		perpendicular_bisector(px1, py1, px3, py3, &k2, &b2);
		crossover_point(k1, b1, k2, b2, &center_x, &center_y);
		for (n = 3; n <= 100; ++n)
			if (check(n))
				break;
		printf("n: %d\n", n);
	}

	return 0;
}
