def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

if __name__ == "__main__":
	n = int(read())
	print 2
	for i in range(2, n+1):
		print i * pow(i+1, 2) - i + 1
