/**
 * 223 Rectangle Area
 * Performance: speed=%, memory=%
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
		int sum = (C-A)*(D-B) + (G-E)*(H-F);
		int left = Math.max(A, E), right = Math.min(C, G), bottom = Math.max(B, F), top = Math.min(D, H);
		return (right > left && top > bottom) ? sum - (right-left) * (top - bottom) : sum;

	}
	public int computeArea_ShaBi(int A, int B, int C, int D, int E, int F, int G, int H) {
		int[] areas = new int[]{(C-A)*(D-B), (G-E)*(H-F)};

		if (A <= E && G <= C && B <= F && H <= D) return areas[0];
		if (E <= A && C <= G && F <= B && D <= H) return areas[1];

		System.out.printf("not full\n");

		int[] a = new int[]{A, B, C, D}, b = new int[]{E, F, G, H};
		int common = 0;
		if ((common = half(a, b)) != 0) return areas[0]+areas[1]-common;
		if ((common = half(b, a)) != 0) return areas[0]+areas[1]-common;

		System.out.printf("not hafl\n");

		// A <--> C, B <--> D
		// (E, F), (E, H), (G, H), (G, F)
		if (A <= E && E <= C) {
			if (B <= F && F <= D) common = (D-F) * (C-E);
			if (B <= H && H <= D) common = (H-B) * (C-E);
			System.out.printf("left conner");
		}
		if (A <= G && G <= C) {
			if (B <= F && F <= D) common = (G-A) * (D-F);
			if (B <= H && H <= D) common = (G-A) * (H-B);
			System.out.printf("rght conner");
		}

		return areas[0]+areas[1]-common;
	}

	int half(int[] a, int[] b) {
		if (((a[1] <= b[1] && b[1] <= a[3]) || (a[1] <= b[3] && b[3] <= a[3]))
			&& a[0] <= b[0] && b[2] <= a[2]) return (b[2]-b[0]) * (b[3] > a[3] ? a[3]-b[1] : b[3]-a[1]);
		if (((a[0] <= b[0] && b[0] <= a[2]) || (a[0] <= b[2] && b[2] <= a[2]))
			&& ((a[1] <= b[1] && b[3] <= a[3]))) return (b[3]-b[1]) * (b[2] > a[2] ? a[2]-b[0] : b[2]-a[0]);
		return 0;
	}

	public static void main(String args[]) {
	}
}

