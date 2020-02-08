package main

import (
	"bytes"
	"fmt"
	"sort"
)

type FreqItem struct {
	byt  byte
	freq int
}

type FreqItemSlice []FreqItem

func (s FreqItemSlice) Len() int           { return len(s) }
func (s FreqItemSlice) Less(i, j int) bool { return s[j].freq < s[i].freq }
func (s FreqItemSlice) Swap(i, j int)      { s[i], s[j] = s[j], s[i] }

// M 57% 97.6% ???%
//
func frequencySort(s string) string {
	a := make([]FreqItem, 128)
	for i := range a {
		a[i].byt = (byte)(i)
	}
	for _, c := range s {
		a[c].freq++
	}
	sort.Sort(FreqItemSlice(a))

	var buf bytes.Buffer
	for _, x := range a {
		for x.freq > 0 {
			buf.WriteByte(x.byt)
			x.freq--
		}
	}
	return buf.String()
}

func main() {
	fmt.Println(frequencySort(""))
	fmt.Println(frequencySort("aaa"))
	fmt.Println(frequencySort("aba"))
	fmt.Println(frequencySort("tree"))
	fmt.Println(frequencySort("458549030"))
}