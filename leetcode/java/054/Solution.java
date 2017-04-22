import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

	private int height, width;
	private int curY, curX;
	private int[][] matrix;
	List<Integer> result = new ArrayList<Integer>();

	public List<Integer> spiralOrder(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
			return new ArrayList<Integer>();

		this.matrix = matrix;
		height = matrix.length;
		width = matrix[0].length+1;
		curY = 0;
		curX = -1;

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

		return result;
	}

	private boolean goNSteps(int dy, int dx, int nsteps) {
		if (nsteps <= 0)
			return false;
		for (int i = 0; i < nsteps; ++i) {
			curY += dy;
			curX += dx;
			//System.out.printf("%3d\n", matrix[curY][curX]);
			result.add(matrix[curY][curX]);
		}
		return true;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int height = scanner.nextInt();
		int width = scanner.nextInt();
		int matrix[][] = new int[height][width];
		for (int y = 0; y < height; ++y)
			for (int x = 0; x < width; ++x)
				matrix[y][x] = scanner.nextInt();
		System.out.printf("---\n");
		Solution solution = new Solution();
		List<Integer> result = solution.spiralOrder(matrix);
		for (Integer i : result)
			System.out.printf("%d ", i);
		System.out.printf("\n");
	}
}
