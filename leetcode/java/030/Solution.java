import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

public class Solution {
	private Map<String, Integer> template, map;
	private int single, nwords;

	public List<Integer> findSubstring(String string, String[] words) {
		ArrayList<Integer> answer;
		Set<Integer> result;
		ArrayList<String> splited;
		int i;

		answer = new ArrayList<Integer>();
		if (words.length == 0) return answer;
		if (words[0].length() == 0) {
			if (string.length() == 0) answer.add(0);
			return answer;
		}

		template = new HashMap<String, Integer>();
		map = new HashMap<String, Integer>();
		for (String w : words) {
			template.put(w, template.getOrDefault(w, 0)+1);
			map.put(w, 0);
		}
		//for (Map.Entry<String, Integer> entry : template.entrySet())
			//System.out.printf("%s: %d\n", entry.getKey(), entry.getValue());

		nwords = words.length;
		single = words[0].length();
		result = new HashSet<Integer>();
		for (i = 0; i < single; ++i) {
			splited = split(string.substring(i), single);
			//System.out.printf("splited: %s\n", splited);
			check(splited, words, i, result);
		}

		for (Integer integer : result)
			answer.add(integer);

		return answer;
	}

	private void check(ArrayList<String> splited, String[] words, int offset, Set<Integer> result) {
		int b, e, bmax;
		String word;

		bmax = splited.size() - nwords;
		for (b = 0; b <= bmax; ++b) {
			word = splited.get(b);
			//System.out.printf("\tb: %2d: %s\n", b, word);
			if (!map.containsKey(word)) continue;
			map.put(word, 1);
			for (e = b + 1; e < b + nwords; ++e) {
				word = splited.get(e);
				//System.out.printf("\t\te: %2d: %s\n", e, word);
				if (!map.containsKey(word) || map.get(word) >= template.get(word))
					break;
				map.put(word, map.get(word)+1);
			}
			if (e - b == nwords) {
				//System.out.printf("\t\taccepted: %2d\n", offset+b*single);
				result.add(offset+b*single);
			}
			reset(map);
		}
	}

	private void reset(Map<String, Integer> map) {
		for (Map.Entry<String, Integer> e : map.entrySet())
			e.setValue(0);
	}

	private ArrayList<String> split(String s, int delta) {
		ArrayList<String> strs = new ArrayList<String>();
		for (int i = 0; i+delta <= s.length(); i += delta)
			strs.add(s.substring(i, i+delta));
		return strs;
	}

	public static void main(String args[]) {
		List<Integer> answer;
		Solution solution = new Solution();
		String s = "xabcxxacbxxxbacxxxxbcaxxxxxcabxxxxxxcbaxxxxxxx";
		//String s = "barfoothefoobarman";
		String words[] = {"x", "x", "x"};
		System.out.printf("s.length(): %d\n", s.length());

		answer = solution.findSubstring(s, words);
		answer.sort(Comparator.naturalOrder());
		for (Integer integer : answer)
			System.out.printf("%d ", integer);
		System.out.println();

	}
}
