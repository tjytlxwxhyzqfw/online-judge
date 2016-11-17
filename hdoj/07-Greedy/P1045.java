/**
 * 1045 Fire Net
 *
 * States-Compressing DP
 */

import java.io.FileInputStream;
import java.util.Scanner;

public class P1045 {
	private static int map[][] = new int[4][4];
	private static int maxNumOfBlocks;
	private static int size;

	public static void main(String args[]) {
		try {
			System.setIn(new FileInputStream("Inputs/1045"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(System.in);
		while (initACase(scanner)) {
			for (int y = 0; y < size; ++y) {
				for (int x = 0; x < size; ++x) {
					dfs(1, y, x);
				}
			}
			System.out.println("" + maxNumOfBlocks);
		}
	}
	private static void dfs(int deepth, int y, int x) {
		if (map[y][x] != 0)
			return;
		place(y, x);
		if (maxNumOfBlocks < deepth)
			maxNumOfBlocks = deepth;
		for (int j = 0; j < size; ++j) {
			for (int i = 0; i < size; ++i) {
				dfs(deepth + 1, j, i);
			}
		}
		unplace(y, x);
	}
	private static void unplace(int y, int x) {
		//printMap(String.format("before clear (%d, %d)", y, x));
		set(y, x, true);
		//printMap(String.format("after clear (%d, %d)", y, x));
	}
	private static void place(int y, int x) {
		//printMap(String.format("before set (%d, %d)", y, x));
		set(y, x, false);
		//printMap(String.format("after set (%d, %d)", y, x));
	}
	private static void set(int y, int x, boolean clear) {
		int tag = -2;
		int increment = 1;
		if (clear) {
			tag = 0;
			increment = -1;
		}
		map[y][x] = tag;
		for (int i = y + 1; i < size && map[i][x] != -1; ++i)
			map[i][x] += increment;
		for (int i = y - 1; i >= 0 && map[i][x] != -1; --i)
			map[i][x] += increment;
		for (int i = x + 1; i < size && map[y][i] != -1; ++i)
			map[y][i] += increment;
		for (int i = x - 1; i >= 0 && map[y][i] != -1; --i)
			map[y][i] += increment;
	}
	private static void printMap() {
		for (int y = 0; y < size; ++y) {	
			for (int x = 0; x < size; ++x) {
				System.out.printf("%2d", map[y][x]);
			}
			System.out.println();
		}
	}
	private static void printMap(String msg) {
		System.out.println(msg + ":");
		printMap();
		trySleep(0);
	}
	private static boolean initACase(Scanner scanner) {
		size = scanner.nextInt();
		if (size == 0)
			return false;
		maxNumOfBlocks = 0;
		for (int y = 0; y < size; ++y) {
			String line = scanner.next();
			for (int x = 0; x < size; ++x) {
				char ch = line.charAt(x);
				map[y][x] = (ch == 'X' ? -1 : 0);
			}
		}
		//printMap("initial map[][]");
		return true;
	}
	private static void trySleep(int s) {
		int ms = s * 1000;
		try {
			Thread.sleep(ms);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
