/**
 * 900 RLE Iterator 51.5% Medium 163/61
 * Performance: speed=%, memory=%
 */

import java.util.*;

public class RLEIterator {
	int[] a; 
	int p;

	public RLEIterator(int[] a) {
		this.a = a;
	}

	public int next(int n) {
		if (p >= a.length) return -1;
		int total = a[p];
		while (total < n) {
			p += 2;
			if (p >= a.length) return -1;
			total += a[p];
		}
		a[p] = total - n;
		return a[p+1];
	}

	public static void main(String args[]) {
		RLEIterator s = new RLEIterator(new int[]{3, 8, 0, 9, 2, 5});
		assert s.next(2) == 8;
		assert s.next(1) == 8;
		assert s.next(1) == 5;
		assert s.next(2) == -1;
		System.out.println("done");
	}
}

