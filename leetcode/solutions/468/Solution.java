/**
 * 468 Validate IP Address 21.6% Medium	144/850
 * Performance: speed=98%, memory=100%
 */

import java.util.*;

public class Solution {
	public String validIPAddress(String ip) {
		if (isv4(ip)) return "IPv4";
		else if (isv6(ip)) return "IPv6";
		else return "Neither";
	}

	boolean isv4(String ip) {
		ip += ".";
		int n = -1, nlen = 0, len = 0, nsegs = 0;
		for (int i = 0; i < ip.length(); ++i) {
			char ch = ip.charAt(i);
			if ('0' <= ch && ch <= '9') {
				if (n == -1) n = ch - 48;
				else n = n * 10 + ch - 48;
			} else if (ch == '.') {
				if (n < 0 || n > 255) return false;
				++len;
				++nsegs;
				if (n / 100 != 0) len += 3;
				else if (n / 10 != 0) len += 2;
				else len += 1;
				n = -1;
				// System.out.printf("len=%d, nsegs=%d\n", len, nsegs);
			} else {
				return false;
			}
		}
		return len == ip.length() && nsegs == 4;
	}

	boolean isv6(String ip) {
		if (ip.startsWith(":") || ip.endsWith(":") || ip.indexOf("::") != -1) return false;

		String[] items = ip.split(":");
		if (items.length != 8) return false;

		for (String item : items) {
			if (item.length() == 0 || item.length() > 4) return false;
			String lower = item.toLowerCase();
			for (int i = 0; i < lower.length(); ++i) {
				char ch = lower.charAt(i);
				if (!('0' <= ch && ch <= '9' || 'a' <= ch && ch <= 'f')) return false;
			}
		}

		return true;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		
		// isv4()
		assert s.isv4("0.0.0.0"); // true
		assert !s.isv4("0.0.0"); // false
		assert !s.isv4("0"); // false
		assert !s.isv4("00.0.0.0"); // false
		assert s.isv4("23.7.138.255"); // true
		assert s.isv4("255.255.155.255"); // true

		// isv6()
		assert s.isv6("0:0:0:0:0:0:0:0"); // true
		assert s.isv6("0:00:0000:0000:000:00:000:00"); // true
		assert s.isv6("ffff:ffff:ffff:ffff:ffff:ffff:ffff:ffff"); // true
		assert !s.isv6("ffff:ffff:ffff:ffff:ffff:ffff:ffff:0ffff"); // false
		assert !s.isv6("ffff:ffff:ffff:ffff:ffff:ffff::0ffff"); // false
		assert !s.isv6(":0:0:0:0:0:0:0:0"); // false
		assert !s.isv6("0:0:0:0:0:0:0:0:"); // false
		assert !s.isv6("0::0:0:0:0:0:0:0"); // false

		// validIPAddress()
		assert s.validIPAddress("").equals("Neither");
	}
}

