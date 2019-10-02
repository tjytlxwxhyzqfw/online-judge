/**
 * 622 Design Circular Queue 41.0% Medium 311 58
 * Performance: speed=14%, memory=25%
 */

import java.util.*;

class MyCircularQueue {
	int[] q;
	int front, rear;

	/** Initialize your data structure here. Set the size of the queue to be k. */
	public MyCircularQueue(int k) {
		q = new int[k+1];
		front = rear = 0;
	}
	
	/** Insert an element into the circular queue. Return true if the operation is successful. */
	public boolean enQueue(int value) {
		if (isFull()) return false;
		q[rear] = value;
		rear = ++rear % q.length;
		return true;
	}
	
	/** Delete an element from the circular queue. Return true if the operation is successful. */
	public boolean deQueue() {
		if (isEmpty()) return false;
		front = ++front % q.length;
		return true;
	}
	
	/** Get the front item from the queue. */
	public int Front() {
		return isEmpty() ? -1 : q[front];
	}
	
	/** Get the last item from the queue. */
	public int Rear() {
		return isEmpty() ? -1 : q[(rear-1+q.length) % q.length];
	}
	
	/** Checks whether the circular queue is empty or not. */
	public boolean isEmpty() {
		return front == rear;
	}
	
	/** Checks whether the circular queue is full or not. */
	public boolean isFull() {
		return (rear + 1) % q.length == front;
	}

	public static void main(String[] args) {
	}
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
