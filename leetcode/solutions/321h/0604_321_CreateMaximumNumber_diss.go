// review logs:
// 20200707: 题目描述: 给定两个整数序列a, b, 从a, b中顺序地取出k个数, 组成一个新数, 使之最大, 返回这个最大数.
//    - merge两个序列使结果最大: a = [6 6 5] b = [6 7], 如果我们总是仅比较序列的第一个元素, 并且如果相等就从
//    第一个数组中取的话, 最终merge的结果是: [6 6 6 7 5]; 而最大的结果是: [6 7 6 6 5]; 也就说说, 我们每次
//    选择前, 都要比较整个数组(剩下的元素), 而不是仅仅比较头部.
//    - 衍生题目(中): 给一个数组, 从中顺序取出k个元素, 使这k个元素顺序排列而成的那个数字最大
//    - 论证: 从a中取出x个元素最大化, 同时从b中取出k-x个元素最大化, 那么两个元素merge之后的新数字, 一定是最大的.
//      反证: 给定最终答案aAbBcC (小写从a中取), 如果存在一个非最大的a的子序列(xyz)拼接后比最终答案大, 比如
//      xAyBzC, 那么因为abc > xyz, 显然茅盾. 如果是另一种顺序: xyzABC, 那么根据merge算法, 显然也是不可能的.

package main

import (
	"container/list"
	_ "github.com/stretchr/testify/assert" // to add this into god manifest
)

// id=321 ud=630/214 pass=26 s=? m=?
func maxNumber(a, b []int, k int) []int {
	max := make([]int, 0)
	if k == 0 {
		return max
	}
	for i := 0; i <= k; i++ {
		if len(a) < i || len(b) < k-i {
			continue
		}
		x := selectN(a, i)
		y := selectN(b, k-i)
		z := merge(x, y)
		// log.Printf("i=%d x=%v y=%v z=%v", i, x, y, z)
		if len(max) < k || compare(max, z) < 0 {
			max = z
		}
	}
	// log.Printf("k=%d: %v <= %v %v", k, max, a, b)
	return max
}

func compare(a, b []int) int {
	for i := 0; i < len(a) && i < len(b); i++ {
		if a[i] != b[i] {
			return a[i] - b[i]
		}
	}
	return len(a) - len(b)
}

func selectN(a []int, n int) []int {
	q := list.New()
	for i := 0; i < len(a); i++ {
		for q.Back() != nil && a[i] > q.Back().Value.(int) && q.Len()-1+len(a)-i >= n {
			q.Remove(q.Back())
		}
		if q.Len() < n {
			q.PushBack(a[i])
		}
	}
	result := make([]int, 0, n)
	for p := q.Front(); p != nil; p = p.Next() {
		result = append(result, p.Value.(int))
	}
	return result
}

func merge(a, b []int) []int {
	c := make([]int, len(a)+len(b))
	i, j, p := 0, 0, 0
	for i < len(a) && j < len(b) {
		// log.Printf("a[%d]=%d, b[%d]=%d, p=%d, same=%d", i, a[i], j, b[j], p, same)

		delta := compare(a[i:], b[j:])
		if delta > 0 {
			c[p], p, i = a[i], p+1, i+1
		} else {
			c[p], p, j = b[j], p+1, j+1
		}
	}

	for ; i < len(a); c[p], p, i = a[i], p+1, i+1 {
	}
	for ; j < len(b); c[p], p, j = b[j], p+1, j+1 {
	}
	return c
}

