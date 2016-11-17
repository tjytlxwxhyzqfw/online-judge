def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def gor(start):
	while True:
		yield start
		start = ('r' if start == 'b' else 'b')

def sync(a, g):
	d = {'r':0, 'b':0}
	for x in a:
		if x != next(g):
			d[x] += 1
	return min(d['r'], d['b']) + abs(d['r'] - d['b'])

if __name__ == "__main__":
	n = int(read())
	a = list(read())

	x = sync(a, gor('r'))
	y = sync(a, gor('b'))

	print min(x, y)
