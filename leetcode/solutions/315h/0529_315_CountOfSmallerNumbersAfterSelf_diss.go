// review logs
// -----------
//
// 20200606: the common solution of this problem should be same with 327(CountOfRangeSum) => merge sort
// 20200723: (1) 线段树(✅) (2) 归并排序(✅) (3) TreeMap+Ceil&Floor: 这个解法貌似是不可行的吧? 

package main

import (
	_ "github.com/stretchr/testify/assert" // to add this into god manifest
	"sort"
)

// id=315 pass=41 ud=2116/76 s=60 m=
// [5 2 6 1]
func countSmaller(a []int) []int {
	ivs := make([]iv, len(a))
	for i, v := range a {
		ivs[i] = iv{i: i, v: v}
	}
	sort.Slice(ivs, func(i, j int) bool { return ivs[i].v < ivs[j].v })

	// b[original index] = sorted index
	b := make([]int, len(a))
	for i := len(ivs)-1; i >= 0; i-- {
		if i+1 < len(ivs) && ivs[i].v == ivs[i+1].v {
			b[ivs[i].i] = b[ivs[i+1].i]
		} else {
			b[ivs[i].i] = i
		}
	}

	c := make([]int, len(a))
	root := build(0, len(a))
	for i := len(a) - 1; i >= 0; i-- {
		j := b[i] // original index -> sorted index
		c[i] = root.query(j)
		// log.Printf("%2d: ori=%d sorted=%d smaller=%d", a[i], i, j, c[i])
		root.addOne(j+1, len(a))
	}

	// log.Printf("%v ======> %v", a, c)
	return c
}

type iv struct {
	i int
	v int
}

type node struct {
	begin, end int
	val        int
	left, rght *node
}

func build(begin, end int) *node {
	if begin >= end {
		return nil
	}
	if begin+1 == end {
		return &node{begin: begin, end: end}
	}
	middle := begin + (end-begin)/2
	return &node{
		begin: begin,
		end:   end,
		left:  build(begin, middle),
		rght:  build(middle, end),
	}
}

func (n *node) addOne(begin, end int) {
	// log.Printf("addOne: [%d, %d) in node[%d, %d)", begin, end, n.begin, n.end)
	if begin >= end {
		return
	}
	if n.begin == begin && n.end == end {
		n.val++
		return
	}
	if end <= n.left.end {
		n.left.addOne(begin, end)
	} else if begin >= n.rght.begin {
		n.rght.addOne(begin, end)
	} else {
		n.left.addOne(begin, n.left.end)
		n.rght.addOne(n.rght.begin, end)
	}
}

func (n *node) query(i int) int {
	if n.begin == i && n.end-n.begin == 1 {
		return n.val
	}
	if n.left.begin <= i && i < n.left.end {
		return n.val + n.left.query(i)
	} else {
		return n.val + n.rght.query(i)
	}
}

func main() {
	countSmaller([]int{})
	countSmaller([]int{1})
	countSmaller([]int{1, 2, 3})
	countSmaller([]int{1, 2, 3, 4})
	countSmaller([]int{2, 1})
	countSmaller([]int{5, 4, 3, 2, 1})
	countSmaller([]int{3, 3, 3})
	countSmaller([]int{1, 2, 3, 3, 3, 4, 4, 5})
	countSmaller([]int{5, 4, 4, 3, 3, 3, 2, 2, 1})

	countSmaller([]int{5, 2, 6, 1})
	countSmaller([]int{3, 1, 4, 1, 5, 9, 2, 6, 5, 3})
	countSmaller([]int{3, 1, 4, 1, 5, 9, 2, 6, 5, 2, 7, 1, 8, 2, 8, 3})
}
