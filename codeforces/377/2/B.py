def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

a, n, k = None, None, None

def solve():
	if n == 1:
		return 0
	ret = 0
	for i in range(1, n):
		delta = k - a[i] - a[i-1]
		if delta > 0:
			ret += delta
			a[i] += delta
	return ret

if __name__ == "__main__":
	n, k = read(int)
	a = read(int)

	x = solve()
	print x
	print " ".join([str(x) for x in a])
