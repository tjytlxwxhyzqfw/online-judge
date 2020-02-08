// 986.go

package main

import (
	"fmt"
	"math"
)

// 986 672/26 64% m=? s=?
func main() {
	fmt.Printf("%#v\n", intervalIntersection([][]int{}, [][]int{}))
	a := [][]int{{0, 2}, {5, 10}, {13, 23}, {24, 25}}
	b := [][]int{{1, 5}, {8, 12}, {15, 24}, {25, 26}}
	fmt.Printf("%#v\n", intervalIntersection(a, b))
	c := [][]int{{4, 5}, {8, 14}, {16, 19}, {20, 21}, {23, 25}}
	d := [][]int{{1, 2}, {7, 9}, {10, 11}, {13, 17}, {18, 24}}
	// {8, 9}, {10, 11}, {13, 14}, {16, 17}, {18, 19}, {20, 21}, {23, 24}
	fmt.Printf("%#v\n", intervalIntersection(c, d))
}

func intervalIntersection(a [][]int, b [][]int) [][]int {
	i, j := 0, 0
	result := make([][]int, 0)
	for i < len(a) && j < len(b){
		x, y := a[i], b[j]
		left := int(math.Max(float64(x[0]), float64(y[0])))
		rght := int(math.Min(float64(x[1]), float64(y[1])))
		if left <= rght {
			result = append(result, []int{left, rght})
		}
		if x[1] > y[1] {
			j++
		} else {
			i++
		}
	}
	return result
}
