/*
 * 1789 Doing Homework Again
 *
 * Greedy
 */

import java.io.FileInputStream;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class P1789 {
	private static int reducedScores;
	private static PriorityQueue<Homework> heap;
	private static int sched[];
	private static Homework homeworks[];

	public static void main(String args[]) {
		homeworks = new Homework[1000];
		for (int i = 0; i < 1000; ++i)
			homeworks[i] = new Homework();
		sched = new int[1001];
		heap = new PriorityQueue<Homework>(1000, new Comparator<Homework>() {
			public int compare(Homework h1, Homework h2) {
				return h2.reduce - h1.reduce;
			}
		});

		try {
			System.setIn(new FileInputStream("Inputs/1789"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Scanner scanner = new Scanner(System.in);
		int ncases = scanner.nextInt();
		while (--ncases >= 0) {
			initACase(scanner);
			while (!heap.isEmpty()) {
				Homework h = heap.poll();
				//System.out.printf("homework: deadline: %2d, reduce: %2d\n",
					//h.deadline, h.reduce);
				reducedScores += place(h);
			}
			System.out.println("" + reducedScores);
		}
	}
	private static void initACase(Scanner scanner) {
		reducedScores = 0;
		heap.clear();
		for (int i = 0; i < 1000; ++i)
			sched[i] = 0;

		int nhomeworks = scanner.nextInt();
		for (int i = 0; i < nhomeworks; ++i)
			homeworks[i].deadline = scanner.nextInt();
		for (int i = 0; i < nhomeworks; ++i)
			homeworks[i].reduce = scanner.nextInt();
		for (int i = 0; i < nhomeworks; ++i)
			heap.add(homeworks[i]);
	}
	private static int place(Homework h) {
		int deadline = h.deadline;
		if (sched[deadline] == 0) {
			sched[deadline] = 1;
			//System.out.printf("position: %d\n", deadline);
			return 0;
		}
		for (int i = deadline - 1; i >= 1; --i) {
			if (sched[i] == 0) {
				sched[i] = 1;
				//System.out.printf("position: %d\n", i);
				return 0;
			}
		}
		for (int i = 1000; i > deadline; --i) {
			if (sched[i] == 0) {
				sched[i] = 1;
				//System.out.printf("position: %d\n", i);
				return h.reduce;
			}
		}

		/* if deadline > 1000 */
		return 0;
	}
}

class Homework {
	public int reduce;
	public int deadline;
}
