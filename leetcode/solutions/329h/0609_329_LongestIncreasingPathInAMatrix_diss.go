// review logs:
// ------------
//
// 20200710: 题目描述: 给定一个矩阵, 求一个不断上升的路径, 这个路径的最大长度是多少?
//   排除dfs: 复杂度太高; 排除bfs: 处理不了螺旋形的路径; 
//   解法: 将所有元素排序. 对于每个元素x, d[x]表示以x结尾的最长路径. 
//   对于每个元素x, 求其4个邻居ABCD在排序数组中的位置, 如果某个邻居A排在x前面, 那么d[x] = 1 + d[A], 问题解决.
//   实现上, 我们需要对于x, 记录其: (1) x的坐标(以便计算4邻); (2) x在排序数组中的位置b[i_x][j_x] = k_sorted;

package main

import (
	_ "github.com/stretchr/testify/assert" // to add this into god manifest
	"sort"
)

// elem -> original neighbors (y, x)
// map[ori_y][ori_x] -> idx in sorted array
// elem -> (y, x) -> idx_sorted
// id=329 ud=1796/34 pass=42 s=14 m=25
func longestIncreasingPath(a [][]int) int {
	if len(a) == 0 || len(a[0]) == 0 {
		return 0
	}
	arr := make([]elem, len(a)*len(a[0]))
	p := 0
	for y := 0; y < len(a); y++ {
		for x := 0; x < len(a[0]); x++ {
			arr[p], p = elem{val: a[y][x], y: y, x: x}, p+1
		}
	}
	sort.Slice(arr, func(i, j int) bool { return arr[i].val < arr[j].val })

	// i need to know where its 4 neighbors lie in the sorted array
	// so i map the original (y, x) to sorted index
	ori2idx := make([][]int, len(a))
	for j := 0; j < len(ori2idx); j++ {
		ori2idx[j] = make([]int, len(a[0]))
	}
	for i := 0; i < len(arr); i++ {
		ori2idx[arr[i].y][arr[i].x] = i
	}

	dp := make([]int, len(arr))
	dp[0] = 1
	max := dp[0]
	for i := 0; i < len(dp); i++ {
		dp[i] = 1
		// because we know all its 4 neighbors, we are able to avoid scan all elems before i
		for _, nb := range arr[i].neighbors(a) {
			nbi := ori2idx[nb[0]][nb[1]]
			if arr[nbi].val < arr[i].val && dp[nbi] + 1 > dp[i] {
				dp[i] = dp[nbi] + 1
				// log.Printf("update a[%d][%d]=%d -> %d (from a[%d][%d]=%d)",
					// arr[i].y, arr[i].x, arr[i].val, dp[i], arr[nbi].y, arr[nbi].x, arr[nbi].val)
			}
		}
		if dp[i] > max {
			max = dp[i]
		}
	}

	// log.Printf("%d <====== %v", max, a)
	return max
}

type elem struct {
	val  int
	y, x int
}

func (e elem) neighbors(a [][]int) [][]int {
	result := make([][]int, 0)
	for _, d := range [][]int{{1, 0}, {-1, 0}, {0, 1}, {0, -1}} {
		j, i := e.y+d[0], e.x+d[1]
		if 0 <= j && j < len(a) && 0 <= i && i < len(a[0]) {
			result = append(result, []int{j, i})
		}
	}
	return result
}

// tle, just same as my expectation
func dfs(a [][]int, v [][]bool, y, x int, depth int) int {
	v[y][x] = true
	max := depth
	for _, d := range [][]int{{1, 0}, {-1, 0}, {0, 1}, {0, -1}} {
		j, i := y+d[0], x+d[1]
		if j >= 0 && j < len(a) && i >= 0 && i < len(a[0]) && !v[j][i] && a[j][i] > a[y][x] {
			depth1 := dfs(a, v, j, i, depth+1)
			if depth1 > max {
				max = depth1
			}
		}
	}
	v[y][x] = false
	return max
}

func main() {
	longestIncreasingPath([][]int{{}})
	longestIncreasingPath([][]int{{1, 2, 3, 4}})
	longestIncreasingPath([][]int{{4, 3, 2, 1}})
	longestIncreasingPath([][]int{{4}, {3}, {2}})
	longestIncreasingPath([][]int{{9, 9, 4}, {6, 6, 8}, {2, 1, 1}})
	longestIncreasingPath([][]int{{3, 4, 5}, {3, 2, 6}, {2, 2, 1}})
	longestIncreasingPath([][]int{{3, 4, 5}, {3, 7, 6}, {2, 2, 1}})
}
