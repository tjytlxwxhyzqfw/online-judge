// 85.go.diss

package main

import "fmt"

// id=85 ud=2181/61 pass=36% s=68 m=?
func maximalRectangle(m [][]byte) int {
	if len(m) == 0 || len(m[0]) == 0 {
		return 0
	}
	h := make([][]int, len(m))
	for i := range h {
		h[i] = make([]int, len(m[0]))
	}
	for y := 0; y < len(m); y++ {
		for x := 0; x < len(m[0]); x++ {
			if m[y][x] == '0' {
				continue
			}
			h[y][x] = 1
			if y > 0 {
				h[y][x] += h[y-1][x]
			}
		}
	}

	maxArea := 0
	for y := 0; y < len(m); y++ {
		area := solveLnX(h[y])
		if maxArea < area {
			maxArea = area
		}
	}

	return maxArea
}

// solution of problem 84, copy directly with no modification
func solveLnX(hs []int) int {
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
		stack[top+1], top = i, top+1
	}

	top = -1 // clear stack
	for i := len(hs) - 1; i >= 0; i-- {
		for top >= 0 && hs[stack[top]] >= hs[i] {
			top--
		}
		rght[i] = len(hs)
		if top >= 0 {
			rght[i] = stack[top]
		}
		stack[top+1], top = i, top+1
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

func main() {
	assert(maximalRectangle([][]byte{}), 0)
	assert(maximalRectangle([][]byte{{'0', '1'}, {'0', '1'}}), 2)
	assert(maximalRectangle([][]byte{{'1'}}), 1)
	assert(maximalRectangle([][]byte{{'1', '1'}, {'1', '1'}}), 4)
	assert(maximalRectangle([][]byte{{'1', '1'}, {'1', '0'}}), 2)
	assert(maximalRectangle(
		[][]byte{
			{'1', '0', '1', '0', '0'},
			{'1', '0', '1', '1', '1'},
			{'1', '1', '1', '1', '1'},
			{'1', '0', '0', '1', '0'}}),
			6)
	assert(maximalRectangle(
		[][]byte{
			{'1', '0', '1', '0', '0'},
			{'1', '0', '1', '1', '1'},
			{'1', '1', '1', '1', '0'},
			{'1', '0', '0', '1', '0'}}),
		4)
	assert(maximalRectangle(
		[][]byte{
			{'1', '0', '1', '0', '0'},
			{'1', '0', '1', '1', '1'},
			{'0', '1', '0', '0', '1'},
			{'1', '0', '0', '1', '0'}}),
		3)
}

func assert(real, expected int) {
	if expected != real {
		panic(fmt.Sprintf("expected=%d, real=%d", expected, real))
	}
	fmt.Printf("ok, expected=real=%d\n", real)
}
