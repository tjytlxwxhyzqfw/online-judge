// review logs:
// 20200702: 我应该是从来没有看过diss吧? 至今不知道真正牛批的解法是什么
// 20200722: 有个想法: 假设指针位置在i处, 我们维护以下几个变量:
//   (1) 当前滑窗的右端点=i
//   (2) 当前滑窗的左端点=j
//   (3) 上个滑窗内的最大值=max
//   (4) 上个滑窗内最大值出现的位置=maxAt
//   现在, 我们判断一下"i左边第一个比i大的数出现的位置k"
//   依据上述结果, 更新上述4个变量:
//   (1) i=i+1
//   (2) j=j+1
//   (3) 如果k在滑窗内, 那么i=k, 继续找i左边第一个比i大的数出现的位置, 直到出窗为止, max=a[i]
//   (4) maxAt=i
//   找个时间实践一下上述想法.

package main

import (
	"fmt"
	"log"
)

// id=239 pass=41 ud=2827/162 s=54 m=
func maxSlidingWindow(a []int, k int) []int {
	h := newHeap(len(a))
	for i := 0; i < k-1; i++ {
		h.offer(node{idx: i, val: a[i]})
	}
	result := make([]int, 0)
	for i := k-1; i < len(a); i++ {
		h.offer(node{idx: i, val: a[i]})
		left := i-k+1
		for h.peek().idx < left {
			h.poll()
		}
		result = append(result, h.peek().val)
	}
	return result
}

type node struct {
	idx, val int
}

type heap struct {
	a   []node
	end int
}

func (h *heap) size() int {
	return h.end - 1
}

func (h *heap) peek() node {
	return h.a[1]
}

func (h *heap) poll() node {
	top := h.peek()
	h.end--
	h.a[1] = h.a[h.end]
	h.down(1)
	return top
}

func (h *heap) offer(val node) {
	h.a[h.end] = val
	h.end++
	h.up(h.end - 1)
}

func (h *heap) up(i int) {
	p := i / 2
	for p > 0 && h.a[i].val > h.a[p].val {
		h.a[i], h.a[p] = h.a[p], h.a[i]
		i, p = p, p/2
	}
}

func (h *heap) down(p int) {
	c := p * 2
	for c < h.end {
		if c+1 < h.end && h.a[c+1].val > h.a[c].val {
			c++
		}
		if h.a[p].val > h.a[c].val {
			break
		}
		h.a[p], h.a[c] = h.a[c], h.a[p]
		p, c = c, c*2
	}
}

func newHeap(n int) *heap {
	size := 1
	for size < n {
		size <<= 1
	}
	return &heap{a: make([]node, 2*size), end: 1}
}

func main() {
	log.Printf("%v", maxSlidingWindow([]int{}, 1))
	log.Printf("%v", maxSlidingWindow([]int{1}, 1))
	log.Printf("%v", maxSlidingWindow([]int{1, 2, 3}, 1))
	log.Printf("%v", maxSlidingWindow([]int{1, 2, 3}, 2))
	log.Printf("%v", maxSlidingWindow([]int{1, 2, 3}, 3))
	log.Printf("%v", maxSlidingWindow([]int{3, 2, 1}, 1))
	log.Printf("%v", maxSlidingWindow([]int{3, 2, 1}, 2))
	log.Printf("%v", maxSlidingWindow([]int{3, 2, 1}, 3))
	log.Printf("%v", maxSlidingWindow([]int{1, 3, -1, -3, 5, 3, 6, 7}, 3))
}

func assert(real, expected int) {
	if expected != real {
		panic(fmt.Sprintf("fatal: expected=%d, real=%d", expected, real))
	}
	log.Printf("ok, expected=real=%d\n", real)
}
