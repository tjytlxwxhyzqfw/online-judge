/**
 * 2124 Repair the Wall
 *
 * Primary, Heap
 */

import java.io.FileInputStream;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class P2124 {
	private static int used;
	private static int nblocks;
	private static int length;
	private static PriorityQueue<Integer> heap = new PriorityQueue<Integer>(
		600, new Comparator<Integer>() {
			@Override
			public int compare(Integer x, Integer y) {
				return y - x;
			}
		});

	public static void main(String args[]) {
		try {
			System.setIn(new FileInputStream("Inputs/2124"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(System.in);
		while (initACase(scanner)) {
			for (int i = 0; i < nblocks; ++i) {
				//System.out.printf("length: %d\n", length);
				++used;
				if ((length -= heap.poll()) <= 0)
					break;
			}
			System.out.println(String.format(
				"%s", length > 0 ? "impossible" : used));
		}
	}
	private static boolean initACase(Scanner scanner) {
		if (!scanner.hasNext())
			return false;
		used = 0;
		heap.clear();
		length = scanner.nextInt();
		nblocks = scanner.nextInt();
		for (int i = 0; i < nblocks; ++i)
			heap.add(scanner.nextInt());
		return true;
	}
}
