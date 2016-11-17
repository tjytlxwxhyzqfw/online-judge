/**
 * 2037 今年暑假不AC
 *
 * DP
 */

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class P2037 {
	private static int nprograms;
	private static Program programs[] = new Program[100];
	private static int s[] = new int[100];

	static {
		for (int i = 0; i < 100; ++i)
			programs[i] = new Program();
	}

	public static void main(String args[]) {
		try {
			System.setIn(new FileInputStream("Inputs/2037"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(System.in);
		while (initACase(scanner)) {
			int smax = 0;
			for (int i = nprograms - 1; i >= 0; --i) {
				fillS(i);
				if (s[i] > smax)
					smax = s[i];
				//System.out.printf("s[%2d]: %2d\n", i, s[i]);
			}
			System.out.println("" + smax);
		}
	}
	private static void fillS(int i) {
		if (i == nprograms - 1) {
			s[i] = 1;
			return;
		}
		Program program = programs[i];
		int tmax = 0;
		for (int x = i + 1; x < nprograms; ++x) {
			if (program.before(programs[x]) && s[x] > tmax)
				tmax = s[x];
		}
		s[i] = 1 + tmax;
	}
	private static boolean initACase(Scanner scanner) {
		nprograms = scanner.nextInt();
		if (nprograms == 0)
			return false;
		for (int i = 0; i < nprograms; ++i) {
			int startTime = scanner.nextInt();
			int endTime = scanner.nextInt();
			programs[i].init(startTime, endTime);
		}
		Arrays.sort(programs, 0, nprograms);
		//printPrograms();
		return true;
	}
	private static void printPrograms() {
		for (int i = 0; i < nprograms; ++i)
			System.out.printf("%s, ", programs[i].toString());
		System.out.printf("\n");
	}
	static class Program implements Comparable<Program> {
		public int start;
		public int end;

		public void init(int s, int e) {
			start = s;
			end = e;
		}

		@Override
		public int compareTo(Program rival) {
			return start - rival.start;
		}

		public boolean before(Program another) {
			return end <= another.start;
		}

		@Override
		public String toString() {
			return String.format("(%3d, %3d)", start, end);
		}
	}
}
