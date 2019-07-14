/**
 * 138 - Copy List with Random Pointer
 *
 * 题意: 给定一个链表, 每个元素都有一个额外的指针指向链表中的另一个元素. 复制此链表.
 *
 * 你知道怎么用java唯一标识一个对象吗???
 */

import java.util.Map;
import java.util.TreeMap;

public class Solution {

	/* 这个方法不对, 因为原题不允许修改原来的链表, 暂时的修改都不行 */
	public RandomListNode copyRandomListII(RandomListNode first) {
		RandomListNode head = new RandomListNode(0);
		head.next = first;
		RandomListNode clone = new RandomListNode(0);

		for (RandomListNode h1=head, h2=clone; h1.next!=null; h1=h1.next, h2=h2.next) {
			RandomListNode target = h1.next;
			RandomListNode copy = new RandomListNode(target.label);

			h2.next = copy;
			copy.random = target.random;
			target.random = copy;
		}

		for (RandomListNode h1=head, h2=clone; h1.next!=null; h1=h1.next, h2=h2.next) {
			RandomListNode target = h1.next;
			RandomListNode copy = h2.next;

			if (copy.random != null) {
				RandomListNode randomOfOriginNode = copy.random;
				target.random = randomOfOriginNode;
				copy.random = randomOfOriginNode.random;
			}
		}

		return clone.next;
	}

	public RandomListNode copyRandomList(RandomListNode first) {
		Map<Integer, RandomListNode> pool = new TreeMap<>();

		for (RandomListNode current = first; current != null; current = current.next) {
			//System.out.printf("current.label=%d\n", current.label);
			RandomListNode copy = new RandomListNode(current.label);
			pool.put(current.hashCode(), copy);
			//System.out.printf("put: %d->%d\n", current.hashCode(), copy.hashCode());
		}

		RandomListNode handler = new RandomListNode(0);
		RandomListNode head = new RandomListNode(0);
		head.next = first;
		for (RandomListNode h1=head, h2=handler; h1.next != null; h1=h1.next, h2=h2.next) {
			RandomListNode target = h1.next;
			RandomListNode copy = pool.get(target.hashCode());

			//System.out.printf("get: %d->%d\n", target.hashCode(), copy.hashCode());
			
			if (target.random != null)
				copy.random = pool.get(target.random.hashCode());
			h2.next = copy;
		}

		return handler.next;
	}

	public static void main(String args[]) {
		RandomListNode first = new RandomListNode(-1);
		RandomListNode second = new RandomListNode(-2);

		first.next = second;
		first.random = second;
		second.random = first;

		RandomListNode clone = new Solution().copyRandomList(first);
		System.out.printf("first.random.label=%d\n", first.random.label);
		System.out.printf("second.random.label=%d\n", second.random.label);
	}
}

class RandomListNode {
	int label;
	RandomListNode next, random;
	RandomListNode(int x) {
		this.label = x;
	}
}
