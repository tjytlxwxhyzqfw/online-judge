n, s = 0, []
rs, bs = [], []

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def check(src):
	d = {'r': 0, 'b': 0}
	for i in range(n):
		if s[i] != src[i]:
			d[src[i]] += 1
	return min(d['r'], d['b']) + abs(d['r']-d['b'])

if __name__ == "__main__":
	rs = ['r' if i % 2 == 0 else 'b' for i in range(100001)]
	bs = ['b' if i % 2 == 0 else 'r' for i in range(100001)]

	n = int(read())
	s = list(read())


	x = check(rs)
	y = check(bs)

	#print (x, y)
	print min(x, y)
