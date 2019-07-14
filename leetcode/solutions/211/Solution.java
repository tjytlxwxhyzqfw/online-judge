/**
 * 0211: Add and Search Word - Data Structure Design
 * Performance: speed=76%, memory=65%
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordDictionary {
	private Map<Integer, Set<String>> storage;

	/** Initialize your data structure here. */
	public WordDictionary() {
		storage = new HashMap<>();
	}
	
	/** Adds a word into the data structure. */
	public void addWord(String word) {
		if (storage.get(word.length()) == null) storage.put(word.length(), new HashSet<>());
		storage.get(word.length()).add(word);
	}
	
	/** Returns if the word is in the data structure.
	  * A word could contain the dot character '.' to represent any one letter.
	  */
	public boolean search(String word) {
		if (storage.get(word.length()) == null) return false;
		for (String x : storage.get(word.length())) if (check(x, word)) return true;
		return false;
	}

	private boolean check(String x, String p) {
		if (x.length() != p.length()) return false;
		for (int i = 0; i < x.length(); ++i) if (x.charAt(i) != p.charAt(i) && p.charAt(i) != '.') return false;
		return true;
	}

	public static void main(String args[]) {
	}
}
