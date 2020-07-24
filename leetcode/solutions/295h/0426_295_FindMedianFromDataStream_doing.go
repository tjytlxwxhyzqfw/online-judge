package main

import (
	_ "github.com/stretchr/testify/assert" // to add this into god manifest
	"log"
	"math"
	"sort"
)

// 2 3 4 5 7 6 8 1
// 2 7 1 -> 2
// 1 3 4 5 2 6 8 7
// 1 3 4 5 7 6 8 2
//   i(j)
//
// 1 2 4 5 7 6 8 3
// {4, 6, 3} -> {3, 4, 6}
// 1 2 3 5 7 4 8 6
// 1 2 3 5 7 6 8 4
//       i(j)
// 1 2 3 4 7 6 8 5

// id=295 pass=42 ud=2211/42 s= m=
type MedianFinder struct {
	s []int
}

func Constructor() MedianFinder {
	return MedianFinder{
		s: make([]int, 0),
	}
}

func (r *MedianFinder) AddNum(x int) {
	r.s = append(r.s, x)
}

func (r *MedianFinder) FindMedian() float64 {
	qsortk(r.s, 0, len(r.s), len(r.s)/2)
	m := float64(r.s[len(r.s)/2])
	if len(r.s)&1 == 0 { // even
		max := math.MinInt32
		for i := 0; i < len(r.s)/2; i++ {
			if r.s[i] > max {
				max = r.s[i]
			}
		}
		m = (m + float64(max)) / 2
	}
	return m
}

func qsortk(a []int, begin, end int, k int) {
	// log.Printf("qsort: %v, [%d, %d), k=%d", a, begin, end, k)
	if end-begin <= 1 {
		return
	}
	if end-begin == 2 {
		if a[begin] > a[end-1] {
			a[begin], a[end-1] = a[end-1], a[begin]
		}
		return
	}

	median := begin + (end-begin)/2
	if a[begin] > a[median] {
		a[begin], a[median] = a[median], a[begin]
	}
	if a[begin] > a[end-1] {
		a[begin], a[end-1] = a[end-1], a[begin]
	}
	if a[median] > a[end-1] {
		a[median], a[end-1] = a[end-1], a[median]
	}

	a[median], a[end-1] = a[end-1], a[median]
	i, j := begin+1, end-2
	for i < j {
		for ; a[i] < a[end-1]; i++ {
		}
		for ; a[j] > a[end-1]; j-- {
		}
		if i < j {
			a[i], a[j] = a[j], a[i]
			i++
			j--
		}
	}
	// log.Printf("a[i=%d]=%d, a[j=%d]=%d, a[end-1=%d]=%d", i, a[i], j, a[j], end-1, a[end-1])
	a[end-1], a[i] = a[i], a[end-1]

	// k is a index
	if k < i {
		qsortk(a, begin, i, k)
	} else if k > i {
		qsortk(a, i+1, end, k)
	} else {
		return
	}
}

// 215: just to test qsortk()
func findKthLargest(nums []int, k int) int {
	k = len(nums) - k // there are k nums >= x, so n-k <= x, so x locate at ***index(from 0)*** n-k
	qsortk(nums, 0, len(nums), k)
	return nums[k]
}

func main() {
	// a := []int{2, 3, 4, 5, 7, 6, 8, 1}
	// qsortk(a, 0, len(a), 4)
	// log.Printf("=> %v", a)
	// return

	a := []int{3,2,3,1,2,4,5,5,6,7,7,8,2,3,1,1,1,10,11,5,6,2,4,7,8,5,6}
	qsortk(a, 0, len(a), 7)
	log.Printf("a(qsortk=7): %v", a)
	sort.Slice(a, func(i, j int) bool {return a[i] < a[j]})
	log.Printf("a(#=%d): %v", len(a), a)
	return

	log.Printf("%d", findKthLargest([]int{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4))
	log.Printf("%d", findKthLargest([]int{7, 6, 5, 4, 3, 2, 1}, 1))
	log.Printf("%d", findKthLargest([]int{7, 6, 5, 4, 3, 2, 1}, 2))
	log.Printf("%d", findKthLargest([]int{7, 6, 5, 4, 3, 2, 1}, 6))
	log.Printf("%d", findKthLargest([]int{7, 6, 5, 4, 3, 2, 1}, 7))

	return

	mf := Constructor()
	mf.AddNum(5)
	log.Printf("%v: %g", mf.s, mf.FindMedian())
	mf.AddNum(7)
	log.Printf("%v: %g", mf.s, mf.FindMedian())
	mf.AddNum(6)
	log.Printf("%v: %g", mf.s, mf.FindMedian())
	mf.AddNum(4)
	log.Printf("%v: %g", mf.s, mf.FindMedian())
	mf.AddNum(2)
	log.Printf("%v: %g", mf.s, mf.FindMedian())
	mf.AddNum(3)
	log.Printf("%v: %g", mf.s, mf.FindMedian())
	mf.AddNum(8)
	log.Printf("%v: %g", mf.s, mf.FindMedian())
	mf.AddNum(1)
	log.Printf("%v: %g", mf.s, mf.FindMedian())
	mf.AddNum(0)
	log.Printf("%v: %g", mf.s, mf.FindMedian())
	mf.AddNum(0)
	log.Printf("%v: %g", mf.s, mf.FindMedian())
	mf.AddNum(0)
	log.Printf("%v: %g", mf.s, mf.FindMedian())
	mf.AddNum(0)
	log.Printf("%v: %g", mf.s, mf.FindMedian())
	mf.AddNum(0)
	log.Printf("%v: %g", mf.s, mf.FindMedian())
	mf.AddNum(0)
	log.Printf("%v: %g", mf.s, mf.FindMedian())
	mf.AddNum(0)
	log.Printf("%v: %g", mf.s, mf.FindMedian())
	mf.AddNum(0)
	log.Printf("%v: %g", mf.s, mf.FindMedian())
	mf.AddNum(0)
	log.Printf("%v: %g", mf.s, mf.FindMedian())
	mf.AddNum(0)
	log.Printf("%v: %g", mf.s, mf.FindMedian())
	mf.AddNum(10)
	log.Printf("%v: %g", mf.s, mf.FindMedian())
	mf.AddNum(10)
	log.Printf("%v: %g", mf.s, mf.FindMedian())
	mf.AddNum(20)
	log.Printf("%v: %g", mf.s, mf.FindMedian())
	mf.AddNum(15)
	log.Printf("%v: %g", mf.s, mf.FindMedian())

}
