import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Solution {

	public String simplifyPath(String path) {
		if (path == null || path.length() == 0)
			return path;

		List<String> items = getSplittedPath(path);
		Stack<String> stack = new Stack<String>();

		for (int i = 0; i < items.size(); ++i) {
			String item = items.get(i);
			if (item.equals(".")) {
				continue;
			} else if (item.equals("..")) {
				if (!stack.empty())
					stack.pop();
			} else {
				stack.push(item);
			}
		}

		String spath = getPathFromStack(stack);
		return spath;
	}

	private List<String> getSplittedPath(String path) {
		List<String> items = new ArrayList<String>();
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < path.length(); ++i) {
			char ch = path.charAt(i);
			if (ch == '/') {
				if (builder.length() != 0) {
					items.add(builder.toString());
					builder.setLength(0);
				} // else just ignore this '/'
			} else {
				builder.append(ch);
			}
		}

		//TODO REMEMBER: do not forget this !!!
		if (builder.length() != 0)
			items.add(builder.toString());

		return items;
	}

	private String getPathFromStack(Stack<String> stack) {
		if (stack == null || stack.empty())
			return "/";
		String last = stack.pop();
		if (stack.empty())
			return "/"+last;
		String path = getPathFromStack(stack);
		return path + "/" + last;
	}

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		String path = scanner.next();
		String spath = new Solution().simplifyPath(path);
		System.out.printf("---> %s\n", spath);
	}
}
