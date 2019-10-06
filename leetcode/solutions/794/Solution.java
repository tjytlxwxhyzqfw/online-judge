/**
 * 794	Valid Tic-Tac-Toe State 30.9% Medium 126/396
 * Performance: speed=12%, memory=100%
 */

import java.util.*;

public class Solution {
	public boolean validTicTacToe(String[] board) {
		int nx = 0, no = 0;
		for (int i = 0; i < board.length; ++i) {
			for (int j = 0; j < board[0].length(); ++j) {
				if (board[i].charAt(j) == 'X') ++nx;
				else if (board[i].charAt(j) == 'O') ++no;
			}
		}
		int nsteps = nx + no;
		if ((nsteps & 1) == 0 && (nx != no)) return false;
		if ((nsteps & 1) == 1 && (nx - no != 1)) return false;

		if (end(board)) {
			char last = ((nsteps & 1) == 0) ? 'O' : 'X';
			for (int i = 0; i < board.length; ++i) {
				String orig = board[i];
				StringBuilder b = new StringBuilder(orig);
				for (int j = 0; j < orig.length(); ++j) {
					if (orig.charAt(j) == last) {
						b.setCharAt(j, ' ');
						board[i] = b.toString();
						if (!end(board)) return true;
					}
				}
				board[i] = orig;
			}
			return false;
		} else return true;
	}

	boolean end(String[] b) {
		for (int i = 0; i < b.length; ++i) if (b[i].equals("XXX") || b[i].equals("OOO")) return true;
		for (int i = 0; i < b[0].length(); ++i) {
			if (b[0].charAt(i) == 'X' && b[1].charAt(i) == 'X' && b[2].charAt(i) == 'X') return true;
			if (b[0].charAt(i) == 'O' && b[1].charAt(i) == 'O' && b[2].charAt(i) == 'O') return true;
		}
		if (b[0].charAt(0) == 'X' && b[1].charAt(1) == 'X' && b[2].charAt(2) == 'X') return true;
		if (b[0].charAt(0) == 'O' && b[1].charAt(1) == 'O' && b[2].charAt(2) == 'O') return true;
		if (b[0].charAt(2) == 'X' && b[1].charAt(1) == 'X' && b[2].charAt(0) == 'X') return true;
		if (b[0].charAt(2) == 'O' && b[1].charAt(1) == 'O' && b[2].charAt(0) == 'O') return true;
		return false;
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		// XOX
		// OXO
		// XOX
		assert s.validTicTacToe(new String[]{"XOX", "OXO", "XOX"});


		// failed
		assert s.validTicTacToe(new String[]{"XXX", "OOX", "OOX"});

		assert s.validTicTacToe(new String[]{"   ", "   ", "   "});
		assert s.validTicTacToe(new String[]{"X  ", "   ", "   "});
		assert s.validTicTacToe(new String[]{"XOX", "O O", "XOX"});

		assert s.validTicTacToe(new String[]{"XXX", "OOO", "XXX"}) == false;
		assert s.validTicTacToe(new String[]{"O  ", "   ", "   "}) == false;
		assert s.validTicTacToe(new String[]{"XOX", " X ", "   "}) == false;
		assert s.validTicTacToe(new String[]{"XXX", "   ", "OOO"}) == false;
		assert s.validTicTacToe(new String[]{"OXO", "XOX", "OXO"}) == false;

		System.out.println("done");
	}
}

