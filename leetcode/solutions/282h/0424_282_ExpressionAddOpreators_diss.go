package main

import (
	_ "github.com/stretchr/testify/assert" // to add this into god manifest
	"log"
)

// id=282 pass=35 ud=1074/171 s=? m=?
func addOperators(s string, t int) []string {
	result := make([]string, 0)
	if len(s) > 0 {
		search(1, s[0], s[0:1], s, t, &result)
	}
	return result
}

func search(i int, c uint8, exp string, s string, t int, result *[]string) {
	if i == len(s) {
		if eval(exp) == t {
			*result = append(*result, exp)
		}
		return
	}
	search(i+1, s[i], exp+"+"+s[i:i+1], s, t, result)
	search(i+1, s[i], exp+"-"+s[i:i+1], s, t, result)
	search(i+1, s[i], exp+"*"+s[i:i+1], s, t, result)
	if c != '0' {
		search(i+1, c, exp+s[i:i+1], s, t, result)
	}
}

func eval(exp string) int {
	// val, {+, -, *}
	tokens := make([][2]int, 0)
	for i := 0; i < len(exp); i++ {
		if '0' <= exp[i] && exp[i] <= '9' {
			if len(tokens) == 0 || tokens[len(tokens)-1][1] > 0 {
				tokens = append(tokens, [2]int{int(exp[i]) - 48, 0})
			} else {
				top := &tokens[len(tokens)-1]
				(*top)[0] = (*top)[0]*10 + int(exp[i]-48)
			}
		} else {
			tokens = append(tokens, [2]int{0, int(exp[i])})
		}
	}

	suffix := make([][2]int, 0)
	stack := make([]int, 0)
	for i := 0; i < len(tokens); i++ {
		// log.Printf("token[%2d]=%v", i, tokens[i])
		if tokens[i][1] == 0 {
			if i > 0 && tokens[i-1][1] == int('-') {
				tokens[i][0] = -tokens[i][0]
			}
			suffix = append(suffix, tokens[i])
		} else {
			for len(stack) > 0 && (tokens[i][1] != int('*') && stack[len(stack)-1] == int('*')) {
				suffix = append(suffix, [2]int{0, int('*')})
				stack = stack[:len(stack)-1]
			}
			stack = append(stack, tokens[i][1])
		}
	}
	for len(stack) > 0 {
		suffix = append(suffix, [2]int{0, stack[len(stack)-1]})
		stack = stack[:len(stack)-1]
	}

	for _, token := range suffix {
		if token[1] > 0 {
			x, y := stack[len(stack)-2], stack[len(stack)-1]
			stack = stack[:len(stack)-2]
			if token[1] == int('+') || token[1] == int('-') {
				stack = append(stack, x+y)
			} else {
				stack = append(stack, x*y)
			}
		} else {
			stack = append(stack, token[0])
		}
	}

	// log.Printf("eval: %s=%d", exp, stack[0])
	return stack[0]
}

func main() {
	log.Printf("%v", addOperators("", 0))
	log.Printf("%v", addOperators("0", 0))
	log.Printf("%v", addOperators("123", 123))
	log.Printf("%v", addOperators("123", 6))
	log.Printf("%v", addOperators("105", 5))
	log.Printf("%v", addOperators("00", 0))
	log.Printf("%v", addOperators("3456237490", 9191))
	log.Printf("%v", addOperators("19930417", 520))
	log.Printf("%v", addOperators("1234567890", 7))
}
