/**
 * 529: Minesweeper
 * Performance: speed=100%, memory=100%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	static private int[][] ds = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
	private int h, w;
	public char[][] updateBoard(char[][] board, int[] click) {
		h = board.length;
		w = board[0].length;
		if (board[click[0]][click[1]] == 'M') {
			board[click[0]][click[1]] = 'X';
			return board;
		}
		dfs(board, click[0], click[1]);
		return board;
	}

	private void dfs(char[][] board, int y, int x) {
		// optimistic: verified by caller
		int n = 0, p = 0;
		for (int k = 0; k < ds.length; ++k) {
			int i = y + ds[k][0], j = x + ds[k][1];
			if (0 <= i && i < h && 0 <= j && j < w && board[i][j] == 'M') ++n; 
		}
		if (n == 0) {
			board[y][x] = 'B';
			for (int k = 0; k < ds.length; ++k) {
				int i = y + ds[k][0], j = x + ds[k][1];
				if (0 <= i && i < h && 0 <= j && j < w && board[i][j] == 'E') dfs(board, i, j); 
			}
		} else board[y][x] = (char)(48 + n);
	}

	public static void main(String args[]) {
	}
}

