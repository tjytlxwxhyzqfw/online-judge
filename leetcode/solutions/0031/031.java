public class Solution {
    public void nextPermutation(int[] nums) {
        int i, j=0, t, len;
        len = nums.length;
        for (i = len-2; i >= 0 && nums[i+1] <= nums[i]; --i);
        if (i < 0) {
            j = (len-1) / 2;
            for (i = 0; i <= j;+i) {
                t = nums[i];
                nums[i] = nums[len-i-1];
                nums[len-i-1] = t;
            }
            return;
        }
        for (j = i+1; j < len && nums[j] > nums[i];+j);
        --j;
        t = nums[j];
        nums[j] = nums[i];
        nums[i] = t;
        Arrays.sort(nums, i+1, len);
    }
}
