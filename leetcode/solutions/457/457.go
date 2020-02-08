package main

import (
	"fmt"
)

// M 28% 100% ???%(2MB) 
// disscuss: in the 2nd loop (for n > 0), you can just set all elements to 0, 
// this makes thing easier than mine original solution.

func circularArrayLoop(a []int) bool {
	for i := range a {
		if a[i] == 0 {
			a[i] = 100000
			continue
		}
		sign, j, n := a[i], i, 0
		for a[j] < 1001 {
			a[j], n = a[j]+10000, n+1 // a[j] has been visited
			k := ((j+a[j]-10000)%len(a) + len(a)) % len(a)
			// fmt.Printf("%2d -> %2d    (i=%d, a[i]=%d)\n", j, k, i, a[i])
			if k == j {
				break
			}
			j = k
			if a[j] < 1001 {
				if a[j]*sign <= 0 {
					break
				}
			} else if a[j] < 11001 {
				return true
			} else {
				break
			}
		}
		j = i
		for n > 0 {
			a[j], j, n = a[j]+90000, ((j+a[j]-10000)%len(a)+len(a))%len(a), n-1
		}
	}
	return false
}

func main() {
	fmt.Println(circularArrayLoop([]int{}))                  // false
	fmt.Println(circularArrayLoop([]int{0}))                 // false
	fmt.Println(circularArrayLoop([]int{1}))                 // false
	fmt.Println(circularArrayLoop([]int{1, 1}))              // true
	fmt.Println(circularArrayLoop([]int{2, 1}))              // false
	fmt.Println(circularArrayLoop([]int{2, 2}))              // false
	fmt.Println(circularArrayLoop([]int{2, -1, 1}))          // true
	fmt.Println(circularArrayLoop([]int{2, -1, 1, 2, 2}))    // true
	fmt.Println(circularArrayLoop([]int{-1, 2}))             // false
	fmt.Println(circularArrayLoop([]int{-2, 1, -1, -2, -2})) // false
}
