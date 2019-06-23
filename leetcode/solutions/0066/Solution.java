import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public int[] plusOne(int digits[]) {
		int n = digits.length;
		int carry = 1;
		for (int i = n-1; i >= 0; --i) {
			digits[i] += carry;
			carry = digits[i] / 10;
			digits[i] = digits[i] % 10;
		}

		int result[] = digits;
		if (carry == 1) {
			result = new int[n+1];
			result[0] = 1;
			for (int i = 1; i <= n; ++i)
				result[i] = digits[i-1];
		}

		return result;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
	}
}
