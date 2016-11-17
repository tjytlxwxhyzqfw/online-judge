/**
 * 1049 Climbing Worm
 */

import java.io.FileInputStream;
import java.util.Scanner;

public class P1049 {
	private static int n, u, d;

	public static void main(String args[]) {
		try {
			System.setIn(new FileInputStream("Inputs/1049"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(System.in);
		while (initACase(scanner)) {
			if (u > n) {
				System.out.println("" + 1);
				continue;
			}
			int increment = u - d;
			int repeat = (n - u) / increment;
			int rough = 2 * repeat + 1;
			if (n - increment * repeat > u)
				rough += 2;
			System.out.println("" + rough);
		}
	}
	private static boolean initACase(Scanner scanner) {
		n = scanner.nextInt();
		if (n == 0)
			return false;
		u = scanner.nextInt();
		d = scanner.nextInt();
		return true;
	}
}
