/**
 * 963 Minimum Area Rectangle II 46.7% Medium 98/155
 * Performance: speed=5%, memory=33%
 */

import java.util.*;

public class Solution {
	public double minAreaFreeRect(int[][] intPoints) {
		double[][] points = new double[intPoints.length][];
		for (int i = 0; i < intPoints.length; ++i) {
			points[i] = new double[]{intPoints[i][0], intPoints[i][1]};
		}
		double min = Double.MAX_VALUE;
		for (int i = 0; i < points.length; ++i) {
			for (int j = i+1; j < points.length; ++j) {
				for (int k = j+1; k < points.length; ++k) {
					for (int l = k+1; l < points.length; ++l) {
						double[][] p4 = new double[][]{points[i], points[j], points[k], points[l]};
						double a = area4(p4);
						if (a != 0) min = Math.min(min, a);
						// System.out.printf("%d, %d, %d, %d => %g (min=%g)\n", i, j, k, l, a, min);
					}
				}
			}
		}

		return min == Double.MAX_VALUE ? 0 : min;
	}

	double area4(double[][] points) {
		double c0 = (points[0][0] + points[1][0] + points[2][0] + points[3][0]) / 4.;
		double c1 = (points[0][1] + points[1][1] + points[2][1] + points[3][1]) / 4.;
		double[] c = new double[]{c0, c1};

		double[] d = new double[4];
		for (int i = 0; i < 4; ++i) d[i] = dist(c, new double[]{points[i][0], points[i][1]});
		Arrays.sort(d);
		if (d[0] != d[3]) return 0;

		return area3(c, points[0], points[1])
			+ area3(c, points[1], points[2])
			+ area3(c, points[2], points[3])
			+ area3(c, points[3], points[0])
			+ area3(c, points[0], points[2])
			+ area3(c, points[1], points[3]);
	}

	double dist(double[] p ,double[] q) {
		return Math.sqrt((p[0]-q[0]) * (p[0]-q[0]) + (p[1]-q[1]) * (p[1]-q[1]));
	}

	double area3(double[] p1, double[] p2, double[] p3) {
		double x = dist(p1, p2), y = dist(p2, p3), z = dist(p3, p1);
		double p = (x + y + z) / 2;
		return Math.sqrt(p * (p-x) * (p-y) * (p-z));
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.minAreaFreeRect(new int[][]{{1, 2}, {2, 1}, {1, 0}, {0, 1}}) - 2 <= 1e-5;
		assert s.minAreaFreeRect(new int[][]{{0, 1}, {2, 1}, {1, 1}, {1, 0}, {2, 0}}) - 1 <= 1e-5;
		assert s.minAreaFreeRect(new int[][]{{0,3},{1,2},{3,1},{1,3},{2,1}}) <= 1e-5;
		assert s.minAreaFreeRect(new int[][]{{3,1},{1,1},{0,1},{2,1},{3,3},{3,2},{0,2},{2,3}}) - 2 <= 1e-5;
		
		System.out.println("done");
	}
}
