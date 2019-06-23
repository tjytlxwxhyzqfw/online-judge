import java.util.ArrayList;
import java.util.List;

/**
 * 129 - Sum Root to Leaf Numbers
 *
 * 坑: 一定要从root==null作为递归地最基本条件,
 * 我开始的时候用了 if (root.left==null&&root.right==null) else if (root.left==null) else if (root.right==null)
 * 这是完全错误的, 这个递归只要碰到一个有null儿子的节点, 就会返回, 导致失败.
 *
 * new StringBuilder(3) generates a StringBuilder() with 3 cells, not a "3"!!!
 */
public class Solution {
    public int  sumNumbers(TreeNode root) {
        if (root == null)
            return 0;

        int ans = 0;
        List<StringBuilder> builders = getNumberBuilders(root);
        for (StringBuilder sb : builders) {
            ans += Integer.parseInt(sb.reverse().toString());
        }
        return ans;
    }

    private List<StringBuilder> getNumberBuilders(TreeNode root) {
        List<StringBuilder> result = new ArrayList<>();

        if (root==null)
            return result;

        List<StringBuilder> left = getNumberBuilders(root.left);
        List<StringBuilder> rght = getNumberBuilders(root.right);

        if (left.isEmpty() && rght.isEmpty()) {
            result.add(new StringBuilder().append(root.val));
        } else {
            for (StringBuilder sb : left)
                sb.append(root.val);
            for (StringBuilder sb : rght)
                sb.append(root.val);
        }

        result.addAll(left);
        result.addAll(rght);

        return result;
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        int ans = new Solution().sumNumbers(root);
        System.out.printf("ans=%d\n", ans);
    }
}

class TreeNode {
    int val;
    TreeNode left, right;

    public TreeNode(int x) {
        val = x;
    }
}
