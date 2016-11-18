def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

if __name__ == "__main__":
	n = int(read())
	a = read(int)

	for i in range(n-1):
		a[i] += a[i+1]

	print " ".join([str(x) for x in a])
