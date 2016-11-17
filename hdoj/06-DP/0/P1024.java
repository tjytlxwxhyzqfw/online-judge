/**
 * 1024 Max Sum Plus Plus
 *
 * Chessboard blocks my eyes.
 */

import java.io.FileInputStream;
import java.util.Scanner;

public class P1024 {
	private static int nnums, npairs;
	private static int a[], nextS[], currS[], nextT[], currT[];
	public static void main(String args[]) {
		a = new int[1000];
		nextS = new int[1000];
		currS = new int[1000];
		nextT = new int[1000];
		currT = new int[1000];

		try {
			System.setIn(new FileInputStream("Inputs/1024"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			initACase(scanner);

			//case primary
			currS[1] = currT[1] = a[nnums - 1];

			//case boundary
			for (int j = 2; j <= npairs; ++j)
				currS[j] = currT[j] = Integer.MIN_VALUE;

			//case generic
			int nrests, jmax, cont, term, res, use, skip;
			for (int i = nnums - 2; i >= 0; --i) {
				swapCurrAndNext();
				nrests = nnums - i + 1;
				jmax = (nrests < npairs ? nrests : npairs);
				for (int j = 1; j <= jmax; ++j) {
					//T
					cont = nextT[j];
					term = (j > 1 ? nextS[j - 1] : 0);
					res = (cont > term ? cont : term);
					currT[j] = 
						(res > Integer.MIN_VALUE ? res + a[i] : Integer.MIN_VALUE);

					//S
					use = currT[j];
					skip = nextS[j];
					currS[j] = (use > skip ? use : skip);

					//debug
					//System.out.printf("t(%2d, %2d): %3d\n", i, j, currT[j]);
					//System.out.printf("s(%2d, %2d): %3d\n", i, j, currS[j]);
				}
				for (int j = nrests + 1; j <= npairs; ++j)
					currT[j] = currS[j] = Integer.MIN_VALUE;
			}
			System.out.println("" + currS[npairs]);
		}
	}
	private static void initACase(Scanner scanner) {
		npairs = scanner.nextInt();
		nnums = scanner.nextInt();
		//System.out.printf("npairs: %2d, nnums: %2d\n", npairs, nnums);
		for (int i = 0; i < nnums; ++i)
			a[i] = scanner.nextInt();
	}
	private static void swapCurrAndNext() {
		int tmp[];

		tmp = nextS;
		nextS = currS;
		currS = tmp;

		tmp = nextT;
		nextT = currT;
		currT = tmp;
	}
}
