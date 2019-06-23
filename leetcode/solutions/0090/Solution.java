/**
 * leetcode 90 - Subsets II
 * 
 * 思路就是正常的生成幂集的思路
 * - 从n-1元集合生成n元集合
 * - 始终保持生成的自己是按照字典序排列起来的
 *
 * 但是你要集中精力解决一个核心问题, 如何避免生成重复的子集
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Solution {
	public List<List<Integer>> subsetsWithDup(int nums[]) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		result.add(new ArrayList<Integer>());
		if (nums == null || nums.length == 0)
			return result;

		Arrays.sort(nums);

		/*
		 * 生成1元集的时候, 用last监视最后一个生成的1元集,
		 * 只有当新生成的1元集不同于last的时候, 才会将这集合插入
		 */
		result.add(Arrays.asList(nums[0]));
		int last = nums[0];
		for (int i = 1; i < nums.length; ++i) {
			if (nums[i] != last) {
				result.add(Arrays.asList(nums[i]));
				last = nums[i];
			}
		}

		//print(result);

		/* i表示我们将要生成的子集的大小
		 * j表示当前的"种子"子集的下标,
		 * current则表示当前这个种子子集
		 * bgn, end表示的是所有的i-1子集的起始位置, 这是个优秀的trick
		 */
		int bgn = 1, end = result.size();
		for (int i = 2; i <= nums.length; ++i) {
			for (int j = bgn; j < end; ++j) {
				List<Integer> current = result.get(j);

				/* idx返回nums[]中第一个可以append到current后面的元素的下标 */
				int idx = first(nums, current);
				if (idx >= nums.length)
					continue;

				last = nums[idx];
				result.add(oneMore(current, last));

				for (int k = idx+1; k < nums.length; ++k) {
					if (nums[k] != last) {
						last = nums[k];
						result.add(oneMore(current, last));
					}
				}
			}
			bgn = end;
			end = result.size();

			//System.out.printf("------> i=%d\n", i);
			//print(result);
		}

		return result;
	}

	/*
	 * 在current后面append新元素的基本原则是: nums中第一个**等于**ccurrent.last的元素
	 *
	 * 特殊情况如下:
	 *
	 * 以[1, 2, 2]为例, 现在我有[2, 2], 如何找到下一个加进去的数?
	 * (其实不存在)
	 *
	 * 按照基本原则, 会生成[2, 2, 2]
	 *
	 * 其实, current的最最后一个数是2, 就在nums中找到2
	 * 然后, 我们进一步检查, 发现current中出现了2个2, nums中也一共只有2个2
	 * 我们就知道, 不能再加2了
	 *
	 */
	private int first(int nums[], List<Integer> list) {
		int bgn = nums.length, end = list.size()-1;
		int target = list.get(end);
		for (int i = 0; i < nums.length; ++i) {
			if (nums[i] == target) {
				bgn = i;
				break;
			}
		}

		while (end >= 0 && bgn < nums.length && list.get(end) == nums[bgn]) {
			--end;
			++bgn;
		}

		return bgn;
	}

	private List<Integer> oneMore(List<Integer> list, int one) {
		List<Integer> result = new ArrayList<Integer>(list);
		result.add(one);
		return result;
	}

	private void print(List<List<Integer>> list) {
		for (List<Integer> li : list)
			System.out.printf("%s\n", li.toString());
	}

	public static void main(String args[]) {
		int nums[] = new int[] {1, 2, 2, 2, 3};
		List<List<Integer>> result = new Solution().subsetsWithDup(nums);
		System.out.printf("total: %d\n", result.size());
	}
}
