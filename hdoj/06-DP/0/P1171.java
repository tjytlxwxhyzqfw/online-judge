/**
 * 1171 Big Event in HDU
 *
 * new appoch
 */

import java.io.FileInputStream;
import java.util.Scanner;

public class P1171 {
	private static int nfacilities, average, total;
	private static int s[][] = new int[50][125000];
	private static int quantity[] = new int[50];
	private static int value[] = new int[50];

	public static void main(String args[]) {
		try {
			System.setIn(new FileInputStream("Inputs/1171"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(System.in);
		while (initACase(scanner)) {
			for (int i = nfacilities - 1; i >= 0; --i) {
				for (int j = 0; j <= average; ++j) {
					fillS(i, j);
					//System.out.printf("(%2d, %5d): %5d\n", i, j, s[i][j]);
				}
			}
			System.out.println("" + s[0][average] + " " + (total - s[0][average]));
		}
	}
	private static void fillS(int fid, int thresh) {
		int thisValue = value[fid];
		int thisQuantity = quantity[fid];

		if (fid == nfacilities - 1) {
			int need = thresh / thisValue;
			if (need * thisValue < thresh)
				need += 1;
			s[fid][thresh] = (need > thisQuantity ? Integer.MAX_VALUE : need * thisValue);
			return;
		}

		int first = s[fid + 1][thresh];
		int extra = (thresh < thisValue ? 0 : s[fid][thresh - thisValue]);
		int second = (extra < Integer.MAX_VALUE ? extra + thisValue : Integer.MAX_VALUE);
		s[fid][thresh] = (first < second ? first : second);
	}
	private static boolean initACase(Scanner scanner) {
		nfacilities = scanner.nextInt();
		if (nfacilities == -1)
			return false;
		total = 0;
		for (int i = 0; i < nfacilities; ++i) {
			value[i] = scanner.nextInt();
			quantity[i] = scanner.nextInt();
			total += value[i] * quantity[i];
		}
		average = (total + 1) / 2;
		return true;
	}
}
