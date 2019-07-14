/**
 * 127 Word Ladder
 *
 * 这个居然也能过....
 * 时间复杂度是 wordList.size() 的3次方了!
 */

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Solution {
	public int ladderLength(String begin, String end, List<String> wordList) {
		if (wordList==null && wordList.isEmpty())
			return 0;

		Set<String> set = new TreeSet<>(wordList);

		Set<String> targets = new TreeSet<>();
		targets.add(begin);
		Integer distance = 1;
		Integer result = 0;
		outer:
		while (!targets.isEmpty()) {
			System.out.printf("targets: %s\n", targets.toString());
			System.out.printf("set:: %s\n", set.toString());
			Set<String> cands = new TreeSet<>();
			for (String target : targets) {
				for (String word : set) {
					if (diffone(target, word)) {
						if (end.equals(word)) {
							result = distance + 1;
							break outer;
						}
						cands.add(word);
					}
				}
			}

			targets.clear();
			targets = cands;
			++distance;

			for (String target : targets)
				set.remove(target);
		}

		return result;
	}

	private boolean diffone(String x, String y) {
		Integer ndiffs = 0;
		Integer length = x.length();
		for (int i = 0; i < length; ++i) {
			if (x.charAt(i) != y.charAt(i)) {
				++ndiffs;
				if (ndiffs > 1)
					break;
			}
		}

		return ndiffs == 1;
	}


	public static void main(String arg[]) {
		String begin = "hit";
		String end = "cog";
		//List<String> set = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
		List<String> set = Arrays.asList("hot");
		int ans = new Solution().ladderLength(begin, end, set);
		System.out.printf("ans=%d\n", ans);
	}
}
