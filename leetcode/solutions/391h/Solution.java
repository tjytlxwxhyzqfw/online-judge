/**
 * 391 Perfect Rectangle
 * pass=30 ud=372/73 s= m=
 */

import java.util.*;

// 20200725: 不写了, 操, 注意一个坑, 那就是[3, 3, 4, 4]这个区间, 划分4个象限的时候, 会再出一个[3, 3, 4, 4], 无限循环+永动

public class Solution {
	public boolean isRectangleCover(int[][] recs) {
		if (recs == null || recs.length==0) return false;
		int minLeft = recs[0][1], minDown = recs[0][0], maxRght = recs[0][3], maxCeil = recs[0][2];
		for (int i = 1; i < recs.length; ++i) {
			if (recs[i][1] < minLeft) minLeft = recs[i][1];
			if (recs[i][0] < minDown) minDown = recs[i][0];
			if (recs[i][3] > maxRght) maxRght = recs[i][3];
			if (recs[i][2] > maxCeil) maxCeil = recs[i][2];
		}
		Node node = new Node(new int[]{minLeft, minDown, maxRght, maxCeil});

		long area = (maxRght-minLeft) * (maxCeil-minDown);
		for (int i = 0; i < recs.length; ++i) {
			int[] rec = new int[]{recs[i][1], recs[i][0], recs[i][3], recs[i][2]};
			node.add(rec);
			long recArea = (rec[2]-rec[0]) * (rec[3]-rec[1]);
			long count = node.count(rec);
			if (count != recArea) return false;
			area -= recArea;
		}
		return area == 0;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		s.isRectangleCover(new int[][]{{1,1,3,3},{3,1,4,2}, {1,3,2,4}, {2,2,4,4}});

		System.out.println("done");
	}
}

class Node {
	// the base point (原点) is located at the bottom-left corner of the screen
	int left, down, rght, ceil;
	long total;
	int[][] quadrants; // 象限
	// quadrant nodes, one node per quadrants, so there are 4 of them
	Node [] qNodes; 

	Node(int[] rec) {
		left = rec[0];
		down = rec[1];
		rght = rec[2];
		ceil = rec[3];
		assert left <= rght && down <= ceil;

		qNodes = new Node[4];
		total = 0;

		// the 4 quartiles
		int cx = left + (rght-left)/2, cy = down + (ceil-down)/2;
                quadrants = new int[][]{
                        {cx, cy, rght, ceil},
                        {left, cy, cx-1, ceil},
                        {left, down, cx-1, cy-1},
                        {cx, down, rght, cy-1}
                };

		System.out.printf("node created: [%d, %d, %d, %d]\n", left, down, rght, ceil);
	}

	long count(int []rec) {
		if (same(rec)) return total;
		long sum = 0;
		for (int i = 0; i < quadrants.length; ++i) {
			int[] intersec = intersection(quadrants[i], rec);
			if (intersec == null) continue;
			if (qNodes[i] == null) continue;
			sum += qNodes[i].count(intersec);
		}
		return sum;
	}

	void add(int[] rec) {
		System.out.printf("add [%d, %d, %d, %d] into [%d, %d, %d, %d]\n",
			rec[0], rec[1], rec[2], rec[3], left, down, rght, ceil);

		total += (rght-left+1) * (ceil-down+1);

		if (left == rec[0] && down == rec[1] && rght == rec[2] && ceil == rec[3]) return;

		for (int i = 0; i < quadrants.length; ++i) {
			int[] intersec = intersection(quadrants[i], rec);
			if (intersec == null) continue;
			System.out.printf("intersec with [%d, %d, %d, %d]@%d: [%d, %d, %d, %d]\n",
				quadrants[i][0], quadrants[i][1], quadrants[i][2], quadrants[i][3],
				i, intersec[0], intersec[1], intersec[2], intersec[3]);
			if (qNodes[i] == null) qNodes[i] = new Node(quadrants[i]);
			qNodes[i].add(intersec);
		}
	}

	boolean same(int []rec) { 
		return left == rec[0] && down == rec[1] && rght == rec[2] && ceil == rec[3];
	}

	int[] intersection(int[] r, int[] s) {
		int left = Math.max(r[0], s[0]);
		int rght = Math.min(r[2], s[2]);
		int down = Math.max(r[1], s[1]);
		int ceil = Math.min(r[3], s[3]);
		if (left > rght || down > ceil) return null;
		return new int[]{left, down, rght, ceil};
	}
}

