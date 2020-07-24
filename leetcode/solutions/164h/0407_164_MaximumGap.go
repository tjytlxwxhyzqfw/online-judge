package main

import (
	"fmt"
	"math"
)

// id=164 pass=34 ud=724/166 s=15 m=?
// i came up with nothing until i read the solution in discussion,
// this solution is very heuristic to bucket sort.
func maximumGap(a []int) int {
	if len(a) < 2 {
		return 0
	}
	min, max := a[0], a[0]
	for i := 1; i < len(a); i++ {
		if a[i] < min {
			min = a[i]
		}
		if a[i] > max {
			max = a[i]
		}
	}

	buckets, bsize := make([][2]int, len(a)), (max-min+len(a)-1)/(len(a)-1)
	for i := range buckets {
		buckets[i][0], buckets[i][1] = math.MaxInt32, math.MinInt32
	}
	for _, x := range a {
		bid := (x - min) / bsize
		if buckets[bid][0] > x {
			buckets[bid][0] = x
		}
		if buckets[bid][1] < x {
			buckets[bid][1] = x
		}
		// fmt.Printf(">>> bid=%d: min=%12d, max=%12d\n", bid, buckets[bid][0], buckets[bid][1])
	}

	// for i, b := range buckets {
		// fmt.Printf("bid=%d: min=%12d, max=%12d\n", i, b[0], b[1])
	// }

	maxGap, x, y := 0, -1, -1
	for i := 0; i < len(buckets); i++ {
		if buckets[i][0] > buckets[i][1] { // empty bucket
			continue
		}
		for j := 0; j < len(buckets[i]); j++ {
			x, y = y, buckets[i][j]
			if x != -1 && y - x > maxGap {
				maxGap = y - x
			}
		}
	}

	return maxGap
}

func main() {
	assert(maximumGap([]int{}), 0)
	assert(maximumGap([]int{1}), 0)
	assert(maximumGap([]int{3, 6, 9, 1}), 3)
	assert(maximumGap([]int{9, 7, 10, 8}), 1)
	assert(maximumGap([]int{1, 2, 3, 98, 99, 100}), 95)
	assert(maximumGap([]int{1, 1, 1}), 0)
}

func assert(real, expected int) {
	if expected != real {
		panic(fmt.Sprintf("expected=%d, real=%d", expected, real))
	}
	fmt.Printf("ok, expected=real=%d\n", real)
}

// 没有桶, 创造桶也要上
