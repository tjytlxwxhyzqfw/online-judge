package main

import (
	"fmt"
	"math"
)

// id=174 pass=29 ud=1080/ s=93 m=?
func calculateMinimumHP(d [][]int) int {
	if len(d) == 0 || len(d[0]) == 0 {
		return 0
	}
	h, w := len(d), len(d[0])

	r := make([][]int, h)
	for i := range r {
		r[i] = make([]int, w)
		for j := range r[i] {
			r[i][j] = math.MaxInt32
		}
	}

	r[h-1][w-1] = max2(1-d[h-1][w-1], 1)

	q := make([][2]int, 0)
	q = append(q, [2]int{h - 1, w - 1})
	for len(q) > 0 {
		y, x := q[0][0], q[0][1]
		q = q[1:]
		// fmt.Printf("%2d, %2d: %d\n", y, x, r[y][x])

		// top
		if y-1 >= 0 {
			if r[y-1][x] == math.MaxInt32 {
				q = append(q, [2]int{y - 1, x})
			}
			r[y-1][x] = min2(r[y-1][x], max2(1, r[y][x]-d[y-1][x]))
		}

		// left
		if x-1 >= 0 {
			if r[y][x-1] == math.MaxInt32 {
				q = append(q, [2]int{y, x - 1})
			}
			r[y][x-1] = min2(r[y][x-1], max2(1, r[y][x]-d[y][x-1]))
		}
	}

	return r[0][0]
}

func max2(x, y int) int {
	if x > y {
		return x
	}
	return y
}

func min2(x, y int) int {
	if x < y {
		return x
	}
	return y
}

func main() {
	d4 := [][]int{{}}
	assert(calculateMinimumHP(d4), 0)

	d3 := [][]int{{-2}}
	assert(calculateMinimumHP(d3), 3)

	d2 := [][]int{{-2, -3, 3}}
	assert(calculateMinimumHP(d2), 6)

	d1 := [][]int{{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}}
	assert(calculateMinimumHP(d1), 7)
}

func assert(real, expect int) {
	if real != expect {
		panic(fmt.Sprintf("real=%d, expect=%d", real, expect))
	}
	fmt.Printf("ok, read=expect=%d\n", real)
}
