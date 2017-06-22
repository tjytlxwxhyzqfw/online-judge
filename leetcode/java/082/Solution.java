/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 * 
 * 假设链表"无头"
 *
 * 这个题的关键在于: 假设在while循环开始的时候, current指针指向的元素一定与current指针之前的元素不同
 * 这样, 每次循环开始的时候, 我们只需要简单判断current之后的元素是不是与current相同就可以了.
 *
 * 想象一个指针一直顺着链表走, 这就是主指针.
 * 然后我们需要一些额外的指针来做出决策, 这就是辅助指针.
 * (虽然这题不是双指针题目)
 *
 * 这个题的if-else语句是很有趣的
 * 你发现, else语句以else条件(也就是if条件的非)来循环
 * 有趣, 有趣.
 *
 * 增加一个新的意识:
 * 步进意识: 循环语句一定要验证循环变量是不是每次都步进了.
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public ListNode deleteDuplicates(ListNode first) {
		ListNode head = new ListNode(0);
		head.next = null;
		ListNode last = head;
		ListNode current = first;
		while (current != null) {
			if (current.next == null || current.next.val != current.val) {
				last.next = current;
				last = current;
			} else {
				while (current.next != null && current.next.val == current.val)
					current = current.next;
			}
			current = current.next;
		}

		last.next = null;
		return head;
	}

	public static void main(String args[]) {
	}
}

class ListNode {
	int val;
	ListNode next;
	ListNode(int x) {
		val = x;
	}
}
