import java.util.Arrays;
import java.util.Scanner;

public class P716B {
	private static Scanner scanner;
	private static String string;
	private static int f[];

	static {
		scanner = new Scanner(System.in);
		string = "";
		f = new int[26];
	}

	public static void main(String args[]) {
		int begin, end, ord;
		string = scanner.next();
		Arrays.fill(f, -1);	
		begin = 0;
		for (end = 0; end < string.length(); ++end) {
			if (end - begin == 26) break;
			ord = string.charAt(end) - 'A';
			if (ord == '?' - 'A') continue;
			if (f[ord] >= begin) begin = f[ord]+1;
			f[ord] = end;
		}

		if (end - begin != 26) {
			System.out.println("-1");
			return;
		}

	
		char nused[] = new char[26];
		for (int i = begin; i < end; ++i) {
			ord = string.charAt(i);
			if (ord == '?') continue;
			nused[ord-'A'] = 1;
		}

		for (int i = 0, j = 0; j < 26; ++j)
			if (nused[j] == 0) nused[i++] = (char)(j+'A');

		for (int i = 0; i < begin; ++i)
			System.out.printf("%c", string.charAt(i) == '?' ? 'A' : string.charAt(i));
		for (int i = begin, j = 0; i < end; ++i)
			System.out.printf("%c", string.charAt(i) == '?' ? nused[j++] : string.charAt(i));
		for (int i = end; i < string.length(); ++i)
			System.out.printf("%c", string.charAt(i) == '?' ? 'A' : string.charAt(i));

		System.out.println();
	}
}
