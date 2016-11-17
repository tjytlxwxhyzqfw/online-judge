/**
 * 1421 搬寝室
 *
 * 看了答案又做的,很NB,估计我很难想出来
 */

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

public class P1421 {
	private static int npairs, nelements;
	private static int s[][], a[];

	public static void main(String[] args) {
		s = new int[2000][1000];
		a = new int[2000];

		try {
			System.setIn(new FileInputStream("Inputs/1421"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			initACase(scanner);
			Arrays.sort(a, 0, nelements);

			//for (int i = 0; i <= nelements - 1; ++i)
				//System.out.printf("%d ", a[i]);
			//System.out.println();
			s[nelements - 2][1] = deltaSquare(nelements - 2, nelements - 1);
			//System.out.printf("s[%d][%d]: %d\n", nelements - 2, 1, s[nelements - 2][1]);
			for (int j = 2; j <= npairs; ++j)
				s[nelements - 2][j] = Integer.MAX_VALUE;

			// optimize
			int nrests, jmax, skip, use;
			for (int i = nelements - 3; i >= 0; --i) {
				//System.out.printf("%2d: %2d\n", i, a[i]);
				nrests = (nelements - i) / 2;
				jmax = (nrests < npairs ? nrests : npairs);
				for (int j = 1; j <= jmax; ++j) {
					skip = s[i + 1][j];
					use = (j > 1 ? s[i + 2][j - 1] : 0);
					//System.out.printf("s[%d][%d]: %d\n", 
						//i + 2, j - 1, s[i + 2][j - 1]);
					//System.out.printf("use1: %5d\n", use);
					if (use < Integer.MAX_VALUE)
						use += deltaSquare(i, i + 1);
					//System.out.printf("use2: %5d\n", use);
					s[i][j] = (skip < use ? skip : use);
					//System.out.printf("skip: %5d, use: %5d\n", skip, use);
					//System.out.printf("(%2d, %2d): %5d\n", i, j, s[i][j]);
				}
					
				for (int j = nrests + 1; j <= npairs; ++j)
					s[i][j] = Integer.MAX_VALUE;
			}
			System.out.println("" + s[0][npairs]);
		}
	}
	private static void initACase(Scanner scanner) {
		nelements = scanner.nextInt();
		npairs = scanner.nextInt();
		for (int i = 0; i < nelements; ++i) {
			a[i] = scanner.nextInt();
			//System.out.printf("%2d: %2d(%4d)\n", i, a[i], a.length);
		}
	}
	private static int deltaSquare(int i, int j) {
		int delta = a[i] - a[j];
		return delta * delta;
	}
}
