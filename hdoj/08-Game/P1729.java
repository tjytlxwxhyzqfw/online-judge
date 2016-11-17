import java.io.FileInputStream;
import java.util.Scanner;
import java.util.ArrayList;

public class P1729 {
	private static Scanner scanner;

	private static final int B = 50;
	private static final int S = 1000001;
	private static int c[], s[], sg[], flag[], nboxes;
	private static int casid = 0;

	static {
		try {
			System.setIn(new FileInputStream("Inputs/1729"));
		} catch (Exception e) {
			throw new RuntimeException();
		}
		scanner = new Scanner(System.in);
		c = new int[B];
		s = new int[B];
		sg = new int[S];
		flag = new int[S];
	}

	public static void main(String args[]) {
		int result = 0;
		while (initACase()) {
			for (int i = 0; i < nboxes; ++i) {
				initSg(i);
				result ^= sg[c[i]];
			}
			System.out.printf("Case %d:\n", ++casid);
			System.out.printf("%s", result == 0 ? "No" : "Yes");
			System.out.println();
		}
	}

	private static void initSg(int i) {
		//System.out.printf("initSg: %d\n", i);
		sg[s[i]] = 0;
		for (int j = s[i]-1; j >= c[i]; --j)
			fillSg(i, j);
	}

	private static void fillSg(int i, int j) {
		if (j >= 1000) {
			sg[j] = s[i] - j;
			return;
		}
		int t = Math.min(j*j, s[i]-j);
		for (int k = 0; k < t; ++k)
			flag[k] = 0;
		for (int k = 1; k <= t; ++k) {
			int sgv = sg[j+k];
			if (sgv < t) flag[sgv] = 1;
		}
		int k;
		for (k = 0; k < t; ++k)
			if (flag[k] == 0) break;
		sg[j] = k;
		//System.out.printf("\tsg[%d]: %d\n", j, sg[j]);
	}
	
	private static boolean initACase() {
		if (!scanner.hasNext())
			return false;
		nboxes = scanner.nextInt();
		if (nboxes == 0)
			return false;
		for (int i = 0; i < nboxes; ++i) {
			s[i] = scanner.nextInt();
			c[i] = scanner.nextInt();
			//System.out.printf("s[%d]: %d, c[%d]: %d\n", i, s[i], i, c[i]);
		}
		return true;
	}
}
