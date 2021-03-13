package main

import (
	"log"
	"sort"
)

// id=452 ud=1033/47
// idea: 原理一, 第一个气球一定会被burst;
// 	     原理二: 如果大气球套着小气球, 那么直接删掉大气球, 不会对最终答案有任何影响.
//       所以, 所有气球按照尾巴排序, 考虑最后两个气球:
//       如果不想交, 那么burst最后一个. 如果相交, 那么直接删掉头部在前的那个(原理二), 重复之前的步骤.
func findMinArrowShots(b [][]int) int {
	sort.Slice(b, func(i, j int) bool { return b[i][1] < b[j][1] })
	total := 0
	for len(b) >= 2 {
		x, y := b[len(b)-1], b[len(b)-2]
		if x[0] > y[1] {
			total += 1
			b = b[:len(b)-1]
		} else {
			// let x be the one with longer tail
			if y[0] < x[0] {
				x, y = y, x
			}
			b = append(b[:len(b)-2], y)
		}
	}
	total += len(b)

	// log.Printf("==> %d ==> %v", total, b)
	return total
}

func main() {
	assert(findMinArrowShots([][]int{})==0)
	assert(findMinArrowShots([][]int{{1, 2}})==1)
	assert(findMinArrowShots([][]int{{1, 2}, {3, 4}})==2)
	assert(findMinArrowShots([][]int{{1, 2}, {3, 4}, {5, 6}})==3)
	assert(findMinArrowShots([][]int{{1, 2}, {3, 4}, {5, 6}, {0, 6}})==3)
	assert(findMinArrowShots([][]int{{1, 3}, {2, 4}, {3, 5}, {4, 6}})==2)
	assert(findMinArrowShots([][]int{{10, 16}, {2, 8}, {1, 6}, {7, 12}})==2)
}

func assert(b bool) {
	log.Printf("\n---\n")
	if !b {
		panic("assertion error")
	}
}
