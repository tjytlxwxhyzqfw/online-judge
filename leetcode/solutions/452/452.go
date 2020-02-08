package main

import (
	"fmt"
	"sort"
)

type ItvSlice [][]int

func (s ItvSlice) Len() int           { return len(s) }
func (s ItvSlice) Less(i, j int) bool { return s[i][0] < s[j][0] }
func (s ItvSlice) Swap(i, j int)      { s[i], s[j] = s[j], s[i] }

// M 47.2% 96.23% ???%
// todo: this problem gets much easier when you sort the ballons by its right end
func findMinArrowShots(points [][]int) int {
	if len(points) == 0 {
		return 0
	}
	sort.Sort(ItvSlice(points))

	stack, top := make([]int, len(points)), 0
	stack[top], top = 0, top + 1
	for i := range points {
		for top > 0 && points[stack[top-1]][1] >= points[i][1] {
			top--
		}
		stack[top], top = i, top + 1
	}

	n, i := 0, 0
	for i < top {
		n++
		rght := points[stack[i]][1]
		for ; i < top; i++ {
			if points[stack[i]][0] > rght {
				break
			}
		}
	}

	return n
}

func main() {
	fmt.Println(findMinArrowShots([][]int{}))
	fmt.Println(findMinArrowShots([][]int{{1, 2}}))
	fmt.Println(findMinArrowShots([][]int{{1, 2}, {0, 3}, {-1, 4}}))
	fmt.Println(findMinArrowShots([][]int{{6, 7}, {4, 5}, {2, 3}, {0, 1}}))
	fmt.Println(findMinArrowShots([][]int{{6, 7}, {4, 5}, {2, 3}, {0, 1}}))
	fmt.Println(findMinArrowShots([][]int{{1, 7}, {3, 9}, {4, 5}, {8, 11}}))
}
