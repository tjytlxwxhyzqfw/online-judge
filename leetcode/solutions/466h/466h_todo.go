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
		// if i > len(s) && sn > len(t) {
			// return 0
		// }
		for j := range t {
			log.Printf("??? i=%d, j=%d, firstMatchedSi=%d, lastMatchedSi=%d", i, j, firstMatchedSi, lastMatchedSi)
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

/*

466. Count The Repetitions
C++ solution inspired by @70664914 with organized explanation
7.6K 53 Last Edit: October 9, 2018 12:18 AM

You can find more of my code/explanations in my github repo lzl124631x/LeetCode

It's easy to come up with a brute force solution and to find that there will be a repetitive pattern
when matching S2through S1. The only problem is how to use the repetitive pattern to save
computation.

Fact: If s2 repeats in S1 R times, then S2 must repeats in S1 R / n2 times.
Conclusion: We can simply count the repetition of s2 and then divide the count by n2.

How to denote repetition:
We need to scan s1 n1 times. Denote each scanning of s1 as an s1 segment.
After each scanning of i-th s1 segment, we will have:
- The accumulative count of s2 repeated in this s1 segment.
- A nextIndex that s2[nextIndex] is the first letter you'll be looking for
  in the next s1 segment.

Suppose s1="abc", s2="bac", nextIndex will be 1; s1="abca", s2="bac", nextIndex will be 2
It is the nextIndex that is the denotation of the repetitive pattern.

Example:

Input: s1="abacb", n1=6 s2="bcaa", n2=1
Return: 3

                             0 1    2 3 0      1    2 3 0      1    2 3 0
S1 -------------->          abacb | abacb | abacb | abacb | abacb | abacb
repeatCount ----->             0  |   1   |   1   |   2   |    2  |   3
Increment of repeatCount ->    0  |   1   |   0   |   1   |    0  |   1
nextIndex ------->             2  |   1   |   2   |   1   |    2  |   1
                                              ^
	|	repetitive pattern found here (we've met 2 before)!
		The pattern repeated 3 times

The nextIndex has s2.size()possible values, ranging from 0 to s2.size() - 1.
Due to PigeonHole principle, you must find two same nextIndex after scanning
s2.size() + 1 s1 segments.

Once you meet a nextIndex you've met before, you'll know that the following nextIndexs and
increments of repeatCount will repeat a pattern.

So let's separate the s1 segments into 3 parts:

- the prefix part before repetitive pattern
- the repetitive part
- the suffix part after repetitive pattern (incomplete repetitive pattern remnant)

All you have to do is add up the repeat counts of the 3 parts.

// OJ: https://leetcode.com/problems/count-the-repetitions/
// Author: github.com/lzl124631x
// Time: O(|s1| * n1) where |s1| is the length of s1
// Space: O(n1)
class Solution { public: int getMaxRepetitions(string s1, int n1, string s2, int n2) { vector<int> repeatCount(n1 + 1, 0); vector<int> nextIndex(n1 + 1, 0); int j = 0, cnt = 0; for (int k = 1; k <= n1; ++k) { for (int i = 0; i < s1.size(); ++i) { if (s1[i] == s2[j]) { ++j; if (j == s2.size()) { j = 0; ++cnt; } } } repeatCount[k] = cnt; nextIndex[k] = j; for (int start = 0; start < k; ++start) { if (nextIndex[start] == j) { // see if you have met this nextIndex before // if found, you can calculate the 3 parts int prefixCount = repeatCount[start]; // prefixCount is the start-th repeatCount // (repeatCount[k] - prefixCount) is the repeatCount of one occurrance of the pattern // There are (n1 - start) / (k - start) occurrances of the pattern // So (n1 - start) / (k - start) * (repeatCount[k] - prefixCount) is the repeatCount of the repetitive part int patternCount = (n1 - start) / (k - start) * (repeatCount[k] - prefixCount); // The suffix contains the incomplete repetitive remnant (if any) // Its length is (n1 - start) % (k - start) // So the suffix repeatCount should be repeatCount[start + (n1 - start) % (k - start)] - prefixCount int suffixCount = repeatCount[start + (n1 - start) % (k - start)] - prefixCount; return (prefixCount + patternCount + suffixCount) / n2; } } } return repeatCount[n1] / n2; } };

Update 12/31/2018

int patternCount = (repeatCount[k] - repeatCount[start]) * (n1 - start) / (k - start); to
int patternCount = (repeatCount[k] - repeatCount[start]) * ((n1 - start) / (k - start)); since a * b / c doesn't necessarily equal a * (b / c). (the old test cases didn't cover this case)

The size of repeatCount and nextIndex should be n1 + 1.
Thanks for comments from @wxd_sjtu, @rjtsdl, @Rongch

Another version using unordered_map to save computation. Reduce runtime from ~80ms to ~4ms.

// OJ: https://leetcode.com/problems/count-the-repetitions/
// Author: github.com/lzl124631x
// Time: O(|s1| * n1) where |s1| is the length of s1
// Space: O(n1)
class Solution { public: int getMaxRepetitions(string s1, int n1, string s2, int n2) { unordered_map<int, int> kToRepeatCount; unordered_map<int, int> nextIndexToK; kToRepeatCount[0] = 0; nextIndexToK[0] = 0; int j = 0, cnt = 0; for (int k = 1; k <= n1; ++k) { for (int i = 0; i < s1.size(); ++i) { if (s1[i] == s2[j]) { ++j; if (j == s2.size()) { j = 0; ++cnt; } } } if (nextIndexToK.find(j) != nextIndexToK.end()) { int start = nextIndexToK[j]; int prefixCount = kToRepeatCount[start]; int patternCount = (n1 - start) / (k - start) * (cnt - prefixCount); int suffixCount = kToRepeatCount[start + (n1 - start) % (k - start)] - prefixCount; return (prefixCount + patternCount + suffixCount) / n2; } kToRepeatCount[k] = cnt; nextIndexToK[j] = k; } return kToRepeatCount[n1] / n2; } };

评: 这个解法也是用了类似于最小公倍数的解法. 不过这个题目的最小公倍数相当难找(我就找错了).
作者定义了一个量: nextIndex, 这个量的意义是: 表示当t把s消费完了之后, t还有所剩余, 那么剩下的第一个字母的index.
t[nextIndex]同时也是我们要在**下一个**s中去寻找的第一个字母.
那么, 当我们遇到了重复的nextIndex的时候, 我们就知道, 最小公倍数来了.

TODO: 把我的想法修正一下, 应该是可以过这道题目的. 代码也可以再写的更加好看一些. 思路也更清楚一些.

*/
