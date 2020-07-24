package main

import (
	"log"
)

// review@20200601: this is a solution that i remove all brackets by brute force. 
//    and there maybe another solution that works: we scan `s` from left to right. 
//    if s[i] == ')', pop and calculate until we popped a ')'.
//    if s[i] == other, just push into the stack.

// id=224 pass=35 ud=1234/118 s=100 m=?
func calculate(s string) int {
	stack := make([]int, 0)
	positive := make([]bool, len(s))
	n, op, ans := 0, '+', 0
	for i, x := range s + " " {
		// not a digit, add n into ans
		if x < '0' || x > '9' {
			if len(stack) > 0 && !positive[stack[len(stack)-1]] {
				n = -n
			}
			if op == '-' {
				n = -n
			}
			// log.Printf("i=%2d, n=%2d", i, n)
			ans += n
			n = 0
		}

		switch x {
		case '+':
			op = '+'
		case '-':
			op = '-'
		case '(':
			if op == '(' {
				positive[i] = positive[stack[len(stack)-1]]
			} else {
				positive[i] = op == '+'
				if len(stack) > 0 && !positive[stack[len(stack)-1]] {
					positive[i] = !positive[i]
				}
				op = '('
			}
			stack = append(stack, i)
		case ')':
			stack = stack[:len(stack)-1]
		case ' ':
			// do nothing
		default:
			// x is supposed to be a digit
			n = n*10 + int(x) - 48
		}
	}

	// log.Printf("%s=%d", s, ans)

	return ans
}

func main() {
	assert(calculate(""), 0)
	assert(calculate("1"), 1)
	assert(calculate("(((1)))"), 1)
	assert(calculate("(((0-1)))"), -1)
	assert(calculate("1+1"), 2)
	assert(calculate("1+2+3+4+5"), 15)
	assert(calculate("0-1-2-3-4-5"), -15)
	assert(calculate("10-((6-1)-(4+1))"), 10)
	assert(calculate("(((5 - (1 - (1 - (1 - (1-1)))))))"), (((5 - (1 - (1 - (1 - (1 - 1))))))))
	assert(calculate("(((5 - (1 + (1 - (1 + (1-1)))))))"), (((5 - (1 + (1 - (1 + (1 - 1))))))))
	assert(calculate("6-((1+1)+(1+1))"), 2)
	assert(calculate("6-((1-1)+(1+1))"), 4)
	assert(calculate("(6-(((1-1)+(1+1))))"), 4)
	assert(calculate("(2-2+5)-(6-(((1-1)+(1+1))))"), 1)
	assert(calculate("(2-(2+5))-(6-(((1-1)+(1+1))))"), -9)
	assert(calculate("((2-2)-(2+5))-(6-(((1-1)+(1+1))))"), -11)
	assert(calculate("((2+2)-(2+5))-(6-(((1-1)+(1+1))))"), -7)
}

func assert(real, expected int) {
	if expected != real {
		log.Fatalf("fatal: expected=%d, real=%d", expected, real)
	}
	log.Printf("ok, expected=real=%d\n", real)
}
