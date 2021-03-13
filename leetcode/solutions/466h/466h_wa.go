package main

import (
	"log"
)

// 题目描述: (s, n)表示字符串s重复n次形成的新字符串, 如("ab", 2)="abab"
// 现在给定(s, n)和(t, m), 请问(s, n)可以覆盖多少个(t, m) ?
// 覆盖: 把x个(t, m)排成一排, 形成的新字符串是(s, n)的子串
//
// 反正我就交这一次, 我说下我的思路, 不行的话我就直接看答案了.
// 思路就是: 我要找到两个数字: sn和tn, 其中sn个s恰好能够覆盖tn个t.
// 一旦我找到了这样的sn和tn, 那么我就能计算出最终答案:
// (s, n)中有 (n/sn) 个整的(s, sn), 能覆盖(n/sn)*tn个t
// 此外, (s, n)中还剩余了(s, n%sn)这样一个零头, 假设这个零头可以覆盖x个t
// 所以(s, n)总共覆盖了: ((n/sn)*tn + x)个t, 那么(s, n)就能够覆盖 ((n/sn)*tn+x)/m)个(t, m)
//
// 隐患: 我用了两层for循环来计算s和t的最小公倍数. 尤其是外层循环没有添加限制.
//   有没有可能外层循环执行太多次导致TLE? 比如出现(sn=1亿07, tn=1亿09)的情况? => 感觉貌似不太可能?
//
// id=466 ud=189/159 pass=28 s= m=
func getMaxRepetitions(s string, n int, t string, m int) int {
	// the next time we encountered firstMatchedSi in s and we are matching t[0],
	// we know that a loop has been found.
	firstMatchedSi := firstGE(t[0], 0, s)

	// we can use exactly #sn s to cover #tn t
	sn, tn := 0, 0
	lastMatchedSi := -1

	// we can cover #covers[i] t with #i s
	covers := []int{0}

	for i := 0; ; i++ {
		if i > len(s) && sn > len(t) {
			return 0
		}
		for j := range t {
			// log.Printf("??? i=%d, j=%d", i, j)
			lastMatchedSi = firstGE(t[j], lastMatchedSi+1, s)
			if lastMatchedSi == -1 {
				return 0
			}
			if lastMatchedSi >= len(s) {
				lastMatchedSi -= len(s)
				sn++
				covers = append(covers, i)
			}
			// log.Printf("match: %c: s[%d](%d) <=> t[%d](%d)", t[j], lastMatchedSi, sn, j, i)
			// a loop has been found
			if lastMatchedSi == firstMatchedSi && j == 0 && i > 0 {
				tn = i
				break
			}
		}
		if tn > 0 {
			break
		}
	}
	if tn == 0 {
		// log.Printf("?????")
		return 0
	}

	log.Printf("sn=%d, tn=%d, covers=%v", sn, tn, covers)
	return ((n/sn) * tn + covers[(n%sn)]) / m
}

func firstGE(c byte, ge int, s string) int {
	result := -1
	for i := range s {
		if s[i] == c && i >= ge {
			return i
		}
		if s[i] == c {
			if i >= ge {
				return i
			}
			if result == -1 {
				result = len(s) + i
			}
		}
	}
	return result
}

func main() {
	// 这个例子出错了. 按照我目前的解法, 在这个例子上, 会出现无限循环.
	// 我试图去修正我的答案 (最多有len(s)个t和len(t)个s), 但是无效, 因为这个答案是2.
	getMaxRepetitions("bacaba", 3, "abacab", 1)
	return
	assert(getMaxRepetitions("baba", 11, "baab", 1) == 7)
	assert(getMaxRepetitions("a", 0, "a", 1) == 0)
	assert(getMaxRepetitions("a", 0, "a", 7) == 0)
	assert(getMaxRepetitions("aaa", 13, "aaaaa", 7) == 1)
	assert(getMaxRepetitions("aaa", 13, "aaaaa", 1) == 7)
	assert(getMaxRepetitions("acb", 4, "ab", 2) == 2)
	assert(getMaxRepetitions("abc", 12, "abc", 1) == 12)
	assert(getMaxRepetitions("abc", 12, "abc", 4) == 3)
	assert(getMaxRepetitions("ab", 12, "abab", 4) == 1)
	assert(getMaxRepetitions("ab", 12, "abab", 2) == 3)
}

func assert(b bool) {
	log.Printf("\n---\n")
	if !b {
		panic("assertion error")
	}
}
