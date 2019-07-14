import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Solution {

	private class Bar implements Comparable<Bar> {
		public int height;
		public int idx;

		public Bar(int height, int idx) {
			this.height = height;
			this.idx = idx;
		}

		public int compareTo(Bar rival) {
			return this.height - rival.height;
		}

		public String toString() {
			String formatter = "idx: %3d, height: %3d";
			return String.format(formatter, idx, height);
		}
	}

	public int trap(int[] heights) {
		/*
		 * 如果输入的bar的个数少于三个的话, 是不可能形成水坑的
		 * 因此直接返回0
		 */
		if (heights == null || heights.length < 3)
			return 0;

		/*
		 * 我们将每个bar都按照其高度排序
		 */
		ArrayList<Bar> bars = new ArrayList<Bar>();
		for (int i = 0; i < heights.length; ++i)
			bars.add(new Bar(heights[i], i));
		bars.sort(Comparator.reverseOrder());
		//for (Bar bar : bars)
			//System.out.printf("%s\n", bar.toString());

		/*
 		 * 计算前缀和
 		 */
		int psum[] = new int[heights.length];
		for (int i = 1; i < heights.length; ++i)
			psum[i] = heights[i]+psum[i-1];

		/*
 		 * 标记所有bar的状态为"未使用
 		 * 由于java自动将boolean[]初始化为false,
 		 * 这里就不需要我们自己初始化了
 		 */
		boolean used[] = new boolean[heights.length];

		/*
 		 * 在算法刚开始的时候, 我们左右的"擎天柱"都是最高的那个
 		 */
		int left = bars.get(0).idx, rght = bars.get(0).idx;
		used[bars.get(0).idx] = true;

		int ans = 0;
		for (int i = 0; i < heights.length; ++i) {
			Bar bar = bars.get(i);
			if (used[bar.idx])
				continue;

			/*
			 * 算法每次迭代, 找到左右两根"擎天柱"之外的最高的柱子bar,
			 * 用bgn和end表示bar以及离它最近的那个"擎天柱"
			 */
			int bgn, end;
			if (bar.idx < left) {
				bgn = bar.idx; 
				end = left;
				left = bgn;
			} else if (bar.idx > rght) {
				bgn = rght;
				end = bar.idx;
				rght = end;
			} else {
				throw new RuntimeException();
			}

			/*
 			 * 在bgn和end之间的所有柱子都将被水淹没
 			 */
			for (int j = bgn; j <= end; ++j)
				used[j] = true;

			/*
			 * s是[bgn,end]之间(不包括两个柱子)形成的面积
			 * t是[bgn,end]之间被柱子占用的面积
			 */
			int s = (end-bgn-1)*Math.min(heights[bgn], heights[end]);
			int t = psum[end-1] - psum[bgn];

			//System.out.printf("bar: %s\n", bar.toString());
			//System.out.printf("\t[left, rght]: [%3d, %3d]\n", left, rght);
			//System.out.printf("\t[bgn, end]: [%3d, %3d]\n", bgn, end);
			//System.out.printf("\ts: %d\n", s);
			//System.out.printf("\tt: %d\n", t);

			ans += s - t;
		}

		return ans;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int nnums = scanner.nextInt();
		int nums[] = new int[nnums];
		for (int i = 0; i < nnums; ++i)
			nums[i] = scanner.nextInt();
		int ans = new Solution().trap(nums);
		System.out.printf("---> %d\n", ans);
	}
}
