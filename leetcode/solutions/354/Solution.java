/**
 * 0354: Russian Doll Envelopes
 *
 * Performance: speed=39%, memory=31%
 *
 * !!!TLE(IN PYTHON)!!!: There is a better solution!!!
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	public int maxEnvelopes(int[][] envelopes) {
		if (envelopes.length == 0) return 0;

		Env[] envs = new Env[envelopes.length];
		for (int i = 0; i < envs.length; ++i) envs[i] = new Env(envelopes[i][0], envelopes[i][1]);
		Arrays.sort(envs);

		int dp[] = new int[envs.length];
		dp[0] = 1;
		for (int i = 1; i < envs.length; ++i) {
			dp[i] = 1;
			for (int j = 0; j < i; ++j) {
				if (envs[j].w < envs[i].w && envs[j].h < envs[i].h)
					dp[i] = Math.max(dp[i], dp[j]+1);
			}
			System.out.printf("[%2d, %2d]: %d\n", envs[i].w, envs[i].h, dp[i]);
		}

		int max = 0;
		for (int i = 0; i < dp.length; ++i) max = Math.max(max, dp[i]);
		return max;
	}

	public static void main(String args[]) {
		new Solution().maxEnvelopes(new int[][]{{5, 4}, {6, 4}, {6, 7}, {2,3}});
	}
}

class Env implements Comparable<Env> {
	int w, h;

	Env(int w, int h) {
		this.w = w;
		this.h = h;
	}

	@Override
	public int compareTo(Env that) {
		if (w == that.w)
			return h - that.h;
		return w - that.w;
	}
}

