// 99.go

package main

import "fmt"

// id=99 ud=1251/66 pass=37 s=20 m=?
//
// intuition 1: tree -> recursive -> blocked
//        -> problem: you cannot fix the order by a sub-tree x, because the
//           final answer might be the swap of x and his parent
// intuition 2: brute force -> swap every two nodes and check the whole tree
//       -> opt 1: you only need to swap a node with its max(left) or min(right)
//
// we search the tree from top down and we first encountered a miss-order at root and his children,
// let it be: `root`, `left`, `right`, and `parent` is `root`'s parent.
// if left > rght -> swap them
// if root < left then swap
//     (1) if root is left child of its parent (root < parent)
//         then we cannot swap root & parent because we will get root (now parent) < parent (now left)
//     (2) if root is right child of its parent (root > parent)
//         then parent(now as root) is still less than is ... -> you must prove that root cannot be
//         swapped with other non-children nodes and this is crazy !!!
//
// --- end bullshit ---
//
// (discuss) something that i didn't know:
// (1) if you traverse a bst by in-order then you get an order sequence
//     and this knowledge provides an O(n) solution
// (2) Morris Traversal, some fancy algorithm (which i don't like)
//     that provides an O(1) extra space solution
//

func recoverTree(root *TreeNode) {
	arr := make([]*TreeNode, 0)
	inOrder(root, &arr)
	var x, y *TreeNode
	var rawX, rawXAt int
	for i := 1; i < len(arr); i++ {
		// fmt.Printf("current: %v\n", arr[i].Val)
		if arr[i].Val < arr[i-1].Val {
			if x == nil {
				x = arr[i-1]
				rawX, rawXAt = arr[i-1].Val, i-1
				arr[i-1].Val = arr[i].Val
			} else {
				y = arr[i]
			}
		}
	}
	arr[rawXAt].Val = rawX
	if y == nil {
		y = arr[rawXAt+1]
	}
	// fmt.Printf("swap: %d <-> %d\n", x.Val, y.Val)
	x.Val, y.Val = y.Val, x.Val
}

func inOrder(root *TreeNode, arr *[]*TreeNode) {
	if root == nil {
		return
	}
	inOrder(root.Left, arr)
	*arr = append(*arr, root)
	inOrder(root.Right, arr)
}

func main() {
	grandson := TreeNode{Val: 2}
	left := TreeNode{Val: 1}
	right := TreeNode{Val: 4, Left: &grandson}
	root := TreeNode{Val: 3, Left: &left, Right: &right}
	recoverTree(&root)

	fmt.Println("--------")

	right = TreeNode{Val: 2}
	left = TreeNode{Val: 3, Right: &right}
	root = TreeNode{Val: 1, Left: &left}
	recoverTree(&root)
}

type TreeNode struct {
	Val int
	Left, Right *TreeNode
}
