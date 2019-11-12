/**
 * 855 Exam Room 39.6% Medium 410/180
 * Performance: speed=%, memory=%
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
		int p = 0, d = 0, x = 0;
		for (int y : set) {
			int p1 = (y + x) / 2;
			int d1 = p1 - x;
			if (d1 > d) {
				p = p1;
				d = d1;
			}
			x = y;
		}
		if (set.size() == 1 && (n-1-x > x)) p = n-1;
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

		System.out.println("done");
	}
}

