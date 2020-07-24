// 124.go.diss

package main

import (
	"fmt"
	"math"
)

// id=124 pass=32 ud=2766/233 s=100 m=?
func maxPathSum(root *TreeNode) int {
	max := math.MinInt32
	maxToLeaf(root, &max)
	return max
}

func maxToLeaf(root *TreeNode, gmax *int) int {
	if root == nil {
		return math.MinInt32
	}

	v := root.Val
	left := maxToLeaf(root.Left, gmax)
	rght := maxToLeaf(root.Right, gmax)
	if v < root.Val + left {
		v = root.Val + left
	}
	if v < root.Val + rght {
		v = root.Val + rght
	}
	// fmt.Printf("v=%d, maxToLeaf=%d, maxBridge=%d\n", root.Val, v, root.Val+left+rght)
	if *gmax < v {
		*gmax = v
	}
	if *gmax < root.Val + left + rght {
		*gmax = root.Val + left + rght
	}
	return v
}

type TreeNode struct {
	Val         int
	Left, Right *TreeNode
}

func main() {
	seven, fifteen := TreeNode{Val: 7}, TreeNode{Val: 15}
	nine, twenty := TreeNode{Val: 9}, TreeNode{Val: 20, Left: &fifteen, Right: &seven}
	minusTen := TreeNode{Val: -10, Left: &nine, Right: &twenty}
	assert(maxPathSum(&minusTen), 42)
}

func assert(real, expected int) {
	if expected != real {
		panic(fmt.Sprintf("expected=%d, real=%d", expected, real))
	}
	fmt.Printf("ok, expected=real=%d\n", real)
}
