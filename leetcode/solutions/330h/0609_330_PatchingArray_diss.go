// review logs
// -----------
// 20200715: 题目描述: 给定一个有序不重复数组如a=[1, 5, 10], 以及一个整数如t=31, 现在要求你向
//   a中添加若干个数字, 使得任何小于等于t的数字, 都能通过a中的某几个元素的和来表示(每个元素用一次).
//   问, 最少要添加几个数字?
//   解: (1) 如果a中没有1, 添加1
//       (2) 假设, 数组中有n个数字, 这n个数字能表示的最大值是max, 且任意<=max的数字都能由这n个数字表出.
//       (3) 那么, 下一个向数组中添加的数字必然是max+1, 且n+1最大可表出max+max+1, 且任意<=2max+1的数都可表出.
//       (4) 由上述原理可构造算法解出此题.
package main

import (
	"fmt"
	_ "github.com/stretchr/testify/assert" // to add this into god manifest
	"log"
	"math"
)

// Patching Array
// id=330 pass=34 ud=464/72 s=? m=87
func minPatches(a []int, n int) int {
	max, count := 0, 0
	for i := 0; i < len(a) && max < n; i++ {
		if a[i] == max+1 {
			max += max+1
			continue
		}
		for max < n && max < a[i] && a[i] != max+1 {
			// log.Printf("a[%d]=%d, max=%d, n=%d", i, a[i], max, n)
			max += max+1
			count++
		}
		max += a[i]
	}
	// log.Printf("break: max=%d, count=%d", max, count)
	for max < n {
		max += max+1
		count++
	}

	// log.Printf("%d <====== %v -> %d", count, a, n)
	return count
}

func main() {
	assert(3, minPatches([]int{1, 5, 10}, 31))
	assert(3, minPatches([]int{1, 5, 10}, 23))
	assert(6, minPatches([]int{0}, 32))
	assert(5, minPatches([]int{8}, 32))
	assert(5, minPatches([]int{1}, 32))
	assert(4, minPatches([]int{2, 8}, 32))
	assert(1, minPatches([]int{1, 3}, 6))
	assert(2, minPatches([]int{1, 5, 10}, 20))
	assert(3, minPatches([]int{1, 5, 10}, 30))
	assert(0, minPatches([]int{1, 2, 2}, 5))
	assert(31, minPatches([]int{}, math.MaxInt32))
	assert(30, minPatches([]int{8}, math.MaxInt32))
	assert(28, minPatches([]int{8, 256, 65536}, math.MaxInt32))
	assert(28, minPatches([]int{1, 2, 31, 33}, math.MaxInt32))
}

func assert(a, b int) {
	if a != b {
		panic(fmt.Sprintf("a=%d, b=%d", a, b))
	}
	log.Printf("ok, %d", a)
}
