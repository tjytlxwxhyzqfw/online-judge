package main

import (
	"log"
	"math"
)

// 题目: Think about Zuma Game. You have a row of balls on the table, colored red(R), yellow(Y), blue(B),
// green(G), and white(W). You also have several balls in your hand. Each time, you may choose a
// ball in your hand, and insert it into the row (including the leftmost place and rightmost place).
// Then, if there is a group of 3 or more balls in the same color touching, remove these balls.
// Keep doing this until no more balls can be removed.
//
// Find the minimal balls you have to insert to remove all the balls on the table. If you cannot
// remove all the balls, output -1.
//
// 我的想法:
// 像这种过程一步一步的过程, 我之前做过类似的题目(todo: which one?)
// 我们完全可以根据最后一步, 将这个题目一分为二, 在执行最后一步之前, 被最后一步分开的两个子问题是互不干扰的.
// 我坚信这道题目的最终解法肯定是这个思路. (这个"坚信"失败了啊- -|)
//
// 对于这个题目, 它很不好分, 我的想法是这样的:
// 考虑上述过程进行到最后一步时, 场上留下的局面, 一共有以下4中情况:
// 1. 还剩唯一一个球X => 分割成两个子问题
// 2. 剩两个球: XX  => 3个子问题
// 3. 还剩3个球: XXX => 4个子问题
// 4. 还剩4个球: XXXX <- XXOXX, 删除O之后留下的局面 => 5个子问题
// 根据鸽笼原理, 应该不会有连剩5个球的局面
// 所以, 我们的解法是:
// 从左到右遍历, 对于每个球X, 分别考虑上述4中情况, 并解题(剩多个球时, X占据最左边球的位置)
// 上述复杂度太高! 应该是不对的, 自我否定了...
//
// 其他想法:
// 1. 动态规划: => 哎? 貌似可行啊!!! => 思路跟上面差不多, 能给出一个O(n^2)的解法啊!!!
// ===> 哎呀好复杂啊, 想晕了, 真的好难想啊, 不想了, 看答案去了.....
//
// ------ 看完答案之后 ------
//
// 答案: 直接DFS ...
// 操了, 明天再想, 今天真的不浪费脑子了, 妈的
//
// 其实, 在"按照最后一个元素分割"以及"DP"这两个思路先后失败之后, 我就应该已经能够意识到, 这个题的复杂度会很高.
// 甚至应该意识到, 这是个DFS问题了. 失策, 失策...
//
// (2020-11-25 15:47)
//
// 泥马, 找DFS都找不到然我放心的解法, 感觉复杂度好高啊...
// 另外, 还要花时间花精力写一个比较复杂的shrink算法: 消除一排小球中的所有三连
// 唔...明天直接看别人的代码了, 不费时间了
//
// (2020-11-26 16:05)
//
// 这道题目已经超出出题人的控制范围了, 讨论区里面, 有好多AC的解都存在反例.
// 我下面这个解会在某些-1的情况下TLE. 除非有好的剪枝策略(目前没有在评论区看到)
// 否则, 很难再优化了.
//
// 这个题目值得记住的地方有两个:
// 1. DFS的解题思路
// 2. Shrink()算法
//
// (2020-12-03 15:23)

// id=488 pass=39 s= m= ud=239/240
func findMinStep(board string, hand string) int {
	board = board + "#"
	count := map[byte]int{}
	for i := range hand {
		count[hand[i]]++
	}
	n := math.MaxInt32
	dfs([]byte(board), count, 0, &n)
	if n == math.MaxInt32 {
		n = -1
	}
	log.Printf("%s -> %d", board, n)
	return n
}

func dfs(row []byte, hand map[byte]int, depth int, result *int) {
	// log.Printf("dfs: %v", string(row))
	if depth >= *result {
		return
	}
	row = shrink(row)
	if len(row) == 1 {
		*result = depth
		return
	}
	for i := 0; i < len(row); i++ {
		p, s := row[:i], row[i:]
		need := 2
		if i+1 < len(row) && row[i] == row[i+1] {
			need = 1
		}
		for k, v := range hand {
			if v - need < 0 {
				continue
			}
			hand[k] = v - need
			insert := string(k)
			if need == 2 {
				insert += string(k)
			}
			row1 := []byte(string(p) + insert + string(s))
			dfs(row1, hand, depth+need, result)
			hand[k] = v
		}
	}
}

// this is a hard procedure that is too difficult for me to come up with such a simple solution
// discuss: recursive  <= it's a brilliant idea !!!
// (2020-12-02 16:03)
func shrink(row []byte) []byte {
	for i, j := 0, 0; j < len(row); j++ {
		if row[j] == row[i] {
			continue
		}
		if j-i >= 3 {
			return shrink(append(row[:i], row[j:]...))
		}
		i = j
	}
	return row
}

func main() {
	assert(findMinStep("RRWWRRBBRR", "WB") == 2)
	return
	assert(findMinStep("BRWGWYY", "YGBWY") == -1)
	assert(findMinStep("", "") == 0)
	assert(findMinStep("WWW", "") == 0)
	assert(findMinStep("", "YRB") == 0)
	assert(findMinStep("WRRBBW", "RB") == -1)
	assert(findMinStep("WWRRBBWW", "WRBRW") == 2)
	assert(findMinStep("G", "GGGGG") == 2)
	assert(findMinStep("RBYYBBRRB", "YRBGB") == 3)
	assert(findMinStep("RRWWRRBBRR", "W") == -1)
	assert(findMinStep("RRWWRRBBBRWR", "W") == 0)
}

func assert(x bool) {
	if !x {
		panic("assertion failed")
	}
}
