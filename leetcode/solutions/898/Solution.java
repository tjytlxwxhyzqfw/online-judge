/**
 * 898 Bitwise ORs of Subarrays 35.4% Medium 315/64
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class Solution {
	public int subarrayBitwiseORs(int[] a) {
		Set<Integer> total = new HashSet<>();
		Set<Integer> last = new HashSet<>();
		for (int i = 0; i < a.length; ++i) {
			Set<Integer> curr = new HashSet<>();
			curr.add(a[i]);
			for (int x : last) curr.add(a[i] | x);
			for (int y : curr) total.add(y);
			last = curr;
		}
		return total.size();
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.subarrayBitwiseORs(new int[]{}) == 0;
		assert s.subarrayBitwiseORs(new int[]{1}) == 1;
		assert s.subarrayBitwiseORs(new int[]{1, 2, 4}) == 6;

		System.out.println("done");
	}
}

