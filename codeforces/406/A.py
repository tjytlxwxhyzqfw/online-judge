import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	a, b = read(int)
	c, d = read(int)

	for i in xrange(100000):
		time = b + a*i
		left = time - d
		if left % c == 0:
			print time
			return

	print -1

if __name__ == "__main__":
	solve()
