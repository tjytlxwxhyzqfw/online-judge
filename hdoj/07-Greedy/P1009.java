/**
 * 1009 FatMouse's Trade
 *
 * Greedy
 */

import java.io.FileInputStream;
import java.util.Arrays;
import java.util.Scanner;

class Bean implements Comparable<Bean> {
	public double weight;
	public double price;
	public double ratio;

	public void init(double weight, double price) {
		this.weight = weight;
		this.price = price;
		ratio = weight / price;
	}

	@Override
	public int compareTo(Bean another) {
		return (ratio - another.ratio < 0 ? 1 : -1);
	}
}

public class P1009 {
	private static int nrooms;
	private static double moneyLeft, poundsTotal;
	private static Bean[] bean = new Bean[1000]; 

	static {
		for (int i = 0; i < 1000; ++i)
			bean[i] = new Bean();
	}

	public static void main(String args[]) {
		try {
			System.setIn(new FileInputStream("Inputs/1009"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(System.in);
		while (initACase(scanner)) {
			//beans will be sorted during case-initing producedure
			for (int i = 0; i < nrooms; ++i) {
				buy(i);
				//!!! not <=
				if (moneyLeft < 0)
					break;
			}
			String result = String.format("%.3f", poundsTotal);
			System.out.println(result);
		}
	}
	private static void buy(int i) {
		double weight = bean[i].weight;
		double price = bean[i].price;

		if (price == 0) {
			poundsTotal += weight;
			return;
		}

		//!!! 0/0=NaN, 1/0 is correct;
		double percentage = moneyLeft / price; 
		if (percentage > 1)
			percentage = 1;
		poundsTotal +=  weight * percentage;
		moneyLeft -= price;
		//System.out.printf("buy %2d: weight: %2f, price: %2f, " +
			//"percentage: %.3f%%, moneyLeft: %.3f\n",
			//i, weight, price, percentage * 100, moneyLeft);
	}
	private static boolean initACase(Scanner scanner) {
		poundsTotal = 0;
		moneyLeft = scanner.nextDouble();
		nrooms = scanner.nextInt();
		if (moneyLeft == -1 && nrooms == -1)
			return false;
		for (int i = 0; i < nrooms; ++i) {
			double weight = scanner.nextDouble();
			double price = scanner.nextDouble();
			bean[i].init(weight, price);
		}
		Arrays.sort(bean, 0, nrooms);
		return true;
	}
}
