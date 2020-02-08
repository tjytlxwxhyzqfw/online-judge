// 926.go

package main

import (
	"fmt"
)

// 926 418/13 s=? m=?
func main() {
	assertEqual(minFlipsMonoIncr("1"), 0)
	assertEqual(minFlipsMonoIncr("0"), 0)
	assertEqual(minFlipsMonoIncr("111"), 0)
	assertEqual(minFlipsMonoIncr("000"), 0)
	assertEqual(minFlipsMonoIncr("000111"), 0)

	assertEqual(minFlipsMonoIncr("0001000"), 1)
	assertEqual(minFlipsMonoIncr("1110111"), 1)

	// online
	assertEqual(minFlipsMonoIncr("00110"), 1)
	assertEqual(minFlipsMonoIncr("010110"), 2)
	assertEqual(minFlipsMonoIncr("00011000"), 2)
}

func assertEqual(real, expected int) {
	hint := fmt.Sprintf("real=%d, expected=%d", real, expected)
	if real != expected {
		panic(hint)
	}
	fmt.Println(hint)
}

// len(s) >= 1
func minFlipsMonoIncr(s string) int {
	left := make([]int, len(s))
	if s[0] == '1' {
		left[0] = 1
	}
	for i := 1; i < len(s); i++ {
		left[i] = left[i-1]
		if s[i] == '1' {
			left[i]++
		}
	}

	rght := make([]int, len(s))
	if s[len(s)-1] == '0' {
		rght[len(s)-1] = 1
	}
	for i := len(s)-2; i >= 0; i-- {
		rght[i] = rght[i+1]
		if s[i] == '0' {
			rght[i]++
		}
	}

	min := rght[0]
	if min > left[len(s)-1] {
		min = left[len(s)-1]
	}
	for i := 0; i < len(s); i++ {
		n := left[i] + rght[i] - 1 // it doesn't matter that s[i] is 0 or 1
		if n < min {
			min = n
		}
	}
	return min
}
