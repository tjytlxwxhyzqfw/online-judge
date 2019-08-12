/**
 * 229 Majority Element II
 * Performance: speed=%, memory=%
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
	public List<Integer> majorityElement(int[] a) {
		int[] b = new int[]{-1, -1}, c = new int[]{0, 0};
		// you find 3 different integers and delete thems all. you repeat the
		// deletion until there are only 2 elements left in the array.
		for (int i = 0; i < a.length; ++i) {
			if (a[i] == b[0]) {++c[0]; continue;}
			if (a[i] == b[1]) {++c[1]; continue;}
			if (c[0] == 0) {++c[0]; b[0] = a[i]; continue;}
			if (c[1] == 0) {++c[1]; b[1] = a[i]; continue;}
			{--c[0]; --c[1];}  // i found 3 different elements
		}

		List<Integer> result = new ArrayList<>();
		for (int k = 0; k < 2; ++k) {
			if (c[k] == 0) continue;
			int count = 0;
			for (int i = 0; i < a.length; ++i) if (a[i] == b[k]) ++count;
			if (count > a.length / 3) result.add(b[k]);
		}

		return result;
	}

	public static void main(String args[]) {
		List<Integer> list = new Solution().majorityElement(new int[]{3, 1, 3, 1, 2, 1, 2, 2});
		System.out.printf("%s\n", list);
	}
}
