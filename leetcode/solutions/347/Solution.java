/**
 * 347 Top K Frequent Elements
 * Performance: speed=78%, memory=47%
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Solution {
	public List<Integer> topKFrequent(int[] nums, int k) {
		Map<Integer, Integer> freq = new HashMap<>();
		for (int i = 0; i < nums.length; ++i) freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
		Map<Integer, List<Integer>> buckets = new TreeMap<>(Comparator.reverseOrder());
		for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
			Integer count = entry.getValue(), n = entry.getKey();
			if (buckets.get(count) == null) buckets.put(count, new ArrayList<>());
			buckets.get(count).add(n);
		}

		List<Integer> list = new ArrayList<>();
		for (Map.Entry<Integer, List<Integer>> entry : buckets.entrySet()) {
			list.addAll(entry.getValue());
			if ((k -= entry.getValue().size()) <= 0) break;
		}
		return list;
	}

	public static void main(String args[]) {
	}
}

