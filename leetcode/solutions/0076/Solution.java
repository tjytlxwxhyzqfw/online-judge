import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
 * 答案: 尾指针不断后移, 遇到包含t的窗口后, 收缩头指针, 直到头指针不能收缩为止
 *
 * 妙用计数器系列
 */

public class Solution {

	public String minWindow(String s, String t) {
		int freq[] = new int[128], prio[] = new int[128];

		Arrays.fill(prio, 0);
		Arrays.fill(freq, 0);

		for (int i = 0; i < t.length(); ++i)
			++prio[t.charAt(i)];

		int total = t.length();

		int bgn = 0, end = 0, minLength = Integer.MAX_VALUE;
		int fst = 0, sec = 0, ncovered = 0;
		for (fst = 0; fst < s.length(); ++fst) {
			char ch = s.charAt(fst);
			if (prio[ch] == 0)
				continue;
			// we encounter a character 'ch' in 't'
			++freq[ch];
			//TODO: ATTENTION
			// if(freq[ch] == prio[ch]
			if (freq[ch] <= prio[ch])
				++ncovered;
			if (ncovered >= total) {
				for (; sec <= fst; ++sec) {
					char ch2 = s.charAt(sec);
					if (prio[ch2] > 0 && freq[ch2]-1 < prio[ch2])
						break;
					--freq[ch2];
				}
				if (fst-sec < minLength) {
					minLength = fst-sec;
					bgn = sec;
					end = fst;
				}
			}
		}

		System.out.printf("bgn: %3d, end: %3d\n", bgn+1, end+1);

		if (minLength == Integer.MAX_VALUE)
			return "";
		return s.substring(bgn, end+1);
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		String s = scanner.next();
		String t = scanner.next();
		String c = new Solution().minWindow(s, t);
		System.out.printf("---> %s\n", c);
	}
}
