import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public int climbStairs(int n) {
		int x = -1, y = 2, z = 1;
		if (n <= 0)
			return 0;
		else if (n == 1)
			return z;
		else if (n == 2)
			return y;

		for (int i = n-2; i >= 1; --i) {
			x = y+z;
			z = y;
			y = x;
		}

		return x;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
	}
}
