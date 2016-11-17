def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

if __name__ == "__main__":
	x = read(int)
	x = sorted(x)
	print x[2]-x[0]
