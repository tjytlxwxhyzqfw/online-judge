// 921.go

package main

import "fmt"

// 921 428/34 s=? m=?
// todo: stack is unnecessary
func main() {
	assertEqual(minAddToMakeValid(""), 0)
	assertEqual(minAddToMakeValid("("), 1)
	assertEqual(minAddToMakeValid("((()(())))"), 0)
	assertEqual(minAddToMakeValid("((()(()))"), 1)
	assertEqual(minAddToMakeValid(")))((("), 6)

	// online
	assertEqual(minAddToMakeValid("())"), 1)
	assertEqual(minAddToMakeValid("((("), 3)
	assertEqual(minAddToMakeValid("()"), 0)
	assertEqual(minAddToMakeValid("()))(("), 4)
}

func assertEqual(real, expected int) {
	hint := fmt.Sprintf("real=%d, expected=%d", real, expected)
	if real != expected {
		panic(hint)
	}
	fmt.Println(hint)
}

func minAddToMakeValid(s string) int {
	stack := &Stack{}
	n := 0
	for _, x := range s {
		if x == '(' {
			stack.Push(byte(x))
		} else {
			if !stack.Empty() {
				stack.Pop()
			} else {
				n++
			}
		}
	}
	return n + stack.Size()
}

type Stack struct {
	arr [1000]byte
	top int
}

func (s *Stack) Push(x byte) {
	s.arr[s.top] = x
	s.top++
}

func (s *Stack) Pop() byte {
	s.top -= 1
	return s.arr[s.top]
}

func (s *Stack) Empty() bool {
	return s.top == 0
}

func (s *Stack) Size() int {
	return s.top
}

