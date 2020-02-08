// 919.go

package main

import "fmt"

// 919 203/44 s=89 m=?
// todo: this solution can be improved from O(lgN) to O(1)
func main() {
	inserter := Constructor(&TreeNode{Val: 1})
	assertEqual(inserter.Insert(2), 1)
	assertEqual(inserter.Insert(3), 1)
	assertEqual(inserter.Insert(4), 2)
	assertEqual(inserter.Insert(5), 2)
	assertEqual(inserter.Insert(6), 3)
	assertEqual(inserter.Insert(7), 3)
	assertEqual(inserter.Insert(8), 4)
	assertEqual(inserter.Insert(9), 4)
	assertEqual(inserter.Insert(10), 5)
}

func assertEqual(real, expected int) {
	hint := fmt.Sprintf("real=%d, expected=%d", real, expected)
	if real != expected {
		panic(hint)
	}
	fmt.Println(hint)
}

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

type CBTInserter struct {
	Root  *TreeNode
	LLMax int // last layer max num of nodes
	LLCur int // last layer current num of nodes
}

func Constructor(root *TreeNode) CBTInserter {
	total := count(root)
	i := 1
	for ; total >= i-1; i *= 2 {
	}
	return CBTInserter{Root: root, LLMax: i / 2, LLCur: total - (i/2-1)}
}

// this initial given tree always contains >= 1 nodes
func (this *CBTInserter) Insert(v int) int {
	// fmt.Printf("llMax=%d, llCur=%d\n", this.LLMax, this.LLCur)
	parent := insert(this.Root, v, this.LLCur, this.LLMax)
	this.LLCur++
	if this.LLCur == this.LLMax {
		this.LLMax *= 2
		this.LLCur = 0
	}
	return parent
}

func (this CBTInserter) Get_root() *TreeNode {
	return this.Root
}

func count(root *TreeNode) int {
	if root == nil {
		return 0
	}
	return 1 + count(root.Left) + count(root.Right)
}

func insert(root *TreeNode, val int, llCur, llMax int) int {
	if llMax == 2 {
		node := &TreeNode{Val: val}
		if root.Left == nil {
			root.Left = node
		} else {
			root.Right = node
		}
		return root.Val
	}
	if llCur < llMax/2 {
		return insert(root.Left, val, llCur, llMax/2)
	} else {
		return insert(root.Right, val, llCur-llMax/2, llMax/2)
	}
}
