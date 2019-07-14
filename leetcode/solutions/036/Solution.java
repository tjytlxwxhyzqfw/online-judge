import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public boolean isValidSudoku(char[][] board) {

		for (int y = 0; y < 9; ++y)
			if (!valiad_rc(y, board, true))
				return false;

		//System.out.printf("rows ok\n");

		for (int x = 0; x < 9; ++x)
			if (!valiad_rc(x, board, false))
				return false;

		//System.out.printf("cols ok\n");

		for (int y = 0; y < 3; ++y)
			for (int x = 0; x < 3; ++x)
				if (!valiad_sq(3*y, 3*x, board))
					return false;

		//System.out.printf("sqs ok\n");

		return true;
	}

	private char getChar(int var, int fix, char board[][], boolean fixrow) {
		if (fixrow)
			return board[fix][var];
		return board[var][fix];
	}

	private boolean used(char ch, boolean[] z) {
		if (ch == '.')
			return false;
		int idx = ch - '1';
		if (z[idx] == true)
			return true;
		z[idx] = true;
		return false;
	}


	private boolean valiad_rc(int fix, char board[][], boolean byrow) {
		boolean z[] = new boolean[9];

		for (int i = 0; i < 9; ++i) {
			char ch = getChar(i, fix, board, byrow);
			if (used(ch, z))
				return false;
		}

		return true;
	}

	private boolean valiad_sq(int top, int left, char board[][]) {
		boolean z[] = new boolean[9];

		for (int y = top; y < top+3; ++y)
			for (int x = left; x < left+3; ++x)
				if (used(board[y][x], z))
					return false;
		return true;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		char board[][] = new char[9][9];

		for (int i = 0; i < 9; ++i) {
			String line = scanner.next();
			for (int j = 0; j < 9; ++j)
				board[i][j] = line.charAt(j);
		}

		boolean ans = new Solution().isValidSudoku(board);
		System.out.printf("--> %s\n", ans);
	}
}
