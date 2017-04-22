import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public void sortColors(int nums[]) {
		int freq[] = new int[3];
		for (int i = 0; i < 3; ++i)
			freq[i] = 0;
		for (int color : nums)
			++freq[color];

		int current = 0;
		for (int i = 0; i < 3; ++i) {
			while (--freq[i] >= 0)
				nums[current++] = i;
		}
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
	}
}
