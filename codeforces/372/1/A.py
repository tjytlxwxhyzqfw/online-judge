def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

if __name__ == "__main__":
	n, m = read(int)
	a = read(int)

	i, j, c = 0, m, 0
	for i in a:
		if i > j:
			c = 0
		j = i + m
		c += 1

	print c
