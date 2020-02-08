// 987.go


type Node struct {
 X, Y, Val int
}

func (n *Node) IsLessThan(m *Node) bool {
 if n.X != m.X {
  return n.X < m.X
 }
 if n.Y != m.Y {
  return n.Y < m.Y
 }
 return n.Val < m.Val
}

// 987 275/678 34% m=? s=?
func verticalTraversal(root *TreeNode) [][]int {
 nodes := make([]*Node, 0)
 traverse(root, 0, 0, &nodes)
 sort.Slice(nodes, func(i, j int) bool {return nodes[i].IsLessThan(nodes[j])})
 result := make([][]int, 0)
 if len(nodes) == 0 {
  return result
 }
 result = append(result, []int{nodes[0].Val})
 for i, p := 1, 0; i < len(nodes); i++ {
  if nodes[i].X != nodes[i-1].X {
   result = append(result, []int{})
   p++
  }
  result[p] = append(result[p], nodes[i].Val)
 }
 return result
}

func traverse(root *TreeNode, x, y int, nodes *[]*Node) {
 if root == nil {
  return
 }
 *nodes = append(*nodes, &Node{X: x, Y: y, Val: root.Val})
 traverse(root.Left, x-1, y+1, nodes)
 traverse(root.Right, x+1, y+1, nodes)
}