def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def compute(n, k):
	return ((n-1)/k)*5 + n

if __name__ == "__main__":
	nps, t = read(int)
	ps = []
	for i in range(nps):
		ps.append(read())
	#print ps
	p = read()

	plen = len(p)
	ps = [len(x) for x in ps]

	nlt = len(filter(lambda x: x < plen, ps))
	nle = len(filter(lambda x: x <= plen, ps))

	print compute(nlt+1, t), compute(nle, t)
