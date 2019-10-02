/**
 * 640	Solve the Equation 40.9% Medium	180/427
 * Performance: speed=100%, memory=100%
 */

import java.util.*;

public class Solution {
	public String solveEquation(String eqt) {
		int n = 0, k = 0, b = 0;
		int ksign = 1, sign = 1;
		char last = '+';
		for (int i = 0; i <= eqt.length(); ++i) {
			char ch = i == eqt.length() ? '+' : eqt.charAt(i);
			// System.out.printf("i=%2d, ch=%c, k=%2d, b=%2d, ksign=%2d, sign=%2d\n", i, ch, k, b, ksign, sign);
			switch (ch) {
				case 'x':
					if (!Character.isDigit(last)) n = 1;
					k += ksign * sign * n; 
					{ sign = 1; n = 0; }
					break;
				case '=':
				case '-':
				case '+':
					if (last != 'x') b += -1 * ksign * sign * n;
					{ sign = 1; n = 0; }
					if (ch == '-') sign = -1;
					else if (ch == '=') ksign = -1;
					break;
				default:
					n = n * 10 + ch - 48;
			}
			last = ch;
		}
		return k == 0 ? (b == 0 ? "Infinite solutions" : "No solution"): "x=" + b / k;
		
	}

	public static void main(String args[]) {
		Solution s = new Solution();


		assert s.solveEquation("x=1").equals("x=1");
		assert s.solveEquation("1=x").equals("x=1");
		assert s.solveEquation("x+1=2x").equals("x=1");
		assert s.solveEquation("2x=x+1").equals("x=1");
		assert s.solveEquation("x+3=2+2x").equals("x=1");
		assert s.solveEquation("3+x=2+2x").equals("x=1");
		assert s.solveEquation("x+3=2x+2").equals("x=1");
		assert s.solveEquation("3+x=2x+2").equals("x=1");
		assert s.solveEquation("3+0x=2x+1").equals("x=1");

		assert s.solveEquation("x=2x").equals("x=0");

		assert s.solveEquation("x+0x=2x-x").equals("Infinite solutions");
		assert s.solveEquation("x=x").equals("Infinite solutions");

		assert s.solveEquation("x+0x=2+x").equals("No solution");
		assert s.solveEquation("0=1").equals("No solution");

		assert s.solveEquation("2x+3x-6x=x+2").equals("x=-1");
		assert s.solveEquation("x+5-3+x=6+x-2").equals("x=2");

                System.out.println("done");
	}
}

