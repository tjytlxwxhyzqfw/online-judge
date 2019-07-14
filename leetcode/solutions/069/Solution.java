import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public int mySqrt(int x) {
		int sq;

		int product;
		for (sq = 0;; ++sq) {
			product = sq*sq;
			if (product < 0 || product >= x)
				break;
		}

		return (product == x ? sq : sq-1);
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			int x = scanner.nextInt();
			int sqr = new Solution().mySqrt(x);
			System.out.printf("sqrt(%d) = %d\n", x, sqr);
		}
	}
}
