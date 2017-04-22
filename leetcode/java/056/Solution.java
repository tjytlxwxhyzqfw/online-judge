import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

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

	class IntervalComparator implements Comparator<Interval> {
		@Override
		public int compare(Interval left, Interval rght) {
			return left.start - rght.start;
		}
	}

	public List<Interval> merge(List<Interval> intervalList) {

		ArrayList<Interval> intervals = new ArrayList<Interval>(intervalList);
		intervals.sort(new IntervalComparator());
		//System.out.printf("sorted: %s\n", intervals.toString());
		int nintervals = intervals.size();

		List<Interval> result = new ArrayList<Interval>();
		if (intervalList == null || intervalList.size() == 0)
			return result;

		Interval frontier = intervals.get(0);
		result.add(frontier);
		for (int i = 1; i < nintervals; ++i) {
			Interval current = intervals.get(i);
			int start = Math.max(frontier.start, current.start);
			int end = Math.min(frontier.end, current.end);
			if (start > end) {
				result.add(current);
				frontier = current;
			} else {
				//!!!ATTENTION!!!
				//合并的时候, 一定要注意并区间的右断点取两个右断点中最大的那个
				//我之前是: frontier.end = current.end
				frontier.end = Math.max(frontier.end, current.end);
			}
		}

		return result;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int nintervals = scanner.nextInt();
		List<Interval> intervalList = new ArrayList<Interval>();
		for (int i = 0; i < nintervals; ++i) {
			int start = scanner.nextInt();
			int end = scanner.nextInt();
			intervalList.add(new Interval(start, end));
		}

		List<Interval> result = new Solution().merge(intervalList);
		for (Interval interval : result)
			System.out.printf("%s\n", interval);
	}
}
