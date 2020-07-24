// 问题描述: 给一个字符串, 加个前缀, 让它变成一个回文字符串, 求出这个最短的前缀

package main

import (
	"fmt"
)

// review@20200601: the best alogrithm to solve this problem is KMP !!!

// id=214 pass=29 ud=978/106 s=11 m=?
func shortestPalindrome(s string) string {
	end := 1
	for i := len(s); i > 0; i-- {
		if isPalindrome(s[:i]) {
			end = i
			break
		}
	}
	p := s
	for j := end; j < len(s); j++ {
		p = string(s[j]) + p
	}
	return p
}

func isPalindrome(s string) bool {
	i, j := 0, len(s)-1
	for i < j {
		if s[i] != s[j] {
			return false
		}
		i, j = i+1, j-1
	}
	return true
}

func main() {
	assert(shortestPalindrome(""), "")
	assert(shortestPalindrome("a"), "a")
	assert(shortestPalindrome("ab"), "bab")
	assert(shortestPalindrome("abxb"), "bxbabxb")
	assert(shortestPalindrome("bxba"), "abxba")
	assert(shortestPalindrome("abcba"), "abcba")
}

func assert(real, expect string) {
	if real != expect {
		panic(fmt.Sprintf("real=%v, expect=%v", real, expect))
	}
	fmt.Printf("ok, read=expect=%v\n", real)
}
