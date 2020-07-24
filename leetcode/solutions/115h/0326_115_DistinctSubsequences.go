// 115.go

// 问题描述: 给定一个字符串s和一个字符串t. 请问, s中有多少个和t相等的子串?

package main

import "fmt"

// id=115 pass=37 ud=996/49 s=38 m=?
//
// abb | cde
// ab| c
//
// dp[i][j] = dp[k1][j-1] + dp[k2][j-1] + ... + dp[kn][j-1], where s[ki] == t[j]
// -> this is a signal to indicate that you can solve this problem with dp
//
// *** most problems with two strings and non-obvious solutions can be solved with either tow-pointers or dp ***
func numDistinct(s, t string) int {
	dp := make([][]int, 1+len(s))
	for i := range dp {
		dp[i] = make([]int, 1+len(t))
	}

	dp[0][0] = 1
	for i := 0; i <= len(s); i++ {
		dp[i][0] = 1
	}
	for j := 1; j <= len(t); j++ {
		dp[0][j] = 0
	}

	for i := 1; i <= len(s); i++ {
		for j := 1; j <= len(t); j++ {
			dp[i][j] = dp[i-1][j]
			if s[i-1] == t[j-1] {
				dp[i][j] += dp[i-1][j-1]
			}
		}
	}

	return dp[len(s)][len(t)]
}

func main() {
	assert(numDistinct("", ""), 1)
	assert(numDistinct("", "a"), 0)
	assert(numDistinct("a", ""), 1)
	assert(numDistinct("a", "a"), 1)
	assert(numDistinct("a", "b"), 0)
	assert(numDistinct("abcd", "abcd"), 1)
	assert(numDistinct("aaa", "a"), 3)
	assert(numDistinct("abbc", "abc"), 2)
	assert(numDistinct("abbccd", "abcd"), 4)
	assert(numDistinct("rabbbit", "rabbit"), 3)
	assert(numDistinct("babgbag", "bag"), 5)
}

func assert(real, expected int) {
	if expected != real {
		panic(fmt.Sprintf("expected=%d, real=%d", expected, real))
	}
	fmt.Printf("ok, expected=real=%d\n", real)
}

// review@0605
// 隐约发现一个规律, 那就是, 几个序列, 在不改变原序列顺序的情况下, 进行重排, 重组织, 重选(子串)之类的操作.
// 有的时候, 这个操作也可能是顺序无关的, 比如087(ScrambleString)等
