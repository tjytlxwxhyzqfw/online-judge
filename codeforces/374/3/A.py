def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve(s):
	s = s.split('W')
	s = filter(lambda x: x, s)
	s = [len(x) for x in s]
	print len(s)
	print " ".join([str(x) for x in s])

if __name__ == "__main__":
	n = int(read())
	s = read()
	solve(s)
