/**
 * 143 Reorder List
 *
 * 题意: 给定[1, 2, 3, 4, 5, 6, 7], 返回[1, 7, 2, 6, 3, 5, 4]
 *
 * 好像, 除了暴力解, 我也没什么办法
 * 
 * 有更好的解法: 先用快慢指针讲链表分成两段, 后半段就地逆织, 然后顺序构造, 只要O(n)
 */

public class Solution {
	public void reorderList(ListNode first) {
		ListNode head = new ListNode(0);
		head.next = first;

		Integer length = 0;
		for (ListNode cur=head; cur.next!=null; cur=cur.next)
			++length;

		if (length <= 2)
			return;

		ListNode fast, slow;
		fast = slow = (length%2==1 ? head : first);
		System.out.printf("fast:%d, slow:%d\n", fast.val, slow.val);

		while (fast!=null) {
			fast = fast.next.next;
			slow = slow.next;
		}

		ListNode handler = new ListNode(0);
		handler.next = slow.next;
		slow.next = null; // 断开!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		print(slow);
		reverse(handler);
		print(handler);

		ListNode current = first;
		while (handler.next != null) {
			ListNode target = removeFirst(handler);
			ListNode neighbor = current.next;
			System.out.printf("current:%2d, target:%2d, neighbor:%2d\n",
				current.val, target.val, neighbor.val);
			current.next = target;
			target.next = neighbor;
			current = neighbor;
		}
	}

	private void reverse(ListNode handler) {
		if (handler.next==null)
			return;

		ListNode x=handler, y=handler.next, z=handler.next.next;
		while (true) {
			y.next = x;
			x = y;
			y = z;
			if (z == null)
				break;
			z = z.next;
		}
		handler.next.next = null;
		handler.next = x;
	}

	private ListNode removeFirst(ListNode handler) {
		if (handler.next == null)
			return null;
		ListNode first = handler.next;
		handler.next = first.next;
		first.next = null;
		return first;
	}

	private void print(ListNode head) {
		for (ListNode c=head; c.next!=null; c=c.next)
			System.out.printf("%d, ", c.next.val);
		System.out.printf("\n");
	}

	public static void main(String args[]) {
	}
}

class ListNode {
	int val;
	ListNode next;
	ListNode (int x) {
		val = x;
	}
}
