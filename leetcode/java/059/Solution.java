import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

	private int curY = 0, curX = -1;
	private int curNum = 1;
	private int width, height, length;
	private int matrix[][];

	public int[][] generateMatrix(int n) {
		matrix = new int[n][n];
		length = width = height = n;
		++width;
		while (true) {
			if (!goNSteps(0, 1, --width))
				break;
			if (!goNSteps(1, 0, --height))
				break;
			if (!goNSteps(0, -1, --width))
				break;
			if (!goNSteps(-1, 0, --height))
				break;
		}

		return matrix;
	}

	private boolean goNSteps(int dy, int dx, int nsteps) {
		//System.out.printf("---->nsteps: %d\n", nsteps);
		if (nsteps == 0)
			return false;
		for (int i = 0; i < nsteps; ++i) {
			//System.out.printf("curY: %3d, curX: %3d\n", curY, curX);
			curY += dy;
			curX += dx;
			matrix[curY][curX] = curNum++;
		}
		return true;
	}

	private void print() {
		for (int y = 0; y < length; ++y) {
			for (int x = 0; x < length; ++x)
				System.out.printf("%3d ", matrix[y][x]);
			System.out.printf("\n");
		}
	}


	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i <= 5; ++i) {
			Solution solution = new Solution();
			solution.generateMatrix(i);
			solution.print();
		}
	}
}
