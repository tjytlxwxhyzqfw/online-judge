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
public class P1074 {
	private static int s[][], t[][];
	private static int nhomeworks;
	private static Homework homeworks[];

	public static void main(String args[]) {
		s = new int[16][32768];
		t = new int[16][32768];
		homeworks = new Homework[15];
		for (int i = 0; i < 15; ++i)
			homeworks[i] = new Homework();

		try {
			System.setIn(new FileInputStream("Inputs/1074"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Scanner scanner = new Scanner(System.in);
		int ncases = scanner.nextInt();
		while (--ncases >= 0) {
			initACase(scanner);
			int nstates = (1 << nhomeworks);
			for (int i = 1; i <= nhomeworks; ++i) {
				for (int j = 0; j < nstates; ++j) {
					fillS(i, j);
					//System.out.printf("(%2d, %5d): %3d\n", i, j, s[i][j]);
				}
			}
			System.out.println("" + s[nhomeworks][0]);
			printPath();
		}
	}
	// confidence: 8
	private static void initACase(Scanner scanner) {
		nhomeworks = scanner.nextInt();
		for (int i = 0; i < nhomeworks; ++i) {
			String name = scanner.next();
			int dead = scanner.nextInt();
			int ndays = scanner.nextInt();
			homeworks[i].init(i, name, dead, ndays);
		}
	}
	// confidence: 8
	private static void fillS(int nhs, int stat) {
		//System.out.printf("fillS: %2d, %5d\n", nhs, stat);
		int min, minid, st, extra, reduces;
		min = Integer.MAX_VALUE;
		minid = -1;
		for (int i = 0; i < nhomeworks; ++i) {
			if (isSet(i, stat))
				continue;
			st = ifSet(i, stat);
			extra = (nhs > 1 ? s[nhs - 1][st] : 0);
			//TODO accelerate
			reduces = getReduce(i, stat);
			//System.out.printf("choose: %2d, extra: %2d, reduces: %2d\n", i, extra, reduces);
			if (extra < Integer.MAX_VALUE)
				reduces += extra;
			if (reduces < min) {
				min = reduces;
				minid = homeworks[i].id;
			}
		}
		s[nhs][stat] = min;
		t[nhs][stat] = minid;
	}
	// confidence: 9
	private static int getReduce(int hid, int stat) {
		int day = 0;
		for (int i = 0; i < nhomeworks; ++i) {
			if (isSet(i, stat))
				day += homeworks[i].days;
		}
		day += homeworks[hid].days;
		int delta = day - homeworks[hid].deadline;
		if (delta < 0)
			return 0;
		return delta;
	}
	// confidence: 9 
	private static boolean isSet(int bit, int flag) {
		return (flag & (1 << bit)) != 0;
	}
	// confidence: 9 
	private static int ifSet(int bit, int flag) {
		return (flag | (1 << bit));
	}
	private static void printPath() {
		for (int s = 0, i = nhomeworks; i > 0; --i) {
			int hid = t[i][s];
			System.out.println(homeworks[hid].name);
			s = ifSet(hid, s);
		}
	}
}
