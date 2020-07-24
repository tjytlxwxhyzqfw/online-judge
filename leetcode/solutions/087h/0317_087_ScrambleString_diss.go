// 87.go.diss

package  main

import "fmt"

// overview@20200602: 入题思路跟312BurstBalloons挺像的

// 这是一道生成判定类问题: 判定t是不是由s经某种规则生成的.
// 这种问题之前有个思路是: 二者都想办法转化成某种标准模式 -> 在这道题里面行不通
// 直觉1: 树状结构 -> 递归 -> 很快发现复杂度太大不可行
// 直觉2: t就摆在这里, 看着t, 让s去生成t, 那么第一步应该怎么做? -> 这个思路最终帮我解决了问题
//
// 首先要把s划分成两部分, 在哪里划分?
// 假设在i处划分, 如果t是由s生成的, 那么必有t的前i个或者后i个字符与s的前i个字符在组成上完全一致.
// (一旦s在某个地方切开两半, 那么这两半就绝不可能再混在一起)
// 也就是说, 只要观察s和t, 我就能直到s的第一步在哪里切割, 找到字符集一致的位置, 就能确定i.
// 如果没有这样的重叠位置, 那么我们可以轻易论证, 无论从哪里切割s, 都不能生成t, 即二者绝不一致.
//
// 考虑一个特殊情况, s的某个前缀同时匹配t的前缀和后缀, 但是匹配前缀的时候s不能生成t, 只有匹配后缀才能生成t.
// 这时候, 当s前缀匹配t的前缀失败的时候, 不要直接返回失败, 而是要再次去尝试匹配其后缀.
//
// 好一道题目, 搞了整整一个下午, 搞死我了 (20200317周二)

// id=87 ud=406/630 pass=32% s=100 m=?
func isScramble(s, t string) bool {
	// fmt.Printf("isScramble: %s, %s\n", s, t)
	if len(s) != len(t) {
		return false
	}
	if len(s) == 0 {
		return true
	}
	if len(s) == 1 {
		return s[0] == t[0]
	}

	n := split(s, t)
	if n < len(s) && isScramble(s[:n], t[:n]) && isScramble(s[n:], t[n:]) {
		return true
	}

	// reverse
	{
		u := []byte(t)
		for i, j := 0, len(t)-1; i < j; i, j = i+1, j-1 {
			u[i], u[j] = u[j], u[i]
		}
		t = string(u)
	}
	n = split(s, t)
	if n < len(s) && isScramble(s[:n], t[:n]) && isScramble(s[n:], t[n:]) {
		return true
	}

	return false
}

func split(s, t string) int {
	splitAt := len(s)
	cntMap, nPos, nNeg := map[byte]int{}, 0, 0
	for i := 0; i < len(s); i++ {
		if cntMap[s[i]] == 0 {
			nPos++
		} else if cntMap[s[i]] == -1 {
			nNeg--
		}
		cntMap[s[i]]++
		if cntMap[t[i]] == 0 {
			nNeg++
		} else if cntMap[t[i]] == 1 {
			nPos--
		}
		cntMap[t[i]]--
		if nPos == 0 && nNeg == 0 {
			splitAt = i+1
			break
		}
	}

	// fmt.Printf("split: %s & %s at %d\n", s, t, splitAt)
	return splitAt
}

func main() {
	// 这是导致我wa的特殊情况, s的最后一个b应该匹配t的第一个b而不是最后一个b
	assert(isScramble("cccccbbab", "bccaccbcb"), true)
	assert(isScramble("ccccbba", "bccaccb"), false)
	assert(isScramble("", ""), true)
	assert(isScramble("a", "a"), true)
	assert(isScramble("a", "b"), false)
	assert(isScramble("aa", "ab"), false)
	assert(isScramble("ab", "ba"), true)
	assert(isScramble("abb", "bab"), true)
	assert(isScramble("abc", "abc"),true)
	assert(isScramble("abc", "bca"),true)
	assert(isScramble("abc", "cba"),true)
	assert(isScramble("abc", "acb"),true)

	// abc [vs] bac
	// ab | c [vs] ba | c -> ok
	assert(isScramble("abc", "bac"), true)

	// abc [vs] cab
	// abc [vs] cab -> ok
	assert(isScramble("abc", "cab"), true)
	assert(isScramble("great", "rgeat"),true)
	// great -> gr | eat -> rg | eat -> rg | tea -> rg | tae
	assert(isScramble("great", "rgtae"),true)
	assert(isScramble("great", "rgate"),true)

	// abcde [vs] caebd
	// abc | de [vs] cae | bd -> x
	assert(isScramble("abcde", "caebd"),false)
	assert(isScramble("abcd", "abcd"),true)
	assert(isScramble("abcd", "cdab"),true)
	assert(isScramble("abcd", "cdba"),true)
}

func assert(real, expected bool) {
	if expected != real {
		panic(fmt.Sprintf("expected=%v, real=%v", expected, real))
	}
	fmt.Printf("ok, expected=real=%v\n", real)
}
