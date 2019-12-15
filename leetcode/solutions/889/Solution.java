/**
 * 889 Construct Binary Tree from Preorder and Postorder Traversal 61.9% Medium 529/35
 * Performance: speed=99%, memory=9%
 */

// todo: understand the stack solution ?
// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/discuss/161268/C%2B%2BJavaPython-One-Pass-Real-O(N)

import java.util.*;

public class Solution {
	public TreeNode constructFromPrePost(int[] pre, int[] post) {
		return build(pre, post, 0, pre.length, 0, post.length);
	}

	public TreeNode build(int[] pre, int[] post, int a, int b, int c, int d) {
		if (a == b) return null;
		TreeNode root = new TreeNode(pre[a]);
		for (int i = c; i < d-1; ++i) {
			if (post[i] == pre[a+1]) {
				// a+i-c+2-a-1 = i-c+1
				root.left = build(pre, post, a+1, a+i-c+2, c, i+1);
				// b-a-i+c-2 = d-c-i+c-2 = d-i-2 = d-1-i-1
				root.right = build(pre, post, a+i-c+2, b, i+1, d-1);
				break;
			}
		}
		return root;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		System.out.println("done");
	}
}

