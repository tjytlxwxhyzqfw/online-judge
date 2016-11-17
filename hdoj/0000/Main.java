/**
 * 2177
 */

import java.util.Scanner;

public class Main {
	private static Scanner scanner;
	private static int x, y;

	static {
		scanner = new Scanner(System.in);
	}

	public static void main(String args[]) {
		int z, z1, z2, k;
		double r = (Math.sqrt(5)+1) / 2;
		while (initACase()) {
			k = y - x;
			z1 = x - (int)Math.floor(k*r);
			z2 = y - (int)Math.floor(k*r) - k;
			if (z1 == 0 && z2 == 0) {
				System.out.println("" + 0);
				continue;
			}

			System.out.println(""+1);

			if (z1 == z2)
				System.out.println(""+(x-z1)+" "+(y-z2));

			k = (int)Math.ceil(x/r);
			if (x == (int)Math.floor(r*k)) {
				z = x + k;
				if (z < y) System.out.println(""+x+" "+z);
			}

			k = (int)Math.ceil(y/r);
			if (y != x && y == (int)Math.floor(r*k)) {
				z = y + k;
				if (z < x) System.out.println(""+y+ " "+z);
			}

			k = (int)Math.ceil(x/(1+r));
			if (x == (int)Math.floor(k*r)+k) {
				z = x - k;
				if (z < y) System.out.println(""+z+" "+x);
			}

			k = (int)Math.ceil(y/(1+r));
			if (y != x && y == (int)Math.floor(k*r)+k) {
				z = y - k;
				if (z < x) System.out.printf(""+z+" "+y);
			}
		}
	}

	private static boolean initACase() {
		if (!scanner.hasNext()) return false;
		x = scanner.nextInt();
		y = scanner.nextInt();
		if (x == 0 && y == 0) return false;
		if (x > y) {
			int t = x;
			x = y;
			y = t;
		}
		return true;
	}
}
