import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// -2147483648 要特别照顾!!

public class Solution {

	public double myPow(double x, int n) {
		boolean special = false;
		if (n == Integer.MIN_VALUE) {
			n += 1;
			special = true;
		}

		// 除法要考虑0!!!!!!!
		int sign = (n == 0 ? 1 : n / Math.abs(n));
		double power = x, ans = 1;
		for (n = Math.abs(n); n != 0; n >>= 1) {
			boolean mul = ((n & 1) == 1);
			if (mul) {
				ans *= power;
			}
			power *= power;
		}

		if (special)
			ans *= x;

		return sign < 0 ? 1.0/ans : ans;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		Solution solution = new Solution();
		while (true) {
			double x = scanner.nextDouble();
			int n = scanner.nextInt();
			double ans = solution.myPow(x, n);
			System.out.printf("ans: %f\n", ans);
		}
	}
}
