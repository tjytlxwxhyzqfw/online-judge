/**
 * 2570 迷障
 *
 * DP
 */

import java.io.FileInputStream;
import java.util.Scanner;

public class P2570 {
	private static int npotions;
	private static int volume;
	private static int target, targetMax;
	private static int s[][] = new int[100][10001];
	private static int concentrations[] = new int[100];

	public static void main(String args[]) {
		try {
			System.setIn(new FileInputStream("Inputs/2570"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(System.in);
		int ncases = scanner.nextInt();
		while (--ncases >= 0) {
			initACase(scanner);

			int last = npotions - 1;
			for (int j = 1; j <= targetMax; ++j) {
				s[last][j] = Integer.MIN_VALUE;
			}
			s[last][0] = 0;
			s[last][concentrations[last]] = 1;
			//System.out.printf(
				//"(%3d, %3d): %3d\n",
				//last, concentrations[last], s[last][concentrations[last]]);

			for (int i = npotions - 2; i >= 0; --i) {
				for (int j = 0; j <= targetMax; ++j) {
					fillS(i, j);
					//System.out.printf("(%3d, %3d): %3d\n", i, j, s[i][j]);
				}
			}

			//TODO read this!
			double concentrationMax = 0.0;
			int volumeMax = 0;
			for (int j = 0; j <= targetMax; ++j) {
				if (s[0][j] == 0)
					continue;
				double concentration = (double)1 * j / s[0][j];
				if (concentration > target)
					continue;

				int volume = s[0][j];
				if (volume > volumeMax) {
					volumeMax = volume;
					concentrationMax = concentration;
				}
			}
			System.out.println(String.format(
				"%d %.2f", volumeMax * volume, concentrationMax / 100));
		}
	}
	private static void initACase(Scanner scanner) {
		npotions = scanner.nextInt();
		volume = scanner.nextInt();
		target = scanner.nextInt();
		targetMax = npotions * target;
		for (int i = 0; i < npotions; ++i)
			concentrations[i] = scanner.nextInt();
	}
	private static void fillS(int id, int thresh) {
		int next = id + 1;
		int cct = concentrations[id];

		// 前一个值不存在: 本值存在
		// 前一个值存在但是是个无效值: 本值多半无效
		int fromNext = (cct > thresh ? -1 : s[next][thresh - cct]);
		int use = (fromNext >= 0 ? 1 + fromNext : Integer.MIN_VALUE); 
		//System.out.printf("(%3d, %3d): fromNext: %3d, use: %3d\n", id, thresh, fromNext, use);
		int skip = s[next][thresh];
		s[id][thresh] = (use > skip ? use : skip);
	}
}
