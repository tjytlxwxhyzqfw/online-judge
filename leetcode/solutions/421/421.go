// p: there is tow nums x, y in a that prefix[31, 30, ..., i] of x ^ y is p.
// assertion: p is the max prefix that two nums in a can form.
// induction: we extend p to the next(lower) bit: (so p = [p1] or p = [p0]):
//	(1) put all prefix of x in a into set s
//  (2) if [p1] ^ x = y && y in s then we known that there is two nums x, y in a that prefix
//	    of x ^ y is p1. (a^b=c => a^c=b). if so, we set p = [p1] else p = [p0].

// i cannot come up with this solution. :(
// and it takes me a lot of time to understand it.

// todo: we dont need mask !!!
func findMaximumXOR(a []int) int {
	p, mask := 0, 0
	for i := 30; i >= 0; i-- {
		bit := 1 << uint(i)
		mask |= bit
		s := map[int]bool{}
		for _, x := range a {
			s[x&mask] = true
		}

		p |= bit
		keep := false
		for k := range s {
			if s[p^k] {
				keep = true
			}
		}
		if !keep {
			p &= ^bit
		}
	}
	return p
}