import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

	private boolean s[][][][] = new boolean[9][9][9][512];

	private int maskRows[] = new int[9];
	private int maskCols[] = new int[9];
	private int maskSqrs[][] = new int[3][3];

	private char board[][];

	public void solveSudoku(char[][] board) {
		this.board = board;

		for (int y = 0; y < 9; ++y) {
			for (int x = 0; x < 9; ++x) {
				char ch = board[y][x];
				if (ch == '.')
					continue;
				int num = ch - '1';
				int bit = (1 << num);

				maskRows[y] |= bit;
				maskCols[x] |= bit;
				maskSqrs[y/3][x/3] |= bit;
			}
		}

		boolean res = dfs(0, 0);
		//System.out.printf("-------> %s\n", res);
		//printBoard();
	}

	private boolean dfs(int y, int x) {
		int maskRowsOrigin = maskRows[y];
		int maskColsOrigin = maskCols[x];
		int maskSqrsOrigin = maskSqrs[y/3][x/3];
		char boardOrigin = board[y][x];

		int xx = (x+1) % 9;
		int yy = (xx == 0 ? y+1 : y);

		int mask = maskGen(y, x);

		if (y == 8 && x == 8) {
			//System.out.printf("[%3d, %3d]: %s\n", y, x, maskString(mask));
			for (int i = 0; i < 9; ++i) {
				int bit = 1<<i;
				boolean ok = (((mask & bit) == 0) || (board[y][x]-'1' == i));
				if (ok) {
					board[y][x] = (char)(i+'1');
					return true;
				}
			}
			return false;
		}

		for (int i = 0; i < 9; ++i) {

			int bit = 1<<i;
			boolean ok = (((mask & bit) == 0) || (board[y][x]-'1' == i));
			if (!ok)
				continue;

			//System.out.printf("\t fill with: %d\n", i);

			maskRows[y] |= bit;
			maskCols[x] |= bit;
			maskSqrs[y/3][x/3] |= bit;
			board[y][x] = (char)(i+'1');

			boolean res = dfs(yy, xx);
			if (res == true) {
				return true;
			} else {
				maskRows[y] = maskRowsOrigin;
				maskCols[x] = maskColsOrigin;
				maskSqrs[y/3][x/3] = maskSqrsOrigin;
				board[y][x] = boardOrigin;
			}
		}

		return false;
	}

	private int maskGen(int y, int x) {
		int mask = 0;

		char ch = board[y][x];
		if (ch == '.') {
			mask = maskRows[y] | maskCols[x] | maskSqrs[y/3][x/3];
		} else {
			int num = ch - '1';
			mask = ~(mask | (1<<num));
			mask &= 511;
		}

		return mask;
	}

	private String maskString(int mask) {
		StringBuilder sb = new StringBuilder();
		for (int j = 0; j < 9; ++j) {
			char ch = ((mask & (1<<j)) == 0 ? '*' : (char)('0'+j));
			sb.append(ch);
		}
		return sb.toString();
	}

	private void printBoard() {
		for (int y = 0; y < 9; ++y) {
			for (int x = 0; x < 9; ++x)
				System.out.printf("%c", board[y][x]);
			System.out.printf("\n");
		}
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		char board[][] = new char[9][9];
		for (int y = 0; y < 9; ++y) {
			String line = scanner.next();
			for (int x = 0; x < 9; ++x) {
				board[y][x] = line.charAt(x);
			}
		}

		Solution solution = new Solution();
		solution.solveSudoku(board);
	}
}
