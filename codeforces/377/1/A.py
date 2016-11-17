def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

if __name__ == "__main__":
	a, b = read(int)
	for i in range(1, 11):
		mod = a*i % 10
		if mod == 0 or mod == b:
			break
	print i
