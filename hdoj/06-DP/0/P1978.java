/**
 * 1978 How many ways
 */

import java.io.FileInputStream;
import java.util.Scanner;

public class P1978 {
	private static int height, width;
	private static int map[][];
	private static int s[][];

	public static void main(String args[]) {
		map = new int[100][100];
		s = new int[100][100];

		try {
			System.setIn(new FileInputStream("Inputs/1978"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Scanner scanner = new Scanner(System.in);
		int ncases = scanner.nextInt();
		while (--ncases >= 0) {
			initACase(scanner);
			for (int y = height - 1; y >= 0; --y) {
				for (int x = width - 1; x >= 0; --x) {
					fillS(y, x);
					//System.out.printf("(%2d, %2d): %2d\n", y, x, s[y][x]);
				}
			}
			System.out.println("" + s[0][0]);
		}
	}
	private static void fillS(int y, int x) {
		if (y == height - 1 && x == width - 1) {
			s[y][x] = 1;
			return;
		}
		int nways = 0;
		int nsteps = map[y][x];
		for (int down = 0; down <= nsteps; ++down) {
			int py = y + down;
			if (py >= height)
				break;	
			int nrights = nsteps - down;
			int begin = (down == 0 ? 1 : 0);
			for (int right = begin; right <= nrights; ++right) {
				int px = x + right;
				if (px >= width)
					break;
				nways += s[py][px];
				nways %= 10000;
			}
		}
		s[y][x] = nways;
	}
	private static void initACase(Scanner scanner) {
		height = scanner.nextInt();
		width = scanner.nextInt();
		for (int y = 0; y < height; ++y)
			for (int x = 0; x < width; ++x)
				map[y][x] = scanner.nextInt();
	}
}
