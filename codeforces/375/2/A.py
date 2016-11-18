def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

if __name__ == "__main__":
	a = read(int)
	a = sorted(a)
	print a[2] - a[0]
