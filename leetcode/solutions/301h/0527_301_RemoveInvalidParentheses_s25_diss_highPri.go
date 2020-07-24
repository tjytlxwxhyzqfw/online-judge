package main

import (
	_ "github.com/stretchr/testify/assert" // to add this into god manifest
)

var finalRightCnt, targetLen int
var resultSet map[string]struct{}

// id=301 ud=2385/97 pass=42 s=25 m=
func removeInvalidParentheses(s string) []string {
	var left, rght, other int
	for _, c := range s {
		if c == '(' {
			left++
		} else if c == ')' {
			if rght < left {
				rght++
			}
		} else {
			other++
		}
	}

	finalRightCnt, targetLen = rght, 2*rght+other
	// log.Printf("s=%s, finalRightCnt=%d, targetLen=%d", s, finalRightCnt, targetLen)
	resultSet = map[string]struct{}{}
	backtrack("", s, 0, 0, 0)

	results := make([]string, 0)
	for k, _ := range resultSet {
		results = append(results, k)
	}
	// log.Printf("%s ======> %v", s, results)
	return results
}

func backtrack(t, s string, left, rght int, next int) {
	// log.Printf("bt: left=%2d, rght=%2d, next=%2d, s=%s, t=%s", left, rght, next, s, t)
	if len(t) == targetLen || next == len(s) {
		if rght == finalRightCnt && rght == left {
			// log.Printf("\tbt: add result: %s", t)
			resultSet[t] = struct{}{}
		}
		return
	}
	if s[next] == ')' {
		backtrack(t, s, left, rght, next+1)
		if rght < left {
			backtrack(t+")", s, left, rght+1, next+1)
		}
	} else if s[next] == '(' {
		backtrack(t+"(", s, left+1, rght, next+1)
		backtrack(t, s, left, rght, next+1)
	} else {
		backtrack(t+string(s[next]), s, left, rght, next+1)
	}
}

// (a)())()
// (a)()() - (a())()
func main() {
	removeInvalidParentheses("")
	removeInvalidParentheses("(")
	removeInvalidParentheses("(a")
	removeInvalidParentheses("a)")
	removeInvalidParentheses("a)(")
	removeInvalidParentheses("()a)(")
	removeInvalidParentheses("a())(")
	removeInvalidParentheses("a)()(")
	removeInvalidParentheses("(a)()")
	removeInvalidParentheses("(a))(")
	removeInvalidParentheses("(((")
	removeInvalidParentheses(")")
	removeInvalidParentheses(")))")
	removeInvalidParentheses("()")
	removeInvalidParentheses(")(")
	removeInvalidParentheses("()()()")
	removeInvalidParentheses("(a)())()")
}
