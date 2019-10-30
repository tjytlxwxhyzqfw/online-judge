/**
 * 831 Masking Personal Information 42.4% Medium 54/238
 * Performance: speed=79%, memory=100%
 */

import java.util.*;

public class Solution {
	public String maskPII(String s) {
		if (s.indexOf("@") != -1) return maskEmail(s);
		else return maskPhoneNum(s);
	}

	String maskEmail(String s) {
		String[] s1 = s.split("@");
		String[] s2 = s1[1].split("\\.");
		String name1 = s1[0].toLowerCase();
		return name1.charAt(0)
			+ "*****"
			+ name1.charAt(name1.length()-1)
			+ "@"
			+ s2[0].toLowerCase()
			+ "."
			+ s2[1].toLowerCase();
	}

	String maskPhoneNum(String s) {
		StringBuilder b = new StringBuilder();
		for (int i = 0; i < s.length(); ++i) {
			if (Character.isDigit(s.charAt(i))) {
				b.append(s.charAt(i));
			}
		}
		String n = b.toString();
		String m = "";
		if (n.length() > 10) {
			m += "+";
			for (int i = 10; i < n.length(); ++i) m += "*";
			m += "-";
		}
		// System.out.printf("n=%s, m(prefix)=%s\n", n, m);
		m += "***-***-" + n.substring(n.length()-4);
		return m;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		System.out.println(s.maskPII("AB@CC.COM"));

		assert s.maskPII("AB@CC.COM").equals("a*****b@cc.com");
		assert s.maskPII("(12)(34)(567 89)-0").equals("***-***-7890");
		assert s.maskPII("86(12)(34)(567 89)-0").equals("+**-***-***-7890");
		assert s.maskPII("866(12)(34)(567 89)-0").equals("+***-***-***-7890");
		assert s.maskPII("8(12)(34)(567 89)-0").equals("+*-***-***-7890");

		System.out.println("done");
	}
}

