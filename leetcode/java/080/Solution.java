public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length==0)
            return 0;
        if (nums.length <= 2)
            return nums.length;
        
        //we have nums.length > 3
        // 1 1 1 2 2 2 2 3 3 3

        int i = 2, n = 2;
        while (i < nums.length) {
            if (nums[i] != nums[i-2]) {
                nums[n++] = nums[i];
                if (nums[n-1] != nums[n-2] && i+1 < nums.length && nums[i+1] == nums[i]) {
                    nums[n++] = nums[i+1];
                    i += 2;
                } else {
                    i += 1;
                }
            } else {
                ++i;
            }
        }

        //System.out.printf("n=%d\n", n);
        return n;
    }
}
