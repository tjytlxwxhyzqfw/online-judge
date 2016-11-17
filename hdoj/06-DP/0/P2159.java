/**
 * 2159 FATE
 *
 * WQBB, GOOD
 */

import java.io.FileInputStream;
import java.util.Scanner;

public class P2159 {
	private static int nexps, ndurs, nmids, nkils;
	private static int s[][][], exp[], dur[];
	public static void main(String args[]) {
		s = new int[100][100][100];
		exp = new int[100];
		dur = new int[100];

		try {
			System.setIn(new FileInputStream("Inputs/2159"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			initACase(scanner);
			for (int i = nmids - 1; i >= 0; --i) {
				for (int j = 0; j <= nexps; ++j) {
					for (int k = 0; k <= nkils; ++k) {
						fillS(i, j, k);
						//System.out.printf("(%2d, %2d, %2d): %2d\n",
							//i, j, k, s[i][j][k]);
					}
				}
			}
			int res = ndurs - s[0][nexps][nkils];
			if (res < 0)
				res = -1;
			System.out.println("" + res);
		}
	}
	private static void fillS(int mid, int exps, int kils) {
		int thisExp = exp[mid];
		int thisDur = dur[mid];
		int need = exps / thisExp;
		if (need * thisExp < exps)
			need += 1;

		if (mid == nmids - 1) {
			s[mid][exps][kils] = (need > kils ? Integer.MAX_VALUE : thisDur * need);
			return;
		}	

		int first = s[mid + 1][exps][kils];
		int remainder = exps - thisExp;
		if (remainder < 0)
			remainder = 0;
		int rest = (kils > 0 ? s[mid][remainder][kils - 1] : Integer.MAX_VALUE);
		if (rest < Integer.MAX_VALUE)
			rest += thisDur;

		s[mid][exps][kils] = (first < rest ? first : rest);
	}
	private static void initACase(Scanner scanner) {
		nexps = scanner.nextInt();
		if (nexps == -1)
			System.exit(1);
		ndurs = scanner.nextInt();
		nmids = scanner.nextInt();
		nkils = scanner.nextInt();
		for (int i = 0; i < nmids; ++i) {
			exp[i] = scanner.nextInt();
			dur[i] = scanner.nextInt();
		}
	}
}
