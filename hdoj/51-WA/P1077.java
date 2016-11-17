/**
 * 1077 Catching Fish
 *
 * 我以为:若一个点集中任意两点的距离不超过l,那么这些点在同一个直径为l的圆内
 * 其实我是错的,比如一个等边三角形.
 */

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Scanner;

public class P1077 {
	private static int nfishes;
	private static double distances[][] = new double[300][300];
	private static double fishes[][] = new double[300][2];

	public static void main(String args[]) {
		try {
			System.setIn(new FileInputStream("Inputs/1077"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(System.in);
		int ncases = scanner.nextInt();
		while (--ncases >= 0) {
			initACase(scanner);
			int nfishesMax = 0;
			for (int i = 0; i < nfishes; ++i) {
				int nfishes = getNFishes(i);
				if (nfishes > nfishesMax)
					nfishesMax = nfishes;
			}
			System.out.println("" + nfishesMax);
		}
	}
	private static int getNFishes(int fid) {
		System.out.printf("getNFishes: fid: %2d\n", fid);
		List<Integer> fishes = new ArrayList<Integer>(300);
		fishes.add(fid);
		for (int i = 0; i < nfishes; ++i) {
			if (fishes.contains(i))
				continue;
			System.out.printf("candidate: %2d\n", i);
			boolean pass = true;
			for (Integer interviewer: fishes) {
				System.out.printf(
					"interviewer: %2d: %.4f\n", interviewer,
					distances[i][interviewer]);
				if (distances[i][interviewer] > 2) {
					pass = false;
					break;
				}
			}
			if (pass) {
				fishes.add(i);
				System.out.printf("pass: %2d\n", i);
			}
		}
		return fishes.size();
	}
	private static void initACase(Scanner scanner) {
		nfishes = scanner.nextInt();
		for (int i = 0; i < nfishes; ++i) {
			fishes[i][0] = scanner.nextDouble();
			fishes[i][1] = scanner.nextDouble();
		}
		for (int i = 0; i < nfishes; ++i) {
			distances[i][i] = 0;
			for (int j = i + 1; j < nfishes; ++j) {
				double dX = fishes[i][0] - fishes[j][0];
				double dY = fishes[i][1] - fishes[j][1];
				double d = Math.sqrt(dX * dX + dY * dY);
				distances[i][j] = distances[j][i] = d;
			}
		}
	}
}
