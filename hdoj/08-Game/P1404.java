/**
 * 1404 Digital Deletions
 *
 * 巴什博弈
 */

import java.io.FileInputStream;
import java.util.Scanner;

public class P1404 {
	//private static long ms = System.currentTimeMillis();
	private static Master master = new Master(6);
	private static String line = null;

	public static void main(String args[]) {
		/*
		try {
			System.setIn(new FileInputStream("Inputs/1404"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		Scanner scanner = new Scanner(System.in);
		//System.out.printf("Ready: %d\n", System.currentTimeMillis() - ms);
		while (initACase(scanner)) {
			int length = line.length();
			int index = Integer.parseInt(line);
			int state = master.query(length, index);
			System.out.println(state == 1 ? "Yes" : "No");
		}
	}
	private static boolean initACase(Scanner scanner) {
		if (!scanner.hasNext())
			return false;
		line = scanner.next();
		return true;
	}
}


class Master {
	private final int P = 2, N = 1;

	private int queues[][];
	private int candsBuffer[];
	private int size;

	private int nloops;

	public Master(int size) {
		this.size = size;
		queues = new int[size + 1][];
		for (int i = 1; i <= size; ++i) {
			int base = pow10(i);
			queues[i] = new int[base];
		}
		candsBuffer = new int[pow10(size)];
		//init();
	}
	private void init() {
		for (int i = 1; i <= size; ++i)
			traverse(i, -1);
		//System.out.printf("nloops: %d\n", nloops);
	}
	private void traverse(int qid, int targetValue) {
		int jmax = pow10(qid);
		int ncands = 0;
		for (int j = 0; j < jmax; ++j) {
			++nloops;
			candsBuffer[ncands++] = j;
		}
		int index, value, ret;
		while (ncands > 0) {
			index = 0;
			for (int i = 0; i < ncands; ++i) {
				//++nloops;
				value = candsBuffer[i];
				ret = makeAllMoves(qid, value);
				//System.out.printf("(%d, %6d): %d\n", qid, value, ret);
				if (ret == 0)
					candsBuffer[index++] = value;
				queues[qid][value] = ret;
				if (value == targetValue && ret != 0)
					return;
			}
			ncands = index;
		}
	}
	private int makeAllMoves(int length, int value) {
		int base = 1;
		int biti, state, lengthRemain;
		for (int i = 0; i < length; ++i) {
			biti = (value / base) % 10;
			if (biti == 0) {
				lengthRemain = length - i - 1;
				if (lengthRemain == 0)
					return 1;
				state = queues[lengthRemain][value / (base * 10)];
				if (state == P)
					return N;
				else if (state == 0)
					return 0;
			} else {
				for (int j = 1; j <= biti; ++j) {
					state = queues[length][value - base * j];
					if (state == P)
						return N;
					else if (state == 0)
						return 0;
				}
			}
			base *= 10;
		}
		return P;
	}
	private int pow10(int i) {
		int ret = 1;
		while (--i >= 0)
			ret *= 10;
		return ret;
	}
	public int query2(int length, int index) {
		return queues[length][index]; 
	}
	public int query(int length, int index) {
		for (int i = 1; i <= length; ++i)
			traverse(i, -1);
		return queues[length][index];
	}
}
