/**
 * 911: Online Election
 * Performance: speed=74%, memory=98%
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TopVotedCandidate {
	private int[] persons;
	private int[] times;
	private int[] winners;

	public TopVotedCandidate(int[] persons, int[] times) {
		this.persons = persons;
		this.times = times;
		winners = new int[times.length];

		Map<Integer, Integer> map = new HashMap<>();
		int maxvotes = 0;
		for (int i = 0; i < persons.length; ++i) {
			if (map.get(persons[i]) == null) map.put(persons[i], 0);
			int votes = 1 + map.get(persons[i]);

			// dont forget update the map
			map.put(persons[i], votes);

			if (votes >= maxvotes) {
				maxvotes = votes;
				winners[i] = persons[i];
			} else {
				winners[i] = winners[i-1];
			}
		}
	}

	public int q(int t) {
		int index = lb(t, times);

		// RuntimeErrorOnSumbition: t might be very large
		if (index == times.length) return winners[winners.length-1];

		if (times[index] == t) return winners[index];
		return winners[index-1];  // safe
	}

	private int lb(int x, int[] a) {
		int i = 0, j = a.length;
		while (i < j) {
			int k = i + (j - i) / 2;
			if (a[k] < x) i = k + 1;
			else j = k;
		}
		return i;
	}
}
