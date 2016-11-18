def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve(a, b):
	c = [x+y for x, y in zip(a, b)]
	f = [True if x >= 0 else False for x in c]
	if not all(f):
		4 * pow(10, 18)
	m = max(c)
	return sum([m-x for x in c])

if __name__ == "__main__":
	a = read(int)

	r = [0 for i in range(7)]
	r[0] = solve(a, [0, 0, 0])
	r[1] = solve(a, [-1, 0, 0])
	r[2] = solve(a, [0, -1, 0])
	r[3] = solve(a, [0, 0, -1])
	r[4] = solve(a, [-1, -1, 0])
	r[5] = solve(a, [0, -1, -1])
	r[6] = solve(a, [-1, 0, -1])

	print min(r)
