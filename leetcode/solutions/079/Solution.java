import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
 * 貌似只能深度优先搜索?
 */

public class Solution {
	private int directions[] = {1, 0, -1, 0, 0, 1, 0, -1};
	private int height, width, current;
	private String word;
	private char board[][];

	public boolean exist(char board[][], String word) {
		if (board == null || board.length == 0 || board[0] == null || board[0].length == 0)
			return word == null || word.length() == 0;
		if (word == null || word.length() == 0)
			return true;

		this.word = word;
		this.board = board;
		height = board.length;
		width = board[0].length;

		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				current = 0;
				boolean result = dfs(y, x);
				if (result)
					return true;
			}
		}

		return false;
	}

	boolean dfs(int y, int x) {
		//System.out.printf("dfs: (%3d, %3d)\n", y, x);
		if (board[y][x] != word.charAt(current)) {
			//System.out.printf("\tfalse: %c != %c\n", board[y][x], word.charAt(current));
			return false;
		}

		++current;
		if (current == word.length()) {
			//System.out.printf("\ttrue\n");
			return true;
		}
		//System.out.printf("\tcurrent: %d\n", current);

		char origin = board[y][x];
		board[y][x] = 0;

		for (int i = 0; i < 4; ++i) {
			int nextX = x + directions[i];
			int nextY = y + directions[i+4];
			if (!(0 <= nextY && nextY < height) || !(0 <= nextX && nextX < width))
				continue;
			if (board[nextY][nextX] == 0)
				continue;
			boolean result = dfs(nextY, nextX);
			if (result)
				return true;
		}

		--current;
		board[y][x] = origin;

		return false;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int height = scanner.nextInt();
		int width = scanner.nextInt();
		char board[][] = new char[height][width];
		for (int y = 0; y < height; ++y) {
			String line = scanner.next();
			for (int x = 0; x < width; ++x)
				board[y][x] = line.charAt(x);
		}
		while (scanner.hasNext()) {
			String target = scanner.next();
			boolean result = new Solution().exist(board, target);
			System.out.printf("%s: %s\n", target, result);
		}
	}
}
