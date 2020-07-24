package main

import (
	"fmt"
)

// id=188 pass=27 ud=1235/75 s=5 m=?
func maxProfit(k int, p []int) int {
	if k > len(p) / 2 {
		k = len(p) / 2
	}
	if k == 0 || len(p) == 0 {
		return 0
	}

	// s: end with `sell` or `nothing`
	// b: must end with `buy`
	s, b := make([][]int, len(p)), make([][]int, len(p))
	for i := 0; i < len(p); i++ {
		s[i] = make([]int, k)
		b[i] = make([]int, k)
	}

	b[0][0] = -p[0]
	for i := 1; i < len(p); i++ {
		b[i][0] = max2(b[i-1][0], -p[i])
		s[i][0] = max2(s[i-1][0], p[i]+b[i-1][0])
		// fmt.Printf("s[%d][0]=%d, b[%d][0]=%d\n", i, s[i][0], i, b[i][0])
	}

	// s[day][tx]:
	// day: 0 1 2 3 4 5 6 7
	//   b: 0 0 1 1 2 2 3 3 <= day/2
	//   s: - 0 0 1 1 2 2 3 <= (day-1)/2
	for i := 1; i < len(p); i++ {
		for j := 1; j < k && j <= i/2; j++ {
			// fmt.Printf("[%d, %d] -> (price=%d): ", i, j, p[i])
			b[i][j] = s[i-1][j-1] - p[i]
			// *** you must be very very careful about these indices because thy might be invalid ***
			if j <= (i-1)/2 {
				b[i][j] = max2(b[i][j], b[i-1][j])
			}

			// fmt.Printf("b=%d", b[i][j])
			if j <= (i-1)/2 {
				s[i][j] = b[i-1][j] + p[i]
				// *** be very very careful ***
				if j <= (i-1-1)/2 {
					s[i][j] = max2(s[i][j], s[i-1][j])
				}
				// fmt.Printf(" s=%d", s[i][j])
			}
			// fmt.Println()
		}
	}

	max := 0
	for i := 0; i < len(p); i++ {
		for j := 0; j < k; j++ {
			// fmt.Printf("- s[%d][%d]=%d\n", i, j, s[i][j])
			max = max2(max, s[i][j])
		}
	}

	return max
}

func max2(x, y int) int {
	if x > y {
		return x
	}
	return y
}

func main() {
	assert(maxProfit(2, []int{2, 1, 2, 0, 1}), 2)
	assert(maxProfit(2, []int{1, 2, 3, 4, 3, 2, 1}), 3)
	assert(maxProfit(5, []int{1, 2}), 1)
	assert(maxProfit(1, []int{1, 2}), 1)

	assert(maxProfit(0, []int{}), 0)
	assert(maxProfit(1, []int{}), 0)
	assert(maxProfit(7, []int{}), 0)
	assert(maxProfit(0, []int{1}), 0)
	assert(maxProfit(1, []int{1}), 0)
	assert(maxProfit(0, []int{1, 2}), 0)
	assert(maxProfit(0, []int{1, 2, 3, 4, 3, 2, 1}), 0)
	assert(maxProfit(1, []int{1, 2, 3, 4, 3, 2, 1}), 3)
	assert(maxProfit(3, []int{1, 2, 3, 4, 3, 2, 1}), 3)
	assert(maxProfit(8, []int{1, 2, 3, 4, 3, 2, 1}), 3)
}

func assert(real, expect int) {
	if real != expect {
		panic(fmt.Sprintf("real=%d, expect=%d", real, expect))
	}
	fmt.Printf("ok, read=expect=%d\n", real)
}
