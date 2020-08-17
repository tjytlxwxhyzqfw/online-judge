heap_tmpl.go

type heap struct {
	cap, next int
	a         [][]int
}

func newHeap(cap int) *heap { return &heap{cap: cap, next: 1, a: make([][]int, cap+1)} }

func (h *heap) size() int {return h.next-1}
func (h *heap) down(i int) {
	for i*2 < h.next {
		smaller := i * 2
		if smaller+1 < h.next && h.a[smaller+1][0] < h.a[smaller][0] {
			smaller++
		}
		if h.a[i][0] <= h.a[smaller][0] {
			break
		}
		h.a[i], h.a[smaller] = h.a[smaller], h.a[i]
		i = smaller
	}
}

func (h *heap) up(i int) {
	for i > 1 && h.a[i][0] < h.a[i/2][0] {
		h.a[i], h.a[i/2] = h.a[i/2], h.a[i]
		i /= 2
	}
}

func (h *heap) insert(x []int) {
	h.a[h.next] = x
	h.up(h.next)
	h.next++
}

func (h *heap) remove() []int {
	h.next--
	h.a[1], h.a[h.next] = h.a[h.next], h.a[1]
	h.down(1)
	return h.a[h.next]
}
