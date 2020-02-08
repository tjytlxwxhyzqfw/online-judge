package main

import (
	"fmt"
)

// 462 M 377/31 52% 100% ???%
// quicksort: (1) use i not mid; (2) use i int recursion;
// todo: read discuss
func minMoves2(a []int) int {
	if len(a) == 0 {
		return 0
	}
	nth(a, 0, len(a), len(a)/2)
	fmt.Println(a)
	m, n := a[len(a)/2], 0
	for _, x := range a {
		if x > m {
			n += x - m
		} else {
			n += m - x
		}
	}
	return n
}

func nth(a []int, begin, end int, n int) {
	if end-begin <= 1 {
		return
	}
	if end-begin == 2 {
		if a[begin] > a[end-1] {
			a[begin], a[end-1] = a[end-1], a[begin]
		}
		return
	}

	mid := begin + (end-begin)/2
	if a[begin] > a[mid] {
		a[begin], a[mid] = a[mid], a[begin]
	}
	if a[begin] > a[end-1] {
		a[begin], a[end-1] = a[end-1], a[begin]
	}
	if a[mid] > a[end-1] {
		a[mid], a[end-1] = a[end-1], a[mid]
	}

	a[mid], a[end-2] = a[end-2], a[mid]
	i, j := begin, end-2
	for i < j {
		for {
			i++
			if a[i] >= a[end-2] {
				break
			}
		}
		for {
			j--
			if a[j] <= a[end-2] {
				break
			}
		}
		if i < j {
			a[i], a[j] = a[j], a[i]
		}
	}
	a[i], a[end-2] = a[end-2], a[i]

	// !!!attention!!!: it is i not `mid`
	// !!!attention!!!: nth(a, begin, i, n) not nth(a, begin, i-1, n) !!!
	if n < i {
		nth(a, begin, i, n)
	} else if n > i {
		nth(a, i+1, end, n)
	}
}

func main() {
	fmt.Println(minMoves2([]int{}))                    // 0
	fmt.Println(minMoves2([]int{0}))                   // 0
	fmt.Println(minMoves2([]int{1, 3, 2}))             // 2
	fmt.Println(minMoves2([]int{1, 4, 2, 3}))          // 4
	fmt.Println(minMoves2([]int{1, 8, 1, 1}))          // 7
	fmt.Println(minMoves2([]int{1, 8, 1, 8}))          // 14
	fmt.Println(minMoves2([]int{10, 8, 100, 1, 1, 1})) // 115
}