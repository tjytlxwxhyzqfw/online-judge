// 998.go

package main

import "fmt"

func main() {
	root := &TreeNode{Val: 1}
	root = insertIntoMaxTree(root, 2)
	fmt.Printf("root.val=%d\n", root.Val)

	root = &TreeNode{Val: 5}
	root.Right = &TreeNode{Val: 3}
	root = insertIntoMaxTree(root, 4)
	fmt.Printf("root.right.val=%d\n", root.Right.Val)
}

type TreeNode struct {
	Val int
	Left, Right *TreeNode
}

// 998 123/291 61%
func insertIntoMaxTree(root *TreeNode, val int) *TreeNode {
	if root == nil {
		return &TreeNode{Val: val}
	}
	if val > root.Val {
		return &TreeNode{Val: val, Left: root}
	}
	root.Right = insertIntoMaxTree(root.Right, val)
	return root
}
