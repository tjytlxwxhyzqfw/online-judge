import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public int maxSubArray(int[] a) {
		if (a == null || a.length == 0)
			return 0;

		int n = a.length;
		int s = a[n-1];

		int t = s;
		for (int i = n-2; i >= 0; --i) {
			t = Math.max(a[i]+t, a[i]);
			s = Math.max(t, s);
		}

		System.out.printf("%d\n", s);
		return s;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
	}
}
