// description: given an array, split it into m continuous subarrays and make the largest sum
// amoung these sub-arrays minimum.
// 20200813: 看了百度来的答案: DP: [i][j]: 把前i个元素划分成j组. 
//   这里我卡了一下, 一度认为[i][j]的答案来自于[i-1][j]和[i-1][j-1]. 迷糊了很久. 
//   下午终于找到了关键点, 那就是"等价划分". [i][j]一定来自于某个对答案空间的等价划分. 
//   而这个等价划分通常是对最后一个元素进行分类讨论而来的. 
//   因此i号元素可以自成1组, 2个成1组, ..., n个成一组, 这才是正确的划分. 
package main

import (
	"log"
	"math"
)

// id=410 ud=1837/78 pass=44 s=27 m=25
func splitArray(a []int, m int) int {
	if m == 0 || len(a) == 0 {
		return 0
	}

	dp := make([][]int, len(a))
	for i := 0; i < len(dp); i++ {
		dp[i] = make([]int, m+1)
	}
	for i := 0; i <= m; i++ {
		dp[0][i] = a[0]
	}

	for i := 1; i < len(dp); i++ {
		dp[i][1] = dp[i-1][1] + a[i]
		for j := 2; j <= m; j++ {
			dp[i][j] = math.MaxInt32
			sum := 0
			for k := i; k > 0; k-- {
				sum += a[k]
				dp[i][j] = min(dp[i][j], max(sum, dp[k-1][j-1]))
			}
			// log.Printf("(%2d, %2d): %d", i, j, dp[i][j])
		}
	}

	return dp[len(a)-1][m]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func main() {
	splitArray([]int{}, 0)
	splitArray([]int{1}, 0)
	splitArray([]int{}, 1)

	assert(splitArray([]int{1}, 1) == 1)
	assert(splitArray([]int{1, 2}, 1) == 3)
	assert(splitArray([]int{1, 2, 3}, 1) == 6)
	assert(splitArray([]int{1, 2, 3, 5}, 1) == 11)

	assert(splitArray([]int{1}, 2) == 1)
	assert(splitArray([]int{1, 1}, 2) == 1)
	assert(splitArray([]int{1, 1, 1}, 2) == 2)
	assert(splitArray([]int{1, 1, 1, 1}, 2) == 2)
	assert(splitArray([]int{1, 1, 1, 1, 1}, 2) == 3)

	assert(splitArray([]int{1, 1, 10}, 2) == 10)
	assert(splitArray([]int{1, 1, 10}, 3) == 10)

	assert(splitArray([]int{7, 2, 5, 10, 8}, 2) == 18)
}

func assert(b bool) {
	log.Printf("\n---\n")
	if !b {
		panic("assertion error")
	}
}
