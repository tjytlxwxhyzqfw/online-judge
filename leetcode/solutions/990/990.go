// 990.go

package main

import "fmt"

func main() {

	// true
	fmt.Println("--- true ---")
	fmt.Println(equationsPossible([]string{}))
	fmt.Println(equationsPossible([]string{"a==b"}))
	fmt.Println(equationsPossible([]string{"a==b", "b==c"}))

	// false
	fmt.Println("\n--- false ---")
	fmt.Println(equationsPossible([]string{"a==b", "b==c", "c==d", "d!=a"}))
	fmt.Println(equationsPossible([]string{"a==b", "a!=b"}))
	fmt.Println(equationsPossible([]string{"a==b", "a==c", "a==d", "c==b", "b!=d"}))
}

type Node struct {
	Parent *Node
	Size int
}

func Find(x *Node) *Node {
	if x.Parent != x {
		x.Parent = Find(x.Parent)
	}
	return x.Parent
}

func Union(x, y *Node) {
	xr, yr := Find(x), Find(y)
	if xr == yr {
		return
	}
	if xr.Size > yr.Size {
		xr, yr = yr, xr
	}
	xr.Parent = yr
	yr.Size += xr.Size
}

// 990 304/3 41% m=? s=?
func equationsPossible(eqs []string) bool {
	nodes := make([]*Node, 26)
	for _, eq := range eqs {
		left, op, rght := eq[0]-97, eq[1], eq[3]-97
		if nodes[left] == nil {
			nodes[left] = &Node{Size: 1}
			nodes[left].Parent = nodes[left]
		}
		if nodes[rght] == nil {
			nodes[rght] = &Node{Size: 1}
			nodes[rght].Parent = nodes[rght]
		}
		if op == '=' {
			Union(nodes[left], nodes[rght])
		}
	}
	for _, eq := range eqs {
		left, op, rght := eq[0]-97, eq[1], eq[3]-97
		if op == '!' && Find(nodes[left]) == Find(nodes[rght]) {
			return false
		}
	}
	return true
}
