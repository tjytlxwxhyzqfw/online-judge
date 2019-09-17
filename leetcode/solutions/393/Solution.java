/**
 * 393 UTF-8 Validation
 * Performance: speed=34%, memory=54%
 */

import java.util.*;

public class Solution {
	public boolean validUtf8(int[] data) {
		int len = 0;
		for (int i = 0; i < data.length; ++i) {
			boolean z = false;
			for (int j = 7; j >= 3; --j) {
				if ((data[i] & (1<<j)) == 0) {
					int n = 7 - j;
					switch (n) {
					case 0:
						if (len > 0) return false;
						break;
					case 1:
						if (--len < 0) return false;
						break;
					default:
						if (len != 0) return false;
						len = n - 1;
					}
					z = true;
					break;
				}
			}
			if (!z) return false;
		}
		return len == 0;
	}

	public static void main(String args[]) {
		boolean b = new Solution().validUtf8(new int[]{255});
		System.out.printf("b=%s\n", b);
	}
}

