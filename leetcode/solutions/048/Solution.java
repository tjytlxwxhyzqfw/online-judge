import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// 用和交换两个元素
// 如果两个元素来自同一个位置,那么最终答案一定是0
// !!!!!!!!!!
// 最好的交换两个元素的方法就是使用临时变量!切记!切记!

public class Solution {

	public void rotate(int[][] a) {
		if (a == null || a.length == 0)
			return;

		int n = a.length;

		//System.out.printf("--- a ---\n");
		//print(a);

		for (int y = 0; y < n; ++y) {
			for (int x = y+1; x < n; ++x) {
				int yy = x;
				int xx = y;

				int t = a[y][x];
				a[y][x] = a[yy][xx];
				a[yy][xx] = t;
			}
		}

		//System.out.printf("--- a.T ---\n");
		//print(a);

		int mid = (n-1)/2;
		for (int y = 0; y < n; ++y) {
			for (int x = 0; x <= mid; ++x) {
				int xx = n-x-1;

				int t = a[y][x];
				a[y][x] = a[y][xx];
				a[y][xx] = t;
			}
		}

		//System.out.printf("--- a.rotate ---\n");
		//print(a);
	}

	private void print(int a[][]) {
		int n = a.length;
		for (int y = 0; y < n; ++y) {
			for (int x = 0; x < n; ++x)
				System.out.printf("%3d ", a[y][x]);
			System.out.printf("\n");
		}
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int a[][] = new int[n][n];
		for (int y = 0; y < n; ++y)
			for (int x = 0; x < n; ++x)
				a[y][x] = scanner.nextInt();
		new Solution().rotate(a);
	}
}
