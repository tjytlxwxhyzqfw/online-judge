/**
 * 692 Top K Frequent Words 47.2% Medium 940 92
 * Performance: speed=99%, memory=73%
 * todo: my solution is nlogn but they require nlogk solution
 *
 * this is a offline problem and i provided a online solution
 * that means my solution works when the input is a stream and topK() can be call at any time
 */

import java.util.*;

public class Solution {
	public List<String> topKFrequent(String[] words, int k) {
		Heap heap = new Heap(words.length);
		for (String word : words) heap.insert(word);
		List<String> list = new ArrayList<>();
		for (int i = 0; i < k; ++i) list.add(heap.delMin().key);
		return list;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
		// s.testHeap();
		System.out.println(s.topKFrequent(new String[]{"a"}, 1));
		System.out.println(s.topKFrequent(new String[]{"a", "a", "a"}, 1));
		System.out.println(s.topKFrequent(new String[]{"a", "b", "c"}, 1));
		System.out.println(s.topKFrequent(new String[]{"a", "b", "c"}, 2));
		System.out.println(s.topKFrequent(new String[]{"a", "b", "c"}, 3));
		System.out.println(s.topKFrequent(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2));
		System.out.println(s.topKFrequent(new String[]{
			"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4));
		System.out.println(s.topKFrequent(new String[]{
			"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 3));
		System.out.println("done");
	}

	void testHeap() {
		Heap heap = new Heap(16);
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String cmd = scanner.next();
			if (cmd.equals("i")) {
				String key = scanner.next();
				heap.insert(key);
			} else {
				System.out.printf("unknown cmd: %s, quit\n", cmd);
				break;
			}
		}
	}
}

class Heap {
	HeapNode[] h;
	int cap, size;
	Map<String, HeapNode> map;

	Heap(int size) {
		++size;
		cap = 1;
		while (cap < size) cap <<= 1;
		h = new HeapNode[cap];

		map = new HashMap<>();
		this.size = 1;
	}

	void insert(String key) {
		HeapNode node = map.get(key);
		if (node == null) {
			map.put(key, h[size] = node = new HeapNode(key, size));
			up(size++);
			System.out.printf("insert new key @%-2d: %s\n", node.id, node.key);
		} else {
			++node.count;
			int oldId = node.id;
			up(node.id);
			System.out.printf("update key @%-2d -> %-2d: %s(%d)\n", oldId, node.id, node.key, node.count);
		}
	}

	HeapNode delMin() {
		if (size == 0) return null;
		HeapNode min = h[1];
		swap(1, --size);
		down(1);
		return min;
	}

	void down(int i) {
		int c = (i << 1), x;
		if ((x = c) >= size) return;
		if (c+1 < size && h[c+1].compareTo(h[c]) < 0) x = c+1;
		if (h[x].compareTo(h[i]) < 0) { swap(i, x); down(x); }
	}

	void up(int i) {
		int p = i / 2;
		if (p != 0 && h[i].compareTo(h[p]) < 0) { swap(i, p); up(p); }
	}

	void swap(int i, int j) {
		HeapNode t = h[i];
		h[i] = h[j];
		h[j] = t;

		h[i].id = i;
		h[j].id = j;
	}
}

class HeapNode implements Comparable<HeapNode> {
	String key;
	int count;
	int id;

	HeapNode(String key, int id) {
		this.key = key;
		this.count = 1;
		this.id = id;
	}

	@Override
	public int compareTo(HeapNode that) {
		return this.count == that.count ? 
			this.key.compareTo(that.key) : that.count - this.count;
	}
}
