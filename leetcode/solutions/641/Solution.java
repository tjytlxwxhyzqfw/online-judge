/**
 * 641	Design Circular Deque 50.0% Medium 134/30
 * Performance: speed=10%, memory=50%
 */

import java.util.*;

class MyCircularDeque {
	int[] q;
	int front, rear;

	/** Initialize your data structure here. Set the size of the deque to be k. */
	public MyCircularDeque(int k) {
		q = new int[k+1];
		front = rear = 0;
	}
	
	/** Adds an item at the front of Deque. Return true if the operation is successful. */
	public boolean insertFront(int value) {
		if (isFull()) return false;
		front = (--front + q.length) % q.length;
		q[front] = value;
		return true;
	}
	
	/** Adds an item at the rear of Deque. Return true if the operation is successful. */
	public boolean insertLast(int value) {
		if (isFull()) return false;
		q[rear] = value;
		rear = ++rear % q.length;
		return true;
	}
	
	/** Deletes an item from the front of Deque. Return true if the operation is successful. */
	public boolean deleteFront() {
		if (isEmpty()) return false;
		front = ++front % q.length;
		return true;
	}
	
	/** Deletes an item from the rear of Deque. Return true if the operation is successful. */
	public boolean deleteLast() {
		if (isEmpty()) return false;
		rear = (--rear+q.length) % q.length;
		return true;
	}
	
	/** Get the front item from the deque. */
	public int getFront() { return isEmpty() ? -1 : q[front]; }
	
	/** Get the last item from the deque. */
	public int getRear() { return isEmpty() ? -1 : q[(rear-1+q.length)%q.length]; }
	
	/** Checks whether the circular deque is empty or not. */
	public boolean isEmpty() { return front == rear; }
	
	/** Checks whether the circular deque is full or not. */
	public boolean isFull() { return (rear + 1) % q.length == front; }
/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */

	public static void main(String args[]) {
		Solution s = new Solution();
		System.out.println("done");
	}
}

