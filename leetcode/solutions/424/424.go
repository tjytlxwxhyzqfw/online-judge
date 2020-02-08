package main

import (
	"fmt"
)

// two pointer problem, very hard to me.
// i use about 3 hours to write this solution.
// solve is too complicated.
//
// i haven't read the disscussions (190830), please read discussions for more details.

func solve(a []int, n, k int) int {
	if n == 0 {
		return 0
	}

	max, p, curr := 0, 0, 0
	i := 0
	for j := 0; j < n; j++ {
		// fmt.Printf("i=%d, j=%d, a[j]=%d, curr=%d, max=%d\n", i, j, a[j], curr, max)
		if a[j] > 0 {
			curr += a[j]
			ext := 0
			if i-1 >= 0 {
				ext -= a[i-1]
			}
			if j+1 < n {
				ext -= a[j+1]
			}
			if ext > k-p {
				ext = k - p
			}
			// fmt.Printf("++ ext: %2d, curr: %2d\n", ext, curr)
			if max < curr+ext {
				max = curr + ext
			}
			continue
		}

		// a[j] < 0
		for ; i < j && p-a[j] > k; i++ {
			if a[i] < 0 {
				// fmt.Printf("return(curr): %d -> %d\n", curr, curr-a[i-1]+a[i])
				p, curr = p+a[i], curr+a[i]
				if i-1 >= 0 {
					curr -= a[i-1]
				}
			}
		}
		if i == j {
			curr = 0
			p = 0
		}

		if p-a[j] <= k {
			p, curr = p-a[j], curr-a[j]
			ext := 0
			if i-1 >= 0 {
				ext -= a[i-1]
			}
			if ext > k-p {
				ext = k - p
			}
			if j+1 < n {
				ext += a[j+1]
			}
			// fmt.Printf("+- ext: %2d, curr: %2d\n", ext, curr)
			if max < curr+ext {
				max = curr + ext
			}
		} else {
			if k > max {
				max = k
			}
			if i == j {
				i++
			}
		}
	}

	return max
}

func characterReplacement(s string, k int) int {
	if len(s) == 0 {
		return 0
	}
	max := 0
	a := make([]int, len(s))
	for i := int32(65); i < 91; i++ {
		j := 0
		a[0] = 1
		if int32(s[0]) != i {
			a[0] = -1
		}
		for _, b := range s[1:] {
			if b == i {
				if a[j] > 0 {
					a[j] += 1
				} else {
					j++
					a[j] = 1
				}
			} else {
				if a[j] < 0 {
					a[j] += -1
				} else {
					j++
					a[j] = -1
				}
			}
		}
		if i < -71 {
			for _, x := range a[:j+1] {
				fmt.Printf("%d, ", x)
			}
			fmt.Println()
		}
		curr := solve(a, j+1, k)
		if curr > max {
			max = curr
		}
	}
	return max
}

func main() {
	/**
	fmt.Printf("solve: %d\n", solve([]int{}, 0, 0))                                        // 0
	fmt.Printf("solve: %d\n", solve([]int{13}, 1, 0))                                      // 13
	fmt.Printf("solve: %d\n", solve([]int{13}, 1, 11))                                     // 13
	fmt.Printf("solve: %d\n", solve([]int{1, -1, 1}, 3, 0))                                // 1
	fmt.Printf("solve: %d\n", solve([]int{-1, 1, -1, 2}, 4, 0))                            // 2
	fmt.Printf("solve: %d\n", solve([]int{-20}, 1, 5))                                     // 5
	fmt.Printf("solve: %d\n", solve([]int{-20, 1}, 2, 5))                                  // 6
	fmt.Printf("solve: %d\n", solve([]int{1, -20, 1}, 3, 20))                              // 22
	fmt.Printf("solve: %d\n", solve([]int{1, -20, 1}, 3, 5))                               // 6
	fmt.Printf("solve: %d\n", solve([]int{-1, 20, -1}, 3, 2))                              // 22
	fmt.Printf("solve: %d\n", solve([]int{-1, 20, -1}, 3, 1))                              // 21
	fmt.Printf("solve: %d\n", solve([]int{1, -20}, 2, 5))                                  // 6
	fmt.Printf("solve: %d\n", solve([]int{-20, 1, -20}, 3, 5))                             // 6
	fmt.Printf("solve: %d\n", solve([]int{-20, 1, -5, 1, -20}, 5, 5))                      // 7
	fmt.Printf("solve: %d\n", solve([]int{-20, 1, -4, 1, -20}, 5, 5))                      // 7
	fmt.Printf("solve: %d\n", solve([]int{21, -4, 1, -20}, 4, 5))                          // 27
	fmt.Printf("solve: %d\n", solve([]int{3, -2, 3, -3, 1, -1, 1, -2, 1, -1, 1}, 11, 2))   // 8
	fmt.Printf("solve: %d\n", solve([]int{3, -2, 3, -3, 1, -1, 1, -2, 1, -1, 1}, 11, 200)) // 19
	fmt.Printf("solve: %d\n", solve([]int{3, -2, 3, -1, 3, -2, 3, -3, 1}, 9, 2))           // 8
	**/

	fmt.Printf("answer: %d\n", characterReplacement("", 2))        // 0
	fmt.Printf("answer: %d\n", characterReplacement("AA", 2))      // 2
	fmt.Printf("answer: %d\n", characterReplacement("ABCDE", 2))   // 3
	fmt.Printf("answer: %d\n", characterReplacement("ABCED", 0))   // 1
	fmt.Printf("answer: %d\n", characterReplacement("ABCED", 4))   // 4
	fmt.Printf("answer: %d\n", characterReplacement("ABAB", 2))    // 4
	fmt.Printf("answer: %d\n", characterReplacement("AABABBA", 1)) // 4
}
