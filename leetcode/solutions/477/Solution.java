/**
 * 477	Total Hamming Distance 49.4% Medium
 * Performance: speed=%, memory=57%
 */

import java.util.*;

public class Solution {
	public int totalHammingDistance(int[] nums) {
		int sum = 0;
		for (int j = 0; j < 32; ++j) {
			int z = 0, nz = 0;
			for (int i = 0; i < nums.length; ++i) {
				if ((nums[i] & (1 << j)) == 0) ++z;
				else ++nz;
			}
			sum += z * nz;
		}
		return sum;
	}

	// better implementation:
	// Performance: speed=51%, memory=85%
	// todo: the speed cannot be improved any more ?
	public int totalHammingDistance_better(int[] nums) {
                int sum = 0, z, mask;
                for (int j = 0; j < 30; ++j) {  // 30: elements <= 10^9
                        { z = 0; mask = (1 << j); }
                        for (int i = 0; i < nums.length; ++i) if ((nums[i] & mask) == 0) ++z;
                        sum += z * (nums.length - z);
                }
                return sum;
        }

	public static void main(String args[]) {
		Solution s = new Solution();
		assert s.totalHammingDistance(new int[]{}) == 0;
		assert s.totalHammingDistance(new int[]{1}) == 0;
		assert s.totalHammingDistance(new int[]{0, 137}) == 3;
		assert s.totalHammingDistance(new int[]{1, 137}) == 2;
		assert s.totalHammingDistance(new int[]{0, 47837}) == 11;
		assert s.totalHammingDistance(new int[]{1, 47837}) == 10;
		assert s.totalHammingDistance(new int[]{4, 14, 2}) == 6;
	}
}

