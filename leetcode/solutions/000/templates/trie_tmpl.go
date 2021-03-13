trie_tmpl.go

type TrieNode struct {
	Br  [128]*TrieNode // branches
	End bool
}

func trieInsert(tn *TrieNode, s string) {
	p := tn
	for i := 0; i < len(s); i++ {
		k := int(s[i] - 'a')
		if p.Br[k] == nil {
			p.Br[k] = &TrieNode{}
		}
		p = p.Br[k]
	}
	p.End = true
}

// v1537

// no used
func trieFind(tn *TrieNode, s string) bool {
	p := tn
	for i := 0; i < len(s); i++ {
		k := int(s[i] - 'a')
		if p.Br[k] == nil {
			return false
		}
	}
	return p.End
}
