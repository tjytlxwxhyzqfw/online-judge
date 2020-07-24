package main

import (
	"log"
	"sort"
)

// review@20200601: i cannot understand my solution.
// but there is another idea: sort all points. at every point store we a list of ranges starting
// from that point. then we scan the points from left to right and at point i:
// 1. offer all ranges starting from i into a max-heap (sorted by height)
// 2. delete from the heap until its top contains the current point i then we know that at point i
//    the height is h[i].
// post-process the points and get the final answer


// id=218 pass=33 ud=1787/100 s=51 m=?
func getSkyline(bs [][]int) [][]int {
	if len(bs) == 0 {
		return make([][]int, 0)
	}

	points := make([]*point, 0)
	for _, b := range bs {
		if b[2] == 0 || b[0] >= b[1] {
			continue
		}
		points = append(points,
			&point{idx: b[0], left: []int{b[2]}, rght: make([]int, 0)},
			&point{idx: b[1], left: make([]int, 0), rght: []int{b[2]}})
	}
	sort.Slice(points, func(i, j int) bool { return points[i].idx < points[j].idx })

	// deduplicate
	p := 0
	for i := 1; i < len(points); i++ {
		if points[i].idx == points[p].idx {
			points[p].left = append(points[p].left, points[i].left...)
			points[p].rght = append(points[p].rght, points[i].rght...)
		} else {
			p++
			points[p] = points[i]
		}
	}
	points = points[:p+1]

	skyline := make([][]int, 0)
	top, h, m := 0, newHeap(len(bs)), map[int]int{}
	for _, p := range points {
		// log.Printf("idx=%2d, val=%4d, top=%2d", p.idx, p.val, top)
		for _, v := range p.left {
			h.offer(v)
		}
		for _, v := range p.rght {
			m[v]++
		}
		for h.size() > 0 && m[h.peek()] > 0 {
			m[h.peek()]--
			h.poll()
		}
		newTop := 0
		if h.size() > 0 {
			newTop = h.peek()
		}
		if newTop != top {
			skyline = append(skyline, []int{p.idx, newTop})
			top = newTop
		}
	}

	return skyline
}

type heap struct {
	a   []int
	end int
}

func (h *heap) size() int {
	return h.end - 1
}

func (h *heap) peek() int {
	return h.a[1]
}

func (h *heap) poll() int {
	top := h.peek()
	h.end--
	h.a[1] = h.a[h.end]
	h.down(1)
	return top
}

func (h *heap) offer(val int) {
	h.a[h.end] = val
	h.end++
	h.up(h.end - 1)
}

func (h *heap) up(i int) {
	p := i / 2
	for p > 0 && h.a[i] > h.a[p] {
		h.a[i], h.a[p] = h.a[p], h.a[i]
		i, p = p, p/2
	}
}

func (h *heap) down(p int) {
	c := p * 2
	for c < h.end {
		if c+1 < h.end && h.a[c+1] > h.a[c] {
			c++
		}
		if h.a[p] > h.a[c] {
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
	return &heap{a: make([]int, 2*size), end: 1}
}

type point struct {
	idx        int
	left, rght []int
}

func main() {
	getSkyline([][]int{})

	var bs, skyline [][]int

	skyline = getSkyline([][]int{{1, 2, 3}})
	log.Printf("skeyline1: %v", skyline)

	bs = [][]int{{0, 2, 3}, {1, 2, 3}, {2, 5, 3}, {2, 6, 3}, {2, 7, 3}, {2, 8, 3}}
	skyline = getSkyline(bs)
	log.Printf("skyline: %v", skyline)

	bs = [][]int{{0, 2, 3}, {2, 5, 3}}
	skyline = getSkyline(bs)
	log.Printf("skyline: %v", skyline)

	bs = [][]int{{6, 7, 13}, {2, 9, 10}, {4, 7, 15}, {5, 7, 15}, {6, 7, 15}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}}
	skyline = getSkyline(bs)
	log.Printf("skeyline: %v", skyline)
}
