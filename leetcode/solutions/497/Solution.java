/**
 * 497: Random Point in Non-overlapping Rectangles
 * Performance: speed=85%, memory=90%
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Solution {
	private Random rand = new Random(47);
	private int[][] rects;
	private int[] areas;

	public Solution(int[][] rects) {
		this.rects = rects;
		areas = new int[rects.length];
		for (int i = 0; i < rects.length; ++i) {
			int area = (rects[i][2] - rects[i][0] + 1) * (rects[i][3] - rects[i][1] + 1);
			areas[i] = area;
			if (i > 0) {
				areas[i] += areas[i-1];
			}
		}
        }
    
	public int[] pick() {
		int area = 1 + rand.nextInt(areas[areas.length-1]);
		int recid = lb(area, areas);
		int pointid = area - 1;
		if (recid > 0) {
			pointid -= areas[recid-1];
		}
		return position(rects[recid]);
	}

	private int lb(int x, int[] a) {
		int i = 0, j = a.length;
		while (i < j) {
			int k = i + (j - i) / 2;
			if (a[k] < x)
				i = k + 1;
			else
				j = k;
		}
		return i;
	}

	// i don't kown the meaing of x, y so i don't use this function
	private int[] position(int index, int[] rect) {
		int width = rect[2] - rect[0];
		int y = index / width + rect[1];
		int x = index % width + rect[0];
		return new int[]{x, y};
	}

	private int[] position(int[] rect) {
		int first = rand.nextInt(rect[2] - rect[0] + 1);
		int second = rand.nextInt(rect[3] - rect[1] + 1);
		return new int[]{rect[0] + first, rect[1] + second};
	}

	public static void main(String args[]) {
	}
}

