package main

import (
	"log"
	"math/rand"
	"sort"
	"time"
)

// 我是先AC了480H, 被评论区提示, 又回来AC的这道题
// 这题比480H要简单一些. 没有删除技巧.

// id=295 pass=45 s=98 m=32 ud=3328/59
type MedianFinder struct {
	left, rght *heap
}

func Constructor() MedianFinder {
	return MedianFinder{
		left: newHeap(1, func(x, y xt) bool { return y < x }),
		rght: newHeap(1, func(x, y xt) bool { return x < y }),
	}

}

func (mf *MedianFinder) AddNum(num int) {
	if num >= int(mf.rght.top()) {
		mf.rght.insert(xt(num))
	} else {
		mf.left.insert(xt(num))
	}
	for mf.left.size() > mf.rght.size() {
		mf.rght.insert(mf.left.remove())
	}
	for mf.rght.size() - mf.left.size() > 1 {
		mf.left.insert(mf.rght.remove())
	}
}

func (mf *MedianFinder) FindMedian() float64 {
	if mf.rght.size() > mf.left.size() {
		return float64(mf.rght.top())
	}
	return float64(mf.left.top() + mf.rght.top()) / 2
}

func (mf *MedianFinder) print2heap(act string) {
	log.Printf("%s: left: top=%g, sz=%d", act, mf.left.top(), mf.left.size())
	log.Printf("%s: rght: top=%g, sz=%d", act, mf.rght.top(), mf.rght.size())
}

type xt int

type heap struct {
	cap, next int
	a         []xt
	lt        func(xt, xt) bool // less than
}

func newHeap(cap int, lt func(xt, xt) bool) *heap {
	h := &heap{
		cap:  cap,
		next: 1,
		a:    make([]xt, cap+1),
		lt: lt,
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
	// double heap size when its full
	if h.next == len(h.a) {
		h.a = append(h.a, h.a...)
		h.cap = len(h.a)
	}
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
	r := rand.New(rand.NewSource(time.Now().UnixNano()))
	var stream []int
	for i := 0; i < 477777; i++ {
		stream = append(stream, r.Intn(100) - 50)
	}
	mf := Constructor()
	for i := 0; i < len(stream); i++ {
		// log.Printf("arr: %#v", stream[:i])
		mf.AddNum(stream[i])
		if i % 100 == 0 {
			log.Printf("i=%d", i)
			assert(mf.FindMedian() == median(stream[:i+1]))
		}
	}
}
