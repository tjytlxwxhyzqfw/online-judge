/**
 * 1074 Doing Homework
 *
 * Greedy ?
 */

import java.io.FileInputStream;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

class Homework {
	public int id;
	public String name;
	public int deadline;
	public int days;

	public void init(int id, String name, int deadline, int days) {
		this.id = id;
		this.name = name;
		this.deadline = deadline;
		this.days = days;
	}

	public String toString() {
		return String.format("%3d %3d %s\n", deadline, days, name);
	}
}

class HomeworkManager {
	private int nhomeworks;
	public Homework homeworks[];
	public PriorityQueue<Homework> heap;

	public HomeworkManager() {
		nhomeworks = 0;
		homeworks = new Homework[15];
		for (int i = 0; i < 15; ++i)
			homeworks[i] = new Homework();
		heap = new PriorityQueue<Homework>(15, 
			new Comparator<Homework>() {
				@Override
				public int compare(Homework x, Homework y) {
					if (x.deadline == y.deadline)
						return x.id - y.id;
					return x.deadline - y.deadline;
				}
			}
		);
	}
	public void init() {
		nhomeworks = 0;
		heap.clear();
	}
	public void addAHomework(int id, String name, int deadline, int days) {
		Homework h = homeworks[nhomeworks++];
		h.init(id, name, deadline, days);
		heap.add(h);
	}
}
		

public class P1074 {
	private static HomeworkManager homeworkManager;
	private static String outputs[];
	private static int nhids, total;
	private static int scheduling[];
	public static void main(String args[]) {
		homeworkManager = new HomeworkManager();
		outputs = new String[15];
		scheduling = new int[150];

		try {
			System.setIn(new FileInputStream("Inputs/1074"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Scanner scanner = new Scanner(System.in);
		int ncases = scanner.nextInt();
		while (--ncases >= 0) {
			initACase(scanner);
			PriorityQueue<Homework> heap = homeworkManager.heap;
			Homework h = null;
			while ((h = heap.poll()) != null) {
				//System.out.println(h);
				outputs[nhids++] = h.name;
				int reduces = place(h);
				//System.out.printf("%s: %2d\n", h.name, reduces);
				total += reduces;
			}
			System.out.println("" + total);
			for (int i = 0; i < nhids; ++i)
				System.out.println(outputs[i]);
		}
	}
	private static void initACase(Scanner scanner) {
		homeworkManager.init();
		int nhomeworks = scanner.nextInt();
		for (int i = 0; i < nhomeworks; ++i) {
			String name = scanner.next();
			int deadline = scanner.nextInt();
			int days = scanner.nextInt();
			homeworkManager.addAHomework(i, name, deadline, days);
		}

		nhids = total = 0;
		for (int i = 0; i < scheduling.length; ++i)
			scheduling[i] = 0;
	}
	private static int place(Homework homework) {
		int ndays = homework.days;
		for (int i = homework.deadline; i > 0; --i) {
			if (scheduling[i] == 0) {
				scheduling[i] = 1;
				if (--ndays == 0)
					break;
			}
		}
		if (ndays == 0)
			return 0;
		int reduces = 0;
		for (int i = homework.deadline + 1; i < scheduling.length; ++i) {
			++reduces;
			if (scheduling[i] == 0) {
				scheduling[i] = 1;
				if (--ndays == 0)
					break;
			}
		}
		return reduces; 
	}
}
