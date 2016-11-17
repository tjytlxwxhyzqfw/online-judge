/**
 * 2391 Filthy Rich
 *
 * DP
 */

import java.io.FileInputStream;
import java.util.Scanner;

public class P2391 {
	private static int height, width;
	private static int map[][] = new int[1000][1000];
	private static int s[][] = new int[1000][1000];

	public static void main(String args[]) {
		try {
			System.setIn(new FileInputStream("Inputs/2391"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(System.in);
		int ncases = scanner.nextInt();
		int y, x, i;
		int right, down;
		for (int cas = 1; cas <= ncases; ++cas) {
			initACase(scanner);
			y = height - 1;
			x = width - 1;
			s[y][x] = map[y][x];
			for (i = width - 2; i >= 0; --i)
				s[y][i] = map[y][i] + s[y][i + 1];	
			for (i = height - 2; i >= 0; --i)
				s[i][x] = map[i][x] + s[i + 1][x];
			for (y = height - 2; y >= 0; --y) {
				for (x = width - 2; x >= 0; --x) {
					right = s[y][x + 1];
					down = s[y + 1][x];
					s[y][x] = map[y][x] + (right > down ? right : down);
					//System.out.printf("(%3d, %2d): %2d\n", y, x, s[y][x]);
				}
			}
			System.out.println("Scenario #" + cas + ":");
			System.out.println("" + s[0][0]);
			System.out.println();
		}
	}
	private static void initACase(Scanner scanner) {
		height = scanner.nextInt();
		width = scanner.nextInt();
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				map[y][x] = scanner.nextInt();
			}
		}
	}
	private static void fillS(int y, int x) { 
		int right = s[y][x + 1];
		int down = s[y + 1][x];
		s[y][x] += (right > down ? right : down);
	}
}
		
