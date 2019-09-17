/**
 * 478	Generate Random Point in a Circle 37.4% Medium
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class Solution {
	double r, xc, yc;
	public Solution(double r, double xc, double yc) {
		this.r = r;
		this.xc = xc;
		this.yc = yc;
	}

	// Performance: speed=76%, memory=100%
	public double[] randPoint_1() {
		double r1 = r * Math.sqrt(Math.random());
		double alpha = .5 * Math.PI * Math.random();
		double y = r1 * Math.sin(alpha), x = Math.sqrt(r1 * r1 - y * y);
		double rand = Math.random();
		if (0 <= rand && rand < .25) { y *= -1; x *= -1; }
		else if (.25 <= rand && rand < .5) y *= -1;
		else if (.5 <= rand && rand < .75) x *= -1;
		return new double[]{x+xc, y+yc};
	}

        // Performance: speed=22%, memory=100%
        public double[] randPoint() {
                double r1 = r * Math.sqrt(Math.random());
                double alpha = 2. * Math.PI * Math.random();
                double y = r1 * Math.sin(alpha), x = r1 * Math.cos(alpha);
                return new double[]{x + xc, y + yc};
        }

	public static void main(String args[]) {
		Solution s = new Solution(1, 0, 0);
		int[] d = new int[4];
		for (int i = 0; i < 10000; ++i) {
			double[] p = s.randPoint();
			if (p[0] > 0 && p[1] > 0) ++d[0];
			else if (p[0] > 0) ++d[1];
			else if (p[1] > 0) ++d[2];
			else ++d[3];
		}
		System.out.printf("%d, %d, %d, %d\n", d[0], d[1], d[2], d[3]);
	}
}

