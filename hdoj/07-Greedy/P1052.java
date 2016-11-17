/**
 * 1052 Tian Ji -- The Horse Racing
 *
 * I Can Prove It;
 */

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class P1052 {
	private static int nhorses;
	private static int silver;
	private static int kings[] = new int[1000];
	private static int tians[] = new int[1000];
	private static int tianMin, tianMax, kingMin, kingMax;

	public static void main(String args[]) {
		try {
			System.setIn(new FileInputStream("Inputs/1052"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(System.in);
		while (initACase(scanner)) {
			for (int i = 0; i < nhorses; ++i) {
				if (tians[tianMin] < kings[kingMin]) {
					//田忌最慢的马一定要输一场,让它输给齐王最快的马
					printRace(kingMax, tianMin);
					silver -= 200;
					++tianMin;
					--kingMax;
				} else if (tians[tianMin] > kings[kingMin]) {
					//齐王最慢的马一定要输一场,让他输给田忌最慢的马
					printRace(kingMin, tianMin);
					silver += 200;
					++tianMin;
					++kingMin;
				} else {
					//田忌最慢的马和齐王最慢的马一样快,
					//此时,无法通过双方最慢的马做出决策,
					//因此我们考察双方最快的马
					if (kings[kingMax] > tians[tianMax]) {
						//齐王最快的马一定要赢一场,让它赢田忌最慢的马
						printRace(kingMax, tianMin);
						silver -= 200;
						++tianMin;
						--kingMax;
					} else if (kings[kingMax] < tians[tianMax]) {
						//田忌最快的马一定要赢一场,让他赢齐王最快的马
						printRace(kingMax, tianMax);
						silver += 200;
						--tianMax;
						--kingMax;
					} else {
						//田忌最快的马和齐王最快的马一样快,
						//田忌最慢的马和齐王最慢的马一样慢,
						//考察双方都是(1,2,3)的情况可知,
						//田忌应该让最慢的马输给齐王最快的马
						printRace(kingMax, tianMin);
						silver += (tians[tianMin] < kings[kingMax] ? -200 : 0);
						++tianMin;
						--kingMax;
					}
				}
			}
			System.out.println("" + silver);
		}
	}
	private static boolean initACase(Scanner scanner) {
		silver = 0;	
		nhorses = scanner.nextInt();
		if (nhorses == 0)
			return false;

		for (int i = 0; i < nhorses; ++i)
			tians[i] = scanner.nextInt();
		for (int i = 0; i < nhorses; ++i)
			kings[i] = scanner.nextInt();

		Arrays.sort(tians, 0, nhorses);
		Arrays.sort(kings, 0, nhorses);
		tianMin = kingMin = 0;
		tianMax = kingMax = nhorses - 1;

		return true;
	}
	private static void printRace(int i, int j) {
		//System.out.printf("before racing: silver: %3d\n", silver);
		//System.out.printf("Tian: %3d, King: %3d\n", tians[j], kings[i]);
	}
}
