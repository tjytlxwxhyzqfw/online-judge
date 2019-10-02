/**
 * 621 Task Scheduler 46.5% Medium 1997/361
 * Performance: speed=77%, memory=88%
 */

/*
hard for me to understand the solution.
disscuss link: https://leetcode.com/problems/task-scheduler/discuss/104500/Java-O(n)-time-O(1)-space-1-pass-no-sorting-solution-with-detailed-explanation
it takes me quit a while to understand this solution.
*/

import java.util.*;

public class Solution {
	public int leastInterval(char[] tasks, int n) {
		int[] count = new int[26];
		int max = 0;
		for (int i = 0; i < tasks.length; ++i) if (++count[tasks[i]-65] > max) max = count[tasks[i]-65];
		int nmax = 0;
		for (int i = 0; i < count.length; ++i) if (count[i] == max) ++nmax;

		int itvCnt = max - 1, itvLen = n - (nmax - 1);
		int delta = itvCnt * itvLen - (tasks.length-max*nmax);
		return tasks.length + (itvLen > 0 && delta > 0 ? delta : 0);
	}

	public static void main(String args[]) {
		Solution s = new Solution();
                System.out.println("done");
	}
}

