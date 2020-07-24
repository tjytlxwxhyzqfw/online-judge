// review logs
// -----------
//
// 20200623(read disscuss): it is totally redundent to use a merge sort! in fact you just need to calculate
//     the prefix sum between column i and column j then sort the prefix sum then use the upper bound
//     algorithm to find the best answer.
// 
// 20200715: 

package main

import (
	"fmt"
	_ "github.com/stretchr/testify/assert" // to add this into god manifest
	"log"
	"math"
	"strconv"
)

// id=363 pass=37 ud=755/53 s=152ms m=6.8MB
func maxSumSubmatrix(matrix [][]int, k int) int {
	if len(matrix) == 0 || len(matrix[0]) == 0 {
		return 0
	}
	a := make([]int, len(matrix))
	max := math.MinInt32
	for i := 0; i < len(matrix[0]); i++ {
		for j := 0; j <= i; j++ {
			for row := 0; row < len(matrix); row++ {
				a[row] = 0
				for col := j; col <= i; col++ {
					a[row] += matrix[row][col]
				}
				if row > 0 {
					a[row] += a[row-1]
				}
			}
			result := mergeSort(a, 0, len(a), k)
			if result > max {
				max = result
			}
		}
	}
	// log.Printf("======> %d", max)
	return max
}

func mergeSort(a []int, begin, end int, k int) int {
	if end-begin <= 1 {
		if end-begin == 1 && a[begin] <= k {
			return a[begin]
		}
		return math.MinInt32
	}
	m := begin + (end-begin)/2

	max := math.MinInt32
	if left := mergeSort(a, begin, m, k); left <= k && left > max {
		max = left
	}
	if rght := mergeSort(a, m, end, k); rght <= k && rght > max {
		max = rght
	}

    // rv: right value
	for _, rv := range a[m:end] {
		// rv - target <= k so we have target = rv-k
		targetUpperBound := upperBound(a[begin:m], rv - k)
		ub := begin + targetUpperBound
		result := math.MinInt32
		if ub < m {
			result = rv - a[ub]
		}
		if ub > begin && a[ub-1] == rv-k {
			result = k
		}
		if result > max {
			max = result
		}
	}

	b, p := make([]int, end-begin), 0
	i, j := begin, m
	for i < m && j < end {
		if a[i] < a[j] {
			b[p], p, i = a[i], p+1, i+1
		} else {
			b[p], p, j = a[j], p+1, j+1
		}
	}
	for ; i < m; b[p], p, i = a[i], p+1, i+1 {
	}
	for ; j < end; b[p], p, j = a[j], p+1, j+1 {
	}
	for i := 0; i < len(b); i++ {
		a[begin+i] = b[i]
	}
	return max
}

func upperBound(a []int, x int) int {
	i, j := 0, len(a)
	for i < j {
		k := i + (j-i)/2
		if a[k] <= x {
			i = k+1
		} else {
			j = k
		}
	}
	return i
}

