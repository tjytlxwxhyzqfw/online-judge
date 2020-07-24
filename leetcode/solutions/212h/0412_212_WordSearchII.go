// 问题描述: 给一个字母矩阵, 然后再给一个字符串列表, 问, 字符串列表中, 有多少个字符串来自矩阵中的某个路径?

package main

import (
	"fmt"
	"log"
)

type trieNode struct {
	End *string
	B   [26]*trieNode // branches
}

func trieInsert(root *trieNode, s string) {
	p := root
	for _, b := range s {
		idx := b - 97
		if p.B[idx] == nil {
			p.B[idx] = &trieNode{}
		}
		p = p.B[idx]
	}
	p.End = &s
}

var Ds = [][2]int{{-1, 0}, {1, 0}, {0, -1}, {0, 1}}

// id=212 pass=32 ud=1936/92 s=46 m=?
func findWords(b [][]byte, w []string) []string {
	trie := &trieNode{}
	for _, s := range w {
		trieInsert(trie, s)
	}
	result := make([]string, 0)
	for i := range b {
		for j := range b[0] {
			dfs(i, j, b, trie.B[b[i][j]-97], &result)
		}
	}
	return result
}

func dfs(y, x int, b [][]byte, root *trieNode, result *[]string) {
	if root == nil {
		return
	}

	if root.End != nil {
		*result = append(*result, *root.End)
		root.End = nil
	}

	ori := b[y][x]
	b[y][x] = '.'
	for _, d := range Ds {
		i, j := y+d[0], x+d[1]
		if !inBoard(i, j, b) || b[i][j] == '.' {
			continue
		}
		dfs(i, j, b, root.B[b[i][j]-97], result)
	}
	b[y][x] = ori
}

func inBoard(y, x int, b [][]byte) bool {
	return 0 <= y && y < len(b) && 0 <= x && x < len(b[0])
}

func main() {
	findWords([][]byte{}, []string{})
	findWords([][]byte{}, []string{"a"})
	findWords([][]byte{{'a'}}, []string{})
	findWords([][]byte{{'a'}}, []string{"a"})

	log.Printf("------")
	board := [][]byte{{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}}
	dict := []string{"oaaneateihkrvlfi", "akhtaoei", "taaak", "tax", "", "n", "x", "eat"}
	log.Printf("result: %v", findWords(board, dict))

	log.Printf("------ 1 ------")
	board = [][]byte{{'a', 'b'}, {'c', 'd'}}
	dict = []string{"ab", "cb", "ac"}
	log.Printf("result: %v", findWords(board, dict))
}

func assert(real, expect int) {
	if real != expect {
		panic(fmt.Sprintf("real=%d, expect=%d", real, expect))
	}
	fmt.Printf("ok, read=expect=%d\n", real)
}
