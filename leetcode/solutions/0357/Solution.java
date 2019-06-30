/**
 * 0357: CountNumbersWithUniqueDigits
 *
 * Performance: speed=100%, memory=6%
 */

class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        int ans = 1;
        for (int i = 1; i <= n; ++i) {
            if (i > 10) break;
            int delta = 1;
            for (int j = 1; j <= i ; ++j) delta *= (j==1?9:(11-j));
            //System.out.printf("delta=%d\n", delta);
            ans += delta;
        }
        return ans;
    }
}
