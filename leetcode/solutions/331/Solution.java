/**
 * 331 Verify Preorder Serialization of a Binary Tree
 * Performance: speed=96%, memory=50% (search)
 * Performance: speed=96%, memory=100% (stack)
 *
 * review logs
 * -----------
 * 20202610: 331 (ac,copied) Verify Preorder Serialization of a Binary Tree (M 39 96 100 19/08/17) 
 * => binary tree: y#x##
 * 20200610: 可以从后向前处理吗? 从后向前遍历, 这个序列必然以两个井号结尾. 我们就不断的去掉两个井号+一个非井号.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class Solution {
	public boolean isValidSerialization(String preorder) {
		if (preorder == null || preorder.length() == 0) return false;
		preorder += ",";
		int n = 0;
		for (int i = 0; i < preorder.length(); ++i) if (preorder.charAt(i) == ',') ++n; 
		int p = 0;

		// 20200610
		// stack中只区分井号和数字, #=true, 数字=false
		// 只要遇到连续的两个井号, 就弹出栈顶的3个元素然后入栈一个true, 表示这个节点被替换成了井号
		// 这是可以的, 因为两个井号预示着我们遇到了一个叶子节点 (对吗?)
		boolean[] stack = new boolean[n];
		for (int i = 0; i < preorder.length(); ++i) {
			if (preorder.charAt(i) == '#') {
				stack[p++] = true;
				while (p >= 3 && stack[p-1] && stack[p-2] && !stack[p-3]) stack[(p=p-2)-1] = true;
				++i; // 跳过井号后面的逗号, 这样的话, 以后再遇到逗号, 就是来自数字
			} else if (preorder.charAt(i) == ',') stack[p++] = false;
		}

		return p == 1 && stack[0];
	}

	public boolean isValidSerialization_search(String preorder) {
		if (preorder == null || preorder.length() == 0) return false;

		int n = 1;
		for (int i = 0; i < preorder.length(); ++i) if (preorder.charAt(i) == ',') ++n;
		boolean[] a = new boolean[n];
		int p = 0;
		for (int i = 0; i < preorder.length(); ++i) {
			if (preorder.charAt(i) == ',') ++p;
			else if (preorder.charAt(i) == '#') a[p] = true;
		}

		// for (int i = 0; i < n; ++i) System.out.printf("%d\n", a[i] ? 0 : 1);
		// System.out.println();

		return search(a, 0, n) == n;
	}

	/* search returns the size of a tree rooted at a[i]
	 * @param a: the pro-order traversing sequence
	 * @param i: a index in array `a` 0 <= i < a.length
	 * @param n: length of a (not necessary)
	 * @returns: the size of the tree rooted at a[i]
	 * (date: 20200610)
	 */
	int search(boolean[] a, int i, int n) {
		if (i >= n) return 0;
		if (a[i]) return 1;

		int left = search(a, i+1, n);
		if (left == 0) return 0;
		int rght = search(a, i+left+1, n); 
		if (rght == 0) return 0;

		// System.out.printf("i=%2d, left=%2d, rght=%2d\n", i, left, rght);
		return left + rght + 1;
	}

	public static void main(String args[]) {
		boolean b1 = new Solution().isValidSerialization("900,300,400,#,#,100,#,#,200,#,600,#,#");
		System.out.printf("result1: %s\n", b1);

		boolean b2 = new Solution().isValidSerialization("111,#");
		System.out.printf("result2: %s\n", b2);

		boolean b3 = new Solution().isValidSerialization("999,#,#,111");
		System.out.printf("result3: %s\n", b3);
	}
}

