/**
 * 901 Online Stock Span 51.5% Medium 304/59
 * Performance: speed=%, memory=%
 */

import java.util.*;

class StockSpanner {
	List<Integer> stocks;
	List<Integer> spanners;
	public StockSpanner() {
		stocks = new ArrayList<>();
		spanners = new ArrayList<>();
	}

	public int next(int price) {
		stocks.add(price);
		int n = 1;
		int p = stocks.size() - 2;
		while(p >= 0 && stocks.get(p) <= price) {
			n += spanners.get(p);
			p -= spanners.get(p);
		}
		spanners.add(n);
		return n;
	}
}

public class Solution {
	public static void main(String args[]) {
		StockSpanner s = new StockSpanner();

		assert s.next(100) == 1;
		assert s.next(80) == 1;
		assert s.next(60) == 1;
		assert s.next(70) == 2;
		assert s.next(60) == 1;
		assert s.next(75) == 4;
		assert s.next(85) == 6;

		System.out.println("done");
	}
}

