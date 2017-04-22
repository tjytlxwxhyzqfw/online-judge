import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Solution {
	public List<String> fullJustify(String words[], int maxWidth) {
		int nwords = words.length;

		List<List<String>> lines = new ArrayList<List<String>>();
		int bgn = 0;
		for (int i = 0; i < nwords; ++i) {
			List<String> ws = getWords(words, bgn, maxWidth);
			if (ws == null || ws.size() == 0)
				break;
			bgn += ws.size();
			lines.add(ws);
		}

		List<String> res = new ArrayList<String>();
		for (int i = 0; i < lines.size(); ++i) {
			List<String> line = lines.get(i);
			int nws = line.size();

			int length = 0;
			for (String w : line)
				length += w.length();

			StringBuilder builder = new StringBuilder();
			if (nws == 1) {
				builder.append(line.get(0));
				builder.append(getNSpaces(maxWidth-length));
			} else {
				if (i+1 == lines.size()) {
					int width = 0;
					for (int j = 0; j < nws; ++j) {
						builder.append(line.get(j));
						width += line.get(j).length();
						if (width < maxWidth) {
							builder.append(' ');
							++width;
						}
					}
					builder.append(getNSpaces(maxWidth-width));
				} else {
					int nsps = maxWidth - length;
					for (int j = 0; j < nws; ++j) {
						builder.append(line.get(j));
						int npaddings = nsps / (nws-1);
						if (nsps % (nws-1) != 0) {
							++npaddings;
							--nsps;
						}
						if (j+1 < nws)
							builder.append(getNSpaces(npaddings));
					}
				}
			}

			res.add(builder.toString());
		}

		return res;
	}

	private List<String> getWords(String words[], int bgn, int maxWidth) {
		int nwords = words.length;
		if (bgn >= nwords)
			return null;

		List<String> line = new ArrayList<String>();
		int length = 0, nused = 0;
		for (int i = bgn; i < nwords; ++i) {
			String word = words[i];
			length += word.length();
			nused += 1;
			if (length+nused-1 > maxWidth) {
				--nused;
				break;
			}
			line.add(word);
		}

		return line;
	}

	private String getNSpaces(int n) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < n; ++i)
			builder.append(' ');
		return builder.toString();
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
	}
}