func main() {
	a := [][]int{{28, 4, -19, 18, -7, -10, 27, 19, 1, 16, 0, 10, -17, 11, 11, 27, -1, 10, 12, -1}, {-2, -19, 17, 4, 25, -20, 4, 3, 4, 28, -10, 7, 16, -14, -3, -19, 6, 17, -4, -7}, {2, 8, 18, -17, -2, 10, -6, -5, 11, 10, 22, -6, 19, -16, 6, -4, 18, 5, 22, -17}, {-14, -7, -20, 13, -19, -20, -15, 21, -11, -10, -8, -9, 10, 13, 6, -10, 15, 9, -15, -2}, {-18, 26, 12, 8, 2, 16, -17, 12, 0, -5, 9, -3, -12, -11, 3, -6, -18, 16, -7, -14}, {5, 29, 25, 22, 11, -3, -2, -15, 4, 13, -17, -2, 0, -2, 20, 10, -18, 6, 25, -20}, {5, -7, 8, 5, 15, 22, 8, -5, 22, -18, -5, -14, 23, 2, -8, 12, -16, -18, 12, -12}, {27, 18, 4, 11, -3, 12, -4, -8, -3, 25, -9, 24, -14, 5, 11, -9, -17, 0, 25, -15}, {26, -7, 18, 4, 4, 18, -17, 9, -19, -9, -19, -8, -4, -13, 10, -11, 6, -16, -12, 12}, {28, 22, 7, 11, -6, 13, 8, 22, 7, -14, 17, 14, 10, 29, 16, 9, -3, 18, -9, 10}, {27, 19, -10, -9, 1, 3, 14, 1, 7, 3, -3, 16, -2, 9, 14, -7, -19, -5, 23, 19}, {-17, 7, -20, 8, -5, -6, -2, 25, 29, 16, 23, 4, 4, 27, 16, 17, 9, 20, -6, 22}, {2, 9, -13, 1, -18, 25, 4, 7, 25, 26, -4, 8, -19, 18, 6, -7, -5, 7, 21, -8}, {-2, 11, 1, 29, 6, -16, -8, 3, 7, 11, 10, -2, -1, -20, 20, 4, 19, 5, 29, -7}, {29, -12, -3, 17, 6, 19, 23, 12, -19, 13, 19, 5, 27, 22, -17, 27, 10, -12, 12, 23}, {24, 16, 4, 25, 15, 13, 24, -19, 1, -7, -19, 13, -13, 14, 13, 26, 9, 18, -9, -18}, {-17, 4, -18, -18, -19, 3, -13, 12, 23, -17, -10, -20, 14, 2, 18, 21, -12, 27, -3, 4}, {27, 13, 12, 14, 16, -9, -2, -15, -20, 8, -2, 24, 18, 15, 26, 21, 27, 17, -15, -3}, {25, -8, 17, -10, -16, 13, 26, -11, -15, 6, -5, -13, 23, 2, 24, -4, 5, 8, -15, -1}, {15, -12, 18, 5, -3, 7, 5, 11, -4, -13, 28, 20, 0, -4, -13, -5, -13, -8, -16, 3}}
	assert(maxSumSubmatrix(a, -123), -128)
	assert(maxSumSubmatrix([][]int{{-2}, {-3}, {11}}, 8), 8)
	//  5 -4 -3  4
	// -3 -4  4  5
	//  5  1  5 -4
	assert(maxSumSubmatrix([][]int{{5, -4, -3, 4}, {-3, -4, 4, 5}, {5, 1, 5, -4}}, 8), 8)
	assert(maxSumSubmatrix([][]int{}, 2), 0)
	assert(maxSumSubmatrix([][]int{{1, 0, 1}, {0, -2, 3}}, 2), 2)
	assert(maxSumSubmatrix([][]int{{1, -9, 5}, {6, -1, -1}, {8, 2, 0}, {3, 4, 3}}, 8), 8)
	assert(maxSumSubmatrix([][]int{{1, -9, 5}, {6, -1, -1}, {8, 2, 0}, {3, 4, 3}}, 80), 24)
	assert(maxSumSubmatrix([][]int{{1, -9, 5}, {6, -1, -1}, {8, 2, 0}, {3, 4, 3}}, -8), -8)
	assert(maxSumSubmatrix([][]int{{1, -9, 5}, {6, -1, -1}, {8, 2, 0}, {3, 4, 3}}, -9), -9)
	assert(maxSumSubmatrix([][]int{{1, -9, 5}, {6, -1, -1}, {8, 2, 0}, {3, 4, 3}}, 5), 5)
}

// ------------ 下面是调试工具 ------------------

func assert(a, b int) {
	if a != b {
		panic(fmt.Sprintf("%d != %d", a, b))
	}
}

// returns [y1, x1, y2, x2]
func debug(a [][]int, target int) [4]int {
	for i := 0; i < len(a); i++ {
		for j := i; j < len(a); j++ {
			for k := 0; k < len(a[0]); k++ {
				for l := k; l < len(a[0]); l++ {
					if sum(a, i, j, k, l) == target {
						return [4]int{i, k, j, l}
					}
				}
			}
		}
	}
	panic("cannot sum up to " + strconv.Itoa(target))
}

func sum(a [][]int, i, j, k, l int) int {
	s := 0
	for x := i; x <= j; x++ {
		for y := k; y <= l; y++ {
			s += a[x][y]
		}
	}
	return s
}

func extract(a [][]int, i, j, k, l int) [][]int {
	result := a[i : k+1]
	for t := 0; t < len(result); t++ {
		result[t] = result[t][j : l+1]
	}
	return result
}

// 认真体会吧!
// 我想的是, 左边2个数, 右边两个数, 利用下面的循环, 可以从大到小遍历(右-左)的左右差值
// 结果呢?! 遗漏了-128这个差值, 导致答案变成了-130
// 为什么呢? 右减左有(|left| * |rght|)个差值, 而我的算法仅仅遍历了(|left|+|rght|)个差值
// 操!
// 下面就是一个活生生的例子:
// 我的算法给出的遍历顺序是: -55=120-175, -57=120-177, -130=47-177. -128=47-175就这么被遗漏掉了!
func fuckyou() {
	a := []int{175, 177, /*<-left, rght->*/ 47, 120}
	begin, m, end := 0, 2, len(a)
	k := -123
	max := math.MinInt32
	leftMin, rghtMax := begin, end-1
	for leftMin < m && rghtMax >= m {
		log.Printf("leftMin=%d(%d), rghtMax=%d(%d), delta=%d", leftMin, a[leftMin], rghtMax, a[rghtMax], a[rghtMax]-a[leftMin])
		if d := a[rghtMax] - a[leftMin]; d <= k && d > max {
			max = d
		}
		if leftMin+1 >= m {
			rghtMax--
			continue
		}
		if rghtMax-1 < m {
			leftMin++
			continue
		}

		// 一开始我还把这两个地方写反了. 上面写成了leftMin++, 下面写成了rghtMax--
		// 操了真是!
		// 纯j8脑瘫傻狗一个
		if a[rghtMax-1]-a[leftMin] > a[rghtMax]-a[leftMin+1] {
			rghtMax--
		} else {
			leftMin++
		}
	}
	log.Printf("==============> max=%d", max)
}
