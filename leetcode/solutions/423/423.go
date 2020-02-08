package main

import (
	"bytes"
	"fmt"
	"math/rand"
)

func printM(m map[byte]int) {
	for _, b := range []byte("abcdefghijklmnopqrstuvwxyz"){
		fmt.Printf("%c: %d\n", b, m[b])
	}
}

func originalDigits(s string) string {
	// fmt.Printf("s: %v\n", s)
	toEn := [][]byte{
		[]byte("zero"),
		[]byte("one"),
		[]byte("two"),
		[]byte("three"),
		[]byte("four"),
		[]byte("five"),
		[]byte("six"),
		[]byte("seven"),
		[]byte("eight"),
		[]byte("nine")}
	digits := []map[int]byte{
		{
			0: 'z',
			2: 'w',
			4: 'u',
			6: 'x',
			8: 'g',
		},
		{
			1: 'o',
			3: 'r',
			5: 'f',
			7: 's',
		},
		// 9 remains
	}

	m := map[byte]int{}
	for _, c := range []byte(s) {
		m[c]++
	}
	// printM(m)

	buckets := [10]int{}
	for i := 0; i < 2; i++ {
		for d, c := range digits[i] {
			buckets[d] = m[c]
			// fmt.Printf("%d -> %d\n", d, m[c])
			for _, x := range toEn[d] {
				m[x] -= buckets[d]
			}
		}
	}
	buckets[9] = m['i']

	var buf bytes.Buffer
	for i, count := range buckets {
		for count > 0 {
			buf.WriteByte(byte(i+48))
			count--
		}
	}
	return buf.String()
}

func shuffle(s string) string {
	bytes := []byte(s)
	for i := len(bytes)-1; i > 0; i-- {
		j := rand.Intn(i+1)
		bytes[i], bytes[j] = bytes[j], bytes[i]
	}
	return string(bytes)
}

func main() {
	fmt.Printf("%v\n", originalDigits(shuffle("")))
	fmt.Printf("%v\n", originalDigits(shuffle("three")))
	fmt.Printf("%v\n", originalDigits(shuffle("nine")))
	fmt.Printf("%v\n", originalDigits(shuffle("fourfiveeightfivefourninezerothreezero")))
	fmt.Printf("%v\n", originalDigits(shuffle("zeroonetwothreefourfivesixseveneightnine")))
}
