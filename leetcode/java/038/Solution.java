import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public String countAndSay(int n) {
		String s = "1";
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < n; ++i) {
			int idx = 0, length = s.length();
			
			sb.delete(0, sb.length());
			while (idx < length) {
				int cnt = count(s, idx, length);
				sb.append(""+cnt+s.charAt(idx));
				idx += cnt;
			}
			s = sb.toString();
			System.out.printf("%3d: %s\n", i, s);
		}
		return s;
	}

	private int count(String s, int bgn, int end) {
		char target = s.charAt(bgn);
		int ans = 0;
		for (int i = bgn; i < end; ++i)
			if (s.charAt(i) == target)
				++ans;
			else
				break;
		return ans;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int num = scanner.nextInt();
		String res = new Solution().countAndSay(num);
		System.out.printf("---> %s\n", res);
	}
}
