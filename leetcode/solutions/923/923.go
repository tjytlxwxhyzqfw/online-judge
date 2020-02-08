// 923.go

package main

import (
	"fmt"
	"sort"
)

// 923 260/50 s=? m=?
// 1. sort is unnecessary
// 2. b([]int) is unnecessary
// 3. use count to determine whether or not z(a[k]) exists
func main() {
	assertEqual(threeSumMulti([]int{1, 1, 1}, 3), 1)
	assertEqual(threeSumMulti([]int{1, 1, 1, 1}, 3), 4)
	assertEqual(threeSumMulti([]int{1, 1, 1, 1, 1, 1, 1}, 3), 35)

	// online
	assertEqual(threeSumMulti([]int{1, 1, 2, 2, 3, 3, 4, 4, 5, 5}, 8), 20)
	assertEqual(threeSumMulti([]int{1, 1, 2, 2, 2, 2}, 5), 12)
}

func assertEqual(real, expected int) {
	hint := fmt.Sprintf("real=%d, expected=%d", real, expected)
	if real != expected {
		panic(hint)
	}
	fmt.Println(hint)
}

func threeSumMulti(a []int, t int) int {
	M := int(1e9+7)

	sort.Ints(a)
	b := make([]int, 0)
	c := [101]int{}
	for i := range a {
		c[a[i]]++
		if i == 0 || a[i] != a[i-1] {
			b = append(b, a[i])
		}
	}

	n := 0
	for i := 0; i < len(b); i++ {
		j := i
		if c[b[i]]-1 == 0 {
			j++
		}
		for ; j < len(b); j++ {
			k := j
			if j == i && c[b[j]]-2 == 0 || j != i && c[b[j]]-1 == 0 {
				k++
			}
			for ; k < len(b); k++ {
				if b[i] + b[j] + b[k] == t {
					n = (n + count(b[i], b[j], b[k], c)) % M
					// fmt.Printf("%d=%d+%d+%d (#=%d)\n", t, b[i], b[j], b[k], n)
					break
				}
			}
		}
	}
	return n
}

func count(x, y, z int, c [101]int) int {
	if x == z {
		n := c[x]
		return n * (n-1) * (n-2) / 6
	}
	if x == y {
		n := c[x]
		m := c[z]
		return m * n * (n-1) / 2
	}
	if y == z {
		n := c[y]
		m := c[x]
		return m * n * (n-1) / 2
	}
	return c[x] * c[y] * c[z]
}

