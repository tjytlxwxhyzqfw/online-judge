// 084.go.diss

package main

import (
	"fmt"
)

func largestRectangleArea(hs []int) int {
	left, rght := make([]int, len(hs)), make([]int, len(hs))
	stack, top := make([]int, len(hs)), -1
	for i, h := range hs {
		for top >= 0 && hs[stack[top]] >= h {
			top--
		}
		left[i] = -1
		if top >= 0 {
			left[i] = stack[top]
		}
		stack[top+1], top = i, top + 1
	}

	top = -1 // clear stack
	for i := len(hs)-1; i >= 0; i-- {
		for top >= 0 && hs[stack[top]] >=  hs[i] {
			top--
		}
		rght[i] = len(hs)
		if top >= 0 {
			rght[i] = stack[top]
		}
		stack[top+1], top = i, top + 1
	}

	maxArea := 0
	for i, h := range hs {
		// fmt.Printf("i=%d, h=%d, left=%d, rght=%d\n", i, h, left[i], rght[i])
		area := (rght[i] - left[i] - 1) * h
		if area > maxArea {
			maxArea = area
		}
	}
	return maxArea
}

func assert(real, expected int) {
	if expected != real {
		panic(fmt.Sprintf("expected=%d, real=%d", expected, real))
	}
	fmt.Printf("ok, expected=real=%d\n", real)
}

func main() {
	assert(largestRectangleArea([]int{1}), 1)
	assert(largestRectangleArea([]int{1, 2}), 2)
	assert(largestRectangleArea([]int{1, 3}), 3)
	assert(largestRectangleArea([]int{1, 1, 1}), 3)
	assert(largestRectangleArea([]int{1, 1, 1, 5}), 5)
	assert(largestRectangleArea([]int{1, 1, 1, 5, 1, 1}), 6)
	assert(largestRectangleArea([]int{2, 1, 5, 6, 2, 3}), 10) // online
	assert(largestRectangleArea([]int{3, 2, 6, 5, 1, 2}), 10)
	assert(largestRectangleArea([]int{3, 2, 6, 5, 2, 20}), 20)

	assert(largestRectangleArea([]int{2, 1, 2}), 3) // wa
}
