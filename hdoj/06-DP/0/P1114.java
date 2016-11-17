/*
 * 1114 Piggy-Bank
 *
 * WQBB
 */
import java.io.FileInputStream;
import java.util.Scanner;

public class P1114 {
	static int ncids, total;
	static int weight[], value[], s[][];

	public static void main(String args[]) {
		weight = new int[500];
		value = new int[500];
		s = new int[500][10000];

		try {
			System.setIn(new FileInputStream("Inputs/1114"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Scanner scanner = new Scanner(System.in);
		int ncases = scanner.nextInt();
		while (ncases-- > 0) {
			initACase(scanner);
			for (int j = 0; j <= total; ++j) {
				int cid = ncids - 1;
				int wei = weight[cid];
				int val = value[cid];
				int need = j / wei;
				if (need * wei == j)
					s[cid][j] = value[cid] * need;
				else
					s[cid][j] = Integer.MAX_VALUE;
			}
			for (int i = ncids - 2; i >= 0; --i) {
				for (int j = 0; j <= total; ++j) {
					int wei = weight[i];
					int val = value[i];
					int first = s[i + 1][j];
					int rest = Integer.MAX_VALUE;
					if (j >= wei) {
						rest = s[i][j -wei];
						if (rest != Integer.MAX_VALUE)
							rest += val;
					}
					s[i][j] = (first < rest ? first : rest);
				}
			}
			int res = s[0][total];
			if (res < Integer.MAX_VALUE)
				System.out.println("The minimum amount of money in the piggy-bank is " 
					+ s[0][total] + ".");
			else
				System.out.println("This is impossible.");
		}
	}

	private static void initACase(Scanner scanner) {
		int empty = scanner.nextInt();
		int full = scanner.nextInt();
		total = full - empty;
		ncids = scanner.nextInt();
		for (int i = 0; i < ncids; ++i) {
			value[i] = scanner.nextInt();
			weight[i] = scanner.nextInt();
		}
	}

	private static void fillS(int cid, int wei) {
		int thisWeight = weight[cid];
		int thisValue = value[cid];
		if (cid == ncids - 1) {
			int need = wei / thisWeight;
			if (thisWeight * need == wei)
				s[cid][wei] = thisValue * need;
			else {
				/* impossible */
				s[cid][wei] = Integer.MAX_VALUE;
			}
			return;
		}

		int first = s[cid + 1][wei];
		int rest = Integer.MAX_VALUE;
		if (wei >= thisWeight) {
			rest = s[cid][wei - thisWeight];
			if (rest != Integer.MAX_VALUE)
				rest += thisValue;
		}
		s[cid][wei] = (first < rest ? first : rest);
	}
}
