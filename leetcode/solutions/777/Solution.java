/**
 * 777 Swap Adjacent in LR String 33.9% Medium 251/232
 * Performance: speed=36%, memory=100%
 */

/*
XXLX
XXXL

XXXR
XXRX
	

XXXRX
XXXXR

XXRLX
XXXRL

start cannot have an L lefter than end
end cannot have an R lefter than start

false case:
"XXRXXLXXXX"
"XXXXRXXLXX"
*/

import java.util.*;

public class Solution {
	public boolean canTransform(String start, String end) {
		StringBuilder left = new StringBuilder(), rght = new StringBuilder();
		for (int i = 0; i < start.length(); ++i) {
			if (start.charAt(i) != 'X') left.append(start.charAt(i));
			if (end.charAt(i) != 'X') rght.append(end.charAt(i));
		}
		if (!left.toString().equals(rght.toString())) return false;

		int numStartL = 0, numStartR = 0, numEndL = 0, numEndR = 0;
		for (int i = 0; i < start.length(); ++i) {
			if (start.charAt(i) == 'L') ++numStartL;
			else if (start.charAt(i) == 'R') ++numStartR;
			if (end.charAt(i) == 'L') ++numEndL;
			else if (end.charAt(i) == 'R') ++numEndR;
			if (numStartL > numEndL) return false;
			if (numEndR > numStartR) return false;
		}
		return true;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		System.out.println("done");
	}
}

