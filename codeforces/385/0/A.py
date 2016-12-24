import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def cleft(s):
	a = [s[-1]]
	a += s[:-1]
	return a

def solve():
	s = list(read())
	n = len(s)

	res = []

	res.append("".join(s))
	for i in range(n):
		s = cleft(s)
		res.append("".join(s))

	#print res
	print len(set(res))
		

if __name__ == "__main__":
	solve()
