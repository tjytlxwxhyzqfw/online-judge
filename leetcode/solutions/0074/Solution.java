import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {

	public boolean searchMatrix(int matrix[][], int target) {
		if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
			return false;

		int height = matrix.length, width = matrix[0].length;
		int i, j, k = 0;

		i = 0;
		j = height-1;
		while (i <= j) {
			k = (i+j)/2;
			if (matrix[k][0] > target)
				j = k - 1;
			else if (matrix[k][width-1] < target)
				i = k + 1;
			else
				break;
		}

		if (!(matrix[k][0] <= target && target <= matrix[k][width-1]))
			return false;

		int idx = Arrays.binarySearch(matrix[k], target);
		return idx >= 0;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
	}
}
