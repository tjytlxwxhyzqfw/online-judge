import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public int lengthOfLastWord(String s) {
		if (s == null || s.length() == 0)
			return 0;

		int i;

        for (i = s.length()-1; i >= 0; --i)
            if (s.charAt(i) != ' ')
                break;
        
        if (i < 0)
            return 0;

        int rght = i;
		for (; i >= 0; --i)
			if (s.charAt(i) == ' ')
				break;

		return rght-i;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		Solution solution = new Solution();
		while (true) {
			String str = scanner.next();
			int res = solution.lengthOfLastWord(str);
			System.out.printf("%d\n", res);
		}
	}
}
