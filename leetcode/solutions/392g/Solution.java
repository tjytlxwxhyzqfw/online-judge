/**
 * 392: Is Subsequence
 * Performance: speed=%, memory=%
 */

// review logs
// -----------
// 20200727:
//
// 今天的想法: 
// 原题: i指向s的某个数, j指向t中的某个数, 消费i. 然后i右移一位, j也右移, 消费新的i.
// FollowUp: 把所有的s放入到一棵前缀树中. 遍历这颗前缀树. 假设i指向树中的某个节点, j指向t中的一个位置.
//   如果i.end==true, 记录i.isSubStr==true. 如果i是一个叶子节点, i回退到父亲,
//   同时j回退到i.父亲的.在t中的匹配位置(因此对于每个节点我们都要记录其在t中的匹配位置). 
// 分析: 上述解法, 对于每个s, 都要回退到某个t的某个位置, 可能可能存在对于每个s都要回退到t的开头重新找,
//   然后找到最后一个才发现匹配不到的极端情况, 这样的话, 就是|t|*|k|*|s|的复杂度了
//
// diss中的想法1: 对t进行预处理, 建立一个map: 字母 -> 此字母在t中出现的位置列表.
//   这样的话, 对于s中的每个字母c, 我们找到c对应的列表, 然后二分查找即可.
//   (c必须有一个序号, 这个序号大于c前继字母的序号)
// 分析: 很稳的解法, log|t| * |k|*|s|
//
// diss中的想法2: 处理t, 每个字母都与它的所有后继字母相连, 这样t就变成了一个有向无环图.
//   (如果t是aaaaaaaaaaaaaa)这样的模式, 这个图要怎么生成?). 
//   然后对于每个s, 在DAG上过一遍, 就能找到s是否是个子串. 复杂度是X+|k|*|s|,
//   X是前处理的复杂度, 有可能会比较大(t比较大的时候).


import java.util.ArrayList;
import java.util.List;

public class Solution {
	public boolean isSubsequence(String s, String t) {
		if (s.equals(t)) return true;

		int j = 0;
		for (int i = 0; i < s.length(); ++i) {
			j = firstFrom(s.charAt(i), j, t) + 1;
		}

		// if you plus one to j, you must be careful with the boundaries
		return j <= t.length();
	}

    // where dose 'x' first show up in s[from:] ?
	private int firstFrom(char x, int from, String s) {
		for (int i = from; i < s.length(); ++i)
			if (s.charAt(i) == x) return i;
		return s.length();
	}

	public static void main(String args[]) {
	}
}

