/**
 * 950 Reveal Cards In Increasing Order 72.3% Medium 552/112
 * Performance: speed=95%, memory=14%
 */

import java.util.*;

public class Solution {
	public int[] deckRevealedIncreasing(int[] deck) {
		Queue<Integer> q = new LinkedList<>();
		for (int i = 0; i < deck.length; ++i) q.offer(i);

		// todo: a[] is not necessary
		int[] a = new int[deck.length];
		int j = 0;
		while (true) {
			a[j++] = q.poll();
			if (!q.isEmpty()) q.offer(q.poll());
			else break;
		}

		// a[x] = y means that the y-th card in original deck was reavaled at the x-th draw
		// so we should make deck[y] = sorted[x]

		int[] sorted = new int[deck.length];
		for (int i = 0; i < deck.length; ++i) {
			sorted[i] = deck[i];
		}
		Arrays.sort(sorted);
		for (int i = 0; i < deck.length; ++i) deck[a[i]] = sorted[i];

		return deck;
	}

	static void printA(int[] a) {
		for (int i = 0; i < a.length; ++i) System.out.printf("%d, ", a[i]);
		System.out.println();
	}

	public static void main(String args[]) {
		Solution s = new Solution();

		printA(s.deckRevealedIncreasing(new int[]{17,13,11,2,3,5,7}));		
		printA(s.deckRevealedIncreasing(new int[]{0}));		
		printA(s.deckRevealedIncreasing(new int[]{0, 1}));		
		printA(s.deckRevealedIncreasing(new int[]{0, 1, 2}));		
		printA(s.deckRevealedIncreasing(new int[]{0, 1, 2, 3}));		
		printA(s.deckRevealedIncreasing(new int[]{0, 1, 2, 3, 4}));		
		printA(s.deckRevealedIncreasing(new int[]{0, 1, 2, 3, 4, 5}));		
		printA(s.deckRevealedIncreasing(new int[]{0, 1, 2, 3, 4, 5, 6}));		
		printA(s.deckRevealedIncreasing(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));		

		System.out.println("done");
	}
}

