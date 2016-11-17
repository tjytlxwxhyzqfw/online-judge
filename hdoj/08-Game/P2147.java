/**
 * 2147
 * tle, optimize as 2147.c
 */

import java.util.Arrays;
import java.util.Scanner;

public class P2147 {
	private static int sg[][], height, width;
	private static Scanner scanner;

	static {
		sg = new int[2001][2001];
		scanner = new Scanner(System.in);
	}

	public static void main(String args[]) {
		int sgv;
		int f[] = new int[3];

		while (initACase()) {
			for (int i = 1; i <= width; ++i)
				sg[1][i] = (i-1) % 2;
			for (int i = 1; i <= height; ++i)
				sg[i][1] = (i-1) % 2;
			for (int h = 2; h <= height; ++h) {
				for (int w = 2; w <= width; ++w) {
					Arrays.fill(f, 0);
					if (sg[h-1][w] < 3) f[sg[h-1][w]] = 1;
					if (sg[h][w-1] < 3) f[sg[h][w-1]] = 1;
					if (sg[h-1][w-1] < 3) f[sg[h-1][w-1]] = 1;
					for (sgv = 0; sgv < 3; ++sgv) if (f[sgv] == 0) break;
					sg[h][w] = sgv;
				}
			}

			if (sg[height][width] == 0)
				System.out.println("What a pity!");
			else
				System.out.println("Wonderful!");
		}

		return;
	}

	private static boolean initACase() {
		if (!scanner.hasNext())
			return false;
		height = scanner.nextInt();
		width = scanner.nextInt();
		if (height == 0 && width == 0)
			return false;
		return true;
	}
}
