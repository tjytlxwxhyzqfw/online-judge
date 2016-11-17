/**
 * 2187 汶川
 */

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class P2187 {
	public static int price;
	public static double weight;
	public static int nthings;
	public static Thing things[] = new Thing[1000];

	static {
		for (int i = 0; i < 1000; ++i)
			things[i] = new Thing();
	}

	public static void main(String args[]) {
		try {
			System.setIn(new FileInputStream("Inputs/2187"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(System.in);
		int ncases = scanner.nextInt();
		while (--ncases >= 0) {
			initACase(scanner);
			for (int i = 0; i < nthings; ++i) {
				putin(i);
				if (price < 0)
					break;
			}
			System.out.println(String.format("%.2f", weight));
		}
	}

	private static void putin(int index) {
		Thing thing = things[index];
		//System.out.printf("%s, putin: %s\n", price, thing);
		int total = thing.weight * thing.price;
		int left = price - total;
		weight += (left < 0 ? (double)1.0 * thing.weight * price / total : thing.weight);
		price = left;
	}

	private static void initACase(Scanner scanner) {
		weight = 0;
		price = scanner.nextInt();
		nthings = scanner.nextInt();
		for (int i = 0; i < nthings; ++i) {
			int price = scanner.nextInt();
			int weight = scanner.nextInt();
			things[i].init(weight, price);
		}
		Arrays.sort(things, 0, nthings);
	}

	static class Thing implements Comparable<Thing> {
		public int weight;
		public int price;

		public void init(int weight, int price) {
			this.price = price;
			this.weight = weight;
		}

		@Override
		public int compareTo(Thing rival) {
			return price - rival.price;
		}

		@Override
		public String toString() {
			return String.format("(%2d, %2d)", price, weight);
		}
	}
}
