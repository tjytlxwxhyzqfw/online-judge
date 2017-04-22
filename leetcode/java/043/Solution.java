import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

	/*
	 * 计算大数乘法还真**不需要**大数加法! - -
	 */
	private String add(String lhs, String rhs) {
		String x = new StringBuffer(lhs).reverse().toString();
		String y = new StringBuffer(rhs).reverse().toString();
		int lenx = x.length(), leny = y.length();
		int lenz = Math.max(lenx, leny) + 1;

		char z[] = new char[lenz];
		Arrays.fill(z, '0');

		int carry = 0;
		int i;
		for (i = 0; i < lenx && i < leny; ++i) {
			int left = x.charAt(i) - '0';
			int rght = y.charAt(i) - '0';
			int sum = left + rght + carry;
			z[i] = (char)((sum%10) + '0');
			carry = sum / 10;
		}

		for (; i < lenx; ++i) {
			int num = x.charAt(i) - '0';
			int sum = num + carry;
			z[i] = (char)((sum%10) + '0');
			carry = sum / 10;
		}

		for (; i < leny; ++i) {
			int num = y.charAt(i)-'0';
			int sum = num + carry;
			z[i] = (char)((sum%10) + '0');
			carry = sum / 10;
		}

		if (carry == 1)
			z[i] = '1';

		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < lenz-1; ++j)
			sb.append(z[j]);
		if (carry == 1)
			sb.append(z[lenz-1]);
		
		return sb.reverse().toString();
	}

	public String multiply(String lhs, String rhs) {
		String x = new StringBuilder(lhs).reverse().toString();
		String y = new StringBuilder(rhs).reverse().toString();
		int lenx = x.length(), leny = y.length();
		int lenz = lenx + leny + 1;
		char z[] = new char[lenz];

		Arrays.fill(z, '0');

		int carry = 0;
		for (int i = 0; i < lenx; ++i) {
			int left = x.charAt(i) - '0';
			for (int j = 0; j < leny; ++j) {
				int rght = y.charAt(j) - '0';
				int product = left * rght;
				int origin = z[i+j] - '0';
				int sum = product + origin + carry;
				z[i+j] = (char)((sum%10) + '0');
				carry = sum / 10;
			}

			int idx = i + leny;
			while (carry != 0) {
				int origin = z[idx] - '0';
				int sum = origin + carry;
				z[idx] = (char)((sum%10) + '0');
				carry = sum / 10;
				++idx;
			}
		}

		while (lenz > 1 && z[lenz-1] == '0')
			--lenz;

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lenz; ++i)
			sb.append(z[i]);

		return sb.reverse().toString();
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		String x = scanner.next();
		String y = scanner.next();
		String z = new Solution().multiply(x, y);
		System.out.printf("%s\n", z);
	}
}
