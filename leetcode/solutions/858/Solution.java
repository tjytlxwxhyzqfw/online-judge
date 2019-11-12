/**
 * 858 Mirror Reflection 52.3% Medium 148/243
 * Performance: speed=%, memory=%
 */

// set primary point at at left-bottom corner
// k = -k after reflection
// we have x, y, k, b and we are able to cal next line (x, y, k, b)

import java.util.*;

public class Solution {
	public int mirrorReflection(int p, int q) {
		double[] xykb = new double[]{0, 0, (double)q/p, 0};
		int corner;
		while ((corner = cornerNum(xykb[0], xykb[1], p)) == -1) nextXYKB(xykb, p);
		return corner;
	}

	void nextXYKB(double[] xykb, double a) {
		// y = kx + b
		double x = xykb[0], y = xykb[1], k = xykb[2], b = xykb[3];

		// (x=0, y=b), (x=a, y=ka+b), (y=0, x=-b/k), (y=a, x=(a-b)/k)
		// 
		// (y-y1)/(x-x1) = k
		// y-y1 = kx - kx1
		// y = kx + (y1-kx1)
		double[] xs = new double[]{0, a, -b/k, (a-b)/k};
		double[] ys = new double[]{b, k*a+b, 0, a};
		double k1 = -k;
		double[] bs = new double[]{b, k*a+b-k1*a, 0 - k1 * (-b/k), a - k1 * ((a-b)/k)};
		for (int i = 0; i < 4; ++i) {
			if (0 <= xs[i] && xs[i] <= a && 0 <= ys[i] && ys[i] <= a && (xs[i] != x || ys[i] != y)) {
				xykb[0] = xs[i];
				xykb[1] = ys[i];
				xykb[2] = k1;
				xykb[3] = bs[i];
			}
		}
		System.out.printf("y=%6.3fx+%6.3f -> y=%6.3fx+%6.3f\n", k, b, k1, xykb[3]);
	}

	int cornerNum(double x, double y, double a) {
		if (x == a && y == 0) return 0;
		if (x == a && y == a) return 1;
		if (x == 0 && y == a) return 2;
		return -1;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.mirrorReflection(2, 1) == 2;
		assert s.mirrorReflection(4, 1) == 2;

		System.out.println("done");
	}
}

