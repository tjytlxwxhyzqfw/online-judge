/**
 * 289 Game of Life
 * Performance: speed=100%, memory=100%
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
	public void gameOfLife(int[][] board) {
		if (board == null || board.length == 0 || board[0] == null) return;

		int[][] ds = new int[][]{{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
		int n, h = board.length, w = board[0].length;
		for (int i = 0; i < h; ++i) {
			for (int j = 0; j < w; ++j) {
				n = 0;
				for (int k = 0; k < ds.length; ++k) {
					int y = i + ds[k][0], x = j + ds[k][1];
					if (0 <= y && y < h && 0 <= x && x < w && (board[y][x] & 1) != 0) ++n;
				}
				if ((board[i][j] & 1) != 0 && (n == 2 || n == 3)) board[i][j] |= 2;
				else if ((board[i][j] & 1) == 0 && n == 3) board[i][j] |= 2;
			}
		}
		for (int i = 0; i < h; ++i) for (int j = 0; j < w; ++j) board[i][j] >>>= 1;
	}

	public static void main(String args[]) {
	}
}

