// review logs:
// 20200707: 给定一个长字符串, 删除其所有重复字符, 使得剩余的字符串字典序最小

package main

import (
	_ "github.com/stretchr/testify/assert" // to add this into god manifest
	"log"
	"strings"
)

var result string
var setSize []int
var bkcnt int

// id=316 pass=35 ud=1222/102 s= m=
// todo: use stack
func removeDuplicateLetters(s string) string {
	var arr [26]bool
	var set = map[byte]struct{}{}
	setSize = make([]int, len(s))
	for i := len(s)-1; i >= 0; i-- {
		arr[s[i]-'a'] = true
		set[s[i]] = struct{}{}
		setSize[i] = len(set)
	}
	result = ""
	for i := len(arr)-1; i >= 0; i-- {
		if arr[i] {
			result += string(i + 'a')
		}
	}
	bkcnt = 0
	backtrack("", s, 0)

	log.Printf("%s ======> %s (%d)", s, result, bkcnt)
	return result
}

func backtrack(t, s string, next int) {
	bkcnt++
	// log.Printf("backtrack: s=%s next=%v t=%s", s, next, t)
	if next == len(s) || len(t) == len(result) {
		if len(t) == len(result) && t < result {
			// log.Printf("\tupdate result: %s -> %s", result, t)
			result = t
		}
		return
	}
	if len(t) + setSize[next] < len(result) {
		return
	}
	backtrack(t, s, next+1)
	if !strings.Contains(t, s[next:next+1]) {
		backtrack(t+s[next:next+1], s, next+1)
	}
}


func main() {
	removeDuplicateLetters("")
	removeDuplicateLetters("a")
	removeDuplicateLetters("aaaaaa")
	removeDuplicateLetters("abcdefg")
	removeDuplicateLetters("ddccbbaabbccdd")
	removeDuplicateLetters("dcba")
	removeDuplicateLetters("cbacdcbc")
	removeDuplicateLetters("bcabc")
	removeDuplicateLetters("bcacbcbcb")
	removeDuplicateLetters("rusrbofeggbbkyuyjsrzornpdguwzizqszpbicdquakqws")
}
