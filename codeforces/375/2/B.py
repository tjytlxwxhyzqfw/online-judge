import re

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve(s):
	a, b = '_', '_'
	out = True
	for x in s:
		if x == '(':
			out = False
			a += '_'
			b += '_'
			continue
		if x == ')':
			out = True
			a += '_'
			b += '_'
			continue
		if out:
			a += x
		else:
			b += x

	#print a
	a = a.split('_')
	a = [len(x) for x in a]
	#print a

	#print b
	b = b.split('_')
	b = filter(lambda x: x, b)
	#print b

	print max(a), len(b)

if __name__ == "__main__":
	n = int(read())
	s = list(read())
	solve(s)
