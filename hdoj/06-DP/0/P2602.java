/*
 * 2602 Bone Collector
 *
 * Primary DP
 */

import java.io.FileInputStream;
import java.util.Scanner;

public class P2602 {
	private static int nbones, volume;
	private static int weight[], value[];
	private static int s[][];

	public static void main(String[] args) {
		weight = new int[1000];
		value = new int[1000];
		s = new int[1000][1001];

		try {
			System.setIn(new FileInputStream("Inputs/2602"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Scanner scanner = new Scanner(System.in);
		int ncases = scanner.nextInt();
		while (ncases-- > 0) {
			initACase(scanner);
			for (int bid = nbones - 1; bid >= 0; --bid) {
				for (int vol = 0; vol <= volume; ++vol) {
					fillS(bid, vol);
					//System.out.printf("(%2d, %2d): %3d\n", bid, vol, s[bid][vol]);
				}
			}
			System.out.println("" + s[0][volume]);
		}
	}

	private static void initACase(Scanner scanner) {
		nbones = scanner.nextInt();
		volume = scanner.nextInt();

		for (int bid = 0; bid < nbones; ++bid)
			value[bid] = scanner.nextInt();
		for (int bid = 0; bid < nbones; ++bid)
			weight[bid] = scanner.nextInt();
	}

	private static void fillS(int bid, int vol) {
		int thisVol = weight[bid];
		int thisVal = value[bid];
		if (bid == nbones - 1) {
			s[bid][vol] = (thisVol > vol ? 0 : thisVal);
			return;
		}

		int next = bid + 1;
		int value1 = s[next][vol];
		int value2 = Integer.MIN_VALUE;
		if (thisVol <= vol)
			value2 = thisVal + s[next][vol - thisVol];
		s[bid][vol] = (value1 > value2 ? value1 : value2);
	}
}
