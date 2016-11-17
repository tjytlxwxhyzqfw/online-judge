/**
 * 1050 Moving Tables
 *
 * 共享的点数决定时间
 */

import java.io.FileInputStream;
import java.util.Scanner;

public class P1050 {
	private static int max;
	private static int corridor[] = new int[200];
	public static void main(String args[]) {
		try {
			System.setIn(new FileInputStream("Inputs/1050"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(System.in);
		int ncases = scanner.nextInt();
		while (--ncases >= 0) {
			initACase(scanner);
			System.out.println("" + max * 10);
		}
	}
	private static void initACase(Scanner scanner) {
		max = 0;
		for (int i = 0; i < 200; ++i)
			corridor[i] = 0;
		int nmoves = scanner.nextInt();
		for (int i = 0; i < nmoves; ++i) {
			int start = scanner.nextInt();
			int end = scanner.nextInt();
			int from = (start - 1) / 2;
			int to = (end - 1) / 2;
			if (to < from) {
				int tmp = to;
				to = from;
				from = tmp;
			}
			for (int j = from; j <= to; ++j) {
				corridor[j] += 1;
				if (corridor[j] > max)
					max = corridor[j];
			}
		}
		//System.out.printf("corridor[]:\n");
		//for (int i = 0; i < 200; ++i) {
			//System.out.printf("%d", corridor[i]);
		//}
		//System.out.println();
	}
}
	
