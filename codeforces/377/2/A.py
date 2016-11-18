def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve(k, r):
	for i in range(1, 11):
		for j in range(0, 10001):
			if j * 10 == i * k:
				return i
			if j * 10 + r == i * k:
				return i
	assert False

if __name__ == "__main__":
	k, r = read(int)
	ans = solve(k, r)
	print ans
