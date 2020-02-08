// 951.go

package main

func main() {
}

type TreeNode struct {
	Val int
	Left *TreeNode
	Right *TreeNode
}

// 951 404/17 s=? m=?
func flipEquiv(root1 *TreeNode, root2 *TreeNode) bool {
	if root1 == nil && root2 == nil {
		return true
	}
	if root1 == nil || root2 == nil {
		return false
	}
	if root1.Val != root2.Val {
		return false
	}
	return flipEquiv(root1.Left, root2.Left) && flipEquiv(root1.Right, root2.Right) ||
		flipEquiv(root1.Left, root2.Right) && flipEquiv(root1.Right, root2.Left)
}
