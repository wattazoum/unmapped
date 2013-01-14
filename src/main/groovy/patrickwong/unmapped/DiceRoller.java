package patrickwong.unmapped;

import java.security.SecureRandom;
import java.util.Random;

public class DiceRoller {
	private static Random random = new SecureRandom();
	
	public static int roll(int dieSize) {
		return random.nextInt(dieSize) + 1;
	}
	
	public static int binary() {
		int temp = random.nextInt(2);
		if (temp > 0) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public static int binaryPool(long n) {
		int total = 0;
		if (n > 0) {
			for (int i = 0; i < n; i++) {
				total += binary();
			}
		} else {
			for (int i = 0; i < (n * -1); i++) {
				total -= binary();
			}
		}
		return total;
	}
	
	public static int nextInt(int n) {
		return random.nextInt(n);
	}
}