// 问题描述: 给定一个数组, 和一个闭区间[lower, upper], 求数组中和落在闭区间[lower, upper]内的子区间个数.

// review logs:
// ------------
//
// 20200707: 没有有价值的思考
//
// 20200710: 衍生问题(中偏难): 给定a, b两个有序数组以及一个闭区间[lo, up], 求x in a, y in b, 满足lower <= y-x <= upper,
//   有多少对满足条件的(x, y)?
//   解: a中有两个指针i, j, 且a[i]是第一个满足b0-ai <= up的数, aj是**最后一个**满足b0-aj >= lo的数.
//   (可见i指向一个小数, j指向一个大数). 现在考虑b[1] > b[0], 因此b[1]-a[i] >= b[0]-a[i], 可能>up, 所以i
//  可能需要向右移动一下. b[1]-a[j] >= b[0]-a[j] >= lo, 但是可能不是**最后一个**, 所以j也可能需要右移一下. 所以i, j
//  始终是右移的, 可以用一次遍历完成计数.
//
//  [另一种理解方式]
//  原来, k是所有满足以下条件的指针:
//  lo <= b[0] - a[k] <= up
//  现在换成b1: lo  <= b[1] - a[k]-(b1-b0) <= up
//  即: lo <= b[1] - (a[k] + b1-b0) <= up
//  现在要求k', 满足 lo <= b1 - ak' <= up
//  所以ak' = ak + b1-b0, 即ak'要更大, 说明整个区间(左边界&右边界)都要右移
//
//  举例说明: lo = 300, up = 1100
// a = [50, 90, 100, 200, 300, 400, 600, 700, 750, 800, 900, 1000, 1200, 1300, 1500]
// b = [1200, 1500, 1800]
// b[0] = 1200, a[i] = 100, a[j] = 900 (i, j全都右移)
// b[1] = 1500, a[i] = 300, a[j] = 1200 (i, j全部右移)
// b[2] = 1800, a[i] = 700, a[j] = 1500 (i, j全部右移)


// very similliar with 315(CountOfSmallerNumbersAfterSelf)
package main

import (
	_ "github.com/stretchr/testify/assert" // to add this into god manifest
)

// id=327 pass=34 ud=693/86 s=8ms(100?) m=?
func countRangeSum(a []int, lower, upper int) int {
	if len(a) == 0 {
		return 0
	}
	sum := make([]int64, len(a))
	sum[0] = int64(a[0])
	for i := 1; i < len(a); i++ {
		sum[i] = sum[i-1] + int64(a[i])
	}
	// log.Printf("input psum: %v", sum)
	count := mergeCount(sum, 0, len(sum), int64(lower), int64(upper))
	// log.Printf("%d <= %v [%d, %d]", count, a, lower, upper)
	return count
}

func mergeCount(p []int64, i, j int, lower, upper int64) int {
	if j - i <= 1 {
		if j-i == 1 && lower <= p[i] && p[i] <= upper {
			return 1
		}
		return 0
	}

	k := i+(j-i)/2
	left := mergeCount(p, i, k, lower, upper)
	rght := mergeCount(p, k, j, lower, upper)
	merging := merge(p, i, k, j, lower, upper)
	return left + rght + merging
}

// a, b are sorted
func merge(a []int64, lo, mi, hi int, lower, upper int64) int {
	left, rght := a[lo:mi], a[mi:hi]
	smaller, larger, count := 0, 0, 0
	for i := 0; i < len(rght); i++ {
		for smaller < len(left) && rght[i]-left[smaller] > upper {
			smaller++
		}
		for larger < len(left) && rght[i]-left[larger] >= lower {
			larger++
		}
		if larger > smaller {
			count += larger - smaller
		}
	}

	merged := make([]int64, len(left)+len(rght))
	i, j, p := 0, 0, 0
	for i < len(left) && j < len(rght) {
		if left[i] < rght[j] {
			merged[p], p, i = left[i], p+1, i+1
		} else {
			merged[p], p, j = rght[j], p+1, j+1
		}
	}
	for ; i < len(left); merged[p], p, i = left[i], p+1, i+1 {
	}
	for ; j < len(rght); merged[p], p, j = rght[j], p+1, j+1 {
	}
	for i := lo; i < hi; i++ {
		a[i] = merged[i-lo]
	}

	return count
}

func main() {
	countRangeSum([]int{}, 0, 1)
	countRangeSum([]int{}, 1, 0)
	countRangeSum([]int{1}, 0, 0)
	countRangeSum([]int{1}, 1, 1)
	countRangeSum([]int{1}, 2, 2)
	countRangeSum([]int{1, 2, 3, 4, 5}, 3, 6)
	countRangeSum([]int{-2, 5, -1}, -2, 2)
	countRangeSum([]int{3, 1, -4, 1, -5, 9, -2, -6}, -5, -1)
	countRangeSum([]int{3, 1, -4, 1, -5, 9, -2, -6}, 0, 5)
	countRangeSum([]int{3, 1, -4, 1, -5, 9, -2, -6, 5, 3}, -5, 5)
	countRangeSum([]int{3, 1, -4, 1, -5, 9, -2, -6, 5, 3}, 5, -5)
}
