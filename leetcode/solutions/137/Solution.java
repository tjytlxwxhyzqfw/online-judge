/**
 * 137 - Single Number II
 *
 * 服, 草
 *
 * 如果每个数都出现了两次呢? 亦或!!!!!!!!!!!!!!!!!!!!!!!
 */

public class Solution {
	public int singleNumber(int nums[]) {
		int zeros[] = new int[32];
		for (Integer num : nums) 
			for (int i = 0; i < 32; ++i)
				zeros[i] += (1-((num>>i)&1));

		int ans = 0;
		for (int i = 0; i < 32; ++i)
			ans |= (zeros[i]%3==1 ? 0 : 1<<i);
			

		return ans;
	} 

	public static void main(String args[]) {
	}
}
