/**
 * 1077 Catching Fish
 *
 * 我以为:若一个点集中任意两点的距离不超过l,那么这些点在同一个直径为l的圆内
 * 其实我是错的,比如一个等边三角形.
 *
 * 正解:两点做弦,求其圆心
 *
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
			int nfishesMax = 1;
			for (int i = 0; i < nfishes; ++i) {
				for (int j = i + 1; j < nfishes; ++j) {
					double[][] centers = findCenter(i, j);
					if (centers == null)
						continue;
					//printCenterInfo(i, j, centers);
					int nfishes1 = catchFishes(centers[0][0], centers[0][1]);
					int nfishes2 = catchFishes(centers[1][0], centers[1][1]);
					//System.out.printf("nf1: %3d, nf2: %3d\n", nfishes1, nfishes2);
					int nfishes = (nfishes1 > nfishes2 ? nfishes1 : nfishes2);
					if (nfishes > nfishesMax)
						nfishesMax = nfishes;
					
				}
			}
			System.out.println("" + nfishesMax);
		}
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
	private static int catchFishes(double xc, double yc) {
		double dx, dy;
		double x, y;
		int n = 0;
		for (int i = 0; i < nfishes; ++i) {
			x = fishes[i][0];
			y = fishes[i][1];
			dx = x - xc;
			dy = y - yc;
			//System.out.printf("%9.7f\n", dx * dx + dy * dy);
			if (dx * dx + dy * dy < 1.0002)
				++n;
		}
		return n;
	}
			
	private static double[][] findCenter(int p1, int p2) {
		double x1 = fishes[p1][0];
		double y1 = fishes[p1][1];
		double x2 = fishes[p2][0];
		double y2 = fishes[p2][1];

		double bottom = distances[p1][p2];
		if (bottom > 2)
			return null;

		double xm = (x1 + x2) / 2;
		double ym = (y1 + y2) / 2;

		double result[][] = new double[2][2];

		if (y1 == y2) {
			result[0][0] = result[1][0] = xm;
			double height = Math.sqrt(1 - bottom * bottom / 4);
			result[0][1] = y1 + height;
			result[1][1] = y1 - height;
			return result;
		}

		double k = -1 * (x2 - x1) / (y2 - y1);
		double b = ym - k * xm;
		double t = b - y1;
		double A = 1 + k * k;
		double B = 2 * (t * k - x1);
		double C = t * t + x1 * x1 - 1;
		double sdelta = Math.sqrt(B * B - 4 * A * C);
		result[0][0] = (-1 * B + sdelta) / (2 * A);
		result[1][0] = (-1 * B - sdelta) / (2 * A);
		result[0][1] = k * result[0][0] + b;
		result[1][1] = k * result[1][0] + b;

		return result;
	}
	private static void printCenterInfo(int p1, int p2, double centers[][]) {
		double x1 = fishes[p1][0], y1 = fishes[p1][1];
		double x2 = fishes[p2][0], y2 = fishes[p2][1];
		double xc1 = centers[0][0], yc1 = centers[0][1];
		double xc2 = centers[1][0], yc2 = centers[1][1];

		System.out.printf("point1: (%7.5f, %7.5f)\n", x1, y1);
		System.out.printf("point2: (%7.5f, %7.5f)\n", x2, y2);
		System.out.printf("center1: (%7.5f, %7.5f)\n", xc1, yc1);
		System.out.printf("center2: (%7.5f, %7.5f)\n", xc2, yc2);
		double d1 = calD(x1, y1, xc1, yc1);
		double d2 = calD(x2, y2, xc1, yc1);
		double d3 = calD(x1, y1, xc2, yc2);
		double d4 = calD(x2, y2, xc2, yc2);

		System.out.printf("%9.7f, %9.7f, %9.7f, %9.7f\n", d1, d2, d3, d4);

		if (d1 - 1 > 0.00001 || d2 - 1 > 0.00001 || d3 -1 > 0.00001 || d4 - 1 > 0.00001)
			while (true){}

		//try {
			//Thread.sleep(300);
		//} catch (Exception e) {
			//e.printStackTrace();
		//}
	}
	private static double calD(double x1, double y1, double x2, double y2) {
		double dx = x2 - x1;
		double dy = y2 - y1;
		return Math.sqrt(dx * dx + dy * dy);
	}
}
