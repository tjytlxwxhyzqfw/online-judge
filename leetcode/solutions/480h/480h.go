package main

import (
	"log"
	"sort"
)

// 我把目前能想到的思路都摆出来, 剩下的我就不想了, 直接看答案:
// 滑动窗口的处理工具: (1) 堆(复杂度太高) (2) 双指针(貌似有解, 但是编码很复杂, 根据以往经验, 多半是错的)
// 中位数的处理工具: 快速排序(复杂度太高)
// 在无思路的时候考虑: 分治(明显不行), 动态规划(找不到点)
// OK, 不挣扎了, 看答案去了...
//
// 补充一下双指针的思路: 维护中间3个或者4个数, 然后每当窗口滑动一次, 仅根据中间3-4个数,
// ***貌似***就能够判定中位数了(吗?)
//
// 2020-11-16 16:46
//
// 1. 跟295有联系 -> 还是得先把前500吃透啊... 慢慢来吧... => 295是个难题, 这个我解了, 但是没有解出来
// 2. c++: multiset
//
// 恰饭研读以下内容:
// Almost the same idea of Find Median from Data Streamhttps://leetcode.com/problems/find-median-from-data-stream/
//
// Use two Heaps to store numbers.
// maxHeap for numbers smaller than current median,
// minHeap for numbers bigger than and equal to current median.
// A small trick I used is always make size of minHeap equal (when there are even numbers)
// or 1 element more (when there are oddnumbers) than the size of maxHeap.
// Then it will become very easy to calculate current median.
//
// Keep adding number from the right side of the sliding window and
// remove number from left side of the sliding window.
// And keep adding current median to the result.
//
//
// 重要经验: 这道题里面的重复数字在我编程的给我带来了极大麻烦, 最终还是为每个数字通过其ID区分其唯一性的!
// (2020-11-18 21:15)
//
// AC, 但是具体别人是怎么实现的双堆平衡, 我还没看呢. 等AC了295之后统一看吧! (2020-11-19 11:00)
//
// 刚才重温了解法, 有人给出了用heap的解法, 直接用了java PriorityQueue的内置remove()方法. 这个方法的复杂度是O(n).
// 所以直接用这种方法的解的复杂度是O(nk), 与暴力解法等同. 而如何才能高效解决remove问题呢? 答案是: TreeSet<>(). 
// 我用O(lgn)的时间复杂度去找最小值, 同时也用O(lgn)的时间复杂度去删除. 
// TreeSet的缺点是无法处理重复元素, 但是不是问题, 当元素值相同的时候, 比较其index就行了. (我的xt数据结构本质上
// 也是这个思路, 只不过别人的思路更简单一些)
// 果然TreeSet才是好方法啊!!! (2020-11-19 14:39)

// id=480 pass=38 ud=1123/91 s=73 m=?
func medianSlidingWindow(nums []int, k int) []float64 {
	if len(nums) == 0 || k == 0 {
		return nil
	}

	w := worker{}
	w.init(2 * len(nums))
	for i := 0; i < k; i++ {
		w.median(xt{val: float64(nums[i]), idx: i}, -1)
		// log.Printf("%g => %v (init)", w.rght.top().val, nums[:i+1])
	}

	result := []float64{w.rght.top().val}
	for i := k; i < len(nums); i++ {
		// log.Printf("=====> i=%d -> %d", i, nums[i])
		result = append(result, w.median(xt{val: float64(nums[i]), idx: i}, i-k))
		// log.Printf("%g => %v", w.rght.top().val, nums[i-k+1:i+1])
		// assert(median(nums[i-k+1:i+1]) == w.rght.top().val)
	}

	return result
}

type worker struct {
	left, rght *heap
	lset, rset map[int]bool
}

func (w *worker) init(cap int) {
	w.left = newHeap(cap, func(x, y xt) bool { return y.val < x.val })
	w.rght = newHeap(cap, func(x, y xt) bool { return x.val < y.val })
	w.lset = map[int]bool{}
	w.rset = map[int]bool{}
}

