/**
 * 1051 Wooden Sticks
 *
 * 真正的套娃问题
 */

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class P1051 {
	private static int nqueues;
	private static int nsticks;
	private static Stick sticks[] = new Stick[5000];
	private static int queues[] = new int[5000];

	static {
		for (int i = 0; i < 5000; ++i)
			sticks[i] = new Stick();
	}

	public static void main(String args[]) {
		try {
			System.setIn(new FileInputStream("Inputs/1051"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(System.in);
		int ncases = scanner.nextInt();
		while (--ncases >= 0) {
			initACase(scanner);
			for (int i = 0; i < nsticks; ++i) {
				int weight = sticks[i].weight;
				int minusIndex = Arrays.binarySearch(queues, 0, nqueues, weight);
				if (minusIndex >= 0)
					continue;
				int index = -1 * minusIndex - 1;
				queues[index] = weight;
				if (index == nqueues)
					++nqueues;
			}
			System.out.println("" + nqueues);
		}
	}
	private static void initACase(Scanner scanner) {
		nqueues = 0;
		nsticks = scanner.nextInt();
		for (int i = 0; i < nsticks; ++i) {
			int length = scanner.nextInt();
			int weight = scanner.nextInt();
			sticks[i].init(length, weight);
		}
		Arrays.sort(sticks, 0, nsticks);
	}

	static class Stick implements Comparable<Stick> {
		public int length;
		public int weight;

		public void init(int length, int weight) {
			this.length = length;
			this.weight = weight;
		}

		@Override
		public int compareTo(Stick rival) {
			if (length == rival.length)
				return rival.weight - weight;
			return rival.length - length;
		}

		@Override
		public String toString() {
			return String.format("(%3d, %3d)", length, weight);
		}
	}
}
