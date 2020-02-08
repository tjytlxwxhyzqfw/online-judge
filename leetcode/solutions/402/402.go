package main

import (
	"bytes"
	"fmt"
)

// todo: diss: this problem can be solved by a stack, and the stack solution is better than mine;

func removeKdigits(num string, k int) string {
	var buffer bytes.Buffer
	buffer.Grow(len(num) - k)

	deleted := make([]bool, len(num))
	i, j := 0, 1
	for k > 0 && j < len(num) {
		for i >= 0 && num[i] > num[j] {
			deleted[i] = true
			k--
			if k == 0 {
				break
			}
			for i >= 0 && deleted[i] {
				i--
			}
		}
		i, j = j, j+1
	}

	if k > 0 {
		for i := len(deleted) - 1; i >= 0; i-- {
			if !deleted[i] {
				deleted[i] = true
				k -= 1
				if k == 0 {
					break
				}
			}
		}
	}

	for i, b := range deleted {
		if !b && (num[i] != '0' || (num[i] == '0' && buffer.Len() > 0)) {
			buffer.WriteByte(num[i])
		}
	}
	if buffer.Len() == 0 {
		buffer.WriteByte('0')
	}
	return buffer.String()
}

func main() {
	fmt.Printf("%v\n", removeKdigits("0", 0))
	fmt.Printf("%v\n", removeKdigits("1", 1))
	fmt.Printf("%v\n", removeKdigits("14365", 2))
	fmt.Printf("%v\n", removeKdigits("14365", 3))
	fmt.Printf("%v\n", removeKdigits("1432219", 3))
	fmt.Printf("%v\n", removeKdigits("10200", 1))
	fmt.Printf("%v\n", removeKdigits("10200", 2))
	fmt.Printf("%v\n", removeKdigits("10200", 3))
	fmt.Printf("%v\n", removeKdigits("10", 1))
	fmt.Printf("%v\n", removeKdigits("4562", 3))
	fmt.Printf("%v\n", removeKdigits("1234567890", 9))
}