func (w *worker) median(new xt, del int) float64 {
	if del >= 0 {
		delete(w.lset, del)
		delete(w.rset, del)
	}

	// we must keeps that w.rght.top() >= w.left.top()
	// this is the reason why we compare `new` with rght.top() before we insert
	if new.val >= w.rght.top().val {
		w.rght.insert(new)
		w.rset[new.idx] = true
	} else {
		w.left.insert(new)
		w.lset[new.idx] = true
	}

	// assert(w.left.top().val <= w.rght.top().val)
	// assert(len(w.lset) <= w.left.size())
	// assert(len(w.rset) <= w.rght.size())

	for len(w.lset) > len(w.rset) {
		w.strip(w.left, w.lset)
		top := w.left.remove()
		w.rght.insert(top)
		delete(w.lset, top.idx)
		w.rset[top.idx] = true
	}

	for len(w.rset)-len(w.lset) > 1 {
		w.strip(w.rght, w.rset)
		top := w.rght.remove()
		w.left.insert(top)
		delete(w.rset, top.idx)
		w.lset[top.idx] = true
	}

	w.strip(w.left, w.lset)
	w.strip(w.rght, w.rset)

	if len(w.lset) == len(w.rset) {
		w.rght.insert(xt{
			val: float64(w.left.top().val+w.rght.top().val) / 2,
			idx: -1,
		})
	}

	return w.rght.top().val
}

func (w *worker) strip(h *heap, s map[int]bool) {
	for h.size() > 0 && !s[h.top().idx] {
		h.remove()
	}
}

func (w *worker) print2heap(act string) {
	log.Printf("%s: left: top=%g, sz=%d, ssz=%d", act, w.left.top(), w.left.size(), len(w.lset))
	log.Printf("%s: rght: top=%g, sz=%d, ssz=%d", act, w.rght.top(), w.rght.size(), len(w.rset))
}

type xt struct {
	val float64
	idx int
}

type heap struct {
	cap, next int
	a         []xt
	lt        func(xt, xt) bool // less than
}

func newHeap(cap int, lt ...func(xt, xt) bool) *heap {
	h := &heap{
		cap:  cap,
		next: 1,
		a:    make([]xt, cap+1),
	}
	if len(lt) == 1 {
		h.lt = lt[0]
	}
	return h
}

func (h *heap) size() int { return h.next - 1 }

func (h *heap) down(i int) {
	for i*2 < h.next {
		smaller := i * 2
		if smaller+1 < h.next && h.lt(h.a[smaller+1], h.a[smaller]) {
			smaller++
		}
		if !h.lt(h.a[smaller], h.a[i]) {
			break
		}
		h.a[i], h.a[smaller] = h.a[smaller], h.a[i]
		i = smaller
	}
}

func (h *heap) up(i int) {
	for i > 1 && h.lt(h.a[i], h.a[i/2]) {
		h.a[i], h.a[i/2] = h.a[i/2], h.a[i]
		i /= 2
	}
}

func (h *heap) insert(x xt) {
	h.a[h.next] = x
	h.up(h.next)
	h.next++
}

func (h *heap) remove() xt {
	h.next--
	h.a[1], h.a[h.next] = h.a[h.next], h.a[1]
	h.down(1)
	return h.a[h.next]
}

func (h *heap) top() xt { return h.a[1] }

func assert(x bool) {
	if !x {
		panic("assertion failed")
	}
}

func median(a []int) float64 {
	var b []int
	for i := range a {
		b = append(b, a[i])
	}
	sort.Ints(b)
	if len(b)%2 == 1 {
		return float64(b[len(b)/2])
	}
	return float64(b[len(b)/2-1]+b[len(b)/2]) / 2
}

func main() {
	medianSlidingWindow([]int{100, 0, 1}, 2)
	return
	medianSlidingWindow([]int{1, 2, 3, 4, 2, 3, 1, 4, 2}, 3)
	medianSlidingWindow([]int{0}, 0)
	medianSlidingWindow([]int{1}, 0)
	medianSlidingWindow([]int{1}, 1)
	medianSlidingWindow([]int{6, 6, 6, 6, 6}, 1)
	medianSlidingWindow([]int{6, 6, 6, 6, 6}, 2)
	medianSlidingWindow([]int{6, 6, 6, 6, 6}, 3)
	medianSlidingWindow([]int{6, 6, 6, 6, 6}, 4)
	medianSlidingWindow([]int{6, 6, 6, 6, 6}, 5)
	medianSlidingWindow([]int{1, 3, -1, -3, 5, 3, 6, 7}, 1)
	medianSlidingWindow([]int{1, 3, -1, -3, 5, 3, 6, 7}, 2)
	medianSlidingWindow([]int{1, 3, -1, -3, 5, 3, 6, 7}, 3)
	medianSlidingWindow([]int{1, 3, -1, -3, 5, 3, 6, 7}, 4)
	medianSlidingWindow([]int{1, 3, -1, -3, 5, 3, 6, 7}, 5)
	medianSlidingWindow([]int{1, 3, -1, -3, 5, 3, 6, 7}, 6)
	medianSlidingWindow([]int{1, 3, -1, -3, 5, 3, 6, 7}, 7)
	medianSlidingWindow([]int{1, 3, -1, -3, 5, 3, 6, 7}, 8)
}
