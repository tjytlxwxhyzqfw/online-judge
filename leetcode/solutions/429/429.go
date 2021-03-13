package main

import (
	"container/list"
	"log"
)

// id=429 ud=630/46 pass=65 s=100 m=
func levelOrder(root *Node) [][]int {
	result := make([][]int, 0)
	if root == nil {
		return result
	}
	q := list.New()
	q.PushBack(&elem{node: root, depth: 0})
	for q.Len() > 0 {
		curr := q.Remove(q.Front()).(*elem)
		if len(result) == curr.depth {
			result = append(result, make([]int, 0))
		}
		result[curr.depth] = append(result[curr.depth], curr.node.Val)
		for _, c := range curr.node.Children {
			q.PushBack(&elem{node: c, depth: curr.depth + 1})
		}
	}
	return result
}

type elem struct {
	node *Node
	depth int
}

type Node struct {
	Val int
	Children []*Node
}

func main() {
	root := &Node{Val: 1}
	log.Printf("%#v", levelOrder(root))
	root.Children = []*Node{{Val: 3}, {Val: 2}, {Val: 4}}
	log.Printf("%#v", levelOrder(root))
	root.Children[0].Children = []*Node{{Val: 5}, {Val: 6}}
	log.Printf("%#v", levelOrder(root))
}

func assert(b bool) {
	log.Printf("\n---\n")
	if !b {
		panic("assertion error")
	}
}
