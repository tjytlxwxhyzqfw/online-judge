// review logs
// -----------
// 20200715: 题目描述: 给定一个字符串列表a=[bat, tab, cat], 从字符串列表中任意取两个元素拼在一起,
//   如果拼完之后是一个回文字符串, 则记下二者位置如(i, j). 返回所有满足条件的(i, j)对.
//   解法思路: 
//      (1) 对于任意一个字符串如s=tab, 如果将tab排在后面, 那么前面的字符串t必须依次消费b->a->t.
//      (2) 如果t消费不完s, 如t=ba, 那么需要判断s剩下的那个前缀"t"是否是回文的.
//      (3) 将所有字符串放入一棵前缀树, 就可以方便地从右向左消费s
//      (4) 对于每一个字符串, 求出其每个前缀是否回文, 就可以方便地判定剩下的字符串前缀是否是一个回文前缀.
package main

import (
	_ "github.com/stretchr/testify/assert" // to add this into god manifest
	"sort"
)

// PalindromePairs
// id=336 ud=1296/149 pass=33 s=33 m=?
func palindromePairs(words []string) [][]int {
	// log.Printf("words: %v", words)
	result1 := solve(words)
	for i := 0; i < len(words); i++ {
		words[i] = reverse(words[i])
	}
	result2 := solve(words)
	for i := 0; i < len(result2); i++ {
		result2[i][0], result2[i][1] = result2[i][1], result2[i][0]
	}
	result := append(result1, result2...)
	if len(result) > 2 {
		sort.Slice(result, func(i, j int) bool {
			if result[i][0] == result[j][0] {
				return result[i][1] < result[j][1]
			}
			return result[i][0] < result[j][0]
		})
		p := 1
		for i := 1; i < len(result); i++ {
			if result[i][0] == result[p-1][0] && result[i][1] == result[p-1][1] {
				continue
			}
			result[p] = result[i]
			p++
		}
		result = result[:p]
	}
	// log.Printf("result: %v", result)
	return result
}

func reverse(s string) string {
	t := []byte(s)
	for i, j := 0, len(t)-1; i < j; i, j = i+1, j-1 {
		t[i], t[j] = t[j], t[i]
	}
	return string(t)
}

func solve(words []string) [][]int {
	table, root := make([][]bool, len(words)), &trieNode{idx: -1}
	for i, w := range words {
		table[i] = prefixPalindromeTable(w)
		root.insert(w, i)
	}
	result := make([][]int, 0)
	for i, _ := range words {
		result = append(result, asSuffix(i, words, table, root)...)
	}
	// log.Printf("\t%v <= %v", result, words)
	return result
}

type trieNode struct {
	idx  int // >= 0 means and end
	next [128]*trieNode
}

func (tn *trieNode) insert(word string, idx int) {
	if len(word) == 0 {
		tn.idx = idx
		return
	}
	if tn.next[word[0]] == nil {
		tn.next[word[0]] = &trieNode{idx: -1}
	}
	tn.next[word[0]].insert(word[1:], idx)
}

func asSuffix(i int, words []string, table [][]bool, root *trieNode) [][]int {
	result, current := make([][]int, 0), root
	for k := len(words[i]) - 1; k >= 0; k-- {
		if current.idx >= 0 && current.idx != i && table[i][k] {
			result = append(result, []int{current.idx, i})
		}
		if current.next[words[i][k]] == nil {
			return result
		}
		current = current.next[words[i][k]]
	}
	if current.idx >= 0 && current.idx != i {
		result = append(result, []int{current.idx, i})
	}
	return result
}

func prefixPalindromeTable(word string) []bool {
	result := make([]bool, len(word))
	for i := 0; i < len(word); i++ {
		j, k := 0, i
		for ; j < k && word[j] == word[k]; j, k = j+1, k-1 {
		}
		result[i] = j >= k
	}
	return result
}

func main() {
	palindromePairs([]string{"a", ""})
	palindromePairs([]string{"abacd", "dc"})
	palindromePairs([]string{"dcaba", "cd"})
	palindromePairs([]string{"a", "aa"})
	palindromePairs([]string{"aab", "a"})
	palindromePairs([]string{})
	palindromePairs([]string{"ab"})
	palindromePairs([]string{"ababa", "a"})
	palindromePairs([]string{"ababa", "a", "ab"})
	palindromePairs([]string{"ababa", "a", "ab", "aba", "abab"})
	palindromePairs([]string{"abcd", "dcba", "lls", "s", "sssll"})
	palindromePairs([]string{"", "abcd", "dcba", "lls", "s", "sssll"})
	palindromePairs([]string{"bat", "tab", "cat"})
}
