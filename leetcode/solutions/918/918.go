918.go

package main

import (
	"fmt"
	"math"
)

func assertEqual(real, expected int) {
	if real != expected {
		panic(fmt.Sprintf("real=%d, expected=%d", real, expected))
	}
	fmt.Printf("ok, real = expected = %d\n", real)
}

func main() {
	assertEqual(maxSubarraySumCircular([]int{1}), 1)
	assertEqual(maxSubarraySumCircular([]int{-1}), -1)
	assertEqual(maxSubarraySumCircular([]int{1, -1, 1}), 2)
	assertEqual(maxSubarraySumCircular([]int{1, 1, -10, 1, 1}), 4)
	assertEqual(maxSubarraySumCircular([]int{1, 1, 1, 1, 1}), 5)
	assertEqual(maxSubarraySumCircular([]int{-3, -2, -1, -2, -3}), -1)
	assertEqual(maxSubarraySumCircular([]int{5, 3, -3, 3, 5}), 16)
	assertEqual(maxSubarraySumCircular([]int{1, 1, -1, 10, -100, 10, -1, 1, 1}), 22)

	// real cases
	assertEqual(maxSubarraySumCircular([]int{1, -2, 3, -2}), 3)
	assertEqual(maxSubarraySumCircular([]int{5, -3, 5}), 10)
	assertEqual(maxSubarraySumCircular([]int{3, -1, 2, -1}), 4)
	assertEqual(maxSubarraySumCircular([]int{3, -2, 2, -3}), 3)
	assertEqual(maxSubarraySumCircular([]int{-2, -3, -1}), -1)
}

// 918 450/24 s=29 m=?
func maxSubarraySumCircular(a []int) int {
	max, _, _ := maxCSub(a)
	// fmt.Printf("max=%d\n", max)

	sum := 0
	for i := range a {
		sum += a[i]
		a[i] = -a[i]
	}

	max1, maxI, maxJ := maxCSub(a)
	// fmt.Printf("max1=%d [%d, %d]\n", max1, maxI, maxJ)
	if (maxI != 0 || maxJ != len(a)-1) && sum + max1 > max {
		max = sum + max1
	}

	return max
}

func maxCSub(a []int) (int, int, int) {
	max, sum := math.MinInt32, 0
	maxI, maxJ, i := 0, 0, 0
	for j, x := range a {
		sum += x
		if sum > max {
			max, maxI, maxJ = sum, i, j
		}
		if sum < 0 {
			sum = 0
			i = j + 1
		}
	}
	return max, maxI, maxJ
}

