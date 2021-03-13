// 446h.go ArithmeticSlicesII - Subsequence
package main

import (
	"log"
)

// 题目描述: 给定一个整型数组, 求其中的所有的等差序列(len>=3)的个数.
//
// 第一个思路(wrong): 遍历所有对(i, j), 找到以i, j为起点的所有等差序列.
// 这个思路的错误在于: (i, j)开始的等差序列可能有非常多个:
// hard example:  consider the following sequence: 1, 2, 3, 4, 3, 4, 3, 4
//
// 第二个思路: 动态规划: dp[i][j] => 以[i][j]为结尾的等差序列的个数.
//     我们找到i, j前面的那个数: prev, prev可能出现在多个位置上.
//     因此, 我们维护一个map: 值->位置列表(392G), 然后配合lb来索引所有出现在i之前的位置.
//     然后dp[i][j] = sigma(dp[k][i], k = lb算法索引出的所有位置).
//
// [关于第二个思路的复杂度问题]
//     注意, 如果数组有n个相同的元素组成, 那么理论上, 算法的最差复杂度是: O(n^3logn),
//     但是由于等差序列的数目几乎是指数级的增长, 到n=30的时候, 就已经涨到了10亿个.
//     而题设又保证结果不会超过20亿个, 因此我任务最差复杂度不会达到. 可以放心去做.

// id=446 pass=32 ud=500/59 s=100 m=?
func numberOfArithmeticSlices(a []int) int {
	m := map[int][]int{}
	for i, x := range a {
		list := m[x]
		if list == nil {
			list = make([]int, 0)
		}
		list = append(list, i)
		m[x] = list
	}

	dp := make([][]int, len(a))
	for i := 0; i < len(dp); i++ {
		dp[i] = make([]int, len(a))
	}

	total := 0
	for i := 0; i < len(a); i++ {
		for j := i+1; j < len(a); j++ {
			prev := a[i]-(a[j]-a[i])
			p := lb(m[prev], i)
			// log.Printf("a[%d]=%d, a[%d]=%d, prev=%d, list=%v", i, a[i], j, a[j], prev, m[prev][:p])
			dp[i][j] = p
			for k := 0; k < p; k++ {
				dp[i][j] += dp[m[prev][k]][i]
			}
			total += dp[i][j]
		}
	}

	log.Printf("==> %d ==> %v", total, a)
	return total
}

func lb(a []int, x int) int {
	i, j := 0, len(a)
	for i < j {
		k := i + (j-i)/2
		if a[k] < x {
			i = k+1
		} else {
			j = k
		}
	}
	return i
}

func main() {
	assert(numberOfArithmeticSlices([]int{3, 1, 4, 1, 5, 9, 2, 6, 5, 3}) == 9)
	assert(numberOfArithmeticSlices([]int{2, 7, 1, 8, 2, 8, 1, 8, 2, 8, 4, 5, 9}) == 12)
	assert(numberOfArithmeticSlices([]int{}) == 0)
	assert(numberOfArithmeticSlices([]int{1}) == 0)
	assert(numberOfArithmeticSlices([]int{1, 1}) == 0)
	assert(numberOfArithmeticSlices([]int{1, 1, 1}) == 1)
	assert(numberOfArithmeticSlices([]int{1, 1, 1, 1}) == 5)
	assert(numberOfArithmeticSlices([]int{1, 1, 1, 1, 1}) == 16)
	assert(numberOfArithmeticSlices([]int{1, 1, 1, 1, 1,  1, 1, 1, 1, 1,  1, 1, 1, 1, 1,
	1, 1, 1, 1, 1,  1, 1, 1, 1, 1,   1, 1, 1, 1, 1}) == 1073741358)
}

func assert(b bool) {
	log.Printf("\n---\n")
	if !b {
		panic("assertion error")
	}
}
