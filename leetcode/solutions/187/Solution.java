/**
 * 187 Repeated DNA Sequences 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
	public List<String> findRepeatedDnaSequences(String s) {
		Map<String, Integer> map = new TreeMap<>();
		for (int i = 0; i+10 <= s.length(); ++i) {
			String key = s.substring(i, i+10);
			Integer count = map.get(key);
			map.put(key, count==null ? 1 : count+1);
		}
		
		List<String> result = new ArrayList<>();
		for (String key : map.keySet()) {
			Integer count = map.get(key);
			if (count > 1)
				result.add(key);
		}

		return result;
	}

	public static void main(String args[]) {
	}
}

