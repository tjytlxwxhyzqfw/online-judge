/**
 * 2191 珍惜现在,感恩生活
 */

import java.util.Scanner;
import java.io.FileInputStream;

public class P2191 {
	static int[] coin = new int[100];
	static int[] weight = new int[101];
	static int[] number = new int[100];
	static int coins, ids;
	static int[][] s = new int[100][101];

	private static void initACase(Scanner scanner) {
		coins = scanner.nextInt();
		ids = scanner.nextInt();
		//System.out.printf("coins: %3d, ids: %3d\n", coins, ids);
		for (int i = 0; i < ids; ++i) {
			coin[i] = scanner.nextInt();
			weight[i] = scanner.nextInt();
			number[i] = scanner.nextInt();
			//System.out.printf("%2d: coin: %2d, weight: %2d, number: %2d\n",
					//i, coin[i], weight[i], number[i]);
		}
	}

	public static void main(String[] args) {
		Scanner scanner;
		int ncas;
		
		try {
			System.setIn(new FileInputStream("Inputs/2191"));
		} catch (Exception e) {
			System.err.println(e.getClass().getName());
			return;
		}

		scanner = new Scanner(System.in);

		ncas = scanner.nextInt();
		while (ncas-- != 0) {
			initACase(scanner);
			for (int i = ids - 1; i >= 0; --i) {
				for (int j = 0; j <= coins; ++j) {
					fillS(i, j);
					//System.out.printf("(%2d, %2d): %3d\n", i, j, s[i][j]);
				}
			}
			System.out.println("" + s[0][coins]);
		}
	}

	private static void fillS(int id, int remainder) {
		int num;
		int thisMax, nextMax, max;
		int left;

		num = remainder / coin[id];
		if (num > number[id])
			num = number[id];

		if (id == ids - 1) {
			s[id][remainder] = num * weight[id];
			return;
		}

		max = 0;
		for (int i = 0; i <= num; ++i) {
			left = remainder - i * coin[id];
			nextMax = s[id + 1][left];
			thisMax = nextMax + i * weight[id];
			if (thisMax > max)
				max = thisMax;
		}

		s[id][remainder] = max;
	}
}
