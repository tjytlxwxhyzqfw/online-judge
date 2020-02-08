// 966.go

func spellchecker(words, queries []string) []string {
 same, lower, vowel := &Node{First: math.MaxInt32}, &Node{First: math.MaxInt32}, &Node{First:math.MaxInt32}
 for i, w := range words {
  insert(i, w, same)
  w = strings.ToLower(w)
  insert(i, w, lower)
  insert(i, eraseVowel(w), vowel)
 }
result := make([]string, 0)
 for _, q := range queries {
  var y string
  if i := find(q, same); i >= 0 {
   y = words[i]
  } else if i := find(strings.ToLower(q), lower); i >= 0 {
   y = words[i]
  } else if i := find(eraseVowel(q), vowel); i >= 0 {
   y = words[i]
  }
  fmt.Printf("%s -> %s\n", q, y)
  result = append(result, q)
 }

 return result
}

func eraseVowel(s string) string {
 vowel := map[string]bool{"a": true, "e": true, "i": true, "o": true, "u": true}
 t := ""
 for _, b := range s {
  if vowel[string(b)] {
   t += "_"
  } else {
   t += strings.ToLower(string(b))
  }
 }
 return t
}

type Node struct {
 Next [128]*Node
 First int
}

func insert(i int, s string, root *Node) {
 current := root
 for _, b := range s {
  // fmt.Printf("inserting: %s, b=%s\n", s, string(b))
  if current.Next[b] == nil {
   current.Next[b] = &Node{First: math.MaxInt32}
  }
   current = current.Next[b]
 }
 if current.First > i {
  current.First = i
 }
}

func find(s string, root *Node) int {
 current := root
 for _, b := range s {
  if current.Next[b] == nil {
   return -1
  }
  current = current.Next[b]
 }
 if current.First == math.MaxInt32 {
  return -1
 }
 return current.First
}