// 424.go
// 我的思路有问题, 不需要heap, 直接遍历26个字母就行了, 见总结.
// id=424 ud= pass= s= m=
func characterReplacement(s string, k int) int {
 c := make([]int, 26)
 h := newHeap(2*len(s))
 max := 0
 repl := 0
 i := 0
 for j := 0; j < len(s); j++ {
  id := int(s[j]-65)
  c[id]++
  h.insert([]int{id, c[id]})
  for h.a[1][1] != c[h.a[1][0]] {
   h.remove()
  }
  repl = j-i+1-h.a[1][1]
  for repl > k {
   idi := int(s[i]-65)
   c[idi]--
   i++
   h.insert([]int{idi, c[idi]})
   for h.a[1][1] != c[h.a[1][0]] {
    h.remove()
   }
   repl = j-i+1-h.a[1][1]
  }
  if j-i+1 > max {
   max = j-i+1
  }
 }
 return max
}

type heap struct {
 cap, next int
 a         [][]int
}

func newHeap(cap int) *heap { return &heap{cap: cap, next: 1, a: make([][]int, cap+1)} }

func (h *heap) size() int {return h.next-1}
func (h *heap) down(i int) {
 for i*2 < h.next {
  larger := i * 2
  if larger+1 < h.next && h.a[larger+1][1] > h.a[larger][1] {
   larger++
  }
  if h.a[i][1] >= h.a[larger][1] {
   break
  }
  h.a[i], h.a[larger] = h.a[larger], h.a[i]
  i = larger
 }
}

func (h *heap) up(i int) {
 for i > 1 && h.a[i][1] > h.a[i/2][1] {
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