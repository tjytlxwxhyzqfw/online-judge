915.go

// 915 256/20
// idea1: bs => wa, pass
// idea2: 2p => nlgn
// idea3(diss):
//   we set left = [0, i]
//   but we found j > i that a[j] < max(0, i)
//   we set left = [0, j]
//   repeat ...
func partitionDisjoint(a []int) int {
	count := map[int]int{}
	for _, x := range a {
		count[x]++
	}
	keys := make([]int, 0)
	for k, _ := range count {
		keys = append(keys, k)
	}
	sort.Ints(keys)

	k, max := 0, a[0]
	p := decrease(a[0], 0, keys, count)
	for max > keys[p] {
		k++
		if a[k] > max {
			max = a[k]
		}
		p = decrease(a[k], p, keys, count)
	}

	return k + 1
}

func decrease(v, p int, keys []int, count map[int]int) int {
	count[v]-- // we are ok with this in golang
	for count[keys[p]] == 0 {
		p++
	}
	return p
}