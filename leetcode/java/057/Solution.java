import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//我目前的解法是有点复杂的
//我可以结合上一个题的现成思路,
//先插入, 然后再合并, 那是最好的

class Interval {
	int start, end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}

	public String toString() {
		return String.format("[%3d, %3d]", start, end);
	}
}

public class Solution {
	public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
		if(newInterval == null)
			return intervals;

		List<Interval> result = new ArrayList<Interval>();

		int start = newInterval.start, end = newInterval.end;
		boolean merging = false, located = false;
		for (Interval interval : intervals) {
			int curStart = interval.start;	
			int curEnd = interval.end;

			if (!located) {
				if (curStart > start) {
					merging = true;
					located = true;
				} else if (curStart <= start && start <= curEnd) {
					merging = true;
					located = true;
					newInterval.start = curStart;
				}
			} 

			if (merging) {
				if (curStart > end) {
					result.add(newInterval);
					result.add(interval);
					merging = false;
				} else if (curStart <= end && end <= curEnd) {
					newInterval.end = curEnd;
					result.add(newInterval);
					merging = false;
				}
			} else { 
				result.add(interval);
			}
		}

		if (!located || merging)
			result.add(newInterval);

		return result;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int nintervals = scanner.nextInt();
		int left = scanner.nextInt();
		int rght = scanner.nextInt();
		List<Interval> intervalList = new ArrayList<Interval>();
		for (int i = 0; i < nintervals; ++i) {
			int start = scanner.nextInt();
			int end = scanner.nextInt();
			intervalList.add(new Interval(start, end));
		}

		List<Interval> result = new Solution().insert(intervalList, new Interval(left, rght));
		for (Interval interval : result)
			System.out.printf("%s\n", interval);
	}
}
