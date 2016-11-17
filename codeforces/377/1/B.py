def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

if __name__ == "__main__":
	n, m = read(int)
	a = read(int)
	total = 0
	for i in range(1, n):
		if a[i]+a[i-1] < m:
			d = m-a[i]-a[i-1]
			total += d
			a[i] += d
	print total
	print " ".join([str(x) for x in a])
