/**
 * 649	Dota2 Senate 38.1% Medium 222/170
 * Performance: speed=99%, memory=75%
 */

import java.util.*;

public class Solution {
	public String predictPartyVictory(String senate) {
		boolean[] a = new boolean[senate.length()];
		int nr = 0, nd = 0; // nr = num of radiant
		for (int i = 0; i < a.length; ++i) if (a[i] = senate.charAt(i) == 'R') ++nr; else ++nd;

		int nrb = 0, ndb = 0, n = a.length; // nrb = num of radiant band
		while (true) {
			int p = 0;
			for (int i = 0; i < n; ++i) {
				if (a[i]) if (nrb > 0) --nrb; else { a[p++] = a[i]; ++ndb; if (--nd <= 0) break; }
				else      if (ndb > 0) --ndb; else { a[p++] = a[i]; ++nrb; if (--nr <= 0) break; }
			}

			if (nd <= 0) return "Radiant";
			else if (nr <= 0) return "Dire";

			n = p;
		}

	}

	public static void main(String args[]) {
		Solution s = new Solution();

		assert s.predictPartyVictory("R").equals("Radiant");
		assert s.predictPartyVictory("RR").equals("Radiant");
		assert s.predictPartyVictory("RD").equals("Radiant");
		assert s.predictPartyVictory("RRRRRDDDDD").equals("Radiant");
		assert s.predictPartyVictory("RRDDD").equals("Radiant");
		assert s.predictPartyVictory("RDRDRD").equals("Radiant");
		assert s.predictPartyVictory("DRDRDR").equals("Dire");

                System.out.println("done");
	}
}

