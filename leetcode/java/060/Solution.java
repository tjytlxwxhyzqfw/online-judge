import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public String getPermutation(int n, int k) {
		if (k <= 0)
			return null;

		int a[] = new int[n];
		for (int i = 1; i <= n; ++i)
			a[i-1] = i;

		for (int i = 2; i <= k; ++i)
			next(a);

		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < n; ++j)
			sb.append(a[j]);

		return sb.toString();
	}

	private void next(int a[]) {
		int n = a.length;
		if (a == null || n <= 1)
			return;
		
		int left = -1;
		for (int i = n-2; i >= 0; --i) {
			if (a[i] < a[i+1]) {
				left = i;
				break;
			}
		}
		if (left == -1)
			return;

		int rght = 0;
		for (int i = n-1; i > left; --i) {
			if (a[i] > a[left]) {
				rght = i;
				break;
			}
		}

		int t = a[left];
		a[left] = a[rght];
		a[rght] = t;

		for (int i = left+1; i <= (left+n)/2; ++i) {
			int j = left+n-i;

			t = a[i];
			a[i] = a[j];
			a[j] = t;
		}

	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		new Solution().getPermutation(5, 6);
	}
}
