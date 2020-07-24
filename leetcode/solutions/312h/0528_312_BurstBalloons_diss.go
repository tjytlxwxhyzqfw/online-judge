package main

import (
	_ "github.com/stretchr/testify/assert" // to add this into god manifest
)

// id=312 pass=50 ud=2188/60 s=90 m=?
//
// feature:
// -------
// (1) after some steps there are n balloons remain and the result of the rest n balloons have no
//     relationship with the paths that we reach n from original balloons. (strong intuition but
//     not very useful for coming up with the final solution)
// (2) what balloon is supposed to be burst first ? the smallest one ? the largest one ? the one
//     at some peak ? the one at some trough ? or the one ... (where i reached)
// (3) or ... we try to burst every balloon first and see what happens (where i don't reached)
// (4) what if we do the same for the last one ? (the final answer)
func maxCoins(a []int) int {
	a = append([]int{1}, append(a, 1)...)
	d := make([][]int, len(a))
	for i := 0; i < len(d); i++ {
		d[i] = make([]int, len(a))
	}
	for i := 1; i < len(d)-1; i++ {
		d[i][i] = a[i-1] * a[i] * a[i+1]
	}

	left, rght := 1, len(d)-2
	for step := 1; step <= rght-left; step++ {
		for i := left; i+step <= rght; i++ {
			for k := i; k <= i+step; k++ {
				score := a[i-1]*a[k]*a[i+step+1] + d[i][k-1] + d[k+1][i+step]
				if score > d[i][i+step] {
					d[i][i+step] = score
				}
			}
		}
	}

	// log.Printf("%2d: <====== %v", d[left][rght], a)
	return d[left][rght]
}

func main() {
	maxCoins([]int{})
	maxCoins([]int{3})
	maxCoins([]int{2, 3})
	maxCoins([]int{3, 1, 5, 8})
	maxCoins([]int{2, 3, 4, 5, 6})
	maxCoins([]int{2, 30, 40, 50, 2})
	maxCoins([]int{3, 1, 4, 1, 5, 9, 2, 6, 5, 3})
}
