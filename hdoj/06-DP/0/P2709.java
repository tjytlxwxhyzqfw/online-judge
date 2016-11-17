/*
 * 2709 Sumsets
 */

import java.util.Scanner;

public class P2709 {
	private static int s[][];
	public static void main(String[] args) {
		s = new int[2][1000001];
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			int n = scanner.nextInt();
			if (n == 1) {
				System.out.println("" + 1);
				continue;
			}

			/* n >= 2 */

			int last[], current[];
			last = s[0];
			current = s[1];
			for (int i = 19; i >= 1; --i) {
				int tmp[] = last;
				last = current;
				current = tmp;
				for (int j = 0; j <= n; ++j) {
					fillS(i, j, last, current);
					//System.out.printf("(%7d, %7d): %9d\n", 1 << i, j, s[i][j]);
				}
			}
			
			System.out.println("" + current[n]);
		}
	}

	private static void fillS(int od, int val, int last[], int current[]) {
		int power = 1 << od;
		if (od == 19) {
			current[val] = val / power + 1;
			return;
		}

		int first = last[val];
		int second = (val < power ? 0 : current[val - power]);
		current[val] = (first + second) % 1000000000;
	}
}
			
			

		
