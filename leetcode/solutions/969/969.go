// 969.go

package main

import (
	"fmt"
	"math"
)

func main() {
	fmt.Println(pancakeSort([]int{}))
	fmt.Println(pancakeSort([]int{1}))
	fmt.Println(pancakeSort([]int{1, 1, 1}))
	fmt.Println(pancakeSort([]int{1, 2, 3}))
	fmt.Println(pancakeSort([]int{3, 2, 1}))
	fmt.Println(pancakeSort([]int{4, 5, 8, 5, 4, 9, 0, 3, 0}))
}

// 969 252/308 s=_ m=_
func pancakeSort(a []int) []int {
	p := NewPancakeSorter(a)
	result := make([]int, 0)
	for range a {
		x, y := p.StepOn()
		result = append(result, x+1)
		result = append(result, y+1)
		p.Print()
	}
	return result
}

type PancakeSorter struct {
	arr  []int
	size int

	stack []int
	max   int
}

func NewPancakeSorter(arr []int) *PancakeSorter {
	return &PancakeSorter{
		arr:   arr,
		size:  len(arr),
		stack: make([]int, len(arr)),
	}
}

func (p *PancakeSorter) StepOn() (int, int) {
	max, maxAt := math.MinInt32, -1
	top := 0
	for i := 0; i < p.size; i++ {
		p.stack[top] = p.arr[i]
		top++
		if p.arr[i] >= max {
			max, maxAt = p.arr[i], i
		}
	}

	i := 0
	for top > 0 {
		if p.stack[top-1] != max {
			p.arr[i] = p.stack[top-1]
			i++
			top--
		} else {
			break
		}
	}
	for j := 0; j < top; j++ {
		p.arr[i] = p.stack[j]
		i++
	}

	p.size--
	return maxAt, p.size
}

func (p *PancakeSorter) Print() {
	for i := 0; i < p.size; i++ {
		fmt.Printf("%d ", p.arr[i])
	}
	fmt.Printf("| ")
	for i := p.size; i < len(p.arr); i++ {
		fmt.Printf("%d ", p.arr[i])
	}
	fmt.Printf("(size=%d)\n", p.size)
}
