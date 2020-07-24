package main

import (
	"fmt"
	"log"
)

// id=233 pass=31 ud=248/566 s=100 m=?
func countDigitOne(n int) int {
	count := 0
	last, mask := 0, 1
	for n >= mask {
		i := n / mask % 10
		// log.Printf("n=%d, mask=%d, i=%d, count=%d, last=%d", n, mask, i, count, last)

		count += i * last
		if i > 1 {
			count += mask
		} else if i == 1 {
			count += n % mask + 1
		}

		last += 9 * last + mask
		mask *= 10
	}
	return count
}

func main() {
	assert(countDigitOne(0), 0)
	assert(countDigitOne(1), 1)
	assert(countDigitOne(7), 1)
	assert(countDigitOne(10), 2)
	assert(countDigitOne(13), 6)
	assert(countDigitOne(25), 13)
	assert(countDigitOne(1000), 301)
	assert(countDigitOne(9999), 4000)
	assert(countDigitOne(31415926), 32328647)
}

func assert(real, expected int) {
	if expected != real {
		panic(fmt.Sprintf("fatal: expected=%d, real=%d", expected, real))
	}
	log.Printf("ok, expected=real=%d\n", real)
}
