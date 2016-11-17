/**
 * 2544 最短路
 *
 * Dijkstra
 * [2016-07-03 10:23] no heap
 * [2016-07-03 11:01] AC, heap is hard to use while optimizing dijkstra.
 */

import java.io.FileInputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;

public class P2544 {
	private static int npoints;
	private static int polls;
	private static int minimal;
	private static Point[] points;
	private static int[][] adjacent;

	public static void main(String[] args) {
		Scanner scanner;

		points = new Point[101];
		adjacent = new int[101][101];

		try {
			System.setIn(new FileInputStream("Inputs/2544"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		scanner = new Scanner(System.in);

		while (initACase(scanner)) {
			Point point = points[1];
			point.color = 1;
			point.distance = 0;
			while ((point = select()) != null) {
				//System.out.printf("point: %s\n", point);
				point.color = 2;
				if (point.id == npoints) {
					minimal = point.distance;
					break;
				}

				for (int neiborid = 0; neiborid <= 100; ++neiborid) {
					if (point.neibors[neiborid] == 0)
						continue;

					Point neibor = points[neiborid];
					//System.out.printf("\tneiber: %s\n", neibor);
					if (neibor.color == 2)
						continue;

					int viaCurrent = point.distance + adjacent[point.id][neibor.id];
					if (viaCurrent < neibor.distance) {
						//System.out.printf("\t\t%2d: %2d -> %2d\n",
							//neibor.id, neibor.distance, viaCurrent);
						neibor.distance = viaCurrent;
						neibor.color = 1;
						neibor.lastid = point.id;
					}
				}
			}
			System.out.println("" + minimal);
		}
	}

	private static Point select() {
		int min = Integer.MAX_VALUE;
		int pid = -1;
		for (int i = 0; i <= npoints; ++i) {
			Point point = points[i];
			if (point == null)
				continue;
			if (point.color == 1 && point.distance < min) {
				pid = point.id;
				min = point.distance;
			}
		}

		return (pid == -1 ? null : points[pid]);
	}

	private static void printTraces() {
		for (int id = 1; id <= npoints; ++id) {
			Point p = points[id];
			System.out.printf("%2d, +%2d: ", id, p.distance);
			p.printTrace(points);
			System.out.println("");
		}
	}

	private static boolean initACase(Scanner scanner) {
		int nroads;

		polls = 0;
		minimal = Integer.MAX_VALUE;
		for (int i = 0; i <= 100; ++i)
			points[i] = null;
		for (int i = 0; i <= 100; ++i)
			for (int j = 0; j <= 100; ++j)
				adjacent[i][j] = Integer.MAX_VALUE;

		npoints = scanner.nextInt();
		nroads = scanner.nextInt();

		//System.out.printf("npoints: %2d, nroads: %2d\n", npoints, nroads);

		if (npoints == 0 && nroads == 0)
			return false;

		for (int i = 0; i < nroads; ++i) {
			int pid1, pid2;
			int distance, old;
			Point p;

			pid1 = scanner.nextInt();
			pid2 = scanner.nextInt();
			distance = scanner.nextInt();

			//System.out.printf("pid1: %2d, pid2: %2d, distance: %2d\n", 
				//pid1, pid2, distance);

			getPointCompulsively(pid1).neibors[pid2] = 1;
			getPointCompulsively(pid2).neibors[pid1] = 1;

			old = adjacent[pid1][pid2];
			if (distance < old)
				adjacent[pid1][pid2] = adjacent[pid2][pid1] = distance;
		}
		
		return true;
	}

	private static Point getPointCompulsively(int pid) {
		Point p;

		p = points[pid];
		if (p == null)
			p = points[pid] = new Point(pid);

		return p;
	}
}

class Point {
	public int id;
	public int distance;
	public int color;
	public int lastid;
	public int[] neibors;

	public Point(int pid) {
		id = pid;
		distance = Integer.MAX_VALUE;
		color = 0;
		lastid = -1;
		neibors = new int[101];
		for (int i = 0; i < 101; ++i)
			neibors[i] = 0;
	}

	public Point clone() {
		Point p; 

		p = new Point(id);

		p.distance = distance;
		p.neibors = neibors;
		p.lastid = lastid;

		return p;
	}

	public String toString() {
		return "id: " + id 
				+ ", distance: " + distance
				+ ", color: " + color
				+ ", lastid: " + lastid;
	}

	public void printTrace(Point[] points) {
		Point current, last;
		id = this.id;
		while (id != -1) {
			System.out.printf("%2d ", id);
			id = points[id].lastid;
		}
	}
}
