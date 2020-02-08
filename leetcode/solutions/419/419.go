package main

import (
	"fmt"
)

func countBattleships(board [][]byte) int {
	if len(board) == 0 || len(board[0]) == 0 {
		return 0
	}
	h, w, n := len(board), len(board[0]), 0
	for y := 0; y < h; y++ {
		for x := 0; x < w; x++ {
			if board[y][x] == 'X' {
				n++
				// fmt.Printf("y=%d, x=%d\n", y, x)
				if y-1 >= 0 && board[y-1][x] == 'X' {
					// fmt.Printf("y=%d, x=%d (adj: y-1)\n", y, x)
					n--
				}
				if x-1 >= 0 && board[y][x-1] == 'X' {
					// fmt.Printf("y=%d, x=%d (adj: x-1)\n", y, x)
					n--
				}
			}
		}
	}
	return n
}

func main() {
	b1 := [][]byte{{}}
	fmt.Printf("n1=%d\n", countBattleships(b1))

	b2 := [][]byte{{'X'}}
	fmt.Printf("n2=%d\n", countBattleships(b2))

	b3 := [][]byte{{'.'}}
	fmt.Printf("n3=%d\n", countBattleships(b3))

	b4 := [][]byte{{'X', '.', '.', 'X'}, {'.', '.', '.', 'X'}, {'.', '.', '.', 'X'}}
	fmt.Printf("n4=%d\n", countBattleships(b4))

	b5 := [][]byte{{'X', '.', '.', 'X'}}
	fmt.Printf("n5=%d\n", countBattleships(b5))

	b6 := [][]byte{{'X'}, {'X'}, {'.'}, {'X'}}
	fmt.Printf("n6=%d\n", countBattleships(b6))

	b7 := [][]byte{{'X'}, {'X'}, {'X'}, {'X'}}
	fmt.Printf("n7=%d\n", countBattleships(b7))
}
