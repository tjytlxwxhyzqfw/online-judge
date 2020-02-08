// 955.go

package main

import "fmt"

func main() {
	assertEqual(minDeletionSize([]string{}), 0)
	assertEqual(minDeletionSize([]string{"a"}), 0)
	assertEqual(minDeletionSize([]string{"a", "b", "c", "d"}), 0)
	assertEqual(minDeletionSize([]string{"za", "yb", "xc"}), 1)
	assertEqual(minDeletionSize([]string{"az", "by", "cx"}), 0)
	assertEqual(minDeletionSize([]string{"zazbw", "ybybc", "xcxbc"}), 1)
	assertEqual(minDeletionSize([]string{"zazbw", "ybybc", "xbxbc"}), 2)
}

func assertEqual(real, expected int) {
	hint := fmt.Sprintf("real=%d, expected=%d", real, expected)
	if real != expected {
		panic(hint)
	}
	fmt.Println(hint)
}

// 955 171/39 s=_ m=_
func minDeletionSize(a []string) int {
	if len(a) <= 1 {
		return 0
	}

	b, c := make([]string, len(a)), make([]string, len(a))
	for i := 0; i < len(a[0]); i++ {
		for j := 0; j < len(a); j++ {
			c[j] = b[j] + string(a[j][i])
		}

		good := true
		for j := 1; j < len(a); j++ {
			// this comparison can be optimistic:
			// consider c[j][:i-1] and c[j+1][:i-1], we have c[j][:i-1] <= c[j+1][:i-1]
			// if c[j][:i-1] < c[j+1][:i-1], then c[j][:i] < c[j+1][:i]
			// if c[j][:i-1] == c[j+1][:i-1], we must let letters in current column be c[j][i] <= c[j+1][i]
			// so we use a array to record if c[j][:i-1] < c[j+1][:i-1], lets say s[j].
			// s[j] = true <=> c[j][:i-1] < c[j+1][:i-1]
			// so if s[j] is true then we dont care c[j][i] & c[j+1][i] and s[j] remains true,
			// if s[j] is false:
			// (1) c[j][i] > c[j+1][i] -> delete column i
			// (2) c[j][i] == c[j+1][i] -> keep column  i but s[j] remains false
			// (3) s[ij][i] < c[j+1][i] -> keep column i and set s[j] = true
			// todo: implement the optimistic mentioned above
			if c[j] < c[j-1] {
				good = false
				break
			}
		}
		if !good {
			continue
		}

		for j := 0; j < len(a); j++ {
			b[j] = c[j]
		}
	}
	return len(a[0]) - len(b[0])
}

