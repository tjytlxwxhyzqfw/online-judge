package main

import (
	"log"
)

// 整体思路: 我们限制s的次数, 但是不限制t的次数. 我们看看, 把s的次数全部用光之后, 我们能得到多少个t.
// 这里有一个非常麻烦的点, 就是我们必须区分: 循环前匹配了多少个t, 循环匹配了多少个t, 以及循环后匹配了多少个t.
// 如果真的把代码的逻辑分成3段去写, 就会巨麻烦, 非常难以实现. 因此, 我们只用一个循环. 当我们发现(s, t)的重复
// loop的时候, 我们计算出一共可以有多少个这样的loop, 进而来利用loop数量直接更新指向t和s的指针(i, j).
// 然后继续循环(相当于后处理). 这样, 当s用完的时候, i指向的就是(t, m)中的一个index, 所以i/len(t)就是t的个数.
// id=466 ud=189/159 pass=28 s=0ms m=?
func getMaxRepetitions(s string, n int, t string, m int) int {
	sTotal := len(s) * n // 字符串(s, n)的长度

	// j%len(s) -> [j, i]: s中的index在(t, m)中最后匹配的位置
	// 这个集合是用来判断是否出现循环的关键. 我对循环的定义是: 如果t[0] = s[j']并且
	// s[j']这个字母上一次匹配到的t中的字母也是0, 那么就出现了循环.
	// 即: (i', j') == (i'', j'') mod(len), 且i' % len(t) == 0
	jSet := map[int][2]int{}
	for i, j := 0, -1; ; i++ {
		// 我们开始这次循环的目的, 是为了给(t, m)[i]寻找在s中的匹配位置.
		// 但是, 如果s已经用完了, 那么(t, m)[i]的意义变得非常隐晦了.
		// 通俗讲, 我们给i的定义是在s中最后被匹配到的那个数字. 现在s用完了, 那么i就必须-1.
		if j >= sTotal {
			i--
		}
		// 如果我们在s中连续寻找了len(s)个字符, 都没找到目标字符, 那么我们就知道, s永不可能匹配t
		numNotMatched := 0
		for j++; j < sTotal; j++ {
			// log.Printf("(%2d, %2d)", i, j)
			if t[i%len(t)] == s[j%len(s)] {
				// log.Printf("matched: %s: (t=%2d, idx=%2d) (s=%2d, idx=%2d) (jSet=%v)",
					// t[i%len(t):i%len(t)+1], i/len(t), i%len(t), j/len(s), j%len(s), jSet)
				break
			}
			numNotMatched++
			if numNotMatched == len(s) {
				return 0
			}
		}
		// s用完了, 我们直接返回答案.
		if j >= sTotal {
			// log.Printf("ans => %d", i/len(t)/m)
			return i / len(t) / m
		}
		// (1) 为了防止重复发现loop而引起意外, 我们发现一个loop之后, 就会把jSet设置成nil
		// (2) 为了发现循环, 我们只会关注t[0]在(s, n)中匹配的位置, 不是t[0]的直接继续循环不管他.
		if jSet == nil || i%len(t) != 0 {
			continue
		}
		// 如果j%len(s)已经匹配过一个t[0]了(在jSet中已经有记录了), 那么现在它又匹配到一个t[0], 说明
		// 循环出现了. 我们利用循环的个数和长度, 直接更新指针
		if last, ok := jSet[j%len(s)]; ok {
			loopLen := j - last[0]
			loopCnt := (sTotal - last[0]) / loopLen // 循环数量=(s, n)中剩下的字符串/循环的长度
			// log.Printf("loop found: (t=%2d, s=%2d): len=%d, cnt=%d\nj: %d -> %d\ni: %d -> %d",
				// last[1]%len(t), last[0]%len(s), loopLen, loopCnt, j, j+(loopCnt-1)*loopLen, i, i+(loopCnt-1)*(i-last[1]))
			j += (loopCnt - 1) * loopLen
			i += (loopCnt - 1) * (i - last[1])
			jSet = nil
		} else {
			jSet[j%len(s)] = [2]int{j, i}
		}
	}
}
// 1902

func main() {
	assert(getMaxRepetitions("aaa", 2, "a", 1) == 6)
	assert(getMaxRepetitions("acb", 4, "ab", 2) == 2)

	assert(getMaxRepetitions("bacaba", 3, "abacab", 1) == 2) // passed
	assert(getMaxRepetitions("baba", 11, "baab", 1) == 7)    // passed
	assert(getMaxRepetitions("a", 0, "a", 1) == 0)
	assert(getMaxRepetitions("a", 0, "a", 7) == 0)
	assert(getMaxRepetitions("aaa", 13, "aaaaa", 7) == 1)
	assert(getMaxRepetitions("aaa", 13, "aaaaa", 1) == 7)


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
