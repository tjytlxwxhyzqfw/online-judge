package main

import (
	"fmt"
	"sort"
)


// describe my idea with mathematical induction:
// let q be the final order.
// init: sort by heights for the group of people whose k = 0, and copy them to q
// for k = n:
//       1. sort group[n] by heights
//       2. for each people p in group[n], we insert p at q[i] where:
//				(m(i-1) == n && m(i) > n) or (i == current length of q) (**insertion rule**)
//				m(i) is the number of people whose h >= p.h and index < p.index
//
// prove:
//
// when k = 0, the rules are satisfied
//
// assume that the rules are satisfied when k = n, when k = n+1, we are inserting
// (h'[n+1], k'[n+1]), let be the queue:
//
// => before insertion: (*, *), (h[n], k[n]), (h[n+1], k[n+1]), (#, #)
// => after insertion: (*, *), (h[n], k[n]), (h'[n+1], k'[n+1]), (h[n+1], k[n+1]), (#, #)
//
// in q(after), we already know that (*, *)s are good to us, if we can prove that (#, #)s are also
// good to us, then the correction of this idea is proved.
//
// for (h[n+i], k[n+i]) (i >= 1):
//  if (h[n+i] > h'[n+1], we are good
//  if (h[n+i] <= h'[n+1]
//		m(n+i) in q(before) is smaller than or equals to k[n+i] (it cannot be greater than k[n+i])
//		if m(n+i) is smaller than k[n+i], we are good too
//		if m(n+i) == k[n+i] then k[n+i] is smaller than or equal to k'[n+1] (k is sorted before insertion):
//			=> if k[n+i] == k'[n+1] we must have h'[n+1] > h[n+i] (because group[n] has bee sorted
//			by heights), this is impossible because we already have h[n+i] <= h'[n+1]
// 			=> if k[n+i] < k'[n+1], we have m(n+i) == k[n+i] < k'[n+1] in q(before), but we already
// 			have h'[n+1] >= h[n+i], then we know that (h', k') must be inserted after
// 			(h[n+i], k[n+i]) (**insertion rule**) which is impossible too because we have already
// 			assumed that (h', k') is inserted before (h[n+i], k[n+i])
//
// so all cases where the rules might be broken are all impossible thus the rules are satisfied when
// k = (n+1), then the correction of my idea is validated.
//
func reconstructQueue(people [][]int) [][]int {
	k2a := make([][]int, len(people))
	for _, p := range people {
		h, k := p[0], p[1]
		k2a[k] = append(k2a[k], h)
	}

	ans := make([][]int, len(people))
	p := 0
	for k, a := range k2a {
		// fmt.Printf("k=%d, #a=%d\n", k, len(a))
		sort.Ints(a)
		if k == 0 {
			for _, x := range a {
				ans[p] = []int{x, 0}
				p++
			}
			continue
		}
		for _, x := range a {
			// fmt.Printf("\t(x=%d, k=%d), p=%d\n", x, k, p)
			i, q := 0, 0
			for j, y := range ans[:p] {
				if y[0] >= x {
					i++
					if i > k {
						q = j
						break
					}
				}
			}
			if q == 0 {
				q = p
			}

			// printA(ans, 0, p, "\tbefore copy")
			copy(ans[q+1:p+1], ans[q:p])
			ans[q] = []int{x, k}
			p++
			// printA(ans, 0, p, "\tafter copy")
		}
	}

	return ans
}

func printA(a [][]int, i, j int, tag string) {
	fmt.Printf("%s: ", tag)
	for _, p := range a[i:j] {
		fmt.Printf("(%d,%d), ", p[0], p[1])
	}
	fmt.Println()
}

func main() {
	ans1 := reconstructQueue([][]int{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}})
	printA(ans1, 0, len(ans1), "ans1")

	ans2 := reconstructQueue([][]int{})
	printA(ans2, 0, len(ans2), "ans2")

	ans3 := reconstructQueue([][]int{{1, 0}})
	printA(ans3, 0, len(ans3), "ans3")

	ans4 := reconstructQueue([][]int{{1, 0}, {2, 0}})
	printA(ans4, 0, len(ans4), "ans4")

	ans5 := reconstructQueue([][]int{{1, 1}, {2, 0}})
	printA(ans5, 0, len(ans5), "ans5")

	ans6 := reconstructQueue([][]int{{8, 0}, {5, 1}, {7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 2}, {5, 2}})
	printA(ans6, 0, len(ans6), "ans6")
}
