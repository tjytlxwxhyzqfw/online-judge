/**
 * 519 Random Flip Matrix 33.5% Medium
 * Performance: speed=5%, memory=66%
 * todo: there is a much better solution in disscuss;
 */

/*

Similar idea with some explanation:
Here we are modeling the matrix as a 1d array with initial size of row*cols.
For each flip, randomly pick an index from 0 to size-1 and flip it.
int r = rand.nextInt(total--);
Then swap that flipped element with the tail element (index: size-1), store that mapping info (key: origin index, value: index of the tail) into a Map and decrease the size.
map.put(r, map.getOrDefault(total, total));
Next time when we randomly pick a same index we can go to the map and find the actual element stores in that index
int x = map.getOrDefault(r, r);

class Solution {

    Map<Integer, Integer> map;
    int rows, cols, total;
    Random rand;
    
    public Solution(int n_rows, int n_cols) {
        map = new HashMap<>();
        rand = new Random();
        rows = n_rows; 
        cols = n_cols; 
        total = rows * cols;
    }
    
    public int[] flip() {
        int r = rand.nextInt(total--);
        int x = map.getOrDefault(r, r);
        map.put(r, map.getOrDefault(total, total));
        return new int[]{x / cols, x % cols};
    }
    
    public void reset() {
        map.clear();
        total = rows * cols;
    }
}

*/

import java.util.*;

public class Solution {
	int[][] m;
	int[] size;
	int rows, cols;
	int n;
	Random r;

	public Solution(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		m = new int[rows][];
		size = new int[rows];
		Arrays.fill(size, cols);
		n = rows * cols;
		r = new Random(47);
	}

	public int[] flip() {
		if (n <= 0) return null;

		int idx = r.nextInt(n), start = 0, i;
		for (i = 0; i < rows; ++i) {
			if (start <= idx && idx < start + size[i]) break;
			start += size[i];
		}
		if (m[i] == null) {
			m[i] = new int[cols];
			for (int j = 0; j < cols; ++j) m[i][j] = j;
		}
		// System.out.printf("idx=%d, i=%d, idx-start=%d, col=%d\n", idx, i, idx-start, m[i][idx-start]);
		int col = m[i][idx-start];
		m[i][idx-start] = m[i][--size[i]];
		--n;
		return new int[]{i, col};
	}

	public void reset() {
		n = cols * rows;
		for (int i = 0; i < rows; ++i) {
			size[i] = cols;
			m[i] = null;
		}
	}

	public static void main(String args[]) {
		Solution s = new Solution(3, 4);
		for (int i = 0; i < 15; ++i) {
			int[] p = s.flip();
			if (p != null) System.out.printf("(%d, %d)\n", p[0], p[1]);
			else System.out.println("(null)");
			if (i == 1) s.reset();
		}
	}
}
