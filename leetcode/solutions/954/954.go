// 954.go

package main

import (
	"fmt"
	"sort"
)

func main() {
	// true
	fmt.Println(canReorderDoubled([]int{}))
	fmt.Println(canReorderDoubled([]int{1, 2}))
	fmt.Println(canReorderDoubled([]int{0, 0, 0, 0, 1, 2}))
	fmt.Println(canReorderDoubled([]int{1, 2, 4, 8}))
	fmt.Println(canReorderDoubled([]int{1, 2, 4, 8, 16, 32}))
	fmt.Println(canReorderDoubled([]int{2, -2, 4, -4}))

	fmt.Println("---")

	// false
	fmt.Println(canReorderDoubled([]int{3, 1, 3, 6}))
	fmt.Println(canReorderDoubled([]int{2, 1, 2, 6}))
	fmt.Println(canReorderDoubled([]int{1, 2, 4, 16, 8, 4}))
}

// 954 164/32 s=_ m=_
func canReorderDoubled(a []int) bool {
	sort.Slice(a, func(i, j int) bool {
		x, y := a[i], a[j]
		if x < 0 {
			x = -x
		}
		if y < 0 {
			y = -y
		}
		return x < y
	})

	count := map[int]int{}
	for _, x := range a {
		count[x]++
	}

	i, n := 0, len(a)/2
	for n > 0 {
		// fmt.Printf("count: %v\n", count)

		single := a[i]
		i++

		if count[single] == 0 {
			continue
		}
		count[single]--

		doubled := 2 * single
		if count[doubled] == 0 {
			return false
		}
		count[doubled]--

		n--
	}

	return true
}
