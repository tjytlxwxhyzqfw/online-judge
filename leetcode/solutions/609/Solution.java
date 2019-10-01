/**
 * 609	Find Duplicate File in System 56.6% Medium 279/430 191001
 * Performance: speed=93%, memory=93%
 */

import java.util.*;

public class Solution {
	public List<List<String>> findDuplicate(String[] paths) {
		Map<String, List<String>> map = new HashMap<>();
		for (String path : paths) {
			String[] items = path.split(" ");
			for (int i = 1; i < items.length; ++i) {
				int left = items[i].indexOf('(');
				String name = items[i].substring(0, left);
				String content = items[i].substring(left+1, items[i].length() - 1);
				map.putIfAbsent(content, new ArrayList<>());
				map.get(content).add(items[0] + "/" + name);
			}
		}
		List<List<String>> result = new ArrayList<>();
		for (List<String> list : map.values()) if (list.size() > 1) result.add(list);
		return result;
	}

	public static void main(String args[]) {
		Solution s = new Solution();
                System.out.println("done");
	}
}
