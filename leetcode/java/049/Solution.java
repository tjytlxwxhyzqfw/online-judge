import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Solution {

	private class Word {
		public String word;
		public Word(String s, int idx) {
			char arr[] = s.toCharArray();
			Arrays.sort(arr);
			word = new String(arr);
		}
	}

	public List<List<String>> groupAnagrams(String[] strs) {
		if (strs == null || strs.length == 0)
			return new ArrayList<List<String>>();

		Map<String, List<Integer>> map = new HashMap<String, List<Integer>>();
		for (int i = 0; i < strs.length; ++i) {
			char arr[] = strs[i].toCharArray();
			Arrays.sort(arr);
			String key = new String(arr);
			//System.out.printf("---> key: %s\n", key);
			if (!map.containsKey(key))
				map.put(key, new ArrayList<Integer>());
			map.get(key).add(i);
		}

		List<List<String>> groups = new ArrayList<List<String>>();
		for (List<Integer> list : map.values()) {
			//System.out.printf("%s\n", list.toString());
			List<String> group = new ArrayList<String>();
			for (Integer idx : list)
				group.add(strs[idx]);
			groups.add(group);
		}

		return groups;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int nstrs = scanner.nextInt();
		String strs[] = new String[nstrs];
		for (int i = 0; i < nstrs; ++i)
			strs[i] = scanner.next();
		List<List<String>> groups = new Solution().groupAnagrams(strs);
		for (List<String>  group : groups)
			System.out.printf("%s\n", group.toString());
	}
}
