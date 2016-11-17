/**
 * 1800 Flyign to the Mars
 *
 */

import java.io.FileInputStream;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class P1800 {
	private static Map<BigInteger, Integer> map = new HashMap<BigInteger, Integer>(6000);
	private static int maxtimes;
	public static void main(String args[]) {
		try {
			System.setIn(new FileInputStream("Inputs/1800"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			initACase(scanner);
			for(int ntimes : map.values()) {
				if (ntimes > maxtimes)
					maxtimes = ntimes;
			}
			System.out.println("" + maxtimes);
		}
	}
	private static void initACase(Scanner scanner) {
		map.clear();
		maxtimes = Integer.MIN_VALUE;
		int nnumbers = scanner.nextInt();
		while (--nnumbers >= 0) {
			String numberString = scanner.next();
			BigInteger number = new BigInteger(numberString); 
			Integer stored = map.get(number);
			Integer quantity = (stored == null ? 1 : 1 + stored);
			map.put(number, quantity);
			//System.out.printf("(%3s, %3d) put\n", number.toString(), quantity);
		}
	}
}
