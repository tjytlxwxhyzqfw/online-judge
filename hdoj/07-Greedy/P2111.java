/**
 * 2111 Saving HDU
 */

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class P2111 {
	public static int volume;
	public static int value;
	public static int nthings;
	public static Thing things[] = new Thing[100];

	static {
		for (int i = 0; i < 100; ++i)
			things[i] = new Thing();
	}

	public static void main(String args[]) {
		try {
			System.setIn(new FileInputStream("Inputs/2111"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(System.in);
		while (initACase(scanner)) {
			for (int i = 0; i < nthings; ++i) {
				putin(i);
				if (volume < 0)
					break;
			}
			System.out.println("" + value);
		}
	}

	private static void putin(int index) {
		Thing thing = things[index];
		//System.out.printf("putin: %s\n", thing);
		int left = volume - thing.volume;
		value += thing.value * (left < 0 ? volume : thing.volume);
		volume = left;
	}

	private static boolean initACase(Scanner scanner) {
		value = 0;
		volume = scanner.nextInt();
		if (volume == 0)
			return false;
		nthings = scanner.nextInt();
		for (int i = 0; i < nthings; ++i) {
			int value = scanner.nextInt();
			int volume = scanner.nextInt();
			things[i].init(value, volume);
		}
		Arrays.sort(things, 0, nthings);
		return true;
	}

	static class Thing implements Comparable<Thing> {
		public int value;
		public int volume;

		public void init(int value, int volume) {
			this.value = value;
			this.volume = volume;
		}

		@Override
		public int compareTo(Thing rival) {
			return rival.value - value;
		}

		@Override
		public String toString() {
			return String.format("(%2d, %2d)", value, volume);
		}
	}
}
