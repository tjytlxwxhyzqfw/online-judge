/**
 * java
 */

import java.util.ArrayList;
import java.util.List;

public class Solution {

	private String[] keyboard = {" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
	private List<StringBuilder> builders = new ArrayList<StringBuilder>();
	private String string;

	public List<String> letterCombinations(String digits) {
		StringBuilder filtered = new StringBuilder("");
		List<String> answer = new ArrayList<String>();
		int i;

		for (i = 0; i < digits.length(); ++i)
			if (digits.charAt(i) != '1') filtered.append(digits.charAt(i));

		string = filtered.toString();
		if (string.equals(""))
			return answer;

		dfs(0);
		
		for(StringBuilder builder : builders)
			answer.add(builder.toString());

		return answer;
	}

	private void dfs(int deepth) {
		String letters;
		StringBuilder bld;
		char letter;
		int i, idx;

		if (deepth+1 == string.length()) {
			idx = string.charAt(deepth) - '0';
			letters = keyboard[idx];
			//System.out.printf("letters: %s\n", letters);
			for (i = 0; i < letters.length(); ++i) {
				letter = letters.charAt(i);
				bld = new StringBuilder(letter+"");
				//System.out.printf("bld: %s\n", bld.toString());
				builders.add(bld);
			}
			//printbuilders("deepth: "+deepth);
			return;
		}
		dfs(deepth + 1);
		triple(deepth);
		//printbuilders("deepth: "+deepth);
	}

	private void triple(int idx) {
		String letters, str;
		StringBuilder bld;
		int i, j, size;
		char letter;

		idx = string.charAt(idx) - '0';


		size = builders.size();
		letters = keyboard[idx];
		//System.out.printf("idx: %2d, letters: %s\n", idx, letters);
		for (i = 1; i < letters.length(); ++i) {
			letter = letters.charAt(i);
			for (j = 0; j < size; ++j) {
				str = letter + builders.get(j).toString();
				bld = new StringBuilder(str);
				builders.add(bld);
			}
		}

		letter = letters.charAt(0);
		for (j = 0; j < size; ++j)
			builders.get(j).insert(0, letter+"");
	}

	private void printbuilders(String tag) {
		System.out.printf("\n%s\n", tag);
		for (StringBuilder builder : builders)
			System.out.printf("%s\n", builder.toString());
		System.out.printf("\n");
	}

	public static void main(String args[]) {
		String digits = "2016";
		Solution solution = new Solution();
		List<String> result;

		result = solution.letterCombinations(digits);
		for (String s : result)
			System.out.printf("%s\n", s);
	}
}
