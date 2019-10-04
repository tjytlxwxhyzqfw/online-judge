/**
 * 729 My Calendar I 48.7% Medium 429/29
 * Performance: speed=46%, memory=68%
 */

/*
todo: understand the floorKey solution.
*/

import java.util.*;

public class MyCalendar {
	List<Intv> intvs;
	public MyCalendar() {
		intvs = new ArrayList<>();
	}
	public boolean book(int start, int end) {
		for (int i = 0; i < intvs.size(); ++i)
			if (intvs.get(i).overlap(start, end))
				return false;
		intvs.add(new Intv(start, end));
		return true;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		System.out.println("done");
	}
}

class Intv {
	int begin, end;

	Intv(int begin ,int end) {
		this.begin = begin;
		this.end = end;
	}

	boolean overlap(int begin, int end) {
		return !(end <= this.begin || begin >= this.end);
	}
}

