package main

import (
	"fmt"
)

func assertEqual(real, expected int) {
	if real != expected {
		panic(fmt.Sprintf("real=%d, expected=%d", real, expected))
	}
}

func main() {
	a := []string{"amazon", "apple", "facebook", "google", "leetcode"}
	fmt.Printf("%v\n", wordSubsets(a, []string{"e", "o"}))
	fmt.Printf("%v\n", wordSubsets(a, []string{"l", "e"}))
	fmt.Printf("%v\n", wordSubsets(a, []string{"e", "oo"}))
	fmt.Printf("%v\n", wordSubsets(a, []string{"lo", "eo"}))
	fmt.Printf("%v\n", wordSubsets(a, []string{"ec", "oc", "ceo"}))
	fmt.Printf("%v\n", wordSubsets(a, []string{"eee", "t"}))
}

// 916 239/57 s=100 m=?
// for each word in b, we count the letter occurrence
// then we take the max number for each letter to builder a filter, say F
// for each word in a, occurrence of each letter must be greater than the value in F
func wordSubsets(a []string, b[]string) []string {
	count := [26]int{}
	for _, s := range b {
		updateCount(s, &count)
	}

	universal := make([]string, 0)
	for _, s := range a {
		c := [26]int{}
		stat(s, &c)
		if contains(c, count) {
			universal = append(universal, s)
		}
	}

	return universal
}

func stat(s string, out *[26]int) {
	for _, b := range s {
		out[int(b)-97]++
	}
}

func contains(large, small [26]int) bool {
	for i := 0; i < 26; i++ {
		if small[i] > large[i] {
			return false
		}
	}
	return true
}

func updateCount(s string, count *[26]int) {
	c := [26]int{}
	stat(s, &c)
	for i := 0; i < 26; i++ {
		if count[i] < c[i] {
			count[i] = c[i]
		}
	}
}

