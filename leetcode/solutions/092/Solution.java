/*
 * leetcode 092 - Reverse Linked List II
 * 
 * 做了这么多链表的题目, 你应该知道这类题目有些秘诀:
 * - pre, cur, nxt模式几乎是万能的
 * - head指针一定要第一时间加上去!
 * - 每次循环之前一定要仔细检查循环不变式
 */

public class Solution {
	public ListNode reverseBetween(ListNode first, int m, int n) {
		if (first == null)
			return null;

		ListNode head = new ListNode(0);
		head.next = first;

		int idx = 0;
		ListNode current = head, pre = null, nxt = first;
		ListNode before = null, start = null;
		while (idx <= n) {
			if (idx+1 == m)
				before = current;
			else if (idx == m)
				start = current;
			else if (idx > m)
				/* 没错! 倒转一个指针是这么简单 */
				current.next = pre;

			/* 千万别忘了自增++idx */
			++idx;
			pre = current;
			current = nxt;
			if (current == null) {
				/* 退出时别忘了重置nxt!!  即使while()之外没用到nxt */
				nxt = null;
				break;
			}
			nxt = current.next;
		}

		/* 这个检查是必须的, 否则, 如果m/大于链表长度, 这里就会出错 */
		if (idx > m) {
			if (before != null)
				before.next = pre;
			if (start != null)
				start.next = current;
		}

		return head.next;
	}

	public static void main(String args[]) {
		ListNode first = new ListNode(5);
		first.next = null;
		ListNode result = new Solution().reverseBetween(first, 2, 2);
		ListNode current = result;
		while (current != null) {
			System.out.printf("%d, ", current.val);
			current = current.next;
		}
		System.out.printf("\n");
	}

	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}

