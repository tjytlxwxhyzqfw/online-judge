import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public void setZeroes(int matrix[][]) {
		if (matrix == null)
			return;
		if (matrix.length == 0)
			return;
		if (matrix[0].length == 0)
			return;

		boolean clearFirstCol = false;
		int height = matrix.length, width = matrix[0].length;
		for (int y = 0; y < height; ++y) {
			for (int x = 0; x < width; ++x) {
				if (matrix[y][x] == 0) {
					matrix[y][0] = 0;

					if (x == 0)
						clearFirstCol = true;
					else
						matrix[0][x] = 0;
				}
			}
		}

		for (int y = 1; y < height; ++y) {
			if (matrix[y][0] == 0) {
				for (int x = 0; x < width; ++x)
					matrix[y][x] = 0;
			}
		}

		for (int x = 1; x < width; ++x) {
			if (matrix[0][x] == 0) {
				for (int y = 0; y < height; ++y)
					matrix[y][x] = 0;
			}
		}

		if (matrix[0][0] == 0) {
			for (int x = 0; x < width; ++x)
				matrix[0][x] = 0;
		}

		if (clearFirstCol) {
			for (int y = 0; y < height; ++y)
				matrix[y][0] = 0;
		}
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
	}
}
