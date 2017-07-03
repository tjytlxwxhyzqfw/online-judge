import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
	public String addBinary(String a, String b) {
		StringBuilder builder = new StringBuilder();

		int i = a.length()-1;
		int j = b.length()-1;

		int carry = 0;
		while (i >= 0 && j >= 0) {
			int z = carry + a.charAt(i) + b.charAt(j) - 96;
			carry = z / 2;
			z %= 2;
			builder.append(z);
			--i;
			--j;
		}

		while (i >= 0) {
			int z = carry + a.charAt(i) - 48;
			carry = z / 2;
			z %= 2;
			builder.append(z);
			--i;
		}

		while (j >= 0) {
			int z = carry + b.charAt(j) - 48;
			carry = z / 2;
			z %= 2;
			builder.append(z);
			--j;
		}

		if (carry > 0)
			builder.append(carry);

		return builder.reverse().toString();
		
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
	}
}
