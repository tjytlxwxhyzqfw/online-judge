// 984.go

package main

import "fmt"

// 984 135/201 34% m=_ s=_
func main() {
	fmt.Println(strWithout3a3b(0, 0))
	fmt.Println(strWithout3a3b(1, 0))
	fmt.Println(strWithout3a3b(1, 1))
	fmt.Println(strWithout3a3b(1, 2))
	fmt.Println(strWithout3a3b(1, 3))
	fmt.Println(strWithout3a3b(1, 4))
	fmt.Println(strWithout3a3b(2, 0))
	fmt.Println(strWithout3a3b(2, 1))
	fmt.Println(strWithout3a3b(2, 2))
	fmt.Println(strWithout3a3b(2, 3))
	fmt.Println(strWithout3a3b(2, 4))
	fmt.Println(strWithout3a3b(2, 5))
	fmt.Println(strWithout3a3b(2, 6))
	fmt.Println(strWithout3a3b(3, 0))
	fmt.Println(strWithout3a3b(3, 1))
	fmt.Println(strWithout3a3b(3, 2))
	fmt.Println(strWithout3a3b(3, 3))
	fmt.Println(strWithout3a3b(3, 4))
	fmt.Println(strWithout3a3b(3, 5))
	fmt.Println(strWithout3a3b(3, 6))
	fmt.Println(strWithout3a3b(3, 7))
	fmt.Println(strWithout3a3b(3, 8))
}

func strWithout3a3b(a, b int) string {
	more, less := "a", "b"
	if a < b {
		more, less = less, more
		a, b = b, a
	}

	// #more=a >= #less=b
	d := a - b - 1
	result := ""
	for a > 0 || b > 0 {
		if a > 0 {
			result += more
			a--
		}
		if a > 0 && d > 0 {
			result += more
			a--
			d--
		}
		if b > 0 {
			result += less
			b--
		}
	}

	return result
}
