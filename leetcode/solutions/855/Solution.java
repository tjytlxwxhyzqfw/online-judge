/**
 * 855 Exam Room 39.6% Medium 410/180
 * Performance: speed=30%, memory=5%
 */

import java.util.*;

public class ExamRoom {
	TreeSet<Integer> set;
	int n;

	public ExamRoom(int n) {
		set = new TreeSet<>();
		this.n = n;
	}

	public int seat() {
		int p = 0, d = -1;
		int min = n, max = -1;
		for (int x : set) {
			min = Math.min(min, x);
			max = Math.max(max, x);
		}
		// 0, (1), 2, (3) n=4
		if (min != 0) { p = 0; d = min; }
		if (set.size() > 0 && max != n-1 && n-1-max > d) { p = n-1; d = n-1-max; }

		int x = 0;
		for (int y : set) {
			int p1 = (y + x) / 2;
			int d1 = p1 - x;
			if (d1 > d || (d1 == d && p1 < p)) { p = p1; d = d1; }
			x = y;
		}

		set.add(p);
		return p;
	}

	public void leave(int p) {
		set.remove(p);
	}

	public static void main(String args[]) {
		ExamRoom r = new ExamRoom(10);

		assert r.seat() == 0;
		assert r.seat() == 9;
		assert r.seat() == 4;
		assert r.seat() == 2;
		r.leave(4);
		assert r.seat() == 5;
		r.leave(0);
		r.leave(5);
		r.leave(9);
		// only 2 left
		assert r.seat() == 9;
		r.leave(2);
		assert r.seat() == 0;

		System.out.println("done");
	}
}

