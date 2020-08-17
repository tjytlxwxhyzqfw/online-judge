package main

import (
	"log"
)

// id=407 pass= ud=1339/31 pass=42 s= m=
// Trapping Rain Water II
// description: there is a area described by a height map,
//   calculate how much watcher can the area trap after a train.
//
// 20200805: 第一次做这个题, 尝试使用DFS搜索连通域的方法. 结果证明这是错误的.
//   在极端情况下, map呈金字塔形, 这时候每个点都需要logMaxHeight * O(M*N)的复杂度
//   会达到O(M*N*log*M*N)的复杂度, 要寻找其他思路.
// 20200806: 第二次尝试. 算法算了, 去你吗的, 直接看答案了. 垃圾想法就直接忘掉吧, 不要让他们
//   留在脑子里面, 容易把自己的思维搞混乱. 总之, 这道题, 我一直尝试使用dfs搜索单连通域的方法
//   来解决, 但是会有各种各样的问题, 很复杂, 是行不通的.
// 20200813: 这是我看过答案之后给出的解法.
// 	 (1) 将所有边界入堆;
//   (2) 从堆中取出最矮的边界;
//   (3) 将所有挨着最矮边界的格子入堆. 如果碰到一个更矮的格子, 那么:
//     (3.1) 这个更矮的格子不可能盛水比边界还多(<=边界); (3.2) 这个更矮的格子至少能盛边界这么多的水(>=边界)
//     (3.3) 所以它必盛边界那么多的水; (3.4) 修改其高度=边界, 入栈.
//   (4) 回到第(2)步.

func in(y, x int, m [][]int) bool { return 0 <= y && y < len(m) && 0 <= x && x < len(m[0]) }

func trapRainWater(hMap [][]int) int {
	h := newHeap(len(hMap) * len(hMap[0]))
	for y := 0; y < len(hMap); y++ {
		for x := 0; x < len(hMap[0]); x++ {
			if y == 0 || x == 0 || y == len(hMap)-1 || x == len(hMap[0])-1 {
				h.insert(node{y: y, x: x, val: hMap[y][x]})
				hMap[y][x] = -hMap[y][x] - 1
			}
		}
	}
	// log.Printf("h.size()=%d", h.size())

	total := 0
	for h.size() > 0 {
		n := h.remove()
		for _, d := range [][]int{{0, 1}, {0, -1}, {1, 0}, {-1, 0}} {
			y, x := n.y+d[0], n.x+d[1]
			if !in(y, x, hMap) || hMap[y][x] < 0 {
				continue
			}
			if hMap[y][x] < n.val {
				total += n.val - hMap[y][x]
				hMap[y][x] = n.val
			}
			h.insert(node{y: y, x: x, val: hMap[y][x]})
			hMap[y][x] = -hMap[y][x] - 1
		}
	}

	return total
}

type node struct {
	y, x int
	val  int
}

type heap struct {
	cap, next int
	a         []node
}

func newHeap(cap int) *heap { return &heap{cap: cap, next: 1, a: make([]node, cap+1)} }

func (h *heap) size() int { return h.next - 1 }
func (h *heap) down(i int) {
	for i*2 < h.next {
		smaller := i * 2
		if smaller+1 < h.next && h.a[smaller+1].val < h.a[smaller].val {
			smaller++
		}
		if h.a[i].val <= h.a[smaller].val {
			break
		}
		h.a[i], h.a[smaller] = h.a[smaller], h.a[i]
		i = smaller
	}
}

func (h *heap) up(i int) {
	for i > 1 && h.a[i].val < h.a[i/2].val {
		h.a[i], h.a[i/2] = h.a[i/2], h.a[i]
		i /= 2
	}
}

func (h *heap) insert(x node) {
	h.a[h.next] = x
	h.up(h.next)
	h.next++
}

func (h *heap) remove() node {
	h.next--
	h.a[1], h.a[h.next] = h.a[h.next], h.a[1]
	h.down(1)
	return h.a[h.next]
}

func main() {
	graph1 := [][]int{
		{1, 4, 3, 1, 30, 2},
		{3, 2, 1, 11, 2, 11},
		{2, 3, 3, 2, 30, 1}}
	assert(trapRainWater(graph1)==12)
	graph2 := [][]int{
		{1, 1, 1, 1, 1, 1, 1, 1, 1},
		{1, 9, 9, 9, 9, 9, 9, 9, 1},
		{1, 9, 3, 3, 3, 3, 3, 9, 1},
		{1, 9, 3, 9, 9, 9, 3, 9, 1},
		{1, 9, 3, 9, 8, 9, 3, 3, 1},
		{1, 9, 3, 9, 9, 9, 3, 9, 1},
		{1, 9, 3, 3, 3, 3, 3, 9, 1},
		{1, 9, 9, 9, 9, 9, 9, 9, 1},
		{1, 1, 1, 1, 1, 1, 1, 1, 1},
	}
	assert(trapRainWater(graph2)==1)
}

func assert(b bool) {
	log.Printf("\n---\n")
	if !b {
		panic("assertion error")
	}
}
