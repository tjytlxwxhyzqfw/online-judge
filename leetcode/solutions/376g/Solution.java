/**
 * 0376: WiggleSubsequence
 * Performance: speed=100%, memory=91%
 */

// review logs
// -----------
// 20200724: i come with a solution that is simillar with LIS:
//   for i in nums:
//       if i need a lower
//           if nums[i] >= nums[i-1] last = nums[i]
//           else last = nums[i]; need = larger
//       else i need a larger
//           if nums[i] >= nums[i-1] last=nums[i]; need = lower
//           else last = nums[i]
//
//   this is supposed to be a wrong answer. (todo: why ?)

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public int wiggleMaxLength_S12M82(int[] nums) {
		int n = nums.length;
		if (n == 0) return 0;

		int pos[] = new int[n];
		int neg[] = new int[n];
		pos[0] = neg[0] = 1;
		int max = 1;

		for (int i = 1; i < n; ++i) {
			pos[i] = neg[i] = 1;
			for (int j = 0; j < i; ++j) {
				if (nums[i]-nums[j]<0) neg[i] = Math.max(neg[i], pos[j]+1);
				if (nums[i]-nums[j]>0) pos[i] = Math.max(pos[i], neg[j]+1);
			}
			max = Math.max(max, Math.max(pos[i], neg[i]));
			System.out.printf("#=%2d, v=%2d, pos=%2d, neg=%2d, max=%2d\n", i, nums[i], pos[i], neg[i], max);
		}

		return max;
	}

	public int wiggleMaxLength(int[] nums) {
		int n = nums.length;
		if (n == 0) return 0;

		int pos[] = new int[n];
		int neg[] = new int[n+1];
		pos[0] = neg[0] = 1;
		for (int i = 1; i < n; ++i) {
			pos[i] = pos[i-1];
			if (nums[i]-nums[i-1]>0) pos[i] = Math.max(pos[i], neg[i-1]+1);
			neg[i] = neg[i-1];
			if (nums[i]-nums[i-1]<0) neg[i] = Math.max(neg[i], pos[i-1]+1);
			System.out.printf("#=%2d, v=%2d, pos=%2d, neg=%2d\n", i, nums[i], pos[i], neg[i]);
		}
		
		return Math.max(pos[n-1], neg[n-1]);
        }

	public static void main(String args[]) {
		new Solution().wiggleMaxLength(new int[]{1, 7, 4, 9, 2, 5});
		new Solution().wiggleMaxLength(new int[]{1});
		new Solution().wiggleMaxLength(new int[]{1, 1, 1});
		new Solution().wiggleMaxLength(new int[]{1, 1, 1, 2, 2, 2, 1, 1, 1, 2, 2, 2});
	}
}

