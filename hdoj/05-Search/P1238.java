import java.io.*;
import java.util.*;

public class P1238 {
	static public void main(String[] args) {
		try {
			System.setIn(new FileInputStream(new File("../Inputs/1238")));
		} catch (FileNotFoundException e) {
			System.err.print("file not found!");
			System.exit(0);
		}
		Problem problem = new Problem(System.in);
		problem.readNCase();
		while (!problem.finish()) {
			problem.readNextCase();
			int result = problem.solve();
			System.out.println(result);
		}
	}
}

class Problem {
	private BufferedReader stdin;
	private int nCase, currentCase = 0, size = 0;
	private List<String> stringList; 

	Problem(InputStream rawStream) {
		stdin = new BufferedReader(new InputStreamReader(rawStream));
	}

	public void readNCase() {
		try {
			nCase = Integer.parseInt(stdin.readLine());
		} catch (Exception e) {
			nCase = 0;
		}
	}

	public boolean finish() {
		return currentCase >= nCase;
	}

	public void readNextCase() {
		//System.out.println("reading...");
		currentCase += 1;
		try {
			size = Integer.parseInt(stdin.readLine());
		} catch (Exception e) {
			size = 0;
		}
		stringList = new ArrayList<String>(size);
		for (int i = 0; i < size; ++i)
			try {
				stringList.add(stdin.readLine());
			} catch (Exception e) {
				System.err.print("Exception!");
			}
		//for (String s : stringList)
			//System.out.println(s);
	}
	
	public int solve() {
		//System.out.println("solving...");
		if (size <= 0)
			return 0;
		if (size == 1)
			return size;
		List<String> subStrings = new ArrayList<String>(); 
		fillAllSubStrings(stringList.get(0), stringList.get(1), subStrings);
		fillAllSubStrings(stringList.get(0), reverse(stringList.get(1)), subStrings);
		//for (String s : subStrings)
			//System.out.printf("[%s]\n", s);
		if (size == 2)
			return getMaxLength(subStrings);
		for (int i = 2; i < size; ++i)
			filter(stringList.get(i), subStrings);
		return getMaxLength(subStrings);
	}

	/* 得到两个字符串的全部公共字串 */
	private void fillAllSubStrings(String s, String t, List<String> subStrings) {
		for (int i = 0; i < s.length(); ++i) {
			for (int j = 0; j < t.length(); ++j) {
				int x = i, y = j, len = 0;
				while (x < s.length() && y < t.length() && (s.charAt(x++) == t.charAt(y++))) {
					len += 1;
					String sub = String.copyValueOf(s.toCharArray(), i, len);
					if (!subStrings.contains(sub))
						subStrings.add(sub);
				}
			}
		}
	}

	/* 字符串列表中最长字符串的长度 */
	private int getMaxLength(List<String> strings) {
		int max = 0;
		for (String s : strings)
			if (max < s.length())
				max = s.length();
		return max;
	}

	private void filter(String string, List<String> strings) {
		List<String> filted = new ArrayList<String>();
		for (String s : strings) {
			if (string.indexOf(s) == -1 && string.indexOf(reverse(s)) == -1)
				continue;
			filted.add(s);
		}
		strings.clear();
		strings.addAll(filted);
	}

	private String reverse(String s) {
		char [] chars = s.toCharArray();
		int len = s.length();
		for (int i = 0; i < len / 2; ++i) {
			int j = len - i - 1;
			char ch = chars[j];
			chars[j] = chars[i];
			chars[i] = ch;
		}
		return new String(chars);
	}
}
		

