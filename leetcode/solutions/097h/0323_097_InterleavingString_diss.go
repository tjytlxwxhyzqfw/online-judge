// 97.go.diss
// 给定3个字符串, x, y, z. 请问, z是不是由x和y拼出来的?
// 拼接过程必须是: 从x或y的头部取一个, 再从剩下的头部再取一个, 然后再取一个....

package  main

import (
	"fmt"
)

// id=97 pass=30 ud=1179/71

// aabcc
// dbbca
// aadbbcbcac

// aabcc
// dbbca
// aadbbbaccc

//
// aab|c
// a|bad
// aaba(a)bdc
//
// so i can not take chars one by one because if there is a char shows up in both a & b then i am
// not able to determine weather i should take from a or from b;
//
// so how do i know that i can never fix the 'which one' problem ? (so i have to solve this problem
// with a square algorithm

//

func isInterleave(a, b, c string) bool {
	if len(c) != len(a) + len(b) {
		return false
	}
	d := make([][]bool, 1+len(a))
	for i := range d {
		d[i] = make([]bool, 1+len(b))
	}
	d[0][0] = true
	for i := range a {
		d[i+1][0] = c[i] == a[i] && d[i][0]
	}
	for i := range b {
		d[0][i+1] = c[i] == b[i] && d[0][i]
	}
	for i := 1; i <= len(a); i++ {
		for j := 1; j <= len(b); j++ {
			if d[i-1][j] && c[i+j-1] == a[i-1] {
				d[i][j] = true
				continue
			}
			if d[i][j-1] && c[i+j-1] == b[j-1] {
				d[i][j] = true
				continue
			}
			x, y := c[i+j-2], c[i+j-2]
			if d[i-1][j-1] && (x == a[i-1] && y == b[j-1] || x == b[j-1] && y == a[i-1]) {
				d[i][j] = true
			}
		}
	}

	return d[len(a)][len(b)]
}

func main() {
	assert(isInterleave("aabc", "abad", "aabaabdc"), true) // wa
	assert(isInterleave("c", "ca", "cac"), true) // wa
	assert(isInterleave("cc", "cca", "cccac"), true) // wa
	assert(isInterleave("", "", ""), true)
	assert(isInterleave("a", "", "a"), true)
	assert(isInterleave("", "a", "a"), true)
	assert(isInterleave("a", "a", "aa"), true)
	assert(isInterleave("a", "a", "aaa"), false)
	assert(isInterleave("a", "a", "a"), false)
	assert(isInterleave("kkv", "vvk", "kvvkvk"), true)
	assert(isInterleave("aabcc", "dbbca", "aadbbcbcac"), true)
	assert(isInterleave("aabcc", "dbbca", "aadbbbaccc"), false)
}

func assert(real, expected bool) {
	if expected != real {
		panic(fmt.Sprintf("expected=%v, real=%v", expected, real))
	}
	fmt.Printf("ok, expected=real=%v\n", real)
}

// review@0605
// 首先, 都是去一个拼一个取一个拼一个, 跟321(CreateMaximumNumber)有点像.
// 其次, 感觉321比这个难一些? 321的拼那个数字的过程非常复杂. 这个来做这个判定相对比较简单.
// 为什么会有这种差别? 可能是因为, 这是一个判定类问题? (哎? DP也能用来做判定类问题了?)
// 不过这个问题的思路倒是非常通用: 考虑结果字符串, 思考这样一个问题, 它的第一个(或最后一个)字符来自哪个字符串?
// 如果他来自第一个, 哪个dp[1][0]可以拼成结果, 如果它来自第二个, 那么dp[0][1]可以拼成结果.
