/**
 * 2571 命运
 *
 * DP, for its special structure.
 *
 * [2016-07-03] Infinite WAs because -100 is used for -Infinity rather than Integer.MIN_VALUE
 */

import java.io.FileInputStream;
import java.util.Scanner;

public class P2571 {
	private static int map[][], s[][];
	private static int height, width;

	public static void main(String[] args) {
		map = new int[21][1001];
		s = new int[21][1001];

		try {
			System.setIn(new FileInputStream("Inputs/2571"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Scanner scanner = new Scanner(System.in);
		int ncases = scanner.nextInt();
		while (ncases-- > 0) {
			initACase(scanner);

			for (int y = height; y > 0; --y) {
				for (int x = width; x > 0; --x) {
					fillS(y, x);
					//System.out.printf("(%2d, %2d): %3d\n", y, x, s[y][x]);
				}
			}
			System.out.println("" + s[1][1]);
		}
	}

	private static void fillS(int y, int x) {
		int nextX, nextY;
		int value, value1, value2, max;

		if (y == height && x == width) {
			s[y][x] = map[y][x];
			return;
		}

		nextY = y;
		nextX = x + 1;
		value1 = Integer.MIN_VALUE;
		if (inMap(nextY, nextX))
			value1 = s[nextY][nextX];

		nextY = y + 1;
		nextX = x;
		value2 = Integer.MIN_VALUE;
		if (inMap(nextY, nextX))
			value2 = s[nextY][nextX];

		nextY = y;
		max = Integer.MIN_VALUE;
		for (nextX = 2 * x; nextX <= width; nextX += x) {
			value = s[nextY][nextX];
			if (value > max)
				max = value;
		}

		if (value1 > max)
			max = value1;
		if (value2 > max)
			max = value2;

		s[y][x] = map[y][x] + max;
	}

	private static void initACase(Scanner scanner) {
		height = scanner.nextInt();
		width = scanner.nextInt();

		for (int y = 1; y <= height; ++y)
			for (int x = 1; x <= width; ++x)
				map[y][x] = scanner.nextInt();
	}

	private static boolean inMap(int y, int x) {
		return y > 0 && y <= height && x > 0 && x <= width;
	}
}
