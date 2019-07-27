/**
 * 430: FlattenAMultilevelDoublyLinkedList
 * Performance: speed=15%, memory=95%
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {
	public Node flatten(Node head) {
		dof(head);
		Node p = head;
		while (p != null) {
			System.out.printf("%2d\n", p.val);
			p = p.next;
		}
		return head;
	}

	private Node dof(Node head) {
		Node p = head;
		while (p != null) {
			Node next = p.next;
			if (p.child != null) {
				Node q = dof(p.child);

				p.next = p.child;
				p.child.prev = p;
				p.child = null;

				q.next = next;
				if (next != null) next.prev = q;
				else return q;
			}
			if (next == null) return p;
			p = next;
		}
		return null;
	}
	public static void main(String args[]) {
	}
}

class Node { public int val; public Node prev; public Node next; public Node child; public Node() {}
public Node(int _val,Node _prev,Node _next,Node _child) { val = _val; prev = _prev; next = _next; child = _child; }};

