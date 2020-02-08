package main

import (
	"fmt"
)

type TreeNode struct {
	Val int
	Left *TreeNode
	Right *TreeNode
}

func (n *TreeNode) Print() {
	if n == nil {
		fmt.Printf("(nil)\n")
	} else {
		fmt.Printf("%d: ", n.Val)
		if n.Left == nil {
			fmt.Printf("left=(nil) ")
		} else {
			fmt.Printf("left=%d ", n.Left.Val)
		}
		if n.Right == nil {
			fmt.Printf("right=(nil)\n")
		} else {
			fmt.Printf("right=%d\n", n.Right.Val)
		}
	}
}

func (n *TreeNode) ReplaceC(old *TreeNode, new *TreeNode) {
	if n.Left == old {
		n.Left = new
	} else if n.Right == old {
		n.Right = new
	}
}

func deleteNode(root *TreeNode, key int) *TreeNode {
	p, c := (*TreeNode)(nil), root
	for c != nil && c.Val != key {
		p = c
		if c.Val > key {
			c = c.Left
		} else {
			c = c.Right
		}
	}

	// key not found
	if c == nil {
		return root
	}

	// key not own a left child
	if c.Left == nil {
		if p == nil {
			return c.Right
		}
		p.ReplaceC(c, c.Right)
		return root
	}

	// replace c.Val with max child in c.Left
	q, d := c, c.Left
	for d.Right != nil {
		q, d = d, d.Right
	}
	c.Val = d.Val
	q.ReplaceC(d, d.Left)
	return root
}

func main() {
	root := &TreeNode{47, nil, nil}
	deleteNode(root, 7).Print()
	deleteNode(root, 47).Print()

	root = &TreeNode{
		Val: 47,
		Left: &TreeNode{13, nil, nil},
		Right: &TreeNode{59, nil, nil},
	}
	deleteNode(root, 47).Print()

	root = &TreeNode{
		Val: 47,
		Left: &TreeNode{13, nil, nil},
		Right: &TreeNode{59, nil, nil},
	}
	deleteNode(root, 13).Print()

	root = &TreeNode{
		Val: 47,
		Left: &TreeNode{13, nil, nil},
		Right: &TreeNode{59, nil, nil},
	}
	deleteNode(root, 59).Print()

	root = &TreeNode{
		Val: 47,
		Left: &TreeNode{13, nil, nil},
		Right: &TreeNode{59, nil, nil},
	}
	deleteNode(root, 11).Print()
	deleteNode(root, 61).Print()
}