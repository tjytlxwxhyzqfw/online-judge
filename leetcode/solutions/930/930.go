// 930.go

package main

import (
	"fmt"
)

// 930 315/18 s=87 m=?
func main() {
	assertEqual(numSubarraysWithSum([]int{0}, 0), 1)
	assertEqual(numSubarraysWithSum([]int{0, 0, 0}, 0), 6)
	assertEqual(numSubarraysWithSum([]int{0, 0, 0, 1, 0, 0, 0}, 0), 12)
	assertEqual(numSubarraysWithSum([]int{1}, 1), 1)
	assertEqual(numSubarraysWithSum([]int{0, 0, 1, 0, 0}, 1), 9)
	assertEqual(numSubarraysWithSum([]int{0, 0, 1, 0, 0, 1, 0, 0, 0}, 1), 21)
	assertEqual(numSubarraysWithSum([]int{0, 0, 1, 0, 0, 1, 0, 0, 0}, 0), 12)
	assertEqual(numSubarraysWithSum([]int{0, 0, 1, 0, 0, 1, 0, 0, 0}, 2), 12)
	assertEqual(numSubarraysWithSum([]int{0, 0, 1, 0, 0, 1, 0, 0, 0}, 3), 0)
}

func assertEqual(real, expected int) {
	hint := fmt.Sprintf("real=%d, expected=%d", real, expected)
	if real != expected {
		panic(hint)
	}
	fmt.Println(hint)
}

func numSubarraysWithSum(a []int, s int) int {
	b := make([]int, 0)
	b = append(b, -1)
	for i := range a {
		if a[i] == 1 {
			b = append(b, i)
		}
	}

	if len(b) == 1 {
		if s == 0 {
			return len(a) * (len(a)+1) / 2
		} else {
			return 0
		}
	}

	b = append(b, len(a))

	result := 0
	if s == 0 {
		for i := 1; i < len(b); i++ {
			n := b[i] - b[i-1] - 1
			result += n * (n+1) / 2
		}
	} else {
		for i := 1; i < len(b)-1; i++ {
			j := i + s - 1
			if j < len(b)-1 {
				result += (b[i]-b[i-1]) * (b[j+1]-b[j])
			}
		}
	}

	return result
}