func main() {
	maxNumber([]int{2, 5, 6, 4, 4, 0}, []int{7, 3, 8, 0, 6, 5, 7, 6, 2}, 15) // wa:

	maxNumber([]int{3, 4, 8, 9, 3, 0}, []int{6, 1, 9, 1, 1, 2}, 6) // wa: [9, 9, 3, 1, 2, 0]
	maxNumber([]int{6, 1, 9, 1, 1, 2}, []int{3, 4, 8, 9, 3, 0}, 6) // wa: [9, 9, 3, 1, 2, 0]

	maxNumber([]int{6, 7}, []int{6, 0, 4}, 5) // wrong answer
	maxNumber([]int{6, 0, 4}, []int{6, 7}, 5) // wrong answer

	return

	maxNumber([]int{}, []int{}, 0)
	maxNumber([]int{}, []int{9}, 1)
	maxNumber([]int{9}, []int{}, 1)
	maxNumber([]int{9}, []int{9}, 1)
	maxNumber([]int{9}, []int{9}, 2)

	maxNumber([]int{3, 4, 6, 5}, []int{9, 1, 2, 5, 8, 3}, 1)
	maxNumber([]int{3, 4, 6, 5}, []int{9, 1, 2, 5, 8, 3}, 2)
	maxNumber([]int{3, 4, 6, 5}, []int{9, 1, 2, 5, 8, 3}, 3)
	maxNumber([]int{3, 4, 6, 5}, []int{9, 1, 2, 5, 8, 3}, 4)
	maxNumber([]int{3, 4, 6, 5}, []int{9, 1, 2, 5, 8, 3}, 5)
	maxNumber([]int{3, 4, 6, 5}, []int{9, 1, 2, 5, 8, 3}, 6)
	maxNumber([]int{3, 4, 6, 5}, []int{9, 1, 2, 5, 8, 3}, 7)

	maxNumber([]int{6, 4, 3, 5, 7}, []int{}, 1)
	maxNumber([]int{6, 4, 3, 5, 7}, []int{}, 2)
	maxNumber([]int{6, 4, 3, 5, 7}, []int{}, 3)
	maxNumber([]int{6, 4, 3, 5, 7}, []int{}, 4)
	maxNumber([]int{6, 4, 3, 5, 7}, []int{}, 5)
	maxNumber([]int{6, 4, 3, 5, 7}, []int{9, 9, 9, 9, 9}, 1)
	maxNumber([]int{6, 4, 3, 5, 7}, []int{9, 9, 9, 9, 9}, 2)
	maxNumber([]int{6, 4, 3, 5, 7}, []int{9, 9, 9, 9, 9}, 3)
	maxNumber([]int{6, 4, 3, 5, 7}, []int{9, 9, 9, 9, 9}, 4)
	maxNumber([]int{6, 4, 3, 5, 7}, []int{9, 9, 9, 9, 9}, 5)
}

// dp[i][j] = dp[i][j-1] b[j] => dp x
// search: need two much time
// 3989 -> 3989 -> delete to a more smaller number
// i dont know how to solve the problem with k numbers
// but i knwo how to solve the problem with ALL numbers
// => i got the solution !
//
//
// [3, 4, 6, 5]
// [9, 1, 2, 5, 8, 3]
// first: merge => too completed
// [9, 3, 4, 6, 5, 1, 2, 5, 8, 3]
// [9, 3, 4, 6, 5] <-1
// [9, 4, 6, 5, 1] <-2
// [9, 6, 5, 1, 2] <-5
// [9, 6, 5, 2, 5] <-8
// [9, 6, 5, 5, 8] <-3
// [9, 6, 5, 8, 3]
//
// second: two stacks
// [9] []
// [9, 3] []
// [9, 4] [3]
// [9, 6] [3, 4]
// [9, 6, 5] [3, 4]
// [9, 6, 5, 1] [3, 4]
// [9, 6, 5, 2] [3, 4, 1]
// [9, 6, 5, 5] [4, 4, 1, 2]
// [9, 8] [4, 4, 1, 2, 5, 5, 6]
// [9, 8, 3] [4, 4, 1, 2, 5, 5, 6]
//
// third:
// [9]
// ...
// [9, 3, 4, 6, 5]
// +1 from first
// [9, 4, 6, 5, 1]
// +8 from first
// [9, 8, 4, 6, 5]
// once a new elem added:
// (1) move the new one as forward as possible
// (2) shrink the list (delete the first i which s[i] < s[i+1])
// the question is: we do which first ? -> lead to a mess
//
// ^^^^^^^^ they are all bull shit ^^^^^^^^^
//
// solution: select i from first and select (k-i) from second and merge them
// how to select i elements from a digit array and make the result as large as possible ?
// 哎呀哎呀实在是搞不明白, 傻逼题目草拟吗
// 0605: 最终解: 世界复杂度爆炸, O(n^3)都来了估计, 操
