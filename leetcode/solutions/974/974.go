// 974.go

package main

import "fmt"

func main() {
	assertEqual(subarraysDivByK([]int{}, 5), 0)
	assertEqual(subarraysDivByK([]int{5}, 5), 1)
	assertEqual(subarraysDivByK([]int{5, 5, 5, 5}, 5), 10)
	assertEqual(subarraysDivByK([]int{-5, -5, -5, -5}, 5), 10)
	assertEqual(subarraysDivByK([]int{0, 0, 0, 0, 0}, 5), 15)
	assertEqual(subarraysDivByK([]int{5, 0, 5, 0}, 5), 10)
	assertEqual(subarraysDivByK([]int{2, 3}, 5), 1)
	assertEqual(subarraysDivByK([]int{-2, -3}, 5), 1)
	assertEqual(subarraysDivByK([]int{4, 5, 0, -2, -3, 1}, 5), 7)
}

func assertEqual(real, expected int) {
	if real == expected {
		fmt.Printf("ok, %v = %v\n", real, expected)
		return
	}
	panic(fmt.Sprintf("real=%d, expected=%d", real, expected))
}

// 974 s=78 m=? 544/42 47%
func subarraysDivByK(a []int, k int) int {
	m := map[int]int{0: 1}
	s, c := 0, 0
	for _, x := range a {
		s += x
		r := s % k
		if r == 0 {
			c += m[0]
		} else {
			t1, t2 := r, r-k
			if r < 0 {
				t2 = k + r
			}
			fmt.Printf("s=%d, t1=%d, t2=%d\n", s, t1, t2)
			c += m[t1] + m[t2]
		}
		m[r]++
	}
	return c
}
