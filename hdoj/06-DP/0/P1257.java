/**
 * 1257 最少拦截系统
 *
 * Binary Search
 */

import java.io.FileInputStream;
import java.util.Scanner;

public class P1257 {
	public static void main(String args[]) {
		Systems systems = new Systems();
		try {
			System.setIn(new FileInputStream("Inputs/1257"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			systems.init();
			int nmissiles = scanner.nextInt();
			if (nmissiles == 0)
				break;
			while (--nmissiles >= 0) {
				int height = scanner.nextInt();
				systems.update(height);
			}
			System.out.println("" + systems.getNsystems());
		}
	}
}

class Systems {
	private int heights[];
	private int nsystems;

	public Systems() {
		nsystems = 0;
		heights = new int[100000];
	}
	public void init() {
		nsystems = 0;
	}
	public void update(int height) {
		int position = bsearch(height);
		if (position == nsystems)
			heights[nsystems++] = height;
		else
			heights[position] = height;
	}
	private int bsearch(int height) {
		int begin = 0;
		int end = nsystems;
		while (begin < end) {
			int x = (begin + end) / 2;
			//System.out.printf("bsearch: x: %2d\n", x);
			if (heights[x] > height)
				end = x;
			else if (heights[x] < height)
				begin = x + 1;
			else
				return x;
		}
		return end;
	}
	public void print() {
		for (int i = 0; i < nsystems; ++i)
			System.out.printf("%2d ", heights[i]);
		System.out.println("");
	}
	public int getNsystems() {
		return nsystems;
	}
}
