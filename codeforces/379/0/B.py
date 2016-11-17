def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	k2, k3, k5, k6 = read(int)
	n256 = min(k2, k5, k6)
	k2 -= n256
	n32 = min(k2, k3)

	print 256*n256 + 32*n32

if __name__ == "__main__":
	solve()
