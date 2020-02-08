// 973.go

package main

func main() {
	// 18, 20, 26, 41, 89, 97, 9, 0
	kClosest([][]int{{3, 3}, {5, -1}, {-2, 4}, {4, 5}, {8, 5}, {4, 9}, {0, 3}, {0, 0}}, 1)
	kClosest([][]int{{3, 3}, {5, -1}, {-2, 4}, {4, 5}, {8, 5}, {4, 9}, {0, 3}, {0, 0}}, 2)
	kClosest([][]int{{3, 3}, {5, -1}, {-2, 4}, {4, 5}, {8, 5}, {4, 9}, {0, 3}, {0, 0}}, 3)
	kClosest([][]int{{3, 3}, {5, -1}, {-2, 4}, {4, 5}, {8, 5}, {4, 9}, {0, 3}, {0, 0}}, 4)
	kClosest([][]int{{3, 3}, {5, -1}, {-2, 4}, {4, 5}, {8, 5}, {4, 9}, {0, 3}, {0, 0}}, 5)
	kClosest([][]int{{3, 3}, {5, -1}, {-2, 4}, {4, 5}, {8, 5}, {4, 9}, {0, 3}, {0, 0}}, 6)
	kClosest([][]int{{3, 3}, {5, -1}, {-2, 4}, {4, 5}, {8, 5}, {4, 9}, {0, 3}, {0, 0}}, 7)
	kClosest([][]int{{3, 3}, {5, -1}, {-2, 4}, {4, 5}, {8, 5}, {4, 9}, {0, 3}, {0, 0}}, 8)

	kClosest([][]int{{9, -6}, {-10, 2}, {4, 0}, {5, 8}, {-10, 10}, {-7, 4}, {-2, 6}}, 1)
	kClosest([][]int{{9, -6}, {-10, 2}, {4, 0}, {5, 8}, {-10, 10}, {-7, 4}, {-2, 6}}, 2)
	kClosest([][]int{{9, -6}, {-10, 2}, {4, 0}, {5, 8}, {-10, 10}, {-7, 4}, {-2, 6}}, 3)
	kClosest([][]int{{9, -6}, {-10, 2}, {4, 0}, {5, 8}, {-10, 10}, {-7, 4}, {-2, 6}}, 4)
	kClosest([][]int{{9, -6}, {-10, 2}, {4, 0}, {5, 8}, {-10, 10}, {-7, 4}, {-2, 6}}, 5)
	kClosest([][]int{{9, -6}, {-10, 2}, {4, 0}, {5, 8}, {-10, 10}, {-7, 4}, {-2, 6}}, 6)
	kClosest([][]int{{9, -6}, {-10, 2}, {4, 0}, {5, 8}, {-10, 10}, {-7, 4}, {-2, 6}}, 7)
}

// 973 940/82 s=89 m=?
func kClosest(p [][]int, k int) [][]int {
	a := make([]int, len(p))
	for i, xy := range p {
		a[i] = xy[0]*xy[0] + xy[1]*xy[1]
	}
	qsortK(a, 0, len(a)-1, k-1)
	thresh := a[k-1]
	// fmt.Printf("k=%d, thresh=%d\n", k, thresh)
	result := make([][]int, 0)
	for i, xy := range p {
		if xy[0]*xy[0] + xy[1]*xy[1] <= thresh {
			result = append(result, p[i])
		}
	}
	return result
}

func qsortK(a []int, i, j int, k int) {
	if j - i <= 0 {
		return
	}
	if j - i == 1 {
		if a[i] > a[j] {
			a[i], a[j] = a[j], a[i]
		}
		return
	}

	median3(a, i, j)
	pAt := i + (j-i) / 2
	p := a[pAt]
	// fmt.Printf("i=%d, j=%d, slice=%v\n", i, j, a[i:j+1])

	a[pAt], a[j-1] = a[j-1], a[pAt]
	x, y := i+1, j-2
	for {
		for a[x] < p {
			x++
		}
		for a[y] > p {
			y--
		}
		if x < y {
			a[x], a[y] = a[y], a[x]
			x++
			y--
		} else {
			break
		}
	}
	a[x], a[j-1] = a[j-1], a[x]

	// fmt.Printf("p=%d, x=%d, i=%d, j=%d, slice=%v\n", p, x, i, j, a[i:j+1])

	if k < x {
		qsortK(a, i, x-1, k)
	} else if k > x {
		qsortK(a, x+1, j, k)
	}
}

func median3(a []int, i, j int) {
	k := i + (j-i) / 2
	if a[i] > a[k] {
		a[i], a[k] = a[k], a[i]
	}
	if a[i] > a[j] {
		a[i], a[j] = a[j], a[i]
	}
	if a[k] > a[j] {
		a[k], a[j] = a[j], a[k]
	}
}